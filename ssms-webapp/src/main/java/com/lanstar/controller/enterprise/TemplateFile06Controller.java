/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile06Controller.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.app.Const;
import com.lanstar.identity.Identity;
import com.lanstar.model.system.archive.ArchiveModel;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.model.tenant.TemplateFile06;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.quartz.tenantdb.TaskMap;
import com.lanstar.quartz.tenantdb.TemplateFile06Task;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.enterprise.UniqueTag;

public class TemplateFile06Controller extends TemplateFileController<TemplateFile06> {
    private TemplateFile06Task task = TaskMap.me().getTask( TemplateFile06Task.class );

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
            if ( archiveModel != null ) {
                setAttrs( ModelKit.toMap( archiveModel ) );
                removeAttr( "SID" );
            }
        }
        setAttr( "file", file );
    }

    public void rec_todo() {
        this.setAttr( "todo", "1" );
        this.rec();
        render( "rec.ftl" );
    }

    @Override
    protected TemplateFile06 getDao() {
        return TemplateFile06.dao;
    }

    public void view() {
        setAttr( "fileid", getParaToInt( "sid" ) );
    }

    public void detail() {
        this.rec();
    }

    @Override
    protected SqlBuilder buildWhere() {
        return super.buildWhere().WHERE( "R_TMPFILE=?", getPara( "fileId" ) );
    }

    @Override
    protected SqlBuilder buildOrder() {
        return new SqlBuilder().ORDER_BY( "T_CREATE DESC" );
    }

    @Override
    protected void afterSave( TemplateFile06 model ) {
        Identity operator = identityContext.getIdentity();
        TodoData todoData = task.buildTodoData( model );
        // 如果任务状态为已完成，那么要将代表完成（如果有待办的话）
        // 否则要考虑创建待办了，通过待办创建的验证才可以创建待办
        if ( model.isFinish() ) todoData.finish( operator );
        else if ( task.validate( model ) ) todoData.save( operator );
    }

    @Override
    protected void afterDel( TemplateFile06 model ) {
        task.buildTodoData( model ).cancel();
    }

}
