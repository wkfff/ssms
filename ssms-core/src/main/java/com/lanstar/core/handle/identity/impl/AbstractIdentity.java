/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AbstractorIdentity.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.identity.impl;

import com.lanstar.core.handle.db.DBSessionHolder;
import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.DBSession;
import com.lanstar.db.DS;

import java.sql.SQLException;

public abstract class AbstractIdentity extends DBSessionHolder implements Identity {
    protected String getTanentDbCode() {
        // TODO: 要调整为根据当前用户的租户信息来获取
        return "tanent01";
    }

    @Override
    public int getTanendId() {
        // TODO: 获取租户ID
        return -1;
    }

    /**
     * 获取租户名称
     */
    @Override
    public String getTanentName() {
        // TODO: 获取租户名称
        return null;
    }

    @Override
    protected DBSession buildDbSession() throws SQLException {
        return DS.getDbContext(getTanentDbCode()).createDbSession();
    }
}
