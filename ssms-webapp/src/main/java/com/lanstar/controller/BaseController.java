/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：BaseController.java
 * 创建时间：2015-04-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import com.lanstar.common.helper.BeanHelper;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.action.ActionInvocation;
import com.lanstar.core.handle.identity.IdentityContext;
import com.lanstar.db.ar.ARTable;
import com.lanstar.service.parameter.Parameter;
import com.lanstar.service.parameter.ParameterResolver;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {
    protected final Map<String, Object> defaultValues = new HashMap<>();
    protected final String TABLENAME;
    //参与过滤字段
    protected final Map<String,String> filterFields = new HashMap<>();

    public BaseController( String tablename ) {
        TABLENAME = tablename;
        defaultValues.put( "T_CREATE", "@now()" );
        defaultValues.put( "T_UPDATE", "@now()" );
        setFilterFields();
    }

    protected void mergerValues( ARTable table, HandlerContext context, MergerType mergerType ) {
        mergerValuesWithoutParaMap( table, context, mergerType );
        Map<String, Object> lastValues = new HashMap<>();
        lastValues.putAll( context.getParameterMap() );
        // 移除SID、sid。map考虑忽略大小写怎样？              by 张铮彬#2015-4-26
        lastValues.remove( "sid" );
        lastValues.remove( "SID" );
        lastValues.remove( "T_UPDATE" );
        table.values( lastValues );
    }

    protected void mergerValuesWithoutParaMap( ARTable table, HandlerContext context, MergerType mergerType ) {
        Map<String, Object> lastValues = new HashMap<>();
        lastValues.putAll( defaultValues );
        IdentityContext identityContext = context.getRequestContext().getIdentityContxt();
        int usersid = identityContext.getIdentityId();
        String username = identityContext.getIdentityName();
        switch ( mergerType ) {
            case forUpdate:
                lastValues.put( "R_UPDATE", usersid );
                lastValues.put( "S_UPDATE", username );
                lastValues.remove( "T_CREATE" );
                break;
            case forInsert:
                lastValues.put( "R_CREATE", usersid );
                lastValues.put( "S_CREATE", username );
                lastValues.put( "R_TANENT", context.getIdentity().getId() );
                lastValues.put( "S_TANENT", context.getIdentity().getName() );
                lastValues.put( "P_TANENT", context.getIdentity().getTanentType() );
                break;
        }
        // 移除SID、sid。map考虑忽略大小写怎样？              by 张铮彬#2015-4-26
        lastValues.remove( "sid" );
        lastValues.remove( "SID" );
        lastValues.remove( "T_UPDATE" );
        table.values( lastValues );
    }

    protected final String placeholder( String field, String symbol ) {
        return symbol + field + symbol;
    }

    private ActionValidator validator;

    protected void validatePara( HandlerContext context ) {
        if ( validator != null ) {
            validator = BeanHelper.newInstance( getValidator() );
            validator.intercept( new ActionInvocation( context, null ) );
        }
    }

    protected List<Parameter> resolveMultiParameter( HandlerContext context, String parameterName ) {
        List<Parameter> list;
        try {
            list = ParameterResolver.me().getMultiParameter( parameterName );
        } catch ( SQLException e ) {
            throw new HandleException( e );
        }
        context.setValue( placeholder( parameterName, "_" ), list );
        return list;
    }

    protected abstract Class<? extends ActionValidator> getValidator();

    public enum MergerType {
        forUpdate,
        forInsert;

        public static MergerType withSid( String sid ) {
            return StringHelper.isBlank( sid ) ? forInsert : forUpdate;
        }
    }
    
    protected abstract void setFilterFields();
}
