/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachTextController.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.service.AttachTextService;

public class AttachTextController extends Controller {
    public void get(){
        String field = getPara( "field" );
        String table = getPara( "table" );
        Integer sid = getParaToInt( "sid" );

        IdentityContext context = IdentityContext.getIdentityContext( this );
        AttachTextService service = context.getAttachTextService();
        String content = service.getContent( table, field, sid );
        renderText( content );
    }

    public void save(){
        String field = getPara( "field" );
        String table = getPara( "table" );
        Integer sid = getParaToInt( "sid" );
        String content = getPara( "content" );

        IdentityContext context = IdentityContext.getIdentityContext( this );
        AttachTextService service = context.getAttachTextService();
        service.save( table, field, sid, content );
        renderJson();
    }
}
