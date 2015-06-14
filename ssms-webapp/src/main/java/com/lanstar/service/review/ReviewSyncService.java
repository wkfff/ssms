/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewSyncService.java
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

package com.lanstar.service.review;

import java.util.List;

import com.lanstar.identity.TenantContext;
import com.lanstar.model.tenant.GradeContent;
import com.lanstar.model.tenant.GradeContentR;
import com.lanstar.plugin.activerecord.ModelKit;

class ReviewSyncService {
    private final TenantContext source;
    private final TenantContext target;

    private ReviewSyncService( TenantContext source, TenantContext target ) {
        this.source = source;
        this.target = target;
    }

    public static boolean sync( TenantContext source, TenantContext target, int r_sid, int sid ) {
        return new ReviewSyncService( source, target ).execute( r_sid, sid );
    }

    /**
     * 同步企业的自评数据到评审中
     * 
     * @param r_sid
     *            企业自评主表的编号
     * @param sid
     *            评审主表的编号
     * @return
     */
    private boolean execute( int r_sid, int sid ) {
        try {
            String sql = "select * from ssm_grade_e_d where r_sid=?";
            // List<Record> list = source.getTenantDb().find( sql
            // ,GradeContent.class,r_sid);
            // TODO:用这种方式取的库是否正确，还是要通过上面的方法去取？
            List<GradeContent> list = GradeContent.dao.find( sql, r_sid );

            for ( GradeContent gc : list ) {
                GradeContentR gcr = new GradeContentR();
                ModelKit.copyColumns( gc, gcr, "R_SID", "C_CATEGORY", "C_PROJECT", "C_REQUEST", "C_CONTENT", "N_SCORE",
                        "C_METHOD", "C_DESC", "B_BLANK", "N_SCORE_REAL" );
//                gcr.setR_SID( sid );
                gcr.set( "R_SID", sid );
                gcr.save();
            }
            return true;
        } catch ( Exception e ) {

        }
        return false;
    }
}
