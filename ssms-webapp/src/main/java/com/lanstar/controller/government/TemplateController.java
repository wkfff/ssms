/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateController.java
 * 创建时间：2015-06-02
 * 创建用户：张铮彬
 */

package com.lanstar.controller.government;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lanstar.common.TreeNode;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.Template;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.enterprise.ProfessionService;

import java.util.List;
import java.util.Map;

public class TemplateController extends Controller {
    public void index() {

    }

    public void rec() {

    }
    public void query2(){
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        // 企业
        int eid = this.getParaToInt( "sid" );
        // 专业
        int pro = this.getParaToInt( "pro" );
        Enterprise enterprise = Enterprise.dao.findById( eid );
        Profession profession = Profession.dao.findById( pro );
        identityContext.initReviewService( enterprise, profession );
        
        query();
        render( "query.ftl" );
    }
    /**
     * 查看企业当前生效的达标体系
     */
    public void query(){
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        ProfessionService professionService = identityContext.getReviewService().getEnterpriseContext().getEnterpriseService().getProfessionService();
        Template template = professionService.getSystemTemplate();
        int eid = identityContext.getReviewService().getEnterpriseContext().getTenantId();
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
                columns = Maps.newHashMap( columns );
                String url = input.getStr( "C_URL" );
                if ( StrKit.isEmpty( url ) == false )
                    columns.put( "C_URL", "/e/" + url );
                return columns;
            }
        } );
        List<TreeNode> value = TreeNode.build( "D-0", list, "SID", "R_SID", "C_NAME" );
        setAttr("tree",value);
    }
    
    public void see(){
        
    }
}
