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
 * 评审结果方案.小组成员
 *
 */
public class ReviewResultMember extends Model<ReviewResultMember> {
    public static ReviewResultMember dao = new ReviewResultMember();

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

    /**
     * @return the memberId
     */
    public int getMemberId() {
        return getInt( "R_MEMBER" );
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId( int memberId ) {
        set( "R_MEMBER", memberId );
    }

    /**
     * @return the memberName
     */
    public String getMemberName() {
        return getStr( "S_MEMBER" );
    }

    /**
     * @param memberName the memberName to set
     */
    public void setMemberName( String memberName ) {
        set( "S_MEMBER", memberName );
    }

    /**
     * @return the job
     */
    public String getJob() {
        return getStr( "C_JOB" );
    }

    /**
     * @param job the job to set
     */
    public void setJob( String job ) {
        set( "C_JOB", job );
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return getStr( "C_TEL" );
    }

    /**
     * @param tel the tel to set
     */
    public void setTel( String tel ) {
        set( "C_TEL", tel );
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return getStr( "C_REMARKS" );
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks( String remarks ) {
        set( "C_REMARKS", remarks );
    }

}
