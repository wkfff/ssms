/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFile06.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

import java.util.Date;

public class TemplateFile06 extends TemplateFileModel<TemplateFile06> {
    public static final TemplateFile06 dao = new TemplateFile06();

    public Date getAcceptance() {
        return getDate( "T_ACCEPTANCE" );
    }

    public Date getRectification() {
        return getDate( "T_RECTIFICATION" );
    }

    public boolean isFinish() {
        String finish = getStr( "B_FINISH" );
        return finish.equals( "1" );
    }
}
