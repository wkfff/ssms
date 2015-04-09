/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：a02Controller.java
 * 创建时间：2015年4月1日 下午8:10:07
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import com.lanstar.core.MapModelBean;
import com.lanstar.core.VAR_SCOPE;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.identity.IdentityContext;
import com.lanstar.core.handle.identity.impl.CompanyIdentity;
import com.lanstar.db.JdbcRecordSet;

import java.util.Date;

/**
 * @author F
 */
public class a02Controller {
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

    public ViewAndModel user( HandlerContext context ) {
        Object user = new Object(){
            String username = "USER NAME";
            String password = "PASSWORD!";

            public String getUsername() {
                return username;
            }

            public String getPassword() {
                return password;
            }
        };
        // 判定身份
        IdentityContext identityContxt = context.getRequestContext().getIdentityContxt();
        if ( identityContxt.is( CompanyIdentity.class ) ) {
            // 获取企业身份实例，并从实例中获取企业特有的信息
            // CompanyIdentity companyIdentity = identityContxt.getIdentity( CompanyIdentity.class );
            // companyIdentity.getCompany()
            return context.returnWith().set( user );
        }
        return context.returnWith().set( user );
    }

    public void index5( HandlerContext context ) {
        Date value = new Date();
        context.setValue( "result", 123 );
        context.setValue( "date", value );

        context.setValue( "sessionVar", value, VAR_SCOPE.SESSION );
    }
}
