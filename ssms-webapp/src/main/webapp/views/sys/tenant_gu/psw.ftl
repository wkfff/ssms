<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript">
    var model = {
        oldPwd: ko.observable(),
        newPwd: ko.observable(),
        confirmPwd: ko.observable(),
        SID: ko.observable('${sid!}')
    };
    var events = {
        updateClick: function () {
            if (model.newPwd() != model.confirmPwd()) {
                $.messager.alert("提示", "两次输入密码不一致");
                return false;
            } else {
                model.oldPwd($.md5(model.oldPwd()));
                model.newPwd($.md5(model.newPwd()));
                $.post('updtePSW.do', {
                    oldPwd: model.oldPwd(),
                    newPwd: model.newPwd(),
                    SID: model.SID()
                }, function (flag) {
                    if (flag) {
                        $.messager.alert("提示", "密码修改成功", 'info', function () {
                            window.location.href = '${Referer!}';
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
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" title="密码修改">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: updateClick">修改</a>
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="window.location.href='${Referer!}';">返回</a>
    </div>
    <form class="form" style="padding: 20px">
        <div class="easyui-panel form" style="padding:10px" title="基本信息">
            <p class="long-input ue-clear">
                <label>旧密码</label>
                <span class="control">
                    <input data-bind="textboxValue: oldPwd" type="password" required/>
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>新密码</label>
                <span class="control">
                    <input data-bind="textboxValue: newPwd" type="password" required/>
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>确认密码</label>
                <span class="control">
                    <input data-bind="textboxValue: confirmPwd" type="password"  required/>
                </span>
            </p>
        </div>
    </form>
</div>

</@>