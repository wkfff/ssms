/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewController.java
 * 创建时间：2015-06-02
 * 创建用户：张铮彬
 */

package com.lanstar.controller.review;

import com.lanstar.controller.SimplateController;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.tenant.Review;
import com.lanstar.plugin.activerecord.ModelKit;

public class ReviewController extends SimplateController<Review> {

    @Override
    protected Review getDao() {
        // TODO Auto-generated method stub
        return Review.dao;
    }
    
    public void rec(){
        IdentityContext identityContext= IdentityContextWrap.getIdentityContext( this );
        //通过当前用户find出租户id
        int id=identityContext.getTenantId();
        Review model=Review.dao.findById( id );
        if ( model != null ) setAttrs( ModelKit.toMap( model ) );
    }
    @Override
    public void save(){
        Review model = getModel();
        if(model!=null){
                model.update(); 
                setAttr( "SID", model.getInt( "SID" ) );
                renderJson();
        }
    }
}
