/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ProcedureParameter.java
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ProcedureParameter {
    public static int MODE_UNKONE = 0;
    public static int MODE_IN = 1;
    public static int MODE_INOUT = 2;
    public static int MODE_OUT = 4;

    /**
     * 数据类型，参见java.sql.Types
     */
    private int type;

    /**
     * IN/OUT模式,参见 Parameter.parameterMode
     */
    private int mode;

    /**
     * 从连接中获得指定存储过程的参数信息
     */
    public static List<ProcedureParameter> build( Connection conn, String spname ) throws SQLException {
        ResultSet rs = conn.getMetaData().getProcedureColumns( null, null, spname.toUpperCase(), "%" );
        List<ProcedureParameter> params = new ArrayList<>();
        while ( rs.next() ) {
            ProcedureParameter p = new ProcedureParameter();
            p.mode = rs.getInt( 5 );
            p.type = rs.getInt( 6 );
            params.add( p );
        }
        return params;
    }

    /**
     * @return 参数的数据类型，参见java.sql.Types
     */
    public int getType() {
        return type;
    }

    /**
     * @return 参数的IN/OUT模式，参见ParameterMetaData.ParameterModeXXX
     */
    public int getMode() {
        return mode;
    }
}