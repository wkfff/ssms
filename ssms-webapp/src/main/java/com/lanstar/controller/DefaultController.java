/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Controller.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.dialect.JdbcPageRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.plugin.json.JsonHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DefaultController extends BaseController {
    public DefaultController( String tablename ) {
        super( tablename );
    }

    /**
     * 主页面
     */
    public ViewAndModel index( HandlerContext context ) {
        return context.returnWith();
    }

    /**
     * 列表数据
     */
    public ViewAndModel list( HandlerContext context ) {
        return this.list( context, null );
    }

    protected ViewAndModel list( HandlerContext context,
            TableProcessor processor ) {
        ARTable arTable = context.DB.withTable( this.TABLENAME );
        Map<String, String> filter = this.getFilter( context );
        if ( !filter.isEmpty() ) {
            arTable.where(
                    StringHelper.join( filter.keySet(), " and ", false ),
                    filter.values().toArray() );
        }
        if ( processor != null ) processor.process( arTable );
        DBPaging paging = context.getPaging();
        JdbcPageRecordSet list = arTable.queryPaging( paging );
        return context.returnWith().set(
                EasyUIControllerHelper.toDatagridResult( list ) );
    }

    /**
     * 表单数据
     */
    public ViewAndModel rec( HandlerContext context ) {
        String sid = context.getValue( "sid" );

        JdbcRecord record = null;
        if ( StringHelper.vaildValue( sid ) ) {
            record = context.DB.withTable( this.TABLENAME ).where( "SID=?", sid ).query();
        }
        return context.returnWith().set( record );
    }

    /**
     * 表单.保存
     */
    public ViewAndModel save( HandlerContext context ) {
        // 先验证下参数
        this.validatePara( context );
        String sid = context.getValue( "sid" );
        // 解决如果传递的SID是大写的时候，搜索不到的问题 by 张铮彬#2015-4-25
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        ARTable table = context.DB.withTable( this.TABLENAME );
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        // 根据sid的存在设置where语句
        table.where( StringHelper.vaildValue( sid ), "SID=?", sid ).save();

        if ( !StringHelper.vaildValue( sid ) ) {
            sid = Integer.toString( context.DB.getSID() );
        }

        return context.returnWith().put( "SID", sid );
    }

    @SuppressWarnings("unchecked")
    public void batchSave( HandlerContext context ) {
        String dataStr = context.getValue( "data" );
        int count = 0;
        if ( StringHelper.isBlank( dataStr ) ) return;
        List<Object> list = JsonHelper.parseArray( dataStr );
        for ( Object o : list ) {
            JSONObject map = (JSONObject) o;
            String sid = String.valueOf( map.get( "SID" ) );
            if ( sid == null || sid.startsWith( "_" ) ) {
                map.remove( "SID" );
                sid = null;
            }
            ARTable table = context.DB.withTable( this.TABLENAME );
            this.mergerValuesWithoutParaMap( table, context, MergerType.withSid( sid ) );
            table.values( map );
            // 根据sid的存在设置where语句
            table.where( StringHelper.vaildValue( sid ), "SID=?", sid )
                 .save();
            count++;
        }
        context.setValue( "count", count );
    }

    /**
     * 列表.删除
     */
    public ViewAndModel dels( HandlerContext context ) {
        String ids = context.getValue( "ids" );
        if ( !Strings.isNullOrEmpty( ids ) ) {
            context.DB.withTable( this.TABLENAME )
                      .where( "SID in (" + ids + ")" ).delete();
        }
        return context.returnWith().set( "{}" );
    }

    /**
     * 表单.删除
     *
     * @param context
     *
     * @return
     */
    public ViewAndModel del( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        // 解决如果传递的SID是大写的时候，搜索不到的问题 by 张铮彬#2015-4-25
        if ( Strings.isNullOrEmpty( sid ) ) sid = context.getValue( "SID" );
        if ( !Strings.isNullOrEmpty( sid ) ) {
            context.DB.withTable( this.TABLENAME ).where( "SID = ?", sid )
                      .delete();
        }
        return context.returnWith().set( "" );
    }

    @Override
    public void setFilterFields() {
    }

    /**
     * 对过滤字段处理
     */
    public Map<String, String> getFilter( HandlerContext context ) {
        Map<String, String> filter = new HashMap<String, String>();
        Map<String, String> map = context.getParameterMap();

        for ( String key : map.keySet() ) {
            if ( key.equals( DBPaging.PAGE_INDEX )
                    || key.equals( DBPaging.PAGE_SIZE ) || key.equals( "sid" ) ) continue;
            String value = map.get( key );
            if ( Strings.isNullOrEmpty( value ) ) continue;
            String f = this.filterFields.get( key );
            // 添加默认规则处理，如果在过滤的map中没有找到，则用'='进行处理 by 张铮彬#2015-4-25
            if ( Strings.isNullOrEmpty( f ) ) f = String.format( "%s=?", key );
            else if ( f.indexOf( "like" ) > -1 ) value = "%" + value + "%";
            filter.put( f, value );
        }
        return filter;
    }
}


/*
    TODO: 添加返回字段的过滤，不应该返回所有字段，而是有选择的进行返回。
 */