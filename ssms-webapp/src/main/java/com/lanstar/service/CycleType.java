/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CycleType.java
 * 创建时间：2015-07-07
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public enum CycleType {
    MONTH( "01", "月" ) {
        @Override
        public Date advance( int days, Date date ) {
            Calendar cd = Calendar.getInstance();
            cd.setTime( date );
            cd.add( Calendar.MONTH, days );
            return cd.getTime();
        }
    },

    SEASON( "02", "季" ) {
        @Override
        public Date advance( int days, Date date ) {
            Calendar cd = Calendar.getInstance();
            cd.setTime( date );
            cd.add( Calendar.MONTH, days * 3 );
            return cd.getTime();
        }
    },

    YEAR( "03", "年" ) {
        @Override
        public Date advance( int days, Date date ) {
            Calendar cd = Calendar.getInstance();
            cd.setTime( date );
            cd.add( Calendar.YEAR, days );
            return cd.getTime();
        }
    },
    DAY( "04", "天" ) {
        @Override
        public Date advance( int days, Date date ) {
            Calendar cd = Calendar.getInstance();
            cd.setTime( date );
            cd.add( Calendar.DATE, days );
            return cd.getTime();
        }
    },
    WEEK( "05", "周" ) {
        @Override
        public Date advance( int days, Date date ) {
            Calendar cd = Calendar.getInstance();
            cd.setTime( date );
            cd.add( Calendar.DATE, days * 7 );
            return cd.getTime();
        }
    };

    private final String code;
    private final String name;

    CycleType( String code, String name ) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static List<Parameter> parameters() {
        CycleType[] values = CycleType.values();
        ArrayList<Parameter> list = Lists.newArrayList();
        for ( CycleType value : values ) {
            list.add( new Parameter( value.getCode(), value.getName() ) );
        }
        return list;
    }

    public static CycleType getByCode( String code ) {
        CycleType[] values = CycleType.values();
        for ( CycleType value : values ) {
            if ( value.getCode().equals( code ) ) return value;
        }
        throw new IllegalArgumentException( "无法找到值" );
    }

    /**
     * 获取提前天数
     */
    public abstract Date advance( int days, Date date );
}
