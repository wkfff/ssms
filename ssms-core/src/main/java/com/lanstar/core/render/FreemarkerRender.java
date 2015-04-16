/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FreemarkerRender.java
 * 创建时间：2015-04-11
 * 创建用户：张铮彬
 */

package com.lanstar.core.render;

import com.lanstar.core.RequestContext;
import com.lanstar.plugin.template.TemplateBean;
import com.lanstar.plugin.template.TemplateHelper;
import com.lanstar.plugin.template.freemarker.FreemarkerPlugin;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.io.Writer;

class FreemarkerRender extends AbstractRender {
    private ViewAndModelContext context;

    public FreemarkerRender( ViewAndModelContext context ) {
        super( context );
        this.context = context;
    }

    @Override
    protected void renderHeader( RequestContext requestContext ) {
        RenderHelper.setHtmlHeader( requestContext );
    }

    @Override
    protected void innerRender() throws Exception {
        String viewName = context.getViewPath();
        FreemarkerModel model = new FreemarkerModel( context );
        Writer output = context.getOutput();
        TemplateBean bean = new TemplateBean( viewName, model, output );
        TemplateHelper.render( bean );
    }

    /**
     * Freemarker模型
     */
    private class FreemarkerModel implements TemplateHashModel {
        private final BeansWrapper wrapper = new BeansWrapper( FreemarkerPlugin.VERSION );
        private final ViewAndModelContext context;

        public FreemarkerModel( ViewAndModelContext context ) {
            this.context = context;
            // 避免使用?keys遍历map中时会获取到混合了自定义方法的数据      by 张铮彬(cnzgray@qq.com)
            if ( !wrapper.isSimpleMapWrapper() ) wrapper.setSimpleMapWrapper( true );
        }

        @Override
        public TemplateModel get( String key ) throws TemplateModelException {
            return wrapper.wrap( context.getValue( key ) );
        }

        @Override
        public boolean isEmpty() throws TemplateModelException {
            return false;
        }
    }
}