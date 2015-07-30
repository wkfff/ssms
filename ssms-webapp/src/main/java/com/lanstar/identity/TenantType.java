/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantType.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.model.system.tenant.*;

public enum TenantType {
    /**
     * 系统
     */
    SYSTEM( "S" ) {
        @Override
        public UserModel<?> findUserById( int id ) {
            return SystemUser.dao.findById( id );
        }

        @Override
        protected UserModel<?> findUser( String tenantCode, String username, String password ) {
            return SystemUser.getUser( username, password );
        }
    },
    /**
     * 政府
     */
    GOVERNMENT( "G" ) {
        @Override
        public UserModel<?> findUserById( int id ) {
            return GovernmentUser.dao.findById( id );
        }

        @Override
        protected UserModel<?> findUser( String tenantCode, String username, String password ) {
            return GovernmentUser.getUser( tenantCode, username, password );
        }
    },
    /**
     * 评审
     */
    REVIEW( "R" ) {
        @Override
        public UserModel<?> findUserById( int id ) {
            return ReviewUser.dao.findById( id );
        }

        @Override
        protected UserModel<?> findUser( String tenantCode, String username, String password ) {
            return ReviewUser.getUser( tenantCode, username, password );
        }
    },
    /**
     * 企业
     */
    ENTERPRISE( "E" ) {
        @Override
        public UserModel<?> findUserById( int id ) {
            return EnterpriseUser.dao.findById( id );
        }

        @Override
        protected UserModel<?> findUser( String tenantCode, String username, String password ) {
            return EnterpriseUser.getUser( tenantCode, username, password );
        }
    };

    private String name;

    TenantType( String name ) {
        this.name = name;
    }

    /**
     * 根据租户编码，用户名，密码获取身份标识信息。
     */
    public static Identity getIdentity( String tenantCode, String username, String password ) {
        TenantType type;
        if ( tenantCode.equalsIgnoreCase( SYSTEM.name() ) ) type = SYSTEM;
        else {
            String name = String.valueOf( tenantCode.charAt( 0 ) );
            type = getValue( name );
        }
        UserModel<?> user = type.findUser( tenantCode, username, password );
        return IdentityKit.toIdentity( user );
    }

    /**
     * 根据E-MAIL找用户
     */
    public static UserModel<?> findUserByEmail( String email ) {
        UserModel<?> user = EnterpriseUser.findUserByEmail( email );
        if ( user != null ) return user;

        user = ReviewUser.findUserByEmail( email );
        if ( user != null ) return user;

        user = GovernmentUser.findUserByEmail( email );
        if ( user != null ) return user;

        user = SystemUser.findUserByEmail( email );
        return user;
    }

    /**
     * 根据用户ID获取用户对象
     */
    public abstract UserModel<?> findUserById( int id );

    protected abstract UserModel<?> findUser( String tenantCode, String username, String password );

    public String getName() {
        return name;
    }

    public static TenantType getValue( String name ) {
        if ( SYSTEM.name.equalsIgnoreCase( name ) ) {
            return SYSTEM;
        } else if ( GOVERNMENT.name.equalsIgnoreCase( name ) ) {
            return GOVERNMENT;
        } else if ( REVIEW.name.equalsIgnoreCase( name ) ) {
            return REVIEW;
        } else if ( ENTERPRISE.name.equalsIgnoreCase( name ) ) {
            return ENTERPRISE;
        }
        throw new IllegalArgumentException( "租户类型[" + name + "]未知" );
    }

    @Override
    public String toString() {
        return name;
    }
}
