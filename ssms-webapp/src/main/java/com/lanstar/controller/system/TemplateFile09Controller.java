/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile09Controller.java
 * 创建时间：2015年6月8日 上午10:53:36
 * 创建用户：林峰
 */
package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.controller.system.attachtext.AttachTokenGenerator;
import com.lanstar.core.aop.Before;
import com.lanstar.model.system.TemplateFile09;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;

public class TemplateFile09Controller extends SimplateController<TemplateFile09> {
    @Before(AttachTokenGenerator.class)
    public void view() {
        com.lanstar.model.system.archive.TemplateFile file = getAttr( "file" );
        ArchiveModel<?> model = file.getTemplateModel();
        setAttr( "title", model.getName() );
        setAttrs( ModelKit.toMap( model ) );
        render( "view.ftl" );
    }
    @Override
    protected TemplateFile09 getDao() {
        return TemplateFile09.dao;
    }
    
    @Override
    protected SqlBuilder buildWhere() {
        SqlBuilder sb = new SqlBuilder();
        sb.WHERE(" R_TMPFILE=?",this.getPara( "R_TMPFILE" ) );
        return sb;
    }
}