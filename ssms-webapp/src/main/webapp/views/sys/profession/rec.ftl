<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        SID: '${SID!}',
    };
    var events = {
        saveClick: function () {
            $.post('save.do', model, function (result) {
                if (result.SID)
                    $.messager.alert("提示", "保存成功", "info", function () {
                        window.location.href ="index.html";
                    });
                else {
                    $.messager.alert("提示", "保存失败", "warning");
                }
            }, "json");
        }
    };
    ko.applyBindings($.extend({}, model, events));
</script>
</#assign>
<@layout.doLayout script>
<div class="z-toolbar">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a class="easyui-linkbutton" onclick="window.location.href='index.html'" plain="true" iconCls="icon-undo">返回</a>
</div>
<form class="form" method="post" style="padding:10px 31px;">
    <div class="easyui-panel" title="专业信息" style="padding-bottom: 10px;">
        <p class="long-input ue-clear">
            <label>专业名称</label>
            <span class="control">
                <input data-bind="textboxValue: C_NAME"/>
            </span>
        </p>
     </div>
</form>
</@layout.doLayout>