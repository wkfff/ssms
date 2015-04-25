/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：DBPaging.java
 * 创建时间：2015年4月14日 上午11:15:16
 * 创建用户：林峰
 */
package com.lanstar.db;

/**
 * 分页
 */
public class DBPaging {
    public static final String PAGE_INDEX = "page";
    public static final String PAGE_SIZE = "rows";
    
//    public static final String PAGE_INDEX = "_paging[pageIndex]";
//    public static final String PAGE_SIZE = "_paging[pageSize]";
   /* public static final String PAGE_DATA = "data";

    public static final String PAGING_TOTAL_NAME = "total";
    public static final String PAGING_SIZE_NAME = "pageSize";
    public static final String PAGING_INDEX_NAME = "pageIndex";
    public static final String PAGING_COUNT_NAME = "pageCount";*/

    private int pageIndex;
    private int pageSize;

    public int getStartIndex() {
        int index = (pageIndex - 1) * pageSize;
        return index < 0 ? 0 : index;
    }

    /**
     * 返回当前分页的结束索引位置，以0为开始
     */
    public int getEndIndex() {
        int index = (pageIndex) * pageSize;
        return index < 0 ? 0 : index;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex( int pageIndex ) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "DBPaging [pageSize=" + pageSize + ", pageIndex=" + pageIndex + "]";
    }
}
