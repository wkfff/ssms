/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lanstar.common.TreeNode;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Template;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.ProfessionService;
import com.lanstar.service.ProfessionTemplateService;

import java.util.List;
import java.util.Map;

public class TemplateController extends Controller {
    public void index() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );

        ProfessionService professionService = identityContext.getProfessionService();
        ProfessionTemplateService templateService = professionService.getTemplateService();
        templateService.synchronousTo( identityContext );

        // get template instance
        Template template = professionService.getSystemTemplate();

        // load template tree data
        DbPro tenantDb = identityContext.getTenantDb();
        List<Record> folder = tenantDb.find(
                SqlKit.sql( "system.templateFolder.getFolderByTemplateId" ), template.getId(), template.getId() );
        List<Map<String, Object>> list = Lists.transform( folder, new Function<Record, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply( Record input ) {
                return input.getColumns();
            }
        } );
        List<TreeNode> value = TreeNode.build( "D-0", list, "SID", "R_SID", "C_NAME" );
        if ( value.size() == 1 ) {
            value = value.get( 0 ).getChildren();
        }
        List<TreeNode> tmp = value;
        TreeNode firstRec = null;
        while ( tmp.size() > 0 ) {
            firstRec = tmp.get( 0 );
            tmp = firstRec.getChildren();
        }

        setAttr( "R_SID", template.getId() ).setAttr( "tree", value ).setAttr( "firstRec", firstRec );
    }
}