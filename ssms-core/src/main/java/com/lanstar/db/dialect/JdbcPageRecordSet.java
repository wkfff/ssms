/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdbcPageRecordSet.java
 * 创建时间：2015-04-15
 * 创建用户：张铮彬
 */

package com.lanstar.db.dialect;

import com.lanstar.db.DBPaging;
import com.lanstar.db.JdbcRecordSet;

public class JdbcPageRecordSet {
    private final int pageIndex;
    private final int pageSize;
    private int total;
    private JdbcRecordSet data;

    public JdbcPageRecordSet( DBPaging paging ) {
        if ( paging == null ) {
            this.pageIndex = 0;
            this.pageSize = 0;
        } else {
            this.pageIndex = paging.getPageIndex();
            this.pageSize = paging.getPageSize();
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal( int total ) {
        this.total = total;
    }

    public int getPageCount() {
        if ( pageSize < 1 ) return 1;
        return total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setData( JdbcRecordSet data ) {
        this.data = data;
    }

    public JdbcRecordSet getData() {
        return data;
    }
}
