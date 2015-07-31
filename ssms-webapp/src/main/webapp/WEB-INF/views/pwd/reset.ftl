<@layout.extends name="../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="${RES}/css/base.css"/>
    <link rel="stylesheet" href="${RES}/css/login.css"/>
    </@>

    <@layout.put block="contents">
    <div class="floater"></div>
    <div class="container">
        <div class="content">
            <div class="logo">
                <img src="${RES}/images/login/logo.png"/>
                <img src="${RES}/images/login/title.png"/>
            </div>
            <div class="loginForm">
                <p><label for="password" style="width: auto">请输入新密码:</label></p>

                <div><input type="password" class="password" id="password" style="width: 100%"/></div>

                <p><label for="vcode" style="width: auto">请确认新密码:</label></p>

                <div><input class="vcode" type="password" id="confirm" style="width: 100%;"/></div>

                <p style="white-space:nowrap; text-align: center">
                    <button class="loginBtn" style="margin-left: 0">确认修改</button>
                    <a href="${WEBPATH}/login" style="font-size: 12px; color: #0000ff">返回登录页>></a>
                </p>
            </div>
        </div>
        <div class="footer">
            CopyRight 2015 版权所有 福建永创意信息科技有限公司,福州蓝石电子有限公司 技术支持
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="${RES}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${RES}/js/jquery.md5.js"></script>
    <script type="text/javascript" src="${RES}/js/layui/layer.js"></script>
    <script type="text/javascript" src="${RES}/js/core.js"></script>
    <script type="text/javascript">
        (function () {
            var $password = $('#password');
            var $confirm = $('#confirm');

            $('.loginBtn').click(function () {
                try {
                    var password = $password.val();
                    utils.asserts.notBlank(password, '请填写新密码!');

                    var confirm = $confirm.val();
                    utils.asserts.notBlank(confirm, '请确认密码!');

                    if (password != confirm) {
                        utils.messager.alert("两次输入的密码不一致!");
                    }

                    var parms = {password: $.md5(password), confirm: $.md5(confirm), token: '${token}'};
                    $.post('${WEBPATH}/pwd/save', parms, function (result) {
                        utils.messager.alert(result.msg, function(){
                            if (result.state != 'error') {
                                window.location.href = "${WEBPATH}/login";
                            }
                        });
                    }, "json")
                } catch (e) {
                    utils.messager.alert(e);
                }
            });
        })();
    </script>
    </@>
</@>