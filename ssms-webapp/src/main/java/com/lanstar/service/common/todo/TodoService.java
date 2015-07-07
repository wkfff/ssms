/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoService.java
 * 创建时间：2015-06-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.common.todo;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lanstar.common.Asserts;
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.Tenant;
import com.lanstar.model.Done;
import com.lanstar.model.Todo;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.DbKit;
import com.lanstar.plugin.activerecord.IAtom;
import com.lanstar.plugin.activerecord.TableMapping;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * 待办服务，提供待办的创建、完成等功能。
 */
public class TodoService {
    private final Tenant tenant;

    public TodoService( Tenant tenant ) {
        Asserts.notNull( tenant, "租户上下文不能为空" );
        this.tenant = tenant;
    }

    public static TodoService with( Tenant tenant ) {
        return new TodoService( tenant );
    }

    /**
     * 根据待办bean创建一个待办任务。
     *
     * @param bean     待办bean
     * @param operator 操作人员
     *
     * @return 如果创建成功则返回true，否则返回false。
     */
    public boolean create( TodoBean bean, Identity operator ) {
        Asserts.notNull( bean, "bean can not be null" );
        // 如果待办已经存在，说明这个是异常逻辑产生的待办，应该要提醒报错。
        if ( exists( bean.getSignature(), bean.getSrcId(), bean.getProfessionId(), bean.getTemplateId() ) == true )
            throw new RuntimeException( "待办已经存在，请确认待办生成逻辑是否正确！" );

        Todo todo = new Todo();
        TodoBean.inject( bean, todo );
        todo.setTenant( tenant );
        ModelInjector.injectOpreator( todo, operator, true );
        return todo.save();
    }

    /**
     * 完成bean所描述的待办任务
     *
     * @param bean     要完成的bean
     * @param operator 操作人员
     *
     * @return 如果成功完成则返回true，否则则返回false。
     */
    public boolean finish( final TodoBean bean, final Identity operator ) {
        Asserts.notNull( bean, "bean can not be null" );
        Todo todo;
        if ( bean.getId() != null ) todo = Todo.dao.findById( bean.getId() );
        else todo = getTodo( bean.getSignature(), bean.getSrcId(), bean.getProfessionId(), bean.getTemplateId() );
        return finishTodo( todo, operator );
    }

    /**
     * 根据待办id完成待办任务
     *
     * @param todoId   要完成的待办id
     * @param operator 操作人员
     *
     * @return 如果成功则返回true，否则返回false。
     */
    public boolean finish( int todoId, final Identity operator ) {
        return finishTodo( Todo.dao.findById( todoId ), operator );
    }

    /**
     * 完成根据特征码和记录行id识别的待办任务
     *
     * @param signature 待办特征码
     * @param srcId     待办所对应的记录行id
     * @param operator  操作人员
     *
     * @return 如果成功则返回true，否则返回false。
     */
    public boolean finish( String signature, int srcId, Integer profession, Integer template, final Identity operator ) {
        return finishTodo( getTodo( signature, srcId, profession, template ), operator );
    }

    /**
     * 根据待办ID或者待办
     *
     * @param todoId 待办id
     *
     * @return 待办实例
     */
    public TodoBean getTodo( int todoId ) {
        Todo todo = Todo.dao.findById( todoId );
        return TodoBean.convert( todo );
    }

    /**
     * 根据特征码和记录行id或者待办
     *
     * @param signature 特征码
     * @param srcId     记录行id
     *
     * @return 待办
     */
    public TodoBean getTodoBean( String signature, int srcId, Integer profession, Integer template ) {
        return TodoBean.convert( getTodo( signature, srcId, profession, template ) );
    }

    /**
     * 列举出与给定特征码一致的所有待办
     *
     * @param signature 特征码
     *
     * @return 待办列表
     */
    public List<TodoBean> listTodoBean( String signature ) {
        return listTodoBean( signature, 0 );
    }

    /**
     * 列举出给定大小的的特征码待办
     *
     * @param signature 特征码
     * @param size      数据大小
     *
     * @return 待办列表
     */
    public List<TodoBean> listTodoBean( String signature, int size ) {
        SqlBuilder builder = SQL.SELECT( "*" )
                                .FROM( TableMapping.me().getTable( Todo.class ).getName() )
                                .WHERE( "C_CONTROL=? AND R_TENANT=? AND P_TENANT=?",
                                        signature, tenant.getTenantId(), tenant.getTenantType().getName() )
                                .ORDER_BY( "T_CREATE DESC" )
                                .LIMIT()._If( size > 0, size );
        SqlStatement statement = builder.toSqlStatement();
        List<Todo> todos = Todo.dao.find( statement.getSql(), statement.getParams() );
        return Lists.newArrayList( Lists.transform( todos, new Function<Todo, TodoBean>() {
            @Override
            public TodoBean apply( Todo input ) {
                return TodoBean.convert( input );
            }
        } ) );
    }

    /**
     * 根据特征码和记录行id或者待办
     *
     * @param signature 特征码
     * @param srcId     记录行id
     *
     * @return 待办
     */
    Todo getTodo( String signature, int srcId, Integer profession, Integer template ) {
        List<String> columns = Lists.newArrayList( "C_CONTROL", "R_SID", "R_TENANT", "P_TENANT" );
        List<Object> objects = Lists.newArrayList( (Object) signature, srcId,
                tenant.getTenantId(), tenant.getTenantType().getName() );

        if ( profession != null ) {
            columns.add( "P_PROFESSION" );
            objects.add( profession );
        }
        if ( template != null ) {
            columns.add( "R_TEMPLATE" );
            objects.add( template );
        }
        return Todo.dao.findFirstByColumns( columns, objects );
    }

    /**
     * 完成待办
     *
     * @param bean     要完成的待办
     * @param operator 操作人员
     *
     * @return 如果成功完成则返回true，否则返回false。
     */
    boolean finishTodo( final Todo bean, final Identity operator ) {
        return Db.use( DbKit.getConfig( Todo.class ).getName() ).tx( new IAtom() {
            @Override
            public boolean run() throws SQLException {
                Done done = new Done();
                done.setUrl( bean.getUrl() );
                done.setTitle( bean.getTitle() );
                done.setEndTime( bean.getEndTime() );
                done.setBeginTime( bean.getBeginTime() );
                done.setNotifyTime( bean.getNotifyTime() );
                done.setSignature( bean.getSignature() );
                done.setSrcId( bean.getSrcId() );
                ModelInjector.injectOpreator( done, operator, true );
                done.setTenant( tenant );
                done.save();
                bean.delete();
                return true;
            }
        } );
    }

    /**
     * 判断待办是否存在
     *
     * @param signature 待办特征码
     * @param srcId     记录行id
     *
     * @return 如果存在则返回true，否则返回false。
     */
    public boolean exists( String signature, int srcId, Integer profession, Integer template ) {
        return getTodo( signature, srcId, profession, template ) != null;
    }

    /**
     * 取消待办
     *
     * @param signature 待办特征码
     * @param srcId     记录行id
     *
     * @return 如果取消成功则返回true，否则返回false。
     */
    public boolean cancelTodo( String signature, int srcId ) {
        List<String> columns = Lists.newArrayList( "C_CONTROL", "R_SID", "R_TENANT", "P_TENANT" );
        List<Object> objects = Lists.newArrayList( (Object) signature, srcId,
                tenant.getTenantId(), tenant.getTenantType().getName() );
        return Todo.dao.deleteByColumns( columns, objects ) > 0;
    }
}

