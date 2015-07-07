/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：BlockDirectiveUtils.java
 * 创建时间：2015-06-25
 * 创建用户：张铮彬
 */

package com.lanstar.common.freemarker;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class BlockDirectiveUtils {
    public static Map<String, TemplateDirectiveModel> directives() {
        Map<String, TemplateDirectiveModel> directives = new LinkedHashMap<>();
        directives.put( "extends", new ExtendsDirective() );
        directives.put( "block", new BlockDirective() );
        directives.put( "put", new PutDirective() );
        return directives;
    }

    public static String getBodyResult( TemplateDirectiveBody body ) throws IOException, TemplateException {
        if ( body == null ) {
            return "";
        }

        StringWriter writer = new StringWriter();
        body.render( writer );
        return writer.toString();
    }

    public static String getBlockContentsVarName( String blockName ) {
        return PutDirective.PUT_DATA_PREFIX + blockName + ".contents";
    }

    public static String getBlockName( Environment env, Map params, String paramName ) throws TemplateException {

        SimpleScalar blockNameScalar = (SimpleScalar) params.get( paramName );
        if ( blockNameScalar == null ) {
            throw new TemplateException( "This directive must have '" + paramName + "' attribute.", env );
        }
        return blockNameScalar.getAsString();
    }
}
