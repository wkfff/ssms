/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import java.util.Calendar;
import java.util.Date;

import com.lanstar.common.Asserts;
import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.model.tenant.TemplateFile01Item;
import com.lanstar.model.tenant.TemplateText;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile01Controller extends TemplateFileController<TemplateFile01> {
    
    public void index() {
        Integer templatefileId = getParaToInt();
        Asserts.notNull( templatefileId, "发现非法的参数请求" );
        TemplateFile01 templateFile = getDao().findFirstByColumn( "R_TMPFILE", templatefileId );
        Integer sid=templateFile.get( "SID" );
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        super.rec();
        TemplateFile file = TemplateFile.findFirst( uniqueTag, sid );
        if(file==null){
            file = TemplateFile.findFirst( uniqueTag, templatefileId );
        }else{
            String content=TemplateText.getContent( uniqueTag, file.getTemplateFileCode(), sid );
            setAttr( "C_CONTENT", content );
        }
        setAttr( "file", file );
        String sql = "select * from SSM_STDTMP_FILE_01_ITEM where R_TMPFILE_01= ?";

        setAttr( "pass", TemplateFile01Item.dao.find( sql, sid ) );
    }
    @Override
    protected SqlBuilder buildWhere() {
        return super.buildWhere().WHERE( "R_TMPFILE=?", getPara( "sid" ) );
    }
    @Override
    protected void afterSave( TemplateFile01 model ) {
        String content = getPara("htmlContent");
        Integer fileId=getParaToInt("R_TMPFILE");
        UniqueTag  uniqueTag=identityContext.getEnterpriseService().getUniqueTag();
        TemplateFile file = TemplateFile.findFirst( uniqueTag, fileId );
        Identity identity=identityContext.getIdentity();
        TemplateText.saveContent( uniqueTag, file.getTemplateFileCode(), model.getId(), content, identity );
    }

    public void pass() {
        TemplateFile01Item model = new TemplateFile01Item();
        Integer sid = getParaToInt( "SID" );

        Calendar c = Calendar.getInstance();
        int year = c.get( Calendar.YEAR );
        String sql = "select * from SSM_STDTMP_FILE_01_ITEM where R_TMPFILE_01= ? and '" + year
                + "-01-01' <=T_DATE_01 and T_DATE_01<='" + year + "-12-31'";

        if ( model.findFirst( sql, sid ) == null ) {
            model.set( "R_TMPFILE_01", sid );
            model.set( "T_DATE_01", new Date() );
            ModelInjector.injectOpreator( model, identityContext );

            if ( model.save() ) {
                //审核通过
                renderJson( "1" );
            } else {
                //审核不通过
                renderJson( "0" );
            }
        } else {
            //已审核通过
            renderJson( "3" );
        }
    }

    public void view() {
        super.rec();
    }

    @Override
    protected TemplateFile01 getDao() {
        return TemplateFile01.dao;
    }
}
