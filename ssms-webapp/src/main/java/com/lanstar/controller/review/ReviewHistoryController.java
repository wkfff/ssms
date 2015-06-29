/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewHistoryController.java
 * 创建时间：2015年6月26日 下午2:58:02
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import java.util.List;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.ReviewPlan;
import com.lanstar.plugin.activerecord.Record;

/**
 * @author F
 *
 */
public class ReviewHistoryController extends SimplateController<ReviewPlan> {
    @Override
    protected ReviewPlan getDao() {
        return ReviewPlan.dao;
    }
    
    /**
     * 评审历史查看
     */
    public void tabs(){
        String name = this.getModel().getEnterpriseName();
        this.setAttr( "title", name );
    }
    
    /**
     * 评审历史查看.评分汇总表
     */
    public void sum() {
        int sid = this.getModel().getId();
        List<Record> list = this.tenantDb.find( "select * from V_REVIEW_SUM where R_SID=?", sid);
        this.setAttr( "list", list );
    }

    /**
     * 评审历史查看.扣分项汇总表
     */
    public void sum_ded() {
        int sid = this.getModel().getId();
        List<Record> list = this.tenantDb.find( "select * from V_REVIEW_SUM_DED where R_SID=?", sid );
        this.setAttr( "list", list );
    }
}
