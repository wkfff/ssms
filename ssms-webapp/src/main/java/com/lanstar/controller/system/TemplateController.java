/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateController.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.ModelInjector;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.Before;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.Template;
import com.lanstar.model.system.TemplateFolder;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.tx.Tx;

import java.util.List;

public class TemplateController extends Controller {
    public void index() {
        List<Template> templates = Template.dao.findAll();

        setAttr( "templates", ModelKit.toMap( templates, "SID", "C_NAME" ) );
    }

    @Before(Tx.class)
    public void save() {
        Integer sid = getParaToInt( "id" );
        String name = getPara( "name" );

        Template template;
        if ( sid == null ) template = new Template();
        else template = Template.dao.findById( sid );

        IdentityContext context = IdentityContextWrap.getIdentityContext( this );
        template.setName( name );
        ModelInjector.injectOpreator( template, context );

        if ( sid == null ) {
            template.save();
            TemplateFolder rootFolder = new TemplateFolder();
            rootFolder.setTemplateId( template.getId() );
            rootFolder.setName( template.getName() + "达标体系模板" );
            rootFolder.setTenant( context.getTenant() );
            rootFolder.setOperator( context.getIdentity() );
            rootFolder.save();
        } else template.update();
        renderJson( template.getId() );
    }

    public void del() {
        Integer id = getParaToInt( "id" );
        // TODO 添加删除判断，如果有模板了就不能删除了。
        if ( id != null ) renderJson( Template.dao.deleteById( id ) );
        else renderJson( false );
    }
}
