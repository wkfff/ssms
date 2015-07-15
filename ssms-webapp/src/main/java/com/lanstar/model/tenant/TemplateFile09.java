/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile09.java
 * 创建时间：2015年6月8日 上午10:45:20
 * 创建用户：林峰
 */
package com.lanstar.model.tenant;

import java.util.Date;

public class TemplateFile09 extends TemplateFileModel<TemplateFile09> {
    public static final TemplateFile09 dao = new TemplateFile09();

    /**
     * @return
     */
    public Date getTextnext() {
        // TODO Auto-generated method stub
        return getDate( "T_TEST_NEXT" );
    }
}
