/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile08.java
 * 创建时间：2015年6月8日 上午10:45:14
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import java.util.Date;

public class TemplateFile08 extends TemplateFileModel<TemplateFile08> {
    public static final TemplateFile08 dao = new TemplateFile08();

    public Date getTestNext() {
        return getDate( "T_TEST_NEXT" );
    }

    public void setTestNext( Date next ) {
        set( "T_TEST_NEXT", next );
    }

    public void setTestLast( Date last ) {
        set( "T_TEST_LAST", last );
    }
}
