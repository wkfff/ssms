/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ProfessionParas.java
 * 创建时间：2015-06-12
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.jsconstants;

import com.google.common.collect.Lists;
import com.lanstar.model.system.Industry;
import com.lanstar.model.system.Profession;

import java.util.List;

public class ProfessionParas {
    public static Object getProfessions() {
        List<Industry> list = Industry.dao.find( "select * from sys_industry order by N_INDEX, SID" );
        return Lists.transform( list, new Converter() );
    }

    private static class Converter implements com.google.common.base.Function<Industry, Para> {
        @Override
        public Para apply( Industry input ) {
            Para para = new Para();
            para.code = input.getId();
            para.name = input.getName();
            List<Profession> professions = input.listProfessions();
            if ( professions.size() > 0 ) {
                para.children = Lists.transform( professions, new Converter2() );
            }
            return para;
        }
    }

    private static class Para {
        public Integer code;
        public String name;
        public List<Para> children;
    }

    public static class Converter2 implements com.google.common.base.Function<Profession, Para> {
        @Override
        public Para apply( Profession input ) {
            Para para = new Para();
            para.code = input.getId();
            para.name = input.getName();
            return para;
        }
    }
}
