/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ProfessionGetter.java
 * 创建时间：2015-07-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.jsconstants;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lanstar.model.system.Industry;
import com.lanstar.model.system.Profession;

import java.util.List;

public class ProfessionGetter implements ParametersGetter<List<ProfessionGetter.ProfessionParameter>> {
    @Override
    public List<ProfessionParameter> getParameters() {
        List<Industry> list = Industry.dao.find( "select * from sys_industry order by N_INDEX, SID" );
        return Lists.transform( list, industryConvert );
    }

    @Override
    public String getName() {
        return "$professions";
    }

    private Function<Industry, ProfessionParameter> industryConvert = new Function<Industry, ProfessionParameter>() {
        @Override
        public ProfessionParameter apply( Industry input ) {
            ProfessionParameter para = new ProfessionParameter();
            para.code = input.getId();
            para.name = input.getName();
            List<Profession> professions = input.listProfessions();
            if ( professions.size() > 0 ) {
                para.children = Lists.transform( professions, professionConvert );
            }
            return para;
        }
    };

    private Function<Profession, ProfessionParameter> professionConvert = new Function<Profession, ProfessionParameter>() {
        @Override
        public ProfessionParameter apply( Profession input ) {
            ProfessionParameter para = new ProfessionParameter();
            para.code = input.getId();
            para.name = input.getName();
            return para;
        }
    };

    class ProfessionParameter {
        @JSONField(ordinal = 0)
        Integer code;
        @JSONField(ordinal = 1)
        String name;
        @JSONField(ordinal = 2)
        List<ProfessionParameter> children;

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public List<ProfessionParameter> getChildren() {
            return children;
        }
    }
}
