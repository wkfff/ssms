/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateProp.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import com.google.common.collect.Maps;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.service.Parameter;

import java.util.Map;

/**
 * 模板属性说明文件
 */
@SuppressWarnings("rawtypes")
public final class TemplateProp {
    private String name;
    private String code;
    private Map<ModelType, ModelWrap> models = Maps.newHashMap();

    private Parameter parameter;

    public TemplateProp( String code, String name ) {
        this.name = name;
        this.code = code;
    }

    public TemplateProp putModel( ModelType type, Class<? extends ModelExt> model ) {
        models.put( type, ModelWrap.wrap( model ) );
        return this;
    }

    public ModelWrap getModel( ModelType type ) {
        return models.get( type );
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Parameter getParameter() {
        if ( parameter == null ) {
            parameter = new Parameter( code, name );
        }
        return parameter;
    }
}
