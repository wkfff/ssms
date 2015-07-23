/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateController.java
 * 创建时间：2015-06-02
 * 创建用户：张铮彬
 */

package com.lanstar.controller.review;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.kit.folder.FolderBean;
import com.lanstar.model.kit.folder.TenantFolderTreeBuilder;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.service.enterprise.ProfessionService;

public class TemplateController extends Controller {
    /**
     * 查看企业当前生效的达标体系
     */
    public void view() {
        // 企业
        int eid = this.getParaToInt( 0, 0 );
        // 专业
        int pro = this.getParaToInt( 1, 0 );
        // 是否显示返回，1显示
        int showBack = this.getParaToInt( 2, 0 );
        this.setAttr( "showBack", showBack );
        // 企业编号-专业编号-是否显示返回
        this.setAttr( "viewUrl", eid + "-" + pro + "-" + showBack );

        Enterprise enterprise = Enterprise.dao.findById( eid );
        Profession profession = Profession.dao.findById( pro );
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        identityContext.initReviewService( enterprise, profession );
        ProfessionService professionService = identityContext.getReviewService()
                                                             .getEnterpriseContext()
                                                             .getEnterpriseService()
                                                             .getProfessionService();
        Template template = professionService.getSystemTemplate();

        FolderBean value = TenantFolderTreeBuilder.tree( enterprise, template.getId(), profession.getId() );
        setAttr( "tree", value.getChildren() );

        //当前选中的
        int sid = this.getParaToInt( 3, 0 );
        setAttr( "sid", sid );
        setAttr( "template", template );
    }

    /**
     * 查看详细页面
     * 路径：see/企业编号-专业编号-是否显示返回-模板ID-模板文件编号-版本号-文件编号
     */
    public void see() {
        int eid = this.getParaToInt( 0, 0 );
        int pro = this.getParaToInt( 1, 0 );
        this.setAttr( "viewUrl", eid + "-" + pro + "-" + this.getPara( 2 ) );
        int templateId = getParaToInt( 3, 0 );
        String tmpfile = this.getPara( 4 );
        int version = this.getParaToInt( 5, 0 );
        int sid = this.getParaToInt( 6, 0 );
        this.setAttr( "tmpfile", tmpfile );
        this.setAttr( "version", version );
        this.setAttr( "sid", sid );
        TemplateFile tf = TemplateFile.findFirst( templateId, eid, pro, sid );
        if ( tf != null )
            this.setAttr( "title", tf.get( "C_NAME" ) );
    }
}
