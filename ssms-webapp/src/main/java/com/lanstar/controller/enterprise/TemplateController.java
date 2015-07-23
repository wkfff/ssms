/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.kit.folder.FolderBean;
import com.lanstar.model.kit.folder.TenantFolderTreeBuilder;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.service.enterprise.ProfessionService;
import com.lanstar.service.enterprise.UniqueTag;

import java.util.List;

public class TemplateController extends Controller {
    public void index() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );

        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();

        // load template tree data
        FolderBean value = TenantFolderTreeBuilder.tree( uniqueTag.getTenant(), uniqueTag.getTemplateId(), uniqueTag.getProfessionId() );
        List<FolderBean> tmp = value.getChildren();
        FolderBean firstRec = null;
        while ( tmp.size() > 0 ) {
            firstRec = tmp.get( 0 );
            tmp = firstRec.getChildren();
        }

        this.setAttr( "R_SID", uniqueTag.getTemplateId() ).setAttr( "tree", value.getChildren() ).setAttr( "firstRec", firstRec );
        render( "index.ftl" );
    }


    public void tree() {
        index();
    }

    /**
     * 版本列表
     */
    public void versions() {
        String sql = "select distinct IFNULL(N_VERSION,0) N_VERSION from ssm_template_version where r_tenant=? ";
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        DbPro tenantDb = identityContext.getTenantDb();
        int tenantId = this.getParaToInt( "R_TENANT", identityContext.getTenantId() );
        List<Record> list = tenantDb.find( sql, tenantId );
        this.setAttr( "list", list );
    }

    /**
     * 要素查看
     * 路径：view/版本号
     * 版本号为0时为当前版本
     */
    public void view() {
        // FIXME: 修复重复体系文件的问题
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();

        // load template tree data
        FolderBean value = TenantFolderTreeBuilder.tree( uniqueTag.getTenant(), uniqueTag.getTemplateId(), uniqueTag.getProfessionId() );
        int version = this.getParaToInt( 0, 0 );
        this.setAttr( "tree", value.getChildren() );
        this.setAttr( "version", version );
        this.setAttr( "version_name", version == 0 ? "当前版本" : "版本【" + version + "】" );

        String sid = this.getPara( 1 );
        if ( sid == null ) sid = "0";
        this.setAttr( "sid", Integer.parseInt( sid ) );
    }

    /**
     * 查看详细页面
     * 路径：see/模板文件编号-版本号-文件编号
     */
    public void see() {
        String tmpfile = this.getPara( 0 );
        int version = this.getParaToInt( 1, 0 );
        int sid = this.getParaToInt( 2, 0 );
        this.setAttr( "tmpfile", tmpfile );
        this.setAttr( "version", version );
        this.setAttr( "sid", sid );
        IdentityContext identityContext=IdentityContext.getIdentityContext( this );
        UniqueTag uniqueTag=identityContext.getEnterpriseService().getUniqueTag();
        TemplateFile file = TemplateFile.findFirst( uniqueTag, sid );
        if ( file != null )
            this.setAttr( "title", file.get( "C_NAME" ) );
    }

    /**
     * 归档
     */
    public void archive() {
        //复制当前版本的所有要素到归档表，包括富文本、文件等
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        ProfessionService service = identityContext.getEnterpriseService().getProfessionService();
        service.archive( identityContext );

        //清除模板表中的记录
        render( "versions.ftl" );
    }
}
