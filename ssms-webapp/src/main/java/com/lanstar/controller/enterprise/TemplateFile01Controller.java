/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile01Controller.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.app.Const;
import com.lanstar.common.Asserts;
import com.lanstar.common.ListKit;
import com.lanstar.common.ModelInjector;
import com.lanstar.core.aop.Before;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile01;
import com.lanstar.model.tenant.TemplateFile01Item;
import com.lanstar.model.tenant.TemplateText;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.tx.Tx;
import com.lanstar.plugin.activerecord.tx.TxConfig;
import com.lanstar.quartz.tenantdb.TaskMap;
import com.lanstar.quartz.tenantdb.TemplateFile01Task;
import com.lanstar.render.aspose.AsposeRender;
import com.lanstar.render.aspose.OutputFormat;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.common.todo.TodoType;
import com.lanstar.service.enterprise.UniqueTag;

import java.util.Calendar;
import java.util.Date;

public class TemplateFile01Controller extends TemplateFileController<TemplateFile01> {
    private TemplateFile01Task task = TaskMap.me().getTask( TemplateFile01Task.class );

    public void index() {
        super.index();
        Integer templatefileId = getParaToInt();
        Asserts.notNull( templatefileId, "发现非法的参数请求" );
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        TemplateFile file = TemplateFile.findFirst( uniqueTag, templatefileId );

        TemplateFile01 templateFile = getTemplateFile( uniqueTag, templatefileId );
        String content = "";
        if ( templateFile == null ) {
            ArchiveModel<?> templateModel = file.getTemplateModel();
            if ( templateModel != null ) {
                setAttrs( ModelKit.toMap( templateModel ) );
                removeAttr( "SID" );
                content = templateModel.getContentText();
            }
        } else {
            setAttrs( ModelKit.toMap( templateFile ) );
            content = TemplateText.getContent( uniqueTag, file.getTemplateFileCode(), templateFile.getId() );
            String sql = "select * from SSM_STDTMP_FILE_01_ITEM where R_TMPFILE_01= ?";
            setAttr( "pass", TemplateFile01Item.dao.find( sql, templateFile.getId() ) );
        }

        setAttr( "C_CONTENT", content );
        setAttr( "file", file );
    }

    protected TemplateFile01 getTemplateFile( UniqueTag uniqueTag, Integer templateFileId ) {
        TemplateFile01 model = getDao().findFirstByColumns(
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

    @Override
    protected void afterSave( TemplateFile01 model ) {
        // 保存富文本内容
        String content = getPara( "htmlContent" );
        model.setContentText( content );
    }

    @Override
    protected void afterDel( TemplateFile01 model ) {
        // 取消待办
        task.buildTodoData( model ).cancel();
    }

    public void export() {
        Integer sid = getParaToInt();
        Asserts.notNull( sid, "非法的参数请求" );
        TemplateFile01 fileItem = TemplateFile01.dao.findById( sid );
        String content = fileItem.getContentText();
        render( AsposeRender.me( content, fileItem.getName(), OutputFormat.PDF ) );
    }

    /**
     * 年审
     */
    @Before(Tx.class)
    @TxConfig(Const.TENANT_DB_NAME)
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
                UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
                // 完成待办
                TodoData.with( identityContext.getTenant(), TodoType.STDFILE01, sid )
                        .withProfessionId( uniqueTag.getProfessionId() )
                        .withTemplateId( uniqueTag.getTemplateId() )
                        .finish( identityContext.getIdentity() );
                // 审核通过
                renderJson( "1" );
            } else {
                // 审核不通过
                renderJson( "0" );
            }
        } else {
            // 已审核通过
            renderJson( "3" );
        }
    }

    public void view() {
        Integer templatefileId = getParaToInt( "sid" );
        Asserts.notNull( templatefileId, "发现非法的参数请求" );
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        TemplateFile01 templateFile = getTemplateFile( uniqueTag, templatefileId );
        if ( templateFile == null ) return;
        setAttrs( ModelKit.toMap( templateFile ) );
        TemplateFile file = TemplateFile.findFirst( uniqueTag, templatefileId );
        String content = TemplateText.getContent( uniqueTag, file.getTemplateFileCode(), templateFile.getId() );
        setAttr( "C_CONTENT", content );
        setAttr( "file", file );
    }

    @Override
    protected TemplateFile01 getDao() {
        return TemplateFile01.dao;
    }
}
