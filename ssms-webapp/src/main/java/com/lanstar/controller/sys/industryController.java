/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：industryController.java
 * 创建时间：2015-05-11
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import java.util.ArrayList;
import java.util.List;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.service.parameter.Parameter;

public class industryController extends DefaultController {
    public industryController( ) {
        super( "SYS_INDUSTRY" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return null;
    }
    /**
     * 表单数据
     */
    @Override
    public ViewAndModel rec( HandlerContext context ) {
        List<Parameter> Tlist = new ArrayList<>();
        JdbcRecordSet Trecords = context.SYSTEM_DB.withTable( "SYS_TEMPLATE" ).queryList();
        for(JdbcRecord Trecord:Trecords){
            String templateName=Trecord.getString( "C_NAME" );
            String sid=Trecord.getString( "SID" );
            Parameter parameter=new Parameter( sid, templateName );
            Tlist.add(parameter);
        }
        context.setValue( "template", Tlist );
        return super.rec( context );
    }
}
