/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HomeHandler.java
 * 创建时间：2015年4月21日 上午9:27:34
 * 创建用户：林峰
 */
package com.lanstar.core.handle;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import org.w3c.dom.Element;

import com.lanstar.app.ServletHelper;
import com.lanstar.common.helper.XmlHelper;
import com.lanstar.common.helper.XmlHelper.INodeParser;
import com.lanstar.core.RequestContext;
import com.lanstar.core.ViewAndModel;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.resolver.RenderResolver;
import com.lanstar.core.render.resolver.RenderResolverFactory;

/**
 * 首页
 *
 */
public class HomeHandler implements Handler {
    @Override
    public void handle( HandlerContext context, HandleChain next )
            throws ServletException, IOException {
        RequestContext requestContext = context.getRequestContext();
        String uri = requestContext.getUri();
        if ( uri.equals( "/" ) || uri.startsWith( "/index" ) ) {
            String tenantType = context.getIdentity().getTanentType().toLowerCase();
            this.initPara( context,tenantType);
            RenderResolver resolver = RenderResolverFactory.me().getResolver("html" );
            ViewAndModel vam = new ViewAndModel().view( "/"+tenantType+"/home/index.ftl" );
            Render render = resolver.getRender( vam, requestContext );
            render.render();
        }
        next.doHandle( context );
    }
    
    public void initPara( HandlerContext context,String tenantType ) {
        final StringBuffer sb = new StringBuffer();
        InputStream resource = ServletHelper.getResource( "/WEB-INF/menu/menu_"+tenantType+".xml" );
        try {
            context.setValue( "_USERNAME_",context.getIdentity().getName());
            
            Element root = XmlHelper.getDocumentElement( resource );
            XmlHelper.getSubNodes( root, new INodeParser() {

                @Override
                public void parse( Element node ) {
                    sb.append( "<li value='" )
                            .append( XmlHelper.getProperty( node, "id" ) )
                            .append( "'" )
                            .append(" class='js-component-tabitem tA0 oZ0 nui-tabs-item'" )
                            .append( " title='" )
                            .append( XmlHelper.getProperty( node, "title" ) )
                            .append( "'" )
                            .append( " url='" ).append( XmlHelper.getProperty( node, "url" ) ).append( "'" )
                            .append( " onclick='changeTab(this.value)'>" )
                            .append("<div class='kA0'></div><div class='mE0'></div>" )
                            .append( "<div class='nui-tabs-item-text'>" )
                            .append( XmlHelper.getProperty( node, "title" ) )
                            .append( "</div>" ).append( "</li>" );
                }
            } );
            context.setValue( "_TABS_", sb.toString() );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
