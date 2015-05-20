/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TxSerializable.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord.tx;

import com.lanstar.plugin.activerecord.Config;

public class TxSerializable extends Tx {

    /**
     * A constant indicating that
     * dirty reads, non-repeatable reads and phantom reads are prevented.
     * This level includes the prohibitions in
     * <code>TRANSACTION_REPEATABLE_READ</code> and further prohibits the
     * situation where one transaction reads all rows that satisfy
     * a <code>WHERE</code> condition, a second transaction inserts a row that
     * satisfies that <code>WHERE</code> condition, and the first transaction
     * rereads for the same condition, retrieving the additional
     * "phantom" row in the second read.
     */
    private int TRANSACTION_SERIALIZABLE = 8;

    @Override
    protected int getTransactionLevel( Config config ) {
        return TRANSACTION_SERIALIZABLE;
    }
}