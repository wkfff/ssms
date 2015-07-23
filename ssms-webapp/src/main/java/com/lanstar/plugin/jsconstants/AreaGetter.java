/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AreaGetter.java
 * 创建时间：2015-07-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.jsconstants;

import com.alibaba.fastjson.annotation.JSONField;
import com.lanstar.model.kit.TreeBuilder;
import com.lanstar.model.system.AreaPara;

import java.util.ArrayList;
import java.util.List;

public class AreaGetter implements ParametersGetter<List<AreaGetter.AreaParameter>> {
    @Override
    public List<AreaParameter> getParameters() {
        List<AreaPara> fj = AreaPara.dao.findAll();
        AreaParameter tree = new AreaTreeBuilder( fj ).build();
        return tree.children;
    }

    @Override
    public String getName() {
        return "$areas";
    }

    class AreaParameter {
        @JSONField(ordinal = 0)
        String name;
        @JSONField(ordinal = 1)
        String code;
        @JSONField(ordinal = 2)
        List<AreaParameter> children;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public List<AreaParameter> getChildren() {
            return children;
        }
    }

    class AreaTreeBuilder extends TreeBuilder<AreaParameter, AreaPara> {
        public AreaTreeBuilder( List<AreaPara> list ) {
            super( list, null );
        }

        @Override
        public AreaParameter build() {
            AreaParameter parameter = new AreaParameter();
            parameter.code = "350000";
            parameter.name = "福建";
            addChildren( parameter );
            return parameter;
        }

        private void addChildren( AreaParameter parameter ) {
            parameter.children = new ArrayList<>();
            for ( AreaPara areaPara : children( parameter.code ) ) {
                AreaParameter area = new AreaParameter();
                area.code = areaPara.getCode();
                area.name = areaPara.getName();
                parameter.children.add( area );

                addChildren( area );
            }
        }

        @Override
        public Object getKeyValue( AreaPara item ) {
            return item.getParentCode();
        }
    }
}
