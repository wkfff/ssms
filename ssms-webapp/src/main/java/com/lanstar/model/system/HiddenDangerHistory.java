/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HiddenDangerHistory.java
 * 创建时间：上午11:05:41
 * 创建用户：苏志亮
 */
package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.Model;

/**
 * 隐患排查阅读历史
 *
 */
public class HiddenDangerHistory extends Model<HiddenDangerHistory> {
    public static final HiddenDangerHistory dao = new HiddenDangerHistory();

    public void setTenantId( int tenantId ) {
        set( "R_TENANT", tenantId );
    }

    public void setTenantType( String name ) {
        set( "P_TENANT", name );
    }

    public void setTenantName( String tenantName ) {
        set( "S_TENANT", tenantName );
    }

    public void setHiddenDangerId( Integer sid ) {
        set( "R_SID", sid );
    }
}
