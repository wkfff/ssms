/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoController.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise.todo;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.service.common.todo.TodoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodoController extends Controller {
    protected IdentityContext identityContext;
    protected TodoService todoService;

    @Override
    public void init( HttpServletRequest request, HttpServletResponse response, String urlPara ) {
        super.init( request, response, urlPara );

        identityContext = IdentityContext.getIdentityContext( this );
        todoService = TodoService.with( identityContext.getTenant() );
    }
}
