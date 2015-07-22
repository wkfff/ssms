<@layout.extends name="../../_layouts/base.ftl">

    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/validate.css"/>
    <style type="text/css">
        h1{
            color: #666666;
            font-size: 2em;
            font-weight: bold;
        }
        label {
            width: 70px;
            display: inline-block;
        }

        div {
            margin-top: 15px;
        }

        input[type='password'] {
            padding: 3px;
        }

        .strongLevel span {
            display: inline-block;
            text-align: center;
            width: 50px;
            color: #808080;
            background: #808080;
        }

        .strongLevel span.active {
            color: #FFFFFF;
            background: #008000;
        }
    </style>
    </@>

    <@layout.put block="contents">
    <h1>修改密码</h1>
    <hr/>
    <div class="validate warning" style="display: none" data-bind="visible: result().length>0">
        <em data-bind="text: result"></em>
    </div>
    <form data-bind="submit: submit, with: password">
        <div>
            <label for="old">旧密码</label>
            <input type="password" id="old" name="old" data-bind="textInput: oldPassword"/>
        </div>

        <div>
            <label for="new">新密码</label>
            <input type="password" id="new" name="new" data-bind="textInput: newPassword"/>
            <span class="strongLevel" style="display: none" data-bind="visible: strongLevel()>0">
                <span data-bind="css: {active: strongLevel()==1}">弱</span>
                <span data-bind="css: {active: strongLevel()==2}">中</span>
                <span data-bind="css: {active: strongLevel()==3}">强</span>
                <span data-bind="css: {active: strongLevel()==4}">非常好</span>
            </span>
        </div>

        <div>
            <label for="confirm">确认密码</label>
            <input type="password" id="confirm" name="confirm" data-bind="textInput: confirmPassword"/>
        </div>

        <div style="padding-left: 75px">
            <input type="submit" value="提交"/>
            <input type="reset" value="重置"/>
        </div>
    </form>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
        <#if DEV_MODE>
        <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/knockout.validation.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/knockout.validation.zh-CN.js"></script>
        <#else>
        <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
        </#if>

    <script type="text/javascript">
        (function () {
            function ViewModel() {
                function PasswordModel() {
                    var self = this;
                    this.oldPassword = ko.observable().extend({
                        required: {
                            params: true,
                            message: "请输入原始密码！"
                        }
                    });
                    this.newPassword = ko.observable().extend({
                        required: {
                            params: true,
                            message: "请输入新密码！"
                        }
                    });
                    this.strongLevel = ko.observable(0);
                    this.newPassword.subscribe(function (newValue) {
                        var level = 0;
                        //正则表达式验证符合要求的
                        if (newValue.length > 0) {
                            if (/\d/.test(newValue)) level++; //数字
                            if (/[a-z]/.test(newValue)) level++; //小写
                            if (/[A-Z]/.test(newValue)) level++; //大写
                            if (/\W/.test(newValue)) level++; //特殊字符
                        }
                        if (level > 3) self.strongLevel(newValue.length < 12 ? 3 : 4);
                        else self.strongLevel(level);
                    });
                    this.confirmPassword = ko.observable().extend({
                        required: {
                            params: true,
                            message: "请确认新密码！"
                        },
                        equal: {
                            params: this.newPassword,
                            message: "两次输入的密码不一致！"
                        }
                    });
                }

                var self = this;
                this.password = new PasswordModel();
                this.errors = ko.validation.group(this.password);
                this.result = ko.observable("");

                this.submit = function () {
                    self.result("");
                    if (self.errors().length == 0) {
                        var param = {
                            "old": $.md5(self.password.oldPassword()),
                            "new": $.md5(self.password.newPassword()),
                            "confirm": $.md5(self.password.confirmPassword())
                        };
                        $.post("${BASE_PATH}/submit", param, function (result) {
                            self.result(result);
                        }, 'text');
                    }
                    else self.errors.showAllMessages();
                    return false;
                }
            }

            ko.applyBindings(new ViewModel());
        })();
    </script>
    </@>

</@>