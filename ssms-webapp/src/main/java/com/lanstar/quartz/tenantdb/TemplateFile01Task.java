/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.quartz.tenantdb;

import com.lanstar.identity.Tenant;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.service.common.todo.TodoBean;
import com.lanstar.service.common.todo.TodoService;
import com.lanstar.service.common.todo.TodoType;

import javax.sql.DataSource;
import java.util.List;

public class TemplateFile01Task implements Task {
    @Override
    public void execute( DataSource dataSource ) {
        // TODO: 根据规则通过编写SQL等方式筛选出需要生成待办的数据，然后创建待办
        List<TemplateFile01> all = TemplateFile01.dao.findAll();

        for ( TemplateFile01 file : all ) {
            Tenant tenant = file.getTenant();
            int professionId = file.getProfessionId();
            int templateFileId = file.getTemplateFileId();
            TodoBean bean = new TodoBean();
            bean.setTemplateId( templateFileId );
            bean.setProfessionId( professionId );
            bean.setSrcId( file.getId() );
            bean.setUrl( "..." );
            bean.setTitle( "<<" + file.getName() + ">>将于XXXX年XX月XX日到期" );
            // 生成待办
            TodoType.STDFILE01.createTodo( TodoService.with( tenant ), bean, TodoUser.INST );
        }
    }

}
