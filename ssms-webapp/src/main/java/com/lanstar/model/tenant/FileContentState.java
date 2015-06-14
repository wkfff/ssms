/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TenantFileState.java
 * 创建时间：2015-06-14
 * 创建用户：张铮彬
 */

package com.lanstar.model.tenant;

public enum FileContentState {
    UNKNOWN( 0 ),
    CLONED( 1 ),
    CHANGED( 2 );

    private final int value;

    FileContentState( int value ) {
        this.value = value;
    }

    public static FileContentState valueOf( int value ) {
        for ( FileContentState status : values() ) {
            if ( status.value == value ) return status;
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
