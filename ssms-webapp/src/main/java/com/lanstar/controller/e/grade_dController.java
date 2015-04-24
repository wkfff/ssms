/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_dController.java
 * 创建时间：2015年4月22日 下午3:03:28
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import java.util.Map;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.controller.BaseController.MergerType;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.ar.ARTable;

/**
 * 在线自评
 *
 */
public class grade_dController extends DefaultController {
    public grade_dController() {
        super( "SSM_GRADE_E_D" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_dValidator.class;
    }
    
    public ViewAndModel gridsave(HandlerContext context){
        ARTable table = context.DB.withTable( this.TABLENAME );
        Map<String,String> map = context.getParameterMap();
        for(String key:map.keySet()){
            
        }
        /*
        this.mergerValues( table, context, MergerType.withSid( sid ) );
        // 根据sid的存在设置where语句
        // TODO：一律过滤"null"值？
        table.where( !StringHelper.isBlank( sid ) && !sid.equals( "null" ),
                "SID=?", sid ).save();

        if ( StringHelper.isBlank( sid ) || sid.equals( "null" ) ) {
            sid = Integer.toString( context.DB.getSID() );
        }
        */
        return context.returnWith();
    }
}
