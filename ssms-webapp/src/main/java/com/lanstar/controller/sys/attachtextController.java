/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：attachtextController.java
 * 创建时间：2015-04-18
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.service.attachtext.AttachTextService;

import java.sql.SQLException;

public class attachtextController {
    // TODO: 示例页面，正式上线的时候以后移除掉
    public void index( HandlerContext context ) {}

    public void save( HandlerContext context ) {
        AttachTextService service = context.getAttachTextService();
        String table = context.getValue( "table" );
        String field = context.getValue( "field" );
        int sid = Integer.parseInt( (String) context.getValue( "sid" ) );
        String content = context.getValue( "content" );
        try {
            service.save( table, field, sid, content );
        } catch ( SQLException e ) {
            throw new HandleException( e );
        }
    }

    public ViewAndModel get( HandlerContext context ) {
        AttachTextService service = context.getAttachTextService();
        String table = context.getValue( "table" );
        String field = context.getValue( "field" );
        int sid = Integer.parseInt( (String) context.getValue( "sid" ) );
        try {
            return context.returnWith().set( service.getContent( table, field, sid ) );
        } catch ( SQLException e ) {
            throw new HandleException( e );
        }
    }
}
