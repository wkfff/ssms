/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：para_areaController.java
 * 创建时间：上午10:13:34
 * 创建用户：苏志亮
 */
package com.lanstar.controller.sys;

import com.lanstar.controller.ActionValidator;
import com.lanstar.controller.DefaultController;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.helper.easyui.EasyUIControllerHelper;

/**
 * @author Administrator
 */
public class para_areaController extends DefaultController {

    /**
     * @param tablename
     */
    public para_areaController() {
        super( "SYS_PARA_AREA" );
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Class<? extends ActionValidator> getValidator() {
        // TODO Auto-generated method stub
        return null;
    }

    public ViewAndModel tree( HandlerContext context ) {
        JdbcRecordSet records = context.DB.withTable( TABLENAME )
                                          .orderby( "N_LEVEL ,C_PY_CODE " )
                                          .queryList();
        /*Map<String, TreeNode> map = new HashMap<>();
        Map<String, TreeNode> rootMap = new HashMap<>();
        TreeNode item ;
        for ( JdbcRecord record : records ) {
            String id = record.getString( "SID" );
            String text = record.getString( "C_VALUE" );
            String pid = record.getString( "R_PARENT" );
            // 取出所有根节点
            item = map.get( pid );
            if ( item == null ) {
                item = new TreeNode( id, text, record );
                map.put( id, item );
                rootMap.put( id, item );
            }
        }
        for ( JdbcRecord record : records ) {
            String id = record.getString( "SID" );
            String text = record.getString( "C_VALUE" );
            String pid = record.getString( "R_PARENT" );
            // 先试着根据父id取树节点，如果取不到则当前节点就当一个根节点。后续要再验证一次就是了。
            item = map.get( pid );
            if ( item == null ) {
                item = new TreeNode( id, text, record );
                map.put( id, item );
                rootMap.put( id, item );
            } else {
                TreeNode e = new TreeNode( id, text, record );
                item.getChildren().add( e );
                map.put( id, e );
            }
            if ( item.getChildren().size() > 0 ) rootMap.remove( id );
        }*/
        return context.returnWith()
                      .set( EasyUIControllerHelper.toTree( null, records, "SID", "R_PARENT", "C_VALUE" ) );

    }

}
