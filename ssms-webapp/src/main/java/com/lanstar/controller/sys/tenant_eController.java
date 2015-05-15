/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：tenant_eController.java
 * 创建时间：2015年4月13日 下午5:36:30
 * 创建用户：林峰
 */
package com.lanstar.controller.sys;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.controller.TableProcessor;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.DBPaging;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.ar.ARTable;
import com.lanstar.db.dialect.JdbcPageRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;
import com.lanstar.service.enterprise.EnterpriseProfessionService;
import com.lanstar.service.enterprise.EnterpriseTenantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 企业租户表
 */
public class tenant_eController extends DefaultController {
    public tenant_eController() {
        super( "SYS_TENANT_E" );
    }

    @Override
    public ViewAndModel index( HandlerContext context ) {
        return super.index( context );
    }

    @Override
    public ViewAndModel rec( HandlerContext context ) {
        JdbcRecordSet records = context.SYSTEM_DB.withTable( "SYS_TENANT_E_PROFESSION" )
                                                 .where( "R_TENANT=?", context.getValue( "sid" ) )
                                                 .queryList();
        List<Object> professionValues = new ArrayList<>();
        for ( JdbcRecord record : records ) {
            professionValues.add( record.get( "P_PROFESSION" ) );
        }
        return super.rec( context ).put( "professionValues", professionValues );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return tenant_eValidator.class;
    }

    public void reg( HandlerContext context ) {
    }

    /**
     * 表单.假删除
     */
    @Override
    public ViewAndModel del( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        if ( !Strings.isNullOrEmpty( sid ) ) {
            //更新数据字段B_DELETE的值
            context.DB.withTable( this.TABLENAME ).value( "B_DELETE", 1 ).where( "SID = ?", sid ).update();
        }
        return context.returnWith().set( "" );
    }

    /**
     * 列表数据
     */
    public ViewAndModel list( HandlerContext context ) {
        return this.list( context, null );
    }

    protected ViewAndModel list( HandlerContext context,
            TableProcessor processor ) {
        ARTable arTable = context.DB.withTable( this.TABLENAME ).where( "IFNULL(B_DELETE,'0')<>?", 1 );
        Map<String, String> filter = this.getFilter( context );
        if ( !filter.isEmpty() ) {
            arTable.where(
                    StringHelper.join( filter.keySet(), " and ", false ),
                    filter.values().toArray() );
        }
        if ( processor != null ) processor.process( arTable );
        DBPaging paging = context.getPaging();
        if ( paging == null ) {
            JdbcRecordSet list = arTable.queryList();
            return context.returnWith().set( list );
        } else {
            JdbcPageRecordSet list = arTable.queryPaging( paging );
            return context.returnWith().set(
                    EasyUIControllerHelper.toDatagridResult( list ) );
        }
    }

    @Override
    public ViewAndModel save( HandlerContext context ) {
        // 先验证下参数
        this.validatePara( context );
        String sid = context.getValue( "sid" );
        ARTable table = context.DB.withTable( this.TABLENAME );
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        table.getValues().remove( "professionValues" );
        EnterpriseTenantService service = context.getEnterpriseTenantService();
        String tenantCode = context.getValue( "C_CODE" );
        // 根据sid的存在设置where语句
        if ( StringHelper.isBlank( sid ) || !StringHelper.vaildValue( sid ) ) {
            // 生成租户特征码
            tenantCode = service.buildSignature( (String) context.getValue( "P_COUNTY" ) );
            table.value( "C_CODE", tenantCode );
            // 保存数据
            table.insert();
            sid = Integer.toString( context.DB.getSID() );

            // 创建企业租户的时候同时创建一个admin用户,  默认密码为123456。
            // TODO: 创建用户的时候使用随机密码
            context.DB.withTable( "SYS_TENANT_E_USER" )
                      .value( "C_NAME", "管理员" )
                      .value( "C_USER", "admin" )
                      .value( "R_SID", sid )
                      .value( "S_NAME", context.getValue( "C_NAME" ) )
                      .value( "C_PASSWD", StringHelper.toMD5( "123456" ) )
                      .insert();
        } else {
            table.where( "SID=?", sid ).update();
        }

        // 获取企业专业服务，进行企业专业管理。
        EnterpriseProfessionService professionService = service.getProfessionService( tenantCode );
        String professionValues = context.getValue( "professionValues" );
        ArrayList<Integer> professionList = new ArrayList<>();
        for ( String professionId : Splitter.on( ',' ).trimResults().omitEmptyStrings()
                                            .split( professionValues ) ) {
            professionList.add( Integer.valueOf( professionId ) );
        }
        professionService.setProfession( Ints.toArray( professionList ) );

        return context.returnWith().put( "SID", sid );
    }
}
