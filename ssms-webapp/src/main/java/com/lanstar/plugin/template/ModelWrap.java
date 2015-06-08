/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ModelWrap.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.template;

import com.lanstar.common.kit.BeanKit;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.activerecord.Table;
import com.lanstar.plugin.activerecord.TableMapping;

@SuppressWarnings("rawtypes")
public class ModelWrap {
    private final ModelExt dao;
    private final Class<? extends ModelExt> clazz;

    private ModelWrap( Class<? extends ModelExt> model ) {
        clazz = model;
        dao = BeanKit.newInstance( model );
    }

    public static ModelWrap wrap( Class<? extends ModelExt> model ) {
        return new ModelWrap( model );
    }

    public ModelExt getModel() {
        return BeanKit.newInstance( clazz );
    }

    public Class<? extends ModelExt> getModelClass() {
        return clazz;
    }

    public Table getTable() {
        return TableMapping.me().getTable( clazz );
    }

    public ModelExt getDao() {
        return dao;
    }
}
