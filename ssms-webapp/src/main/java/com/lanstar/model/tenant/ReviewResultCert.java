/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewCert.java
 * 创建时间：2015年6月18日 上午10:18:46
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import com.lanstar.plugin.activerecord.Model;

/**
 * 评审结果.证书
 *
 */
public class ReviewResultCert extends Model<ReviewResultCert> {
    public static ReviewResultCert dao = new ReviewResultCert();

    public Integer getId() {
        return getInt( "SID" );
    }
}
