/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewPlan.java
 * 创建时间：2015年6月17日 下午8:25:03
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import java.util.Date;
import java.util.List;

import com.lanstar.common.kit.DateKit;
import com.lanstar.common.kit.StrKit;
import com.lanstar.identity.Tenant;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.service.AttachTextService;

/**
 * 评审方案
 *
 */
public class ReviewPlan extends Model<ReviewPlan> {
    public static ReviewPlan dao = new ReviewPlan();
    
    /**
     * 获取评审方案对应的评审内容
     */
    public List<ReviewContent> getContents(){
        String sql = "select * from ssm_review_content where r_sid=? order by n_index";
        return ReviewContent.dao.find( sql,getId() );
    }
    
    /**
     * 获取评审方案对应的评审小组成员
     */
    public List<ReviewMember> getMembers(){
        String sql = "select * from ssm_review_members where r_sid=? order by n_index";
        return ReviewMember.dao.find( sql,getId() );
    }
    
    /**
     * 获取评审方案对应的评审证书
     */
    public ReviewCert getCert(){
        String sql = "select * from ssm_review_cert where r_sid=? order by n_index";
        return ReviewCert.dao.findFirst( sql,getId() );
    }
    
    /**
     * 获取评审方案对应的评审报告
     */
    public String getReport(){
        return AttachTextService.SYSTEM.getContent( "SSM_GRADE_R_REPORT", "CONTENT", getId() );
    }
    
    public Integer getId() {
        return getInt( "SID" );
    }

    public String getState() {
        return getStr( "P_STATE" );
    }

    public void setState( String state ) {
        set( "P_STATE", state );
    }

    public int getEnterpriseId() {
        return getInt( "R_ENTERPRISE" );
    }

    public void setEnterpriseId( int enterpriseId ) {
        set( "R_ENTERPRISE", enterpriseId );
    }

    public String getEnterpriseName() {
        return getStr("S_ENTERPRISE");
    }

    public void setEnterpriseName( String enterpriseName ) {
        set("S_ENTERPRISE",enterpriseName);
    }

    public String getEnterpriseAddr() {
        return getStr("C_ENTERPRISE_ADDR");
    }

    public void setEnterpriseAddr( String addr ) {
        set("C_ENTERPRISE_ADDR",addr);
    }
    
    public String getReviewAddr() {
        return getStr("C_REVIEW_ADDR");
    }

    public void setReviewAddr( String addr ) {
        set("C_REVIEW_ADDR",addr);
    }

    public String getStartDate() {
        return DateKit.toStr( getTimestamp( "T_START" ) );
    }

    public void setStartDate( Date date) {
        set( "T_START", DateKit.toStr( date ) );
    }

    public String getEndDate() {
        return DateKit.toStr( getTimestamp( "T_END" ) );
    }

    public void setEndDate( Date date ) {
        set( "T_END", date );
    }

    public int getLeaderId() {
        return getInt("R_LEADER");
    }

    public void setLeaderId( int leaderId ) {
        set( "R_LEADER", leaderId );
    }

    public String getLeaderName() {
        return getStr("S_LEADER");
    }

    public void setLeaderName( String leaderName ) {
        set("S_LEADER",leaderName);
    }


    public int getRsid() {
        return getInt("R_SID");
    }

    public void setRsid( int rsid ) {
        set("R_SID",rsid);
    }
    
    public void setTenant(Tenant tenant){
        set("R_TENANT",tenant.getTenantId());
        set("S_TENANT",tenant.getTenantName());
        set("P_TENANT",tenant.getTenantType().getName());
    }
    
    public void setTenantId(int tenantId){
        set("R_TENANT",tenantId);
    }
    
    public void setTenantName(String tenantName){
        set("S_TENANT",tenantName);
    }
    
    public void setTenantType(String tenantType){
        set("P_TENANT",tenantType);
    }
    
    public int getTenantId() {
        return getInt("R_TENANT");
    }

    public String getTenantName() {
        return getStr("S_TENANT");
    }
    
    public String getContact(){
        return getStr("C_CONTACT");
    }
    
    public void setContact(String contact){
        set("C_CONTACT",contact);
    }
    
    /**
     * 设置评审状态
     * @param id
     * @param type
     * @param state
     */
    private void setState(int id,int type,String state){
        ReviewPlan model = ReviewPlan.dao.findById( id );
        if (model==null) return;
        String s = model.getState();
        if (StrKit.isBlank( s ) || s.length()!=3)  s = "000";
        String[] items = new String[]{s.substring( 0, 1 ),s.substring( 1, 2 ),s.substring( 2, 3 )};
        items[type] = state;
        model.setState( items.toString() );
        model.save();
    }
    
    /**
     * 完成评审内容
     */
    public void setContentComplete(int id){
        setState(id,0,"1");
    }
    /**
     * 完成评审报告
     */
    public void setStateofReport(int id){
        setState(id,1,"1");
    }
    /**
     * 完成评审证书
     */
    public void setStateofCert(int id){
        setState(id,2,"1");
    }
    
    /**
     * 获取评审内容完成状态：0未完成、1已完成
     */
    public String getStateofContent(){
        String state = getState();
        if (StrKit.isBlank( state )) return "0";
        return state.substring( 0, 1 );
        
    }
    /**
     * 获取报告完成状态：0未完成、1已完成
     */
    public String getStateofReport(){
        String state = getState();
        if (StrKit.isBlank( state ) || state.length()<2) return "0";
        return state.substring( 1, 2 );
        
    }
    /**
     * 获取证书完成状态：0未完成、1已完成 
     */
    public String getStateofCert(){
        String state = getState();;
        if (StrKit.isBlank( state )|| state.length()<3) return "0";
        return state.substring( 2, 3 );
        
    }
    
    public void setCount(int value){
        set("N_COUNT",value);
    }
}
