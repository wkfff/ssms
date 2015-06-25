/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PutDirective.java
 * 创建时间：2015-06-25
 * 创建用户：张铮彬
 */

package com.lanstar.common.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.util.Map;

import static com.lanstar.common.freemarker.BlockDirectiveUtils.*;

@SuppressWarnings("rawtypes")
public class PutDirective implements TemplateDirectiveModel {
    public static final String PUT_DATA_PREFIX = PutDirective.class.getCanonicalName() + ".";
    public static final String PUT_BLOCK_NAME_PARAMETER = "block";
    public static final String PUT_TYPE_PARAMETER = "type";

    @Override
    public void execute( Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body ) throws TemplateException, IOException {
        String blockName = getBlockName( env, params, PUT_BLOCK_NAME_PARAMETER );
        PutType putType = getPutType( params );
        String bodyResult = getBodyResult( body );

        env.setVariable( getBlockContentsVarName( blockName ), new SimpleScalar( bodyResult ) );
        env.setVariable( getBlockTypeVarName( blockName ), new SimpleScalar( putType.name() ) );
    }

    private PutType getPutType( Map params ) {
        SimpleScalar putTypeScalar = (SimpleScalar) params.get( PUT_TYPE_PARAMETER );
        PutType putType = null;
        if ( putTypeScalar != null ) {
            putType = PutType.valueOf( putTypeScalar.getAsString().toUpperCase() );
        }

        if ( putType == null ) {
            putType = PutType.APPEND;
        }
        return putType;
    }
}