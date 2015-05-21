/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：RecordKit.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import com.lanstar.common.log.Logger;

public class RecordKit {

    protected final static Logger logger = Logger.getLogger( RecordKit.class );

    public static Model<?> toModel( Class<? extends Model<?>> clazz, Record record ) {
        Model<?> model = null;
        try {
            model = clazz.newInstance();
        } catch ( Exception e ) {
            logger.error( e.getMessage(), e );
            return model;
        }
        for ( String columnName : record.getColumnNames() ) {
            model.set( columnName, record.get( "columnName" ) );
        }
        return model;
    }
}