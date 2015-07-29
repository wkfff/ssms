<@layout.extends name="_layouts/base.ftl">
    <@layout.put block="head">
    <script type="text/javascript">
        if (top != window) {
            top.window.location.href = window.location.href;
        }
    </script>
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
                <p>
                    <label for="username">用户名</label>
                    <input class="username" type="text" id="username"/>
                </p>

                <p>
                    <label for="password">密&nbsp;&nbsp;&nbsp;码</label>
                    <input type="password" class="password" id="password"/>
                </p>

                <p>
                    <label for="vcode">验证码</label>
                    <input class="vcode" type="text" id="vcode" maxlength="4" style="width: 78px;"/>
                    <img src="${WEBPATH}/vcode" class="vimg" title="看不清？点击刷新">
                </p>

                <p style="white-space:nowrap;">
                    <button class="loginBtn">登录</button>
                    <input class="checkbox" name="" type="checkbox" value="" id="checkbox"/>
                    <label for="checkbox" class="remember">记住密码</label>
                    <a href="/pwd" class="forget" style="font-size: 12px; color: #0000ff">忘记密码?</a>
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
    <script type="text/javascript" src="${RES}/js/js.cookie-1.5.1.min.js"></script>
    <script type="text/javascript" src="${RES}/js/layui/layer.js"></script>
    <script type="text/javascript" src="${RES}/js/core.js"></script>
    <script type="text/javascript">
        $(function () {
            var $vimg = $('.vimg').click(function () {
                $vimg.attr('src', '${WEBPATH}/vcode?_t=' + utils.uuid());
            });

            var nochange = true;
            var $username = $('#username').val(Cookies.get('usr'));
            var $password = $('#password').val(Cookies.get('pwd')).change(function () {
                nochange = false;
            });
            var $remember = $('#remember').prop("checked", Cookies.get("remember") == 'true');

            $('.loginBtn').click(function () {
                try{
                    var usr = $username.val();
                    utils.asserts.notBlank(usr, '用户名不能为空!');
                    Cookies.set('usr', usr, { expires: 7 });

                    var pwd = nochange ? $password.val() : $.md5($password.val());
                    if ($remember.prop("checked")) Cookies.set('pwd', pwd, {expires: 7});
                    else Cookies.remove('pwd');
                    utils.asserts.notBlank($password.val(), '密码不能为空!');
                    Cookies.set('remember', $remember.prop("checked"), { expires: 7 });

                    var yzm = $('#vcode').val();
                    utils.asserts.notBlank(yzm, '验证码不能为空!');

                    var parms = { username: usr, password: pwd, yzm: yzm};
                    $.post('login', parms, function (result) {
                        if (result.state == "success") window.location.href = "/";
                        else {
                            utils.messager.alert(result.msg);
                            $vimg.click();
                        }
                    }, "json")
                }catch(e){
                    utils.messager.alert(e);
                }
            });


        });
    </script>
    </@>
</@>