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
        <div style="display: block;height:685px;width:1237px">
            <@layout.block name="list"/>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    </@>
</@>