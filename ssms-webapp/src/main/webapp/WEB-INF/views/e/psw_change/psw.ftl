<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript">
    var model = {
        oldPwd: ko.observable(),
        newPwd: ko.observable(),
        confirmPwd: ko.observable()
    };
    var events = {
        updateClick: function () {
            if (model.newPwd() != model.confirmPwd()) {
                $.messager.alert("提示", "两次输入密码不一致");
                return false;
            } else {
                model.oldPwd($.md5(model.oldPwd()));
                model.newPwd($.md5(model.newPwd()));
                $.post('updatePSW', {
                    oldPwd: model.oldPwd(),
                    newPwd: model.newPwd()
                }, function (flag) {
                    if (flag) {
                        $.messager.alert("提示", "密码修改成功", 'info', function () {
                            window.location.href = window.location.href;
                        })
                    } else {
                        $.messager.alert("提示", "密码修改失败")
                    }
                }, 'json');
            }
        }
    };
    ko.applyBindings($.extend({}, model, events));
</script>
<style type="text/css">
    .form table {
        width: 100%;
        margin: 5px auto;
        table-layout: fixed;
    }

    .form table tr {
        height: 40px;
    }

    .form table .label {
        width: 90px;
    }
</style>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" title="密码修改">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: updateClick">修改</a>
    </div>
    <form class="form" style="padding: 20px">
        <table>
            <colgroup>
                  <col class="label"/>
                  <col/>
                  <col class="label"/>
            <col/>
            </colgroup>
            <tr>
                <td>旧密码</td>
                <td colspan='2'>
                    <input data-bind="textboxValue: oldPwd" type="password" required/>
                </td>
            </tr>

            <tr>
                <td>新密码</td>
                <td colspan='2'>
                    <input data-bind="textboxValue: newPwd" type="password" required/>
                </td>
            </tr>

            <tr>
                <td>确认密码</td>
                <td colspan='2'>
                    <input data-bind="textboxValue: confirmPwd" type="password" required/>
                </td>
            </tr>
        </table>
    </form>
</div>

</@>