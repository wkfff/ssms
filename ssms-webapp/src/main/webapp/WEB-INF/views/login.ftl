<#macro login>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_}</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/login.css"/>
</head>
<body>
<div id="container">
    <div id="bd">
        <div class="login1">
            <div class="login-top"><h1 class="logo"></h1></div>
            <div class="login-input">
                <p class="user ue-clear">
                    <label>用户名</label>
                    <input id="username" type="text"/>
                </p>

                <p class="password ue-clear">
                    <label>密&nbsp;&nbsp;&nbsp;码</label>
                    <input id="password" type="password"/>
                </p>

                <p class="yzm ue-clear">
                    <label>验证码</label>
                    <input id="yzm" type="text"/>
                    <cite><a id="vcode" href="#"><img id="vcodeImg" src="/vcode"/></a></cite>
                </p>
            </div>
            <div class="login-btn ue-clear">
                <a id="login" class="btn">登录</a>

                <div class="remember ue-clear">
                    <input type="checkbox" id="remember"/>
                    <em></em>
                    <label for="remember">记住密码</label>
                </div>
            </div>
        </div>
    </div>
    <div style="text-align:center;color:#FFF;">快速测试入口 >  
     <a href="javascript:doTest('E3501029901');"><span style="color:#FFF">企业端测试用户：E3501029901</span></a>,&nbsp;
     <a href="javascript:doTest('R3501029902');"><span style="color:#FFF">评审端测试用户：R3501029902</span></a>,&nbsp;
     <a href="javascript:doTest('G3501029903');"><span style="color:#FFF">政府端测试用户: G3501029903</span></a>,&nbsp;
     <a href="javascript:doTest('system');"><span style="color:#FFF">系统端测试用户: system</span></a>
    </div>
</div>

<div id="ft">CopyRight&nbsp;2015&nbsp;&nbsp;版权所有&nbsp;&nbsp;福建永创意信息科技有限公司,福州蓝石电子有限公司 技术支持</div>
</body>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript" src="/resource/js/js.cookie-1.5.1.min.js"></script>
<script type="text/javascript" src="/resource/js/common.js"></script>
<script type="text/javascript">
    var height = $(window).height();
    $("#container").height(height);
    $("#bd").css("padding-top", height / 2 - $("#bd").height() / 2);
    $('#password').val(Cookies.get('pwd'));
    $('#username').val(Cookies.get('usr'));
    $('#remember').prop("checked", Cookies.get("remember") == 'true');

    var nochange = true;
    $('#password').change(function () {
        nochange = false;
    });

    $(window).resize(function () {
        var height = $(window).height();
        $("#bd").css("padding-top", $(window).height() / 2 - $("#bd").height() / 2);
        $("#container").height(height);

    });

    $('#vcode').click(function () {
        $('#vcodeImg').attr("src", '');
        $('#vcodeImg').attr("src", '/vcode?_t='+new Date().valueOf());

        return false;
    });

    $('#remember').focus(function () {
        $(this).blur();
    });

    $('#remember').click(function (e) {
        checkRemember($(this));
    });
    function checkRemember($this) {
        // TODO: 添加记住密码的逻辑
        if (!-[1,]) {
            if ($this.prop("checked")) {
                $this.parent().addClass('checked');
            } else {
                $this.parent().removeClass('checked');
            }
        }
    }

    $(document).keypress(function(e) {
        // 回车键事件
        if(e.which == 13) {
            jQuery("#login").click();
        }
    });

    $('#login').click(function () {
        var pwd = nochange ? $('#password').val() : $.md5($('#password').val());
        var usr = $('#username').val();
        if ($('#remember').prop("checked")) {
            Cookies.set('pwd', pwd, { expires: 7 });
        }
        else Cookies.remove('pwd');
        Cookies.set('usr', usr, { expires: 7 });
        Cookies.set('remember', $('#remember').prop("checked"), { expires: 7 });
        var parms = {
            username: usr,
            password: pwd,
            yzm: $('#yzm').val()
        };
        $.post('login', parms, function (result) {
            if (result.state == "success") window.location.href = "/";
            else alert(result.msg);
        }, "json")
    });

    if (top != window) {
        top.window.location.href = window.location.href;
    }
    
    function doTest(usr){
        var parms = {
            username: usr,
            password: 'e10adc3949ba59abbe56e057f20f883e'
        };
        $.post('login', parms, function (result) {
            if (result.state == "success") window.location.href = "/";
            else alert(result.msg);
        }, "json")
    }
</script>
</html>
</#macro>
<@login/>