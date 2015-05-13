/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AuthHandler.java
 * 创建时间：2015-04-05
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity;

import com.lanstar.core.RequestContext;
import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DS;
import com.lanstar.db.DbContext;
import com.lanstar.db.JdbcRecord;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 处理身份认证
 */
public class IdentityHandler implements Handler {
    private static Map<TenantType, String> sqlMap = new LinkedHashMap<>();

    static {
        sqlMap.put( TenantType.ENTERPRISE, "SELECT B.SID U_ID, B.C_NAME U_NAME, A.SID T_ID, A.C_NAME T_NAME FROM `SYS_TENANT_E` A INNER JOIN `SYS_TENANT_E_USER` B ON A.`SID` = B.`R_SID` WHERE UPPER(B.`C_USER`) = ? AND UPPER(B.`C_PASSWD`) = ? AND UPPER(A.`C_CODE`) = ?" );
        sqlMap.put( TenantType.GOVERNMENT, "SELECT B.SID U_ID, B.C_NAME U_NAME, A.SID T_ID, A.C_NAME T_NAME FROM `SYS_TENANT_G` A INNER JOIN `SYS_TENANT_G_USER` B ON A.`SID` = B.`R_SID` WHERE UPPER(B.`C_USER`) = ? AND UPPER(B.`C_PASSWD`) = ? AND UPPER(A.`C_CODE`) = ?" );
        sqlMap.put( TenantType.REVIEW, "SELECT B.SID U_ID, B.C_NAME U_NAME, A.SID T_ID, A.C_NAME T_NAME FROM `SYS_TENANT_R` A INNER JOIN `SYS_TENANT_R_USER` B ON A.`SID` = B.`R_SID` WHERE UPPER(B.`C_USER`) = ? AND UPPER(B.`C_PASSWD`) = ? AND UPPER(A.`C_CODE`) = ?" );
        sqlMap.put( TenantType.SYSTEM, "SELECT SID U_ID, C_NAME U_NAME, 0 T_ID, '系统管理员' T_NAME FROM SYS_USER WHERE UPPER(`C_NAME`)=? AND `C_PASSWD`=? AND 'SYSTEM'=?" );
    }

    @Override
    public void handle( HandlerContext context, HandleChain next ) throws ServletException, IOException {
        RequestContext requestContext = context.getRequestContext();
        if ( !requestContext.hasIdentityContext() ) {
            // 登录信息无效要重定向到登录页
            requestContext.redirect( "/login" );
            return;
        }

        // 使用带有身份标识的上下文继续后续处理
        next.doHandle( context );
    }

    public static boolean login( HandlerContext context, String tenentCode, String username, String password ) {
        final TenantType tenantType = TenantType.getValue( tenentCode.substring( 0, 1 ) );
        String sql = sqlMap.get( tenantType );

        final JdbcRecord record = context.SYSTEM_DB.getDBSession()
                                                   .first( sql, new Object[] { username.toUpperCase(), password.toUpperCase(), tenentCode.toUpperCase() } );
        if (record == null) return false;
        // 创建或者获取身份标识,临时测试使用
        Identity identity = new Identity() {
            @Override
            public int getId() {
                return (int) record.get( "UID" );
            }

            @Override
            public String getName() {
                return record.getString( "U_NAME" );
            }

            @Override
            public int getTenantId() {
                return (int) record.get( "T_ID" );
            }

            @Override
            public String getTenantName() {
                return record.getString( "T_NAME" );
            }

            @Override
            public TenantType getTenantType() {
                return tenantType;
            }

            @Override
            public DbContext getDbContext() throws SQLException {
                // TODO: 要调整为根据当前用户的租户信息来获取
                return DS.getDbContext( "tenant01" );
            }
        };

        // 绑定到请求上下文中
        context.getRequestContext().bindIdentity( new IdentityContextImpl( identity ) );
        return true;
    }
}
