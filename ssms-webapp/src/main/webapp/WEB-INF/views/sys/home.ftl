<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/resource/css/base.css" />
    <link rel="stylesheet" href="/resource/css/home.css" />
    <title>安全生产标准化管理系统</title>
</head>

<body>
<div class="article toolbar">
    <div class="title ue-clear">
        <h2>快捷入口</h2>
    </div>
    <div class="content">
        <ul class="toollist ue-clear">
            <li>
                <a href="javascript:doChangePwd();" class="img"><img src="/resource/images/icon03.png" /></a>
                <p><a href="javascript:doChangePwd();">密码修改</a></p>
            </li>
            <li>
                <a href="javascript:doConfig();" class="img"><img src="/resource/images/icon09.png" /></a>
                <p><a href="javascript:doConfig();">系统配置</a></p>
            </li>
        </ul>
    </div>
</div>
</body>

<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
    var aIndex = 0;
    $(".title-list ul").on("click","li",function(){
        aIndex = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $(".matter-content").removeClass("current").eq(aIndex).addClass("current");
    });

    $(".duty").find("tbody").find("tr:even").css("backgroundColor","#eff6fa");
    
    
    function nav(url){
        window.location.href = url;
    }

    
    function doChangePwd(){
    
    }
    
    function doConfig(){
        
    }
</script>
</html>
