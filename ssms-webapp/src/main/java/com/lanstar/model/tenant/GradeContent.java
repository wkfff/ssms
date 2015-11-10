/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeContent.java
 * 创建时间：2015年5月25日 下午7:10:26
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import com.lanstar.model.TenantModel;

/**
 * 评分内容
 *
 */
public class GradeContent extends TenantModel<GradeContent> {
    public static GradeContent dao = new GradeContent();
    public String getProjiec(){
        return this.getStr( "C_PROJECT" );
    }
    
//    private int R_SID;
//    private int R_STD;
//    private String C_CATEGORY;
//    private String C_PROJECT;
//    private String C_REQUEST;
//    private String C_CONTENT;
//    private int N_SCORE;
//    private String C_METHOD;
//    private String C_DESC;
//    private boolean B_BLANK;
//    private int N_SCORE_REAL;
    
}
