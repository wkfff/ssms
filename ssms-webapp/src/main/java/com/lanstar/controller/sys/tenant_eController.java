/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：tenant_eController.java
 * 创建时间：2015年4月13日 下午5:36:30
 * 创建用户：林峰
 */
package com.lanstar.controller.sys;

import com.google.common.base.Splitter;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.service.enterprise.EnterpriseProfessionService;
import com.lanstar.service.enterprise.EnterpriseTenantService;

import java.util.ArrayList;

/**
 * 企业租户表
 */
public class tenant_eController extends DefaultController {
    public tenant_eController() {
        super( "SYS_TENANT_E" );
    }

    @Override
    public ViewAndModel index( HandlerContext context ) {
        JdbcRecordSet records = context.DB.withTable( "SYS_PARA_AREA" ).orderby( "N_LEVEL, C_CODE" ).queryList();
        return super.index( context )
                    .put( "_area_", EasyUIControllerHelper.toTree( records, "SID", "R_PARENT", "C_VALUE" ) );
    }

    @Override
    public ViewAndModel rec( HandlerContext context ) {
        resolveMultiParameter( context, "SYS_PROFESSION" );
        return super.rec( context );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return tenant_eValidator.class;
    }

    public void reg( HandlerContext context ) {
    }

    @Override
    public ViewAndModel save( HandlerContext context ) {
        // 先验证下参数
        this.validatePara( context );
        String sid = context.getValue( "sid" );
        ARTable table = context.DB.withTable( this.TABLENAME );
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        table.getValues().remove( "industryValue" );
        table.getValues().remove( "professionValues" );
        EnterpriseTenantService service = context.getEnterpriseTenantService();
        String tenantCode = context.getValue( "C_CODE" );
        // 根据sid的存在设置where语句
        if ( StringHelper.isBlank( sid ) || !StringHelper.vaildValue( sid ) ) {
            // 生成租户特征码
            // TODO: 这里要获取的是县级编码
            tenantCode = service.buildSignature( (String) context.getValue( "S_COUNTY" ) );
            table.value( "C_CODE", tenantCode );
            // 保存数据
            table.insert();
            sid = Integer.toString( context.DB.getSID() );
        } else {
            table.where( "SID=?", sid ).update();
        }

        // 获取企业专业服务，进行企业专业管理。
        EnterpriseProfessionService professionService = service.getProfessionService( tenantCode );
        String professionValues = context.getValue( "professionValues" );
        ArrayList<Integer> professionList = new ArrayList<>();
        for ( String professionId : Splitter.on( ',' ).trimResults().omitEmptyStrings()
                                            .splitToList( professionValues ) ) {
            professionList.add( Integer.valueOf( professionId ) );
        }
        professionService.setProfession( professionList );

        return context.returnWith().put( "SID", sid );
    }
}
