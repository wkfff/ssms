/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：MultiParaType.java
 * 创建时间：2015-06-27
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lanstar.model.system.MultiPara;

import java.util.List;

public enum MultiParaType {
    SYS_CYCLE;

    private static final Function<MultiPara, Parameter> MODEL_TO_PARA = new Function<MultiPara, Parameter>() {
        @Override
        public Parameter apply( MultiPara input ) {
            return new Parameter( input.getCode(), input.geValue() );
        }
    };

    public List<Parameter> parameters() {
        return Lists.newArrayList( Lists.transform( list(), MODEL_TO_PARA ) );
    }

    protected List<MultiPara> list() {
        return MultiPara.dao.findByColumn( "C_NAME", name().toUpperCase() );
    }
}
