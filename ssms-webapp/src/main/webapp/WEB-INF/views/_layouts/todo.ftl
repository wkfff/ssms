<@layout.extends name="base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/fix.css"/>
    <#--knockout-->
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <#--jquery-->
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
    <#--easyui-->
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript">
        function panelLoad(url) {
            window.location.href = url;
        }
        var onPanelLoad;
        $(function () {
            if (typeof onPanelLoad == "function") onPanelLoad();
        })
    </script>
    </@>
</@>