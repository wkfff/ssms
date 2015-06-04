/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseController.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.Enterprise;
import com.lanstar.model.system.EnterpriseProfession;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.service.enterprise.EnterpriseProfessionService;
import com.lanstar.service.TenantService;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseController extends SimplateController<Enterprise> {
    @Override
    public void rec() {
        Enterprise model = getModel();
        List<EnterpriseProfession> professions = model.listProfession();

        List<Object> professionValues = new ArrayList<>();
        for ( EnterpriseProfession record : professions ) {
            professionValues.add( record.getProfessionId() );
        }
        setAttr( "professionValues", professionValues );
        super.rec();
    }

    public void reg() {

    }

    @Override
    protected Enterprise getDao() {
        return Enterprise.dao;
    }

    @Override
    protected SqlBuilder buildWhere() {
        return new SqlBuilder()
                .WHERE( "IFNULL(B_DELETE,'0')<>?", "1" )
                ._If( isParaBlank( "C_NAME" ) == false, "C_NAME=?", getPara( "C_NAME" ) );
    }

    @Override
    protected void beforeSave( Enterprise model, boolean[] handled ) {
        Integer sid = model.getInt( "SID" );
        TenantService service = TenantService.me();
        if ( sid == null ) {
            // 生成租户特征码
            String tenantCode = service.buildSignature( TenantType.ENTERPRISE, model.getCountyCode() );
            model.setTenantCode( tenantCode );
            model.save();
            // 添加管理员用户
            service.addAdminUser( model );
            handled[0] = true;
        }
    }

    @Override
    protected void afterSave( Enterprise model ) {
        // 获取企业专业服务，进行企业专业管理。
        EnterpriseProfessionService service = EnterpriseProfessionService.forEnterprise( model );
        String professionValues = getPara( "professionValues" );
        ArrayList<Integer> professionList = new ArrayList<>();
        for ( String professionId : Splitter.on( ',' ).trimResults().omitEmptyStrings()
                                            .split( professionValues ) ) {
            professionList.add( Integer.valueOf( professionId ) );
        }
        service.setProfession( Ints.toArray( professionList ) );
    }
}
