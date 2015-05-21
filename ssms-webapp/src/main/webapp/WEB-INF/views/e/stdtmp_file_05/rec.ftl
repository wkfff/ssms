<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "STDTMP_FILE_05",
            field: 'C_CONTENT',
            sid: '${SID!}'
        }
    };
    var events = {
        saveClick: function () {
            utils.messager.showProgress();
            $.post('save', model, function (result) {
                if (result.SID) {
                    settings.htmleditSettings.save(function (editorResult) {
                        $.messager.alert("提示", "保存成功", "info", function () {
                            utils.messager.closeProgress();
                            window.location.href = 'rec?sid=' + result.SID + "&backURL=${backURL!referer!}";
                        });
                    });
                } else {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存失败", "warning");
                }
            }, "json");
        }
    };
    $(function () {
        ko.applyBindings($.extend({}, model, settings, extModel, events));
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="z-toolbar">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
</div>
<form class="form" method="post" style="padding:10px 31px;">
    <div class="easyui-panel" title="概要" style="padding-bottom: 10px;">
        <p class="long-input ue-clear">
            <label>文件名称</label>
            <span class="control">
                <input data-bind="textboxValue: C_NAME"/>
            </span>
        </p>
    </div>
</form>
</@>