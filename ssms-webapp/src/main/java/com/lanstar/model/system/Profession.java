/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Profession.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.model.system;

import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.List;

public class Profession extends Model<Profession> {
    public static final Profession dao = new Profession();

    public static List<Profession> list( int tenantId ) {
        return dao.find( SqlKit.sql( "system.profession.list" ), tenantId );
    }

    public String getName() {
        return getStr( "C_NAME" );
    }

    public void setName( String name ) {
        set( "C_NAME", name );
    }

    public Integer getId() {
        return getInt( "SID" );
    }

    public void setIndustryId( int industryId ) {
        set( "R_INDUSTRY", industryId );
    }

    public void setTemplateId( Integer templateId ) {
        set( "R_TEMPLATE", templateId );
    }
}
