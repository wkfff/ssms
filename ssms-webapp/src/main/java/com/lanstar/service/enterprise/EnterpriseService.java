/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseService.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.common.Asserts;
import com.lanstar.controller.enterprise.EnterpriseGradeState;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityException;
import com.lanstar.identity.TenantContext;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.TemplateGrade;
import com.lanstar.model.system.TemplateRep;
import com.lanstar.model.tenant.GradeContent;
import com.lanstar.model.tenant.GradePlan;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.service.AttachTextService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnterpriseService {
    private final TenantContext tenantContext;
    private Map<Class<?>, Object> valueMap = new ConcurrentHashMap<>();

    public EnterpriseService( TenantContext tenantContext ) {
        this.tenantContext = tenantContext;
    }

    /**
     * 获取企业支持的专业列表
     */
    public List<Profession> getProfessions() {
        TenantType tenantType = tenantContext.getTenantType();
        if ( tenantType != TenantType.ENTERPRISE )
            throw new IdentityException( "tenant type must be 'ENTERPRISE', but current is " + tenantType + "." );
        final List<Profession> professions = Profession.list( tenantContext.getTenantId() );
        Asserts.notEmpty( professions, "发现异常的企业【%s - %s】，该企业未分配专业！", tenantContext.getTenantCode(), tenantContext.getTenantName() );
        return professions;
    }

    /**
     * 获取当前专业的服务
     */
    public ProfessionService getProfessionService() {
        return getValue( ProfessionService.class );
    }

    /**
     * 确定当前企业的专业服务是否已经初始化
     */
    public boolean professionServiceInitialized() {
        return getProfessionService() != null;
    }

    /**
     * 初始化企业的专业服务
     */
    public void initProfessionService( Profession profession ) {
        setValue( new ProfessionService( profession, tenantContext ) );
    }

    /** 将指定值与上下文绑定 */
    void setValue( Object value ) {
        if ( value == null ) return;
        valueMap.put( value.getClass(), value );
    }

    /** 根据值的类型从上下文中取出值 */
    @SuppressWarnings("unchecked")
    <T> T getValue( Class<T> clazz ) {
        return (T) valueMap.get( clazz );
    }

    /**
     * 获取最新自评方案的编号
     */
    public int getPlanId( int eid, int pro ) {
        String sql = "select sid from SSM_GRADE_PLAN where R_TENANT=? and P_PROFESSION=? and n_state=? order by t_update desc limit 1";
        GradePlan model = GradePlan.dao.findFirst( sql, eid, pro, EnterpriseGradeState.END.getValue() );
        return model == null ? -1 : model.getId();
    }

    /**
     * 本年度是否已经有自评方案
     */
    public boolean hasPlan() {
        String sql = "SELECT 1 FROM SSM_GRADE_PLAN WHERE R_TENANT=? AND P_TENANT='E' AND YEAR(T_START)=YEAR(NOW())";
        Record r = tenantContext.getTenantDb().findFirst( sql, tenantContext.getTenantId() );
        return r != null;
    }

    /**
     * 根据专业从评分标准生成评分内容
     */
    public boolean syncContent( int pro, int planId ) {
        List<TemplateGrade> list = TemplateGrade.dao.getTemplateByPro( pro );
        for ( TemplateGrade gt : list ) {
            GradeContent gc = new GradeContent();
            ModelKit.copyColumns( gt, gc, "C_CATEGORY", "C_PROJECT", "C_REQUEST", "C_CONTENT", "N_SCORE", "C_METHOD" );
            gc.set( "R_SID", planId );
            gc.set( "R_STD", gt.getInt( "SID" ) );
            gc.set( "R_TENANT", tenantContext.getTenantId() );
            gc.set( "S_TENANT", tenantContext.getTenantName() );
            gc.set( "P_TENANT", tenantContext.getTenantType().getName() );
            gc.save();
        }
        return true;
    }

    /**
     * 根据专业从自评报告模板生成自评报告
     */
    public boolean syncReport( int pro, int planId, Identity identity ) {
        String sql = " SELECT C_CONTENT FROM SYS_REPORT_TEMPLATE T1 INNER JOIN SYS_TEMPLATE T2  ON T1.R_SID = T2.SID INNER JOIN SYS_PROFESSION T3  ON T3.R_TEMPLATE = T2.SID WHERE T3.SID = ? AND T1.Z_TYPE=1";
        TemplateRep template = TemplateRep.dao.findFirst( sql, pro );
        if ( template != null ) {
            AttachTextService service = tenantContext.getAttachTextService();
            service.save( "SSM_GRADE_REPORT", "C_CONTENT", planId, template.getStr( "C_CONTENT" ), identity );
        }
        return true;
    }

    public UniqueTag getUniqueTag() {
        UniqueTag tag = new UniqueTag();
        tag.tenantId = tenantContext.getTenantId();
        ProfessionService service = getProfessionService();
        tag.professionId = service.getId();
        tag.templateId = service.getSystemTemplate().getId();
        return tag;
    }
}
