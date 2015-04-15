/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IDialect.java
 * 创建时间：2015-04-01
 * 创建用户：张铮彬
 */

package com.lanstar.db.dialect;

/**
 * 数据库方言
 */
public interface IDialect extends IQueryDialect, IExecuteDialiect {
    /**
     * 获取JdbcDriver
     */
    String getJdbcDriver();

    /**
     * 获取心跳检测SQL
     */
    String getHeartbeatSql();

    /**
     * 获取分页SQL
     */
    String getPagingSql( String sql, int startIndex, int endIndex );
}
