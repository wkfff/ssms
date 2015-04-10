/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Range.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.common.utils;

import java.util.Iterator;

public class Range implements Iterable<Integer> {
    private int min;
    private int count;

    public Range( int min, int count ) {
        this.min = min;
        this.count = count;
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int cur = min;
            private int count = Range.this.count;

            public boolean hasNext() {
                return count != 0;
            }

            public Integer next() {
                count--;
                return cur++; // first return the cur, then increase it.
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
