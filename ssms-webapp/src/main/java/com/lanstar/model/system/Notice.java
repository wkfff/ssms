/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Notice.java
 * 创建时间：2015年6月5日 下午4:28:46
 * 创建用户：林峰
 */
package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.Model;

/**
 * 通知公告
 *
 */
public class Notice extends Model<Notice>{
    public static Notice dao = new Notice();
    
    private String C_TITLE;
    private String C_CONTENT;
    private String T_BEGIN;
    private String T_END;
    
    private String N_READER;
    private String R_PUBLIC;
    private String S_PUBLIC;
    private String T_PUBLIC;
    
    
    public Integer getId(){
        return this.getInt( "SID");
    }
    
    /**
     * @return the c_TITLE
     */
    public String getC_TITLE() {
        return C_TITLE;
    }
    /**
     * @param c_TITLE the c_TITLE to set
     */
    public void setC_TITLE( String c_TITLE ) {
        C_TITLE = c_TITLE;
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
     * @return the t_BEGIN
     */
    public String getT_BEGIN() {
        return T_BEGIN;
    }
    /**
     * @param t_BEGIN the t_BEGIN to set
     */
    public void setT_BEGIN( String t_BEGIN ) {
        T_BEGIN = t_BEGIN;
    }
    /**
     * @return the t_END
     */
    public String getT_END() {
        return T_END;
    }
    /**
     * @param t_END the t_END to set
     */
    public void setT_END( String t_END ) {
        T_END = t_END;
    }
    /**
     * @return the n_READER
     */
    public String getN_READER() {
        return N_READER;
    }
    /**
     * @param n_READER the n_READER to set
     */
    public void setN_READER( String n_READER ) {
        N_READER = n_READER;
    }
    /**
     * @return the r_PUBLIC
     */
    public String getR_PUBLIC() {
        return R_PUBLIC;
    }
    /**
     * @param r_PUBLIC the r_PUBLIC to set
     */
    public void setR_PUBLIC( String r_PUBLIC ) {
        R_PUBLIC = r_PUBLIC;
    }
    /**
     * @return the s_PUBLIC
     */
    public String getS_PUBLIC() {
        return S_PUBLIC;
    }
    /**
     * @param s_PUBLIC the s_PUBLIC to set
     */
    public void setS_PUBLIC( String s_PUBLIC ) {
        S_PUBLIC = s_PUBLIC;
    }
    /**
     * @return the t_PUBLIC
     */
    public String getT_PUBLIC() {
        return T_PUBLIC;
    }
    /**
     * @param t_PUBLIC the t_PUBLIC to set
     */
    public void setT_PUBLIC( String t_PUBLIC ) {
        T_PUBLIC = t_PUBLIC;
    }
    
    

}
