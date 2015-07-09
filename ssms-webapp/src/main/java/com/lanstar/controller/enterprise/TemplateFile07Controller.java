/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile07Controller.java
 * 创建时间：2015年6月8日 上午10:53:07
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

import com.lanstar.app.Const;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile07;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile07Controller extends TemplateFileController<TemplateFile07> {
    @Override
    public void rec() {
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        super.rec();
        Integer pid = getAttrForInt( Const.TEMPLATE_FILE_PARENT_FIELD );
        boolean isNew = pid == null;
        if ( isNew ) pid = getParaToInt( "fileId" );
        TemplateFile file = TemplateFile.findFirst( uniqueTag, pid );
        if ( isNew ) {
            ArchiveModel<?> archiveModel = file.getTemplateModel();
            if(archiveModel!=null){
                setAttrs( ModelKit.toMap( archiveModel ) );
                removeAttr( "SID" );
            }
        }
        setAttr( "file", file );
    }
    
    public void rec_todo(){
        this.setAttr( "todo", "1" );
        this.rec();
        render( "rec.ftl" );
    }
    
    @Override
    protected TemplateFile07 getDao() {
        return TemplateFile07.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return super.buildWhere().WHERE( "R_TMPFILE=?", getPara( "fileId" ) ).ORDER_BY( "T_CERT_REVIEW DESC" );
    }

    public void view() {
        setAttr( "fileid", getParaToInt( "sid" ) );
    }

    public void detail() {
        this.rec();
    }
}
