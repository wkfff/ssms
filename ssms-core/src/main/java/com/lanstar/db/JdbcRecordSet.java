/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JdbcRecordSet.java
 * 创建时间：2015-04-07
 * 创建用户：张铮彬
 */
package com.lanstar.db;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据库记录的基础类
 */
public class JdbcRecordSet extends ArrayList<JdbcRecord> {
    private static final long serialVersionUID = -660397472192306649L;
    
    private DBPaging paging;
    
    /**
     * @return the paging
     */
    public DBPaging getPaging() {
        return paging;
    }

    /**
     * @param paging the paging to set
     */
    public void setPaging( DBPaging paging ) {
        this.paging = paging;
    }

    public JSONObject toJson(){        
        JSONObject r = new JSONObject();
        if (paging!=null){
            r.put( DBPaging.PAGING_TOTAL_NAME, paging.getRecCount() );
            r.put( DBPaging.PAGING_COUNT_NAME, paging.getPageCount() );
            r.put( DBPaging.PAGING_INDEX_NAME, paging.getPageIndex() );
        }
        r.put( DBPaging.PAGE_DATA, this); 
        return r;
    }
    
    public String toJsonString(){
        if (paging==null) return this.toString();
        JSONObject r = new JSONObject();
        r.put( DBPaging.PAGING_TOTAL_NAME, paging.getRecCount() );
        r.put( DBPaging.PAGING_COUNT_NAME, paging.getPageCount() );
        r.put( DBPaging.PAGING_INDEX_NAME, paging.getPageIndex() );
        r.put( DBPaging.PAGE_DATA, this);        
        return r.toJSONString();
    }
}
