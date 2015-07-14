/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Notice.java
 * 创建时间：2015年6月5日 下午4:28:46
 * 创建用户：林峰
 */
package com.lanstar.model.system;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.activerecord.Model;

/**
 * 通知公告
 *
 */
public class Notice extends Model<Notice>{
    public static Notice dao = new Notice();
    
    public Integer getId(){
        return this.getInt( "SID");
    }
    
    public String getContent(){
        String s = getStr("C_CONTENT");
        if (!StrKit.isBlank( s ))
            s = s.replaceAll( "'", "‘" );
        return s;
    }
    
    public void setContent(String value){
        this.set( "C_CONTENT", value );
    }
}
