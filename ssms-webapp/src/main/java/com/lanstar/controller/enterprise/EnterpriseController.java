/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseController.java
 * 创建时间：下午2:47:37
 * 创建用户：苏志亮
 */
package com.lanstar.controller.enterprise;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Enterprise;
import com.lanstar.model.system.EnterpriseProfession;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.service.enterprise.EnterpriseProfessionService;

/**
 * @author Administrator
 *
 */
public class EnterpriseController  extends SimplateController<Enterprise>{
    @Override
    protected Enterprise getDao() {
        // TODO Auto-generated method stub
         return Enterprise.dao;
    }
    
     public void rec(){
         IdentityContext identityContext=IdentityContext.getIdentityContext( this );
         //通过当前用户find出租户id
         int id=identityContext.getTenantId();
         Enterprise model=Enterprise.dao.findById( id );
         List<EnterpriseProfession> professions = model.listProfession();

         List<Object> professionValues = new ArrayList<>();
         for ( EnterpriseProfession record : professions ) {
             professionValues.add( record.getProfessionId() );
         }
         setAttr( "professionValues", professionValues );
         if ( model != null ) setAttrs( ModelKit.toMap( model ) );
     }
     @Override
     public void save(){
         Enterprise model=getModel();
         if(model!=null) {
             model.update(); 
             afterSave( model );
             setAttr( "SID", model.getInt( "SID" ) );
             renderJson();
         }
     }
     
     protected void afterSave( Enterprise model ) {
         // 获取企业专业服务，进行企业专业管理。
         EnterpriseProfessionService service = EnterpriseProfessionService.forEnterprise( model );
         String professionValues = getPara( "professionValues" );
         ArrayList<Integer> professionList = new ArrayList<>();
         for ( String professionId : Splitter.on( ',' ).trimResults().omitEmptyStrings()
                                             .split( professionValues ) ) {
             professionList.add( Integer.valueOf( professionId ) );
         }
         service.setProfession( Ints.toArray( professionList ) );
     }
    
}
