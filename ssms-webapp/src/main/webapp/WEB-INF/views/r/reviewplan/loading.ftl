<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/layout.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    
    <script type="text/javascript">
        $(document).ready(function(){
            $.messager.progress({
                title: '请稍等',
                msg: '系统正在初始化数据，请稍候......'
            });
            
            $.post('/r/reviewplan/init', {eid:${eid!},pro:${pro!}}, function (result) {
                    if (result.SID) {
                        //$.messager.alert("提示", "初始化成功", "info", function () {
                                $.messager.progress('close');
                                window.location.href = 'tabs/' + result.SID+'-${eid!}-'+result.gradePlanId;
                        //});
                    } else {
                        utils.messager.closeProgress();
                        $.messager.alert("提示", "初始化失败", "warning");
                    }
                }, "json");
        });
    </script>
</head>
<body>
</body>
</html>

