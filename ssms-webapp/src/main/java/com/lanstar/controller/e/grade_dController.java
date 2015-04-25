/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：grade_dController.java
 * 创建时间：2015年4月22日 下午3:03:28
 * 创建用户：林峰
 */
package com.lanstar.controller.e;

import java.util.HashMap;
import java.util.Map;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.controller.BaseController.MergerType;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.ar.ARTable;

/**
 * 在线自评
 *
 */
public class grade_dController extends DefaultController {
    public grade_dController() {
        super( "SSM_GRADE_E_D" );
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        return grade_dValidator.class;
    }
    
    public ViewAndModel gridsave(HandlerContext context){
        Map<String, Map<String,Object>> datas = new HashMap<String, Map<String,Object>>();        
        Map<String,String> map = context.getParameterMap();
        for(String key:map.keySet()){
            String[] keys = key.split( "#" );
            if (keys.length>1){
                if (datas.containsKey( keys[0] )){
                    datas.get( keys[0] ).put( keys[1], map.get( key ) );
                }else{
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put( keys[1], map.get( key ) );
                    datas.put( keys[0], data );
                }
            }
        }
        for(String key:datas.keySet()){
            Map<String, Object> data = datas.get( key );
            ARTable table = context.DB.withTable( this.TABLENAME ).where( "SID=?",data.get( "SID" ) );
            table.values( data );
            table.save();
        }
        return context.returnWith();
    }
}
