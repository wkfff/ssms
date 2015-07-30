/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：IdentityContextWrap.java
 * 创建时间：2015-07-26
 * 创建用户：张铮彬
 */

package com.lanstar.identity;

import com.lanstar.app.Const;
import com.lanstar.common.TreeNode;
import com.lanstar.core.Controller;
import com.lanstar.model.system.Profession;
import com.lanstar.model.system.tenant.Enterprise;
import com.lanstar.plugin.activerecord.DbPro;
import com.lanstar.service.AttachFileService;
import com.lanstar.service.AttachTextService;
import com.lanstar.service.enterprise.EnterpriseService;
import com.lanstar.service.review.ReviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO 未来考虑移除这个类
public class IdentityContextWrap implements IdentityContext {
    IdentityContext context;
    private final IdentityContextWrap parent;

    Map<Identity, IdentityContext> map = new ConcurrentHashMap<>();

    private IdentityContextWrap( IdentityContext context, IdentityContextWrap parent ) {
        this.context = context;
        this.parent = parent;
    }

    private IdentityContextWrap( Identity identity ) {
        this.context = new IdentityContextImpl( identity );
        this.parent = this;
    }

    public IdentityContext getContext( Identity identity ) {
        if ( context.getIdentity().equals( identity ) ) return this;
        IdentityContext context = map.get( identity );
        if ( context == null ) {
            context = new IdentityContextImpl( identity );
            map.put( identity, context );
        }
        // 无论如何一定返回的是一个warp的类对象。
        return new IdentityContextWrap( context, this );
    }

    public void switchIdentity( Identity identity ) {
        if ( context.getIdentity().equals( identity ) ) return;
        IdentityContext context = parent.map.get( identity );
        if ( context != null ) {
            this.context = context;
        }
    }

    public static IdentityContext getIdentityContext( Controller controller ) {
        return getIdentityContext( controller.getRequest() );
    }

    public static IdentityContext getIdentityContext( HttpServletRequest request ) {
        IdentityHolder identityHolder = IdentityKit.getIdentityHolder( request );

        HttpSession session = request.getSession();
        IdentityContextWrap contextHolder = (IdentityContextWrap) session.getAttribute( Const.IDENTITY_CONTEXT_KEY );
        if ( contextHolder == null ) {
            contextHolder = new IdentityContextWrap( identityHolder.getMaster() );
            session.setAttribute( Const.IDENTITY_CONTEXT_KEY, contextHolder );
        }
        return contextHolder.getContext( identityHolder.getDefaultIdentity() );
    }

    @Deprecated
    public static boolean hasIdentityContext( Controller controller ) {
        return IdentityKit.hasIdentity( controller );
    }

    @Override
    public Identity getIdentity() {return context.getIdentity();}

    @Override
    public int getId() {return context.getId();}

    @Override
    public String getName() {return context.getName();}

    @Override
    public String getCode() {return context.getCode();}

    @Override
    public void initReviewService( Enterprise tenant, Profession profession ) {context.initReviewService( tenant, profession );}

    @Override
    public String getTenantName() {return context.getTenantName();}

    @Override
    public TenantType getTenantType() {return context.getTenantType();}

    @Override
    public int getTenantId() {return context.getTenantId();}

    @Override
    public String getTenantCode() {return context.getTenantCode();}

    @Override
    public String getTenantDbCode() {return context.getTenantDbCode();}

    @Override
    public DbPro getTenantDb() {return context.getTenantDb();}

    @Override
    public Tenant getTenant() {return context.getTenant();}

    @Override
    public List<TreeNode> getSystemNavgate() {return context.getSystemNavgate();}

    @Override
    public EnterpriseService getEnterpriseService() {return context.getEnterpriseService();}

    @Override
    public AttachTextService getAttachTextService() {return context.getAttachTextService();}

    @Override
    public AttachFileService getAttachFileService() {return context.getAttachFileService();}

    @Override
    public ReviewService getReviewService() {return context.getReviewService();}
}
