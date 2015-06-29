/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewResultController.java
 * 创建时间：2015年6月24日 上午10:19:53
 * 创建用户：林峰
 */
package com.lanstar.controller.enterprise;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.ReviewResultCert;
import com.lanstar.model.tenant.ReviewResultPlan;

/**
 * 评审结果
 *
 */
public class ReviewResultController extends SimplateController<ReviewResultPlan> {

    @Override
    protected ReviewResultPlan getDao() {
        return ReviewResultPlan.dao;
    }

    public void tabs(){
        //获取证书编号
        ReviewResultCert cert = ReviewResultCert.dao.findFirst( "select * from ssm_result_cert where r_sid=?",this.getModel().getId() );
        if (cert!=null) this.setAttr( "certId", cert.getId() );
    }
    
    public void rep(){
        
    }
    
    public void cert(){
        
    }
}
