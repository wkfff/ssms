/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ExtendsDirective.java
 * 创建时间：2015-06-25
 * 创建用户：张铮彬
 */

package com.lanstar.common.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class ExtendsDirective implements TemplateDirectiveModel {

    @Override
    public void execute( Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body ) throws TemplateException, IOException {
        String layoutName = ((SimpleScalar) params.get( "name" )).getAsString();

        processBody( body );
        processLayout( env, layoutName );
    }

    private void processBody( TemplateDirectiveBody body ) throws TemplateException, IOException {
        if ( body == null ) {
            return;
        }

        StringWriter fakeOut = new StringWriter();
        body.render( fakeOut );
    }

    private void processLayout( Environment env, String layoutName ) throws IOException, TemplateException {
        String fullTemplateName = env.toFullTemplateName( ((Template) env.getParent()).getName(), layoutName );
        env.include( fullTemplateName, null, true );
    }
}
