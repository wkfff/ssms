/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TodoController.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise.todo;

import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.service.common.todo.TodoData;
import com.lanstar.service.common.todo.TodoDataFetcher;
import com.lanstar.service.common.todo.TodoType;
import com.lanstar.service.enterprise.UniqueTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

public abstract class TodoController extends Controller {
    protected IdentityContext identityContext;

    @Override
    public void init( HttpServletRequest request, HttpServletResponse response, String urlPara ) {
        super.init( request, response, urlPara );

        identityContext = IdentityContext.getIdentityContext( this );
    }

    public void list() {
        UniqueTag uniqueTag = identityContext.getEnterpriseService().getUniqueTag();
        TodoDataFetcher fetcher = TodoDataFetcher.with( uniqueTag.getTenant(), getTodoType() )
                                                 .withProfessionId( uniqueTag.getProfessionId() )
                                                 .withTemplateId( uniqueTag.getTemplateId() );

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {
            Page<TodoData> page = fetcher.paginate( getParaToInt( PAGE_INDEX ), getParaToInt( PAGE_SIZE ) );
            renderJson( EasyUIControllerHelper.toDatagridResult( page ) );
        } else renderJson( fetcher.fetch() );
    }

    protected abstract TodoType getTodoType();
}
