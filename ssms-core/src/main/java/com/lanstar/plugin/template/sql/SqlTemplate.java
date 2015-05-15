/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlTemplate.java
 * 创建时间：2015年5月14日 上午11:25:54
 * 创建用户：林峰
 */
package com.lanstar.plugin.template.sql;
import com.lanstar.common.helper.BeanHelper;
import com.lanstar.plugin.template.StringTemplateBean;
import com.lanstar.plugin.template.TemplateHelper;
import com.lanstar.plugin.template.freemarker.FreemarkerPlugin;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author F
 *
 */
public class SqlTemplate {
    /**
     * 解析SQL
     * @param src SQL模板
     * @param context 上下文
     * @return 解析后的SQL
     * @throws Exception 
     */
    public static String parse(String src,TemplateContext context){
        try{
            FreemarkerModel model = new FreemarkerModel( context );
            StringTemplateBean bean = new StringTemplateBean(src, model);
            TemplateHelper.evaluate( bean );
            return bean.getStringWriter().toString();
        }catch(Exception e){
            return "";
        }
    }
    /**
     * Freemarker模型
     */
    private static class FreemarkerModel implements TemplateHashModel {
        private final BeansWrapper wrapper = new BeansWrapper( FreemarkerPlugin.VERSION );
        private final TemplateContext context;

        public FreemarkerModel( TemplateContext context ) {
            this.context = context;
            if ( !wrapper.isSimpleMapWrapper() ) wrapper.setSimpleMapWrapper( true );
        }

        @Override
        public TemplateModel get( String key ) throws TemplateModelException {
            return wrapper.wrap( context.getVariable( key ) );
        }

        @Override
        public boolean isEmpty() throws TemplateModelException {
            return false;
        }
    }
}
