/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandlerMeta.java
 * 创建时间：2015-04-02
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle.action;

import com.google.common.base.Splitter;

import java.util.List;

public class ActionMeta {
    private String module;
    private String controller;
    private String action;
    private String render;

    public String getModule() {
        return module;
    }

    public String getController() {
        return controller;
    }

    public String getAction() {
        return action;
    }

    public String getRender() {
        return render;
    }

    @Override
    public String toString() {
        return "ActionMeta{" +
                "module='" + module + '\'' +
                ", controller='" + controller + '\'' +
                ", action='" + action + '\'' +
                ", render='" + render + '\'' +
                '}';
    }


    /**
     * 将URL解析为元数据
     *
     * @param url URL
     *
     * @return 元数据
     *
     * @see ActionMeta
     */
    public static ActionMeta parseUrl( String url ) {
        ActionMeta meta = new ActionMeta();
        // 解析/e/a02/index.html为：
        //     e=>大模块
        //     a02=>a02Controller
        //     index=>Action
        //     .html=>Render
        url = url.replace( '.', '/' );
        List<String> result = Splitter.on( '/' ).omitEmptyStrings().trimResults().splitToList( url );
        if (result.size() != 4) return null;
        meta.module = result.get( 0 );
        meta.controller = result.get( 1 );
        meta.action = result.get( 2 );
        meta.render = result.get( 3 );

        return meta;
    }
}
