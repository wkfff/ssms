/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileModel.java
 * 创建时间：2015-06-09
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.app.Const;
import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.activerecord.TableMapping;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TemplateFileModel<T extends TemplateFileModel<T>> extends ModelExt<T> {
    private final Class<? extends TemplateFileModel<T>> clazz;

    @SuppressWarnings("unchecked")
    public TemplateFileModel() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        clazz = (Class<? extends TemplateFileModel<T>>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    @Override
    public boolean save() {
        boolean success = super.save();
        if ( getIndex() == null ) {
            setIndex( getId() );
            success = update();
        }
        return success;
    }

    public final Integer getId() {
        return getInt( TableMapping.me().getTable( clazz ).getPrimaryKey() );
    }

    public final int getTemplateFileId() {
        return getInt( Const.TEMPLATE_FILE_PARENT_FIELD );
    }

    public final TemplateFile getTemplateFile() {
        return TemplateFile.dao.findById( getTemplateFileId() );
    }

    public final Integer getIndex() {
        return getInt( "N_INDEX" );
    }

    public final void setIndex( int index ) {
        set( "N_INDEX", index );
    }
}
