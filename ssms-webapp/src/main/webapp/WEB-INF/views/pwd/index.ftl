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
                <p><label for="email" style="width: auto">请输入电子邮箱:</label></p>

                <div><input type="text" class="username" id="email" style="width: 100%"/></div>
                <p><label for="vcode" style="width: auto">验证码:</label></p>

                <div>
                    <input class="vcode" type="text" id="vcode" maxlength="4" style="width: 140px;"/>
                    <img src="${WEBPATH}/vcode" class="vimg" title="看不清？点击刷新">
                </div>

                <p style="white-space:nowrap; text-align: center">
                    <button class="loginBtn" style="margin-left: 0">发送邮件</button>
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
    <script type="text/javascript" src="${RES}/js/layui/layer.js"></script>
    <script type="text/javascript" src="${RES}/js/core.js"></script>
    <script type="text/javascript">
        (function () {
            var $vimg = $('.vimg').click(function () {
                $vimg.attr('src', '${WEBPATH}/vcode?_t=' + utils.uuid());
            });

            var $email = $('#email');
            var $vcode = $('#vcode');

            function isEmail(str){
                var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
                return reg.test(str);
            }

            $('.loginBtn').click(function () {
                try {
                    var email = $email.val();
                    utils.asserts.notBlank(email, '请填写正确的邮箱!');
                    if (isEmail(email) == false) throw '请填写正确的邮箱!';

                    var yzm = $vcode.val();
                    utils.asserts.notBlank(yzm, '请填写正确的验证码!');

                    var parms = {email: email, verify: yzm};
                    $.post('${WEBPATH}/pwd/send', parms, function (result) {
                        utils.messager.alert(result.msg, function(){
                            if (result.state == 'error') $vimg.click();
                            else window.location.href = "${WEBPATH}/login";
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