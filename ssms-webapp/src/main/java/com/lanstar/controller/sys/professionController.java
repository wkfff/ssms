/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：professionController.java
 * 创建时间：2015-04-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.controller.TableProcessor;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.ar.ARTable;

public class professionController extends DefaultController {
    public professionController() {
        super( "SYS_PARA_MULTI" );
        // 设置表单保存的默认值
        this.defaultValues.put( "C_NAME", "SYS_PROFESSION" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return professionValidator.class;
    }

    /**
     * 列表数据
     */
    @Override
    public ViewAndModel list( final HandlerContext context ) {
        return super.list( context, new TableProcessor() {
            @Override
            public void process( ARTable arTable ) {
                // 设置默认的WHERE条件
                arTable.where( "C_NAME=?", "SYS_PROFESSION" );
            }
        } );
    }

    /**
     * 表单.保存
     */
    @Override
    public ViewAndModel save( HandlerContext context ) {
        String sid = context.getValue( "sid" );
        String code = context.getValue( "C_CODE" );
        String value = context.getValue( "C_VALUE" );
        ViewAndModel result = super.save( context );
        // 初始化达标创建模板
        if ( StringHelper.isBlank( sid ) ) {
            ARTable table = context.DB.withTable( "SYS_STDTMP_FOLDER" );
            this.mergerValuesWithoutParaMap( table, context, MergerType.withSid( sid ) );
            table.value( "C_NAME", value + "达标体系模板" )
                 .value( "P_PROFESSION", code )
                 .value( "S_PROFESSION", value )
                 .save();
        }
        return result;
    }

    @Override
    public ViewAndModel del( HandlerContext context ) {
        String code = context.getValue( "C_CODE" );
        context.DB.withTable( "SYS_STDTMP_FOLDER" ).where( "P_PROFESSION=?", code ).delete();
        return super.del( context );
    }
}
