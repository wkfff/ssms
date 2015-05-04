<#macro login>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
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
                    <input id="password" type="text"/>
                </p>

                <p class="yzm ue-clear">
                    <label>验证码</label>
                    <input id="yzm" type="text"/>
                    <cite>X394D</cite>
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
</div>
<div style="bottom: 90px; color: #10486b; left: 0; position: absolute; right: 0; text-align: center; z-index: 2;">
    <p>用户暂时无密码，先使用用户名为如下特殊字符进行登录。</p>
    <p>企业用户：E</p>
    <p>评审：R<p/>
    <p>政府：G</p>
    <p>系统：S</p>
</div>
<div id="ft">CopyRight&nbsp;2015&nbsp;&nbsp;版权所有&nbsp;&nbsp;福建永创意信息科技有限公司,福州蓝石电子有限公司 技术支持</div>
</body>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/common.js"></script>
<script type="text/javascript">
    var height = $(window).height();
    $("#container").height(height);
    $("#bd").css("padding-top", height / 2 - $("#bd").height() / 2);

    $(window).resize(function () {
        var height = $(window).height();
        $("#bd").css("padding-top", $(window).height() / 2 - $("#bd").height() / 2);
        $("#container").height(height);

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

    $('#login').click(function () {
        var parms = {
            username: $('#username').val(),
            password: $('#password').val(),
            tenant: null,
            yzm: $('#yzm').val()
        };
        $.post('login', parms, function(){
            window.location.href = "/index";
        } )
    });
</script>
</html>
</#macro>
<@login/>