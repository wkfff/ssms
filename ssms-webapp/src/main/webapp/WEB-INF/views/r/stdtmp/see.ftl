<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="/resource/css/layout.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    
    <#if DEV_MODE>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/plugins/autoheight/autoheight.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>
    <#else>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    </#if>
</head>
<body>

<div class="titlebar">
            <img src="/resource/images/blue/star.png"/>&nbsp;${title!}&nbsp; 
            <span class="backing"><a href="/r/stdtmp/view/${viewUrl!}-${sid!}">返回列表</a></span>
</div>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center', border:false,tools:'#db_tb'">
        <div id="content" class="easyui-panel" style="position: relative;" data-options="onLoad: onLoad" fit="true" title="" href=""></div>
    </div>
</div>

<script type="text/javascript">
    var onPanelLoad;
    function isFunction(obj) {
        return typeof obj === 'function';
    }
    function refreshPanel() {
        $("#content").panel("refresh");
    }
    function panelLoad(url) {
        $("#content").panel("refresh", url);
    }
    function onLoad() {
        if (isFunction(onPanelLoad)) onPanelLoad();
    }
    $(document).ready(function(){
    /*
        var url = '${URL!}';
        if (url.length>0)
            $("#content").panel("setTitle", "").panel("refresh",url);
            */
            var url = '/e/stdtmp_file_${tmpfile}/view?sid=${sid}'
        $("#content").panel("setTitle", "").panel("refresh",url);
    });
</script>
</body>
</html>