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
        String bodyResult = getBodyResult( body );

        Writer out = env.getOut();

        SimpleSequence putContents = getPutContents( env, blockName );
        for ( int i = 0; i < putContents.size(); i++ ) {
            PutObject putObject = (PutObject) putContents.get( i );

            bodyResult = putObject.putType.write( bodyResult, putObject.bodyResult );
        }
        out.write( bodyResult );
    }

    private SimpleSequence getPutContents( Environment env, String blockName ) throws TemplateModelException {
        return (SimpleSequence) env.getVariable( getBlockContentsVarName( blockName ) );
    }
}
