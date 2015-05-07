/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JsonMethod.java
 * 创建时间：2015-05-07
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template.freemarker;

import com.lanstar.plugin.json.JsonHelper;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class JsonMethod implements TemplateMethodModelEx {
    @Override
    public Object exec( List arguments ) throws TemplateModelException {
        if (arguments.size() != 1) return "";
        Object object = arguments.get( 0 );
        if (object instanceof WrapperTemplateModel ) {
            object = ((WrapperTemplateModel) object).getWrappedObject();
        }
        return JsonHelper.toJson( object );
    }
}
