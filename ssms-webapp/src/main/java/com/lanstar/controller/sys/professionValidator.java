/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：professionValidator.java
 * 创建时间：2015-04-27
 * 创建用户：张铮彬
 */

package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecord;

public class professionValidator extends ActionValidator {
    @Override
    protected void validate( HandlerContext c ) {
        JdbcRecord record = c.DB.withTable( "SYS_PARA_MULTI" )
                                .where( "C_NAME=? and C_CODE=?", "SYS_PROFESSION", c.getValue( "C_CODE" ) )
                                .query();
        if ( record != null ) {
            addError( "01", "编码不能重复!" );
        }
    }
}
