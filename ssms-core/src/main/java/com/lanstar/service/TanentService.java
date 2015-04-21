/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityService.java
 * 创建时间：2015-04-16
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.core.handle.identity.Identity;
import com.lanstar.db.DBSession;

import java.sql.SQLException;

/**
 * 租户服务
 */
public abstract class TanentService {
    protected final Identity identity;

    /**
     * 根据身份标识获取租户服务
     * @param identity 身份标识
     */
    public TanentService( Identity identity ) {
        this.identity = identity;
    }

    /**
     * 获取租户对应的数据库上下文
     */
    public DBSession getDBSession() throws SQLException {return identity.getDBSession();}
}
