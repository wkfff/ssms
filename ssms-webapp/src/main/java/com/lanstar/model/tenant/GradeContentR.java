/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeContentR.java
 * 创建时间：2015年5月27日 下午12:04:24
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import com.lanstar.plugin.activerecord.Model;

/**
 * 评审
 *
 */
public class GradeContentR extends Model<GradeContentR> {
public static GradeContentR dao = new GradeContentR();
    
    private int R_SID;
    private int R_STD;
    private String C_CATEGORY;
    private String C_PROJECT;
    private String C_REQUEST;
    private String C_CONTENT;
    private int N_SCORE;
    private String C_METHOD;
    private String C_DESC;
    private boolean B_BLANK;
    private int N_SCORE_REAL;
    /**
     * @return the r_SID
     */
    public int getR_SID() {
        return R_SID;
    }
    /**
     * @param r_SID the r_SID to set
     */
    public void setR_SID( int r_SID ) {
        R_SID = r_SID;
    }
    /**
     * @return the r_STD
     */
    public int getR_STD() {
        return R_STD;
    }
    /**
     * @param r_STD the r_STD to set
     */
    public void setR_STD( int r_STD ) {
        R_STD = r_STD;
    }
    /**
     * @return the c_CATEGORY
     */
    public String getC_CATEGORY() {
        return C_CATEGORY;
    }
    /**
     * @param c_CATEGORY the c_CATEGORY to set
     */
    public void setC_CATEGORY( String c_CATEGORY ) {
        C_CATEGORY = c_CATEGORY;
    }
    /**
     * @return the c_PROJECT
     */
    public String getC_PROJECT() {
        return C_PROJECT;
    }
    /**
     * @param c_PROJECT the c_PROJECT to set
     */
    public void setC_PROJECT( String c_PROJECT ) {
        C_PROJECT = c_PROJECT;
    }
    /**
     * @return the c_REQUEST
     */
    public String getC_REQUEST() {
        return C_REQUEST;
    }
    /**
     * @param c_REQUEST the c_REQUEST to set
     */
    public void setC_REQUEST( String c_REQUEST ) {
        C_REQUEST = c_REQUEST;
    }
    /**
     * @return the c_CONTENT
     */
    public String getC_CONTENT() {
        return C_CONTENT;
    }
    /**
     * @param c_CONTENT the c_CONTENT to set
     */
    public void setC_CONTENT( String c_CONTENT ) {
        C_CONTENT = c_CONTENT;
    }
    /**
     * @return the n_SCORE
     */
    public int getN_SCORE() {
        return N_SCORE;
    }
    /**
     * @param n_SCORE the n_SCORE to set
     */
    public void setN_SCORE( int n_SCORE ) {
        N_SCORE = n_SCORE;
    }
    /**
     * @return the c_METHOD
     */
    public String getC_METHOD() {
        return C_METHOD;
    }
    /**
     * @param c_METHOD the c_METHOD to set
     */
    public void setC_METHOD( String c_METHOD ) {
        C_METHOD = c_METHOD;
    }
    /**
     * @return the c_DESC
     */
    public String getC_DESC() {
        return C_DESC;
    }
    /**
     * @param c_DESC the c_DESC to set
     */
    public void setC_DESC( String c_DESC ) {
        C_DESC = c_DESC;
    }
    /**
     * @return the b_BLANK
     */
    public boolean isB_BLANK() {
        return B_BLANK;
    }
    /**
     * @param b_BLANK the b_BLANK to set
     */
    public void setB_BLANK( boolean b_BLANK ) {
        B_BLANK = b_BLANK;
    }
    /**
     * @return the n_SCORE_REAL
     */
    public int getN_SCORE_REAL() {
        return N_SCORE_REAL;
    }
    /**
     * @param n_SCORE_REAL the n_SCORE_REAL to set
     */
    public void setN_SCORE_REAL( int n_SCORE_REAL ) {
        N_SCORE_REAL = n_SCORE_REAL;
    }
}
