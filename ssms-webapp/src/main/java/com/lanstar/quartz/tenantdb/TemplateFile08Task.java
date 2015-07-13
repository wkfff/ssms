/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile08Task.java
 * 创建时间：2015年7月7日 上午10:52:55
 * 创建用户：林峰
 */
package com.lanstar.quartz.tenantdb;

import java.util.List;

import javax.sql.DataSource;

import com.lanstar.identity.Tenant;
import com.lanstar.model.tenant.TemplateFile08;
import com.lanstar.service.common.todo.TodoBean;
import com.lanstar.service.common.todo.TodoService;
import com.lanstar.service.common.todo.TodoType;

/**
 * 特种设备
 *
 */
public class TemplateFile08Task implements Task {
    @Override
    public void execute( DataSource dataSource ) {
        List<TemplateFile08> all = TemplateFile08.dao.find("select * from SSM_STDTMP_FILE_08 where datediff(t_test_next,now())=30");

        for ( TemplateFile08 file : all ) {
            Tenant tenant = file.getTenant();
            int professionId = file.getProfessionId();
            int templateFileId = file.getTemplateFileId();
            TodoBean bean = new TodoBean();
            bean.setTemplateId( templateFileId );
            bean.setProfessionId( professionId );
            bean.setSrcId( file.getId() );
//            bean.setUrl( "" );
            bean.setTitle( "<<" + file.getName() + ">>临近下次检验("+file.getDate( "T_TEST_NEXT" )+")" );
            // 生成待办
            TodoType.STDFILE08.createTodo( TodoService.with( tenant ), bean, TodoUser.INST );
        }
    }

}