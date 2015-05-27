<#--TODO缺少上传照片这个控件--> 
<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model={
            C_USER :ko.observable(),
            C_NAME :ko.observable(),
            S_SEX :ko.observable(),
            S_NAME :ko.observable('${tenant.S_TENANT!}'),
            T_BIRTH :ko.observable(),
            R_SID :ko.observable('${pid!}'),
            SID :'${SID!}'
    }
    var events = {
            saveClick: function () {
                if ($form.validate($('.form'))) {
                    $.post('save',  model, function (result) {
                        if (result.SID)
                            $.messager.alert("提示", "保存成功", "info", function () {
                                window.location.href = 'index?pid=' +${pid!};
                            });
                        else {
                            $.messager.alert("提示", "保存失败", "warning");
                        }
                    }, 'json');
                }
            }
        };

    function doBack() {
         window.location.href = '${Referer!}';
    }
    ko.applyBindings($.extend({}, model, events));
</script>
</#assign> <@layout.doLayout script>
<div class="easyui-panel" title="企业用户信息">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true"
            iconCls="icon-save" data-bind="click: saveClick">保存</a> <a
            href="#" class="easyui-linkbutton"
            data-options="plain: true" iconCls="icon-undo"
            onclick="doBack()">返回</a>
    </div>
    <form class="form" style="padding: 20px">
        <div class="easyui-panel form" style="padding: 10px"
            title="基本信息">
            <p class="long-input ue-clear">
                <label>用户名:</label> <span class="control"> <input
                    data-bind="textboxValue: C_USER" required />
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>姓名:</label> <span class="control"> <input
                    data-bind="textboxValue: C_NAME" required />
                </span>
            </p>

            <p class="ue-clear">
                <label>出生日期:</label> <span class="control"> <input
                    data-bind="dateboxValue: T_BIRTH" required />
                </span>
            </p>

            <p class="ue-clear">
                <label>性别:</label> <span class="control"> <label><input
                        type="radio" name="S_SEX"
                        data-bind="checked: S_SEX" value="男" />男</label> <label><input
                        type="radio" name="S_SEX"
                        data-bind="checked: S_SEX" value="女" />女</label>
                </span>
            </p>
        </div>
    </form>
</div>
</@layout.doLayout>
