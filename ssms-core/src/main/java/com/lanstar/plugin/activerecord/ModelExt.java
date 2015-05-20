/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ModelExt.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ModelExt<M extends ModelExt<M>> extends Model<M> {

    private String deleteColumnLabel = "deleteflag";

    private String version = "version";

    private Class<? extends ModelExt<M>> clazz;

    @SuppressWarnings("unchecked")
    public ModelExt() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        clazz = (Class<? extends ModelExt<M>>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    public boolean pseudoDelete() {
        return false;
    }

    @Override
    public boolean save() {
        Table tableInfo = TableMapping.me().getTable( clazz );
        if ( pseudoDelete() ) {
            if ( !tableInfo.hasColumnLabel( deleteColumnLabel ) ) {
                throw new ActiveRecordException( "The deleteColumnLabel (" + deleteColumnLabel + ") is not exist" );
            }
            this.set( deleteColumnLabel, 0 );
        }

        return super.save();
    }

    @Override
    public boolean delete() {
        Table tableInfo = TableMapping.me().getTable( clazz );

        boolean result;
        if ( pseudoDelete() ) {
            if ( !tableInfo.hasColumnLabel( deleteColumnLabel ) ) {
                throw new ActiveRecordException( "The deleteColumnLabel (" + deleteColumnLabel + ") is not exist" );
            }
            this.set( deleteColumnLabel, 1 );
            result = this.update();
        } else {
            result = super.delete();
        }

        return result;
    }

    @Override
    public boolean deleteById( Object id ) {
        Table tableInfo = TableMapping.me().getTable( clazz );

        boolean result;
        if ( pseudoDelete() ) {
            if ( !tableInfo.hasColumnLabel( deleteColumnLabel ) ) {
                throw new ActiveRecordException( "The deleteColumnLabel (" + deleteColumnLabel + ") is not exist" );
            }
            String pKey = tableInfo.getPrimaryKey();
            if ( id == null )
                throw new ActiveRecordException( "You can't update model without Primary Key." );
            String sql = "update " + tableInfo.getName() + " set " + deleteColumnLabel + " = 1 where " + pKey + " = ?";
            result = Db.update( sql, id ) >= 1;
        } else {
            result = super.deleteById( id );
        }

        return result;
    }

    public int deleteAll() {
        String primaryKey = TableMapping.me().getTable( clazz ).getPrimaryKey();
        return Db.update( "delete from " + tableName() + " where " + primaryKey + "=?" );
    }

    public List<M> findAll() {
        String sql = "select * from " + tableName();
        if ( pseudoDelete() ) {
            sql += " where " + deleteColumnLabel + " is null or " + deleteColumnLabel + " !=1 ";
        }
        return find( sql );
    }

    public List<M> findBySecurity() {
        String sql = "select * from " + tableName();
        if ( pseudoDelete() ) {
            sql += " where " + deleteColumnLabel + " is null or " + deleteColumnLabel + " !=1 ";
        }
        return find( sql );
    }

    private String tableName() {
        return TableMapping.me().getTable( clazz ).getName();
    }
}