/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：BlockDirective.java
 * 创建时间：2015-06-25
 * 创建用户：张铮彬
 */

package com.lanstar.common.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import static com.lanstar.common.freemarker.BlockDirectiveUtils.*;

@SuppressWarnings("rawtypes")
public class BlockDirective implements TemplateDirectiveModel {

    public static final String BLOCK_NAME_PARAMETER = "name";

    @Override
    public void execute( Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body ) throws TemplateException, IOException {
        String blockName = getBlockName( env, params, BLOCK_NAME_PARAMETER );
        PutType putType = getPutType( env, blockName );
        String bodyResult = getBodyResult( body );

        Writer out = env.getOut();

        String putContents = getPutContents( env, blockName );

        putType.write( out, bodyResult, putContents );
    }

    private PutType getPutType( Environment env, String blockName ) throws TemplateException {
        SimpleScalar putTypeScalar = (SimpleScalar) env.getVariable( getBlockTypeVarName( blockName ) );
        if ( putTypeScalar == null ) {
            return PutType.APPEND;
        }

        return PutType.valueOf( putTypeScalar.getAsString() );
    }

    private String getPutContents( Environment env, String blockName ) throws TemplateModelException {
        SimpleScalar putContentsModel = (SimpleScalar) env.getVariable( getBlockContentsVarName( blockName ) );
        String putContents = "";
        if ( putContentsModel != null ) {
            putContents = putContentsModel.getAsString();
        }
        return putContents;
    }
}
