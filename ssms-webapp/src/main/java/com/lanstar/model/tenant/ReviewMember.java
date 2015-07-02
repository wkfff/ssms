/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewMember.java
 * 创建时间：2015年6月18日 上午10:02:21
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import com.lanstar.plugin.activerecord.Model;

/**
 * 评审小组成员
 *
 */
public class ReviewMember extends Model<ReviewMember> {
    public static ReviewMember dao = new ReviewMember();
    private int R_MEMBER;
    private String S_MEMBER;
    private String C_JOB;
    private String C_TEL;
    private String C_REMARKS;
    
    
    /**
     * @return the r_MEMBER
     */
    public int getR_MEMBER() {
//        return R_MEMBER;
        return getInt("R_MEMBER");
    }

    /**
     * @param r_MEMBER the r_MEMBER to set
     */
    public void setR_MEMBER( int r_MEMBER ) {
//        R_MEMBER = r_MEMBER;
        set( "R_MEMBER", r_MEMBER );
    }

    /**
     * @return the s_MEMBER
     */
    public String getS_MEMBER() {
//        return S_MEMBER;
        return getStr("S_MEMBER");
    }

    /**
     * @param s_MEMBER the s_MEMBER to set
     */
    public void setS_MEMBER( String s_MEMBER ) {
        S_MEMBER = s_MEMBER;
        set("S_MEMBER",s_MEMBER);
    }

    
    
    /**
     * @return the c_JOB
     */
    public String getC_JOB() {
//        return C_JOB;
        return getStr("C_JOB");
    }

    /**
     * @param c_JOB the c_JOB to set
     */
    public void setC_JOB( String c_JOB ) {
        C_JOB = c_JOB;
        set("C_JOB",c_JOB);
    }

    /**
     * @return the c_TEL
     */
    public String getC_TEL() {
//        return C_TEL;
        return getStr("C_TEL");
    }

    /**
     * @param c_TEL the c_TEL to set
     */
    public void setC_TEL( String c_TEL ) {
        C_TEL = c_TEL;
        set("C_TEL",c_TEL);
    }

    /**
     * @return the c_REMARKS
     */
    public String getC_REMARKS() {
//        return C_REMARKS;
        return getStr("C_REMARKS");
    }

    /**
     * @param c_REMARKS the c_REMARKS to set
     */
    public void setC_REMARKS( String c_REMARKS ) {
        C_REMARKS = c_REMARKS;
        set( "C_REMARKS", c_REMARKS );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    /**
     * @return the rsid
     */
    public int getRsid() {
        return getInt( "R_SID" );
    }

    /**
     * @param rsid the rsid to set
     */
    public void setRsid( int rsid ) {
        set( "R_SID", rsid );
    }

}
