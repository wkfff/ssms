<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
</head>
<body>
<div class="titlebar">
    <img src="/resource/images/blue/star.png"/>&nbsp;${title!'查看'}
    <span class="backing"><a href="/e/stdtmp/view/${version!0}-${sid!}">返回列表</a></span>
</div>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center', border:false">
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
        var url = '/e/stdtmp_file_${tmpfile}/view?sid=${sid}'
        $("#content").panel("setTitle", "").panel("refresh",url);
    });
</script>
</body>
</html>