/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ActiveRecordMapping.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.app.model;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.activerecord.ActiveRecordPlugin;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.Table;

import java.util.ArrayList;
import java.util.List;

public class ActiveRecordMapping {
    private List<Table> tableList = new ArrayList<>();

    public ActiveRecordMapping addMapping( String tableName, String primaryKey, Class<? extends Model<?>> modelClass ) {
        tableList.add( new Table( tableName, primaryKey, modelClass ) );
        return this;
    }

    public ActiveRecordMapping addMapping( String tableName, Class<? extends Model<?>> modelClass ) {
        tableList.add( new Table( tableName, modelClass ) );
        return this;
    }

    public final void mappingTo( ActiveRecordPlugin arp ) {
        for ( Table table : tableList ) {
            String primaryKey = table.getPrimaryKey();
            if ( StrKit.isEmpty( primaryKey ) ) arp.addMapping( table.getName(), table.getModelClass() );
            else arp.addMapping( table.getName(), primaryKey, table.getModelClass() );
        }
    }
}
