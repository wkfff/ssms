/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：a02Controller.java
 * 创建时间：2015年4月1日 下午8:10:07
 * 创建用户：林峰
 */
package com.lanstar.controller;

import com.lanstar.core.MapModelBean;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.controller.Controller;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;

/**
 * @author F
 */
public class a02Controller extends Controller {
    public ViewAndModel index( HandlerContext context ) {
        // 返回结果
        return context.returnWith()
                      .put( "abc", "123" );
    }

    public ViewAndModel index2( HandlerContext context ) {
        // 返回结果
        // 这个等同index
        return context.returnWith( "index" )
                      .put( "abc", "123" );
    }

    public ViewAndModel index3( HandlerContext context ) {
        MapModelBean bean = new MapModelBean();
        bean.put( "abc", "234" );
        // 返回结果
        return context.returnWith( "index", bean )
                      .put( "def", "123" )
                      .put( "aaa", 123 );
    }

    public ViewAndModel index4( HandlerContext context ) {
        int insert1 = context.DB.withTable( "demo" )
                                .value( "f1", 1 )
                                .insert();
        int insert2 = context.DB.withTable( "demo" )
                                .value( "f1", 2 )
                                .insert();
        int update = context.DB.withTable( "demo" )
                               .where( "f1=?", 2 )
                               .value( "f1", 3 )
                               .update();
        JdbcRecordSet list = context.DB.withTable( "demo" ).queryList();
        int delete = context.DB.withTable( "demo" )
                               .delete();

        return context.returnWith()
                      .put( "insert1", insert1 )
                      .put( "insert2", insert2 )
                      .put( "update", update )
                      .put( "delete", delete )
                      .put( "list", list );
    }

    public ViewAndModel user(HandlerContext context){
        return context.returnWith().set( 1 );
    }
}
