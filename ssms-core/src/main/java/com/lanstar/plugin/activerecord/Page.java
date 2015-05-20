/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Page.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.activerecord;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private static final long serialVersionUID = -5395997221963176643L;

    private List<T> list;                // list result of this page
    private int pageNumber;                // page number
    private int pageSize;                // result amount of this page
    private int totalPage;                // total page
    private int totalRow;                // total row

    /**
     * Constructor.
     *
     * @param list       the list of paginate result
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @param totalPage  the total page of paginate
     * @param totalRow   the total row of paginate
     */
    public Page( List<T> list, int pageNumber, int pageSize, int totalPage, int totalRow ) {
        this.list = list;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalRow = totalRow;
    }

    /**
     * Return list of this page.
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Return page number.
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Return page size.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Return total page.
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Return total row.
     */
    public int getTotalRow() {
        return totalRow;
    }
}