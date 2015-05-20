/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Token.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.token;

import java.io.Serializable;

public class Token implements Serializable {

    private static final long serialVersionUID = -3667914001133777991L;

    private String id;
    private long expirationTime;

    Token( String id, long expirationTime ) {
        if ( id == null )
            throw new IllegalArgumentException( "id can not be null" );

        this.expirationTime = expirationTime;
        this.id = id;
    }

    Token( String id ) {
        if ( id == null )
            throw new IllegalArgumentException( "id can not be null" );

        this.id = id;
    }

    /**
     * Returns a string containing the unique identifier assigned to this token.
     */
    public String getId() {
        return id;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    /**
     * expirationTime 不予考虑, 因为就算 expirationTime 不同也认为是相同的 token.
     */
    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals( Object object ) {
        return object instanceof Token && ((Token) object).id.equals( this.id );
    }
}