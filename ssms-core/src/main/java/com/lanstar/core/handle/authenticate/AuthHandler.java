/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AuthHandler.java
 * 创建时间：2015-04-05
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.authenticate;

import com.lanstar.core.handle.HandleChain;
import com.lanstar.core.handle.Handler;
import com.lanstar.core.handle.HandlerContext;

/**
 * 处理身份认证
 */
public class AuthHandler implements Handler {
    @Override
    public void handle( HandlerContext context, HandleChain nextHandle ) {
        // 如果身份认证通过，那么使用nextHandle继续处理后续的，否则请求不能继续进行。
        // TODO: 增加身份认证过程
        nextHandle.doHandle( context );
    }
}
