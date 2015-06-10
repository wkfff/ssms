<#--TODO缺少上传照片这个控件-->
<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model={
            C_NAME :ko.observable('${C_NAME!}'),
            S_SEX :ko.observable('${S_SEX!}'),
            S_NAME :ko.observable('${tenantName!}'),
            T_BIRTH :ko.observable('${T_BIRTH!}'),
            R_SID :ko.observable('${tenantId!}'),
            SID :'${SID!}'
    }
    var events = {
            saveClick: function () {
                if ($form.validate($('.form'))) {
                    $.post('save',  model, function (result) {
                        if (result.SID)
                            $.messager.alert("提示", "保存成功", "info", function () {
                                window.location.href = window.location.href;
                            });
                        else {
                            $.messager.alert("提示", "保存失败", "warning");
                        }
                    }, 'json');
                }
            }
     };
    ko.applyBindings($.extend({}, model, events));
</script>
</#assign> <@layout.doLayout script>
<div class="easyui-panel" title="企业用户信息">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true"
            iconCls="icon-save" data-bind="click: saveClick">保存</a> 
    </div>
    <form class="form" style="padding: 20px">
        <div class="easyui-panel form" style="padding: 10px"
            title="基本信息">
            <p class="ue-clear">
                <label>用户名:</label> <span class="control"> <input
                    class="readonly" type="text" value="${C_USER}"
                    readonly />
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
