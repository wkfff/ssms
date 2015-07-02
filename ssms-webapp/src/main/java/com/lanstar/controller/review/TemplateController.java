/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateController.java
 * 创建时间：2015-06-02
 * 创建用户：张铮彬
 */

package com.lanstar.controller.review;

import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lanstar.common.TreeNode;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Enterprise;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.enterprise.ProfessionService;

public class TemplateController extends Controller {
    public void index() {

    }

    public void rec() {

    }

    /**
     * 查看企业当前生效的达标体系
     */
    public void view(){
        // 企业
        int eid = this.getParaToInt( 0,0);
        // 专业
        int pro = this.getParaToInt( 1,0 );
        // 是否显示返回，1显示
        int showBack = this.getParaToInt(2,0);
        this.setAttr( "showBack", showBack );
        this.setAttr( "viewUrl", eid+"-"+pro+"-"+showBack );
        
        Enterprise enterprise = Enterprise.dao.findById( eid );
        Profession profession = Profession.dao.findById( pro );
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        identityContext.initReviewService( enterprise, profession );
        ProfessionService professionService = identityContext.getReviewService().getEnterpriseContext().getEnterpriseService().getProfessionService();
        Template template = professionService.getSystemTemplate();
        String type = identityContext.getReviewService().getEnterpriseContext().getTenantType().getName();
        DbPro tenantDb = identityContext.getReviewService().getEnterpriseContext().getTenantDb();
        List<Record> folder = tenantDb.find(
                SqlKit.sql( "tenant.templateFolder.seeFolderByTemplateId" ),
                template.getId(), eid, type,
                template.getId(), eid, type );
        
        List<Map<String, Object>> list = Lists.transform( folder, new Function<Record, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply( Record input ) {
                Map<String, Object> columns = input.getColumns();
                return columns;
            }
        } );
        List<TreeNode> value = TreeNode.build( "D-0", list, "SID", "R_SID", "C_NAME" );
        setAttr("tree",value);
        
        //当前选中的
        int sid = this.getParaToInt(3,0 );
        setAttr("sid",sid);
    }
    /**
     * 查看详细页面
     * 路径：see/企业编号-专业编号-是否显示返回-模板文件编号-版本号-文件编号
     */
    public void see(){
        this.setAttr( "viewUrl", this.getPara(0)+"-"+this.getPara(1)+"-"+this.getPara(2) );
        String tmpfile = this.getPara( 3 );
        int version = this.getParaToInt( 4, 0 );
        int sid = this.getParaToInt( 5, 0 );
        this.setAttr( "tmpfile", tmpfile );
        this.setAttr( "version", version );
        this.setAttr( "sid", sid );
        TemplateFile tf = TemplateFile.dao.findById( sid );
        if ( tf != null )
            this.setAttr( "title", tf.get( "C_NAME" ) );
    }
}
