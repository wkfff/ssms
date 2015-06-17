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
import com.google.common.collect.Maps;
import com.lanstar.common.TreeNode;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Template;
import com.lanstar.model.tenant.TemplateFile;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.sqlinxml.SqlKit;
import com.lanstar.service.enterprise.ProfessionService;

import java.util.*;

public class TemplateController extends Controller {
    public void index() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );

        ProfessionService professionService = identityContext.getEnterpriseService().getProfessionService();

        // get template instance
        Template template = professionService.getSystemTemplate();
        // sync template
        professionService.sync( identityContext );

        // load template tree data
        DbPro tenantDb = identityContext.getTenantDb();
        List<Record> folder = tenantDb.find( SqlKit.sql( "tenant.templateFolder.getFolderByTemplateId" ),
                template.getId(), identityContext.getTenantId(), identityContext.getTenantType().getName(),
                template.getId(), identityContext.getTenantId(), identityContext.getTenantType().getName() );
        List<Map<String, Object>> list = Lists.transform( folder, new Function<Record, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply( Record input ) {
                Map<String, Object> columns = input.getColumns();
                columns = Maps.newHashMap( columns );
                String url = input.getStr( "C_URL" );
                if ( StrKit.isEmpty( url ) == false ) columns.put( "C_URL", "/e/" + url );
                return columns;
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

        this.setAttr( "R_SID", template.getId() ).setAttr( "tree", value ).setAttr( "firstRec", firstRec );
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
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        ProfessionService professionService = identityContext.getEnterpriseService().getProfessionService();
        Template template = professionService.getSystemTemplate();
        DbPro tenantDb = identityContext.getTenantDb();
        int version = this.getParaToInt( 0, 0 );
        List<Record> folder = tenantDb.find( SqlKit.sql( "tenant.templateFolder.getFolderByTemplateIdAndVersion" ),
                template.getId(), identityContext.getTenantId(), identityContext.getTenantType().getName(), version,
                template.getId(), identityContext.getTenantId(), identityContext.getTenantType().getName(), version );
        List<Map<String, Object>> list = Lists.transform( folder, new Function<Record, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply( Record input ) {
                Map<String, Object> columns = input.getColumns();
                return columns;
            }
        } );
        List<TreeNode> value = TreeNode.build( "D-0", list, "SID", "R_SID", "C_NAME" );
        this.setAttr( "tree", value );
        this.setAttr( "version", version );
        this.setAttr( "version_name", version == 0 ? "当前版本" : "版本【" + version + "】" );

        String sid = this.getPara( 1 );
        this.setAttr( "sid", sid );
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
        TemplateFile tf = TemplateFile.dao.findById( sid );
        if ( tf != null )
            this.setAttr( "title", tf.get( "C_NAME" ) );
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
