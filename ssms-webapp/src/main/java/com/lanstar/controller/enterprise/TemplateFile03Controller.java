/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile03Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.common.Asserts;
import com.lanstar.common.ListKit;
import com.lanstar.identity.TenantType;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile03;
import com.lanstar.model.tenant.TemplateText;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.render.aspose.AsposeRender;
import com.lanstar.render.aspose.OutputFormat;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile03Controller extends TemplateFileController<TemplateFile03> {
    @Override
    public void index() {
        super.index();
        Integer templatefileId = getParaToInt();
        Asserts.notNull( templatefileId, "发现非法的参数请求" );
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        TemplateFile03 templateFile = templateModel(uniqueTag,templatefileId);
        if(templateFile==null) return;
        setAttrs( ModelKit.toMap( templateFile ) );
        TemplateFile file = TemplateFile.findFirst( uniqueTag, templatefileId );
        String content=TemplateText.getContent( uniqueTag, file.getTemplateFileCode(), templateFile.getId() );
        setAttr( "C_CONTENT", content );
        setAttr( "file", file );
    }
    @Override
    protected void afterSave( TemplateFile03 model ) {
        String content = getPara( "htmlContent" );
        model.setContentText( content );
    }
    protected TemplateFile03 templateModel(UniqueTag uniqueTag,Integer templateFileId) {
        TemplateFile03 model = getDao().findFirstByColumns(
            ListKit.newArrayList( "R_TENANT", "P_TENANT", "R_TEMPLATE", "P_PROFESSION", "R_TMPFILE" ),
            ListKit.newObjectArrayList(
                uniqueTag.getTenantId(),
                TenantType.ENTERPRISE.getName(),
                uniqueTag.getTemplateId(),
                uniqueTag.getProfessionId(),
                templateFileId ) );
        if ( model == null ) return null;
        return model;
    }
    public void export() {
        Integer sid = getParaToInt();
        Asserts.notNull( sid, "非法的参数请求");
        TemplateFile03 fileItem = TemplateFile03.dao.findById( sid );
        String content = fileItem.getContentText();
        render( AsposeRender.me( content, fileItem.getName(), OutputFormat.DOC ) );
    }
    @Override
    protected TemplateFile03 getDao() {
        return TemplateFile03.dao;
    }
    
    public void view(){
        super.rec();
    }
}
