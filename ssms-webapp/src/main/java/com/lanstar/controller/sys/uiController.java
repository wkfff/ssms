/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：uiController.java
 * 创建时间：2015年4月18日 下午3:18:53
 * 创建用户：林峰
 */
package com.lanstar.controller.sys;

import java.sql.SQLException;
import java.util.List;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.service.parameter.Parameter;
import com.lanstar.service.parameter.ParameterResolver;

/**
 * UI示例
 *
 */
public class uiController extends DefaultController {
    /**
     * @param tablename
     */
    public uiController() {
        super( "SYS_TENANT_E" );
    }

    /* (non-Javadoc)
     * @see com.lanstar.controller.BaseController#getValidator()
     */
    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return uiValidator.class;
    }

    /* (non-Javadoc)
     * @see com.lanstar.controller.DefaultController#rec(com.lanstar.core.handle.HandlerContext)
     */
    @Override
    public ViewAndModel rec( HandlerContext context ) {
        resolveMultiParameter(context,"USER_SEX");
        return super.rec( context );
    }

    public ViewAndModel getPara(HandlerContext context){
        String parameterName = context.getValue( "name" );
        List<Parameter> list;
        try {
            list = ParameterResolver.me().getMultiParameter( parameterName );
        } catch ( SQLException e ) {
            throw new HandleException( e );
        }
        return context.returnWith().set(list);
    }
}
