/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：para_multiController.java
 * 创建时间：下午3:20:24
 * 创建用户：苏志亮
 */
package com.lanstar.controller.sys;

import com.google.common.base.Strings;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.BaseController;
import com.lanstar.controller.TableProcessor;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;

/**
 * @author Administrator
 *
 */
public class para_multiController extends BaseController {
    /**
     * @param tablename
     */
    public para_multiController() {
        super( "sys_para_multi" );
        // TODO Auto-generated constructor stub
    }

    /**
     * 表单数据
     */
    public ViewAndModel rec( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        String refer="";
        refer = context.getValue( "refer" );
        if (refer!=null&&refer!="") {
            String c_name = context.DB.withTable( this.TABLENAME )
                                      .where( "SID=?", sid ).query()
                                      .getString( "C_NAME" );
            context.setValue("C_NAME", c_name );
            sid=null;
            return context.returnWith()
                          .set(
                                context.DB.withTable( this.TABLENAME ).where( "SID=?", sid ).query()
                                         );
        }else {
            return context.returnWith()
                          .set(
                                context.DB.withTable( this.TABLENAME )
                                          .where( "SID=?", sid )
                                          .query() );
        }

    }

    /**
     * 主页面
     */
    public ViewAndModel index( HandlerContext context ) {
        return context.returnWith();
    }

    /**
     * 列表数据
     */
    public ViewAndModel list( HandlerContext context ) {
        return this.list( context, null );
    }

    protected ViewAndModel list( HandlerContext context,
            TableProcessor processor ) {
        String sql = "select * from sys_para_multi  group by C_NAME";
        String name = (String) context.getParameterMap().get( "C_NAME" );
        if ( name != null && name != "" ) {
            sql = "select * from sys_para_multi  group by C_NAME "
                    + "having C_NAME=" + "'" + name + "'";
        }
        JdbcRecordSet list = context.DB.getDBSession().query( sql, null );
        return context.returnWith()
                      .set(
                            list );
    }

    /**
     * 列表数据
     */
    public ViewAndModel listV( HandlerContext context ) {
        return this.listV( context, null );
    }

    protected ViewAndModel listV( HandlerContext context,
            TableProcessor processor ) {

        String sql = "select * from sys_para_multi  where C_NAME=(select C_NAME from sys_para_multi where SID="
                + context.getValue( "sid" ) + ")";
        System.out.println( sql );
        JdbcRecordSet list = context.DB.getDBSession().query( sql, null );
        return context.returnWith()
                      .set(
                            list );
    }

    /**
     * 表单.保存
     */
    public ViewAndModel save( HandlerContext context ) {
        // 先验证下参数
        this.validatePara( context );
        String sid = context.getValue( "sid" );
        // 解决如果传递的SID是大写的时候，搜索不到的问题 by 张铮彬#2015-4-25
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        ARTable table = context.DB.withTable( this.TABLENAME );
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        // 根据sid的存在设置where语句
        table.where( StringHelper.vaildValue( sid ) && !sid.equals( "null" ),
                     "SID=?", sid ).save();

        if ( StringHelper.isBlank( sid ) || sid.equals( "null" ) ) {
            sid = Integer.toString( context.DB.getSID() );
        }

        return context.returnWith().put( "SID", sid );
    }

    /**
     * 表单.删除
     *
     * @param context
     *
     * @return
     */
    public ViewAndModel del( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        // 解决如果传递的SID是大写的时候，搜索不到的问题 by 张铮彬#2015-4-25
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        if ( !Strings.isNullOrEmpty( sid ) ) {
            context.DB.withTable( this.TABLENAME ).where( "SID = ?", sid )
                      .delete();
        }
        return context.returnWith().set( "" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setFilterFields() {
        // TODO Auto-generated method stub

    }

}
