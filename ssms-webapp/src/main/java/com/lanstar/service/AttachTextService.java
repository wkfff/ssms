/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AttachTextService.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.service;

import com.lanstar.common.ModelInjector;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.Tenant;
import com.lanstar.model.system.AttachText;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class AttachTextService {
    public static final AttachTextService SYSTEM = new AttachTextService( Tenant.SYSTEM_TENANT );
    private final Tenant tenant;

    public AttachTextService( Tenant tenant ) {
        this.tenant = tenant;
    }

    public String getContent( String table, String field, int sid ) {
        AttachText attachText = AttachText.dao.findFirst( SqlKit.sql( "system.attachText.get" ),
                table, field, sid, tenant.getTenantId(), tenant.getTenantType().getName() );
        return attachText == null ? "" : attachText.getContent();
    }

    public Integer save( String table, String field, int sid, String content, IdentityContext operator ) {
        return save( table, field, sid, content, operator.getIdentity() );
    }

    public Integer save( String table, String field, int sid, String content, Identity opeartor ) {
        AttachText attachText = AttachText.dao.findFirst( SqlKit.sql( "system.attachText.get" ),
                table, field, sid, tenant.getTenantId(), tenant.getTenantType().getName() );
        if ( attachText != null ) {
            attachText.setContent( content );
            attachText.setTenant( tenant );
            ModelInjector.injectOpreator( attachText, opeartor, true );
            attachText.update();
        } else {
            attachText = new AttachText();
            attachText.setTable( table );
            attachText.setField( field );
            attachText.setRSid( sid );
            attachText.setContent( content );
            ModelInjector.injectOpreator( attachText, opeartor, false );
            attachText.save();
        }
        return attachText.getId();
    }

    public boolean del( String table, String field, int sid ) {
        AttachText attachText = AttachText.dao.findFirst( SqlKit.sql( "system.attachText.get" ),
                table, field, sid, tenant.getTenantId(), tenant.getTenantType().getName() );
        if ( attachText != null ) return attachText.delete();
        return false;
    }
}
