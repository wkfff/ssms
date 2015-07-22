/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewController.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.common.staticcache.TenantCache;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.tenant.Review;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.staticcache.CacheManager;
import com.lanstar.service.TenantService;

public class ReviewController extends SimplateController<Review> {
    public void reg() {

    }

    @Override
    protected Review getDao() {
        return Review.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder()
                .WHERE( "IFNULL(B_DELETE,'0')<>?", "1" )
                ._If( isParaBlank( "C_NAME" ) == false, "C_NAME=?", getPara( "C_NAME" ) );
    }

    @Override
    protected void beforeSave( Review model, boolean[] handled ) {
        Integer sid = model.getInt( "SID" );
        TenantService service = TenantService.me();
        if ( sid == null ) {
            // 生成租户特征码
            String tenantCode = service.buildSignature( TenantType.REVIEW, model.getCountyCode() );
            model.setTenantCode( tenantCode );
            model.save();
            // 添加管理员用户
            service.addAdminUser( model );
            handled[0] = true;
        }
    }

    @Override
    protected void afterSave( Review model ) {
        CacheManager.me().getCache( TenantCache.class ).refresh();
    }

    @Override
    protected void afterDel( Review model ) {
        CacheManager.me().getCache( TenantCache.class ).refresh();
    }
}
