/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewMemberController.java
 * 创建时间：2015年7月1日 下午5:46:54
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.model.tenant.ReviewMember;
import com.lanstar.plugin.activerecord.ModelKit;

/**
 * 评审成员
 *
 */
public class ReviewMemberController extends SimplateController<ReviewMember> {

    @Override
    protected ReviewMember getDao() {
        return ReviewMember.dao;
    }

    @Override
    public void batchSave() {
        String planId = this.getPara("R_SID");
        ReviewMember.dao.deleteById( planId, "R_SID" );
        String data = this.getPara( "data" );
        if ( !StrKit.isBlank( data ) ) {
            List<ReviewMember> list = JSON.parseArray( data, ReviewMember.class );
            for ( ReviewMember model : list ) {
                model.set( "R_SID", planId );
            }
            ModelKit.batchSave( this.identityContext.getTenantDb(), list );
        }
        this.setAttr( "msg", "保存成功" );
        this.renderJson();
    }
}
