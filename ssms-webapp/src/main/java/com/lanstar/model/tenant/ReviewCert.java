/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewCert.java
 * 创建时间：2015年6月18日 上午10:18:46
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import com.lanstar.model.TenantModel;

/**
 * 评审证书
 *
 */
public class ReviewCert extends TenantModel<ReviewCert> {
    public static final ReviewCert dao = new ReviewCert();

    public Integer getId() {
        return getInt( "SID" );
    }
    
    public void setRsid(int value){
        set("R_SID",value);
    }
    
    public void setEnterpriseName(String enterpriseName){
        set("C_ENTERPRISE",enterpriseName);
    }
    
    public void setProfessionId(Integer proId){
        set("P_PROFESSION",proId);
    }
    
    public void setProfessionName(String proName){
        set("S_PROFESSION",proName);
    }
}
