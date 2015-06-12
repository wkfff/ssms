/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GovernmentController.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.controller.government;

import com.lanstar.controller.SimplateController;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Government;
import com.lanstar.plugin.activerecord.ModelKit;

public class GovernmentController extends SimplateController<Government> {

    @Override
    protected Government getDao() {
        return Government.dao;
    }
    
    public void rec(){
        IdentityContext identityContext=IdentityContext.getIdentityContext( this );
        //通过当前用户find出租户id
        int id=identityContext.getTenantId();
        Government model=Government.dao.findById( id );
        if ( model != null ) setAttrs( ModelKit.toMap( model ) );
    }
    @Override
    public void save(){
        Government model = getModel();
        if(model!=null){
                model.update(); 
                setAttr( "SID", model.getInt( "SID" ) );
                renderJson();
        }
    }
}
