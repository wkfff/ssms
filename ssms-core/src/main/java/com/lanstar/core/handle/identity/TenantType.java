/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantType.java
 * 创建时间：2015-04-27
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity;

public enum TenantType {
    /**
     * 系统
     */
    SYSTEM("S"),

    /**
     * 政府
     */
    GOVERNMENT("G"),

    /**
     * 评审
     */
    REVIEW("R"),

    /**
     * 企业
     */
    ENTERPRISE("E");

    private String name;

    TenantType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TenantType getValue(String name){
        if ( SYSTEM.name.equalsIgnoreCase( name )) {
            return SYSTEM;
        }
        else if ( GOVERNMENT.name.equalsIgnoreCase( name )) {
            return GOVERNMENT;
        }
        else if ( REVIEW.name.equalsIgnoreCase( name )) {
            return REVIEW;
        }else if ( ENTERPRISE.name.equalsIgnoreCase( name )) {
            return ENTERPRISE;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
