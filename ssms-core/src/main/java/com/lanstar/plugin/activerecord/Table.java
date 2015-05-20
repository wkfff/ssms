/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Table.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import com.lanstar.common.kit.StrKit;

import java.util.Collections;
import java.util.Map;

public class Table {

    private String name;
    private String primaryKey;
    private String secondaryKey = null;
    private Map<String, Class<?>> columnTypeMap;    // config.containerFactory.getAttrsMap();

    private Class<? extends Model<?>> modelClass;

    public Table( String name, Class<? extends Model<?>> modelClass ) {
        if ( StrKit.isBlank( name ) )
            throw new IllegalArgumentException( "Table name can not be blank." );
        if ( modelClass == null )
            throw new IllegalArgumentException( "Model class can not be null." );

        this.name = name.trim();
        this.modelClass = modelClass;
    }

    public Table( String name, String primaryKey, Class<? extends Model<?>> modelClass ) {
        if ( StrKit.isBlank( name ) )
            throw new IllegalArgumentException( "Table name can not be blank." );
        if ( StrKit.isBlank( primaryKey ) )
            throw new IllegalArgumentException( "Primary key can not be blank." );
        if ( modelClass == null )
            throw new IllegalArgumentException( "Model class can not be null." );

        this.name = name.trim();
        setPrimaryKey( primaryKey.trim() );    // this.primaryKey = primaryKey.trim();
        this.modelClass = modelClass;
    }

    void setPrimaryKey( String primaryKey ) {
        String[] keyArr = primaryKey.split( "," );
        if ( keyArr.length > 2 )
            throw new IllegalArgumentException( "Supports only two primary key for Composite primary key." );

        if ( keyArr.length > 1 ) {
            if ( StrKit.isBlank( keyArr[0] ) || StrKit.isBlank( keyArr[1] ) )
                throw new IllegalArgumentException( "The composite primary key can not be blank." );
            this.primaryKey = keyArr[0].trim();
            this.secondaryKey = keyArr[1].trim();
        } else {
            this.primaryKey = primaryKey;
        }
    }

    void setColumnTypeMap( Map<String, Class<?>> columnTypeMap ) {
        if ( columnTypeMap == null )
            throw new IllegalArgumentException( "columnTypeMap can not be null" );

        this.columnTypeMap = columnTypeMap;
    }

    public String getName() {
        return name;
    }

    void setColumnType( String columnLabel, Class<?> columnType ) {
        columnTypeMap.put( columnLabel, columnType );
    }

    public Class<?> getColumnType( String columnLabel ) {
        return columnTypeMap.get( columnLabel );
    }

    /**
     * Model.save() need know what columns belongs to himself that he can saving to db.
     * Think about auto saving the related table's column in the future.
     */
    public boolean hasColumnLabel( String columnLabel ) {
        return columnTypeMap.containsKey( columnLabel );
    }

    /**
     * update() and delete() need this method.
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getSecondaryKey() {
        return secondaryKey;
    }

    public Class<? extends Model<?>> getModelClass() {
        return modelClass;
    }

    public Map<String, Class<?>> getColumnTypeMap() {
        return Collections.unmodifiableMap( columnTypeMap );
    }
}