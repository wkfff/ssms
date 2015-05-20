/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TableMapping.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import java.util.HashMap;
import java.util.Map;

public class TableMapping {

    private final Map<Class<? extends Model<?>>, Table> modelToTableMap = new HashMap<>();

    private static TableMapping me = new TableMapping();

    private TableMapping() {}

    public static TableMapping me() {
        return me;
    }

    public void putTable( Table table ) {
        modelToTableMap.put( table.getModelClass(), table );
    }

    @SuppressWarnings("rawtypes")
    public Table getTable( Class<? extends Model> modelClass ) {
        Table table = modelToTableMap.get( modelClass );
        if ( table == null )
            throw new RuntimeException( "The Table mapping of model: " + modelClass.getName()
                    + " not exists. Please add mapping to ActiveRecordPlugin: activeRecordPlugin.addMapping(tableName, YourModel.class)." );

        return table;
    }
}