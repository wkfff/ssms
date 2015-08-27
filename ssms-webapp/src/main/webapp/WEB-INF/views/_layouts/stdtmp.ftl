<@layout.extends name="base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    </@>

    <@layout.put block="contents">
    <div class="container">
        <header class="titlebar">
            <@layout.block name="panel_title"/>
            <span style="position: absolute; right: 15px;">
                <@layout.block name="panel_tools"><a href="/sys/template/">返回列表</a></@>
            </span>
        </header>

        <div class="content">
            <@layout.block name="panel_content"/>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <#if DEV_MODE>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.validation.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.validation.zh-CN.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/htmleditor.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>
    <#else>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js?v=1440657618"></script>
    </#if>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    </@>
</@>