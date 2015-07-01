/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ListKit.java
 * 创建时间：2015-06-30
 * 创建用户：张铮彬
 */

package com.lanstar.common;

import com.google.common.collect.Lists;

import java.util.List;

public abstract class ListKit {
    public static List<Object> newObjectArrayList( Object... objects ) {
        return Lists.newArrayList( objects );
    }

    @SafeVarargs
    public static <E> List<E> newArrayList( E... objects ) {
        return Lists.newArrayList( objects );
    }
}
