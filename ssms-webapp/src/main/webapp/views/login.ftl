<!doctype html>
<!--[if lt IE 7]> <html class="no-js ie6" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/resource/css/login.css"/>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.fullscreenBackground.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#background-image").fullscreenBackground();
            $("#username").focus();
        });

        function doLogin() {
            //window.location.href='index.html';
            //$("#login").submit();
            //v=$.md5(document.getElementById("usrPasswd").value);
            //md5加密
            var v=$("#usrPasswd").val($.md5($("#usrPasswd").val()));
            document.getElementById("login").submit();
        }
    </script>
</head>

<body>
<div id="content" class="box box_bg"></div>

<div id="content1" class="box">
    <div id="header">
        <h1 class="title">安全生产标准化管理系统</h1>
    </div>

    <form id="login" name="login" method="post" action="/index">
        <nobr>用户<input type="text" id="username" name="username" class="username"></nobr>
        <nobr>密码<input type="password" id="usrPasswd" name="usrPasswd" class="password"></nobr>
        <nobr>
            <button type="button" onclick="doLogin();">登录</button>
            <input type="checkbox" name="saveit" id="saveit"><label for="saveit" id="rember">记住用户名与密码</label>
        </nobr>
        <div class="error"><span></span></div>
    </form>


</div>

<div id="footer">CopyRight 2015 All Rights Reserved 技术支持：福州磬基电子有限公司</div>


<div id="background-image">
    <img src="/resource/images/login/bg.jpg" width="1275" height="720"/>
</div>

</body>
</html>
<#--TODO: 替换登录页-->