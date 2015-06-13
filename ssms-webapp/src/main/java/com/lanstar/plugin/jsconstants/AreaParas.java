/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AreaParas.java
 * 创建时间：2015-06-12
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.jsconstants;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lanstar.model.system.AreaPara;

import java.util.List;

public class AreaParas {
    public static List<AreaInfo> getAreas() {
        AreaPara fj = AreaPara.dao.findFirstByColumn( "C_CODE", "350000" );
        List<AreaPara> children = fj.children();
        return Lists.transform( children, new AreaParaConverter() );
    }

    private static class AreaInfo {
        private String name;
        private String code;
        private List<AreaInfo> children;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public List<AreaInfo> getChildren() {
            return children;
        }
    }

    private static class AreaParaConverter implements Function<AreaPara, AreaInfo> {
        @Override
        public AreaInfo apply( AreaPara input ) {
            AreaInfo area = new AreaInfo();
            area.code = input.getCode();
            area.name = input.getName();
            List<AreaPara> children = input.children();
            area.children = Lists.newArrayList( Lists.transform( children, new AreaParaConverter() ) );
            return area;
        }
    }
}
