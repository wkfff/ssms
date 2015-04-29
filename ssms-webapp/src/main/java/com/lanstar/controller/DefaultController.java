/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Controller.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.dialect.JdbcPageRecordSet;

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
        String sid = (String) context.getValue( "sid" );
        return context.returnWith().set(
                context.DB.withTable( this.TABLENAME ).where( "SID=?", sid )
                        .query() );
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
        // TODO：一律过滤"null"值？
        table.where( !StringHelper.isBlank( sid ) && !sid.equals( "null" ),
                "SID=?", sid ).save();

        if ( StringHelper.isBlank( sid ) || sid.equals( "null" ) ) {
            sid = Integer.toString( context.DB.getSID() );
        }

        return context.returnWith().put( "SID", sid );
    }

    /**
     * 列表.删除
     */
    public ViewAndModel dels( HandlerContext context ) {
        String ids = (String) context.getValue( "ids" );
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
        return context.returnWith().set("");
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
                    || key.equals( DBPaging.PAGE_SIZE )|| key.equals( "sid" ) ) continue;
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
