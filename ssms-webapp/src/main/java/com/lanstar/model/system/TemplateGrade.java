/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateGrade.java
 * 创建时间：下午4:27:00
 * 创建用户：苏志亮
 */
package com.lanstar.model.system;

import java.util.List;

import com.lanstar.plugin.activerecord.Model;

/**
 * @author Administrator
 *
 */
public class TemplateGrade extends Model<TemplateGrade> {
    public static final TemplateGrade dao=new TemplateGrade(); 
    
    /**
     * 根据专业取评分标准模板
    */
    public List<TemplateGrade> getTemplateByPro(int pro){
         return this.dao.find( " SELECT T1.* FROM SYS_GRADE_STD T1  INNER JOIN SYS_TEMPLATE T2 ON T1.R_SID=T2.SID  INNER JOIN SYS_PROFESSION T3 ON T3.R_TEMPLATE=T2.SID  WHERE T3.SID=?",pro );
    }

}
