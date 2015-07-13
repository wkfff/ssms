/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AreaPara.java
 * 创建时间：2015-05-25
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.ModelExt;
import com.lanstar.plugin.activerecord.Parent;

import java.util.List;

@Parent(model = AreaPara.class, foreignKey = "R_PARENT")
public class AreaPara extends ModelExt<AreaPara> {
    public static final AreaPara dao = new AreaPara();

    public List<AreaPara> children() {
        return find( "select * from sys_para_area where R_PARENT=? order by N_INDEX,C_CODE", getId() );
    }

    public Object getParentCode() {
        return getStr( "R_CODE" );
    }

    public String getCode() {
        return getStr( "C_CODE" );
    }

    public String getName() {
        return getStr( "C_VALUE" );
    }

    private Integer getId() {
        return getInt( "SID" );
    }
}
