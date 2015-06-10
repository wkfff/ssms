<div id="kocontainer">
    <div class="z-toolbar" data-bind="visible:!readonly">
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    </div>
    <form class="form" method="post" style="padding:10px 31px;">
        <div class="easyui-panel" title="概要" style="padding-bottom: 10px;">
            <p class="long-input ue-clear">
                <label>文件名称</label>
            <span class="control">
                <input data-bind="disable: readonly,textboxValue: C_NAME"/>
            </span>
            </p>
        </div>

        <div class="easyui-panel" title="附件" style="padding-bottom: 10px;" data-bind="visible: SID">
            <a href="javascript:void(0);" data-bind="disable: readonly,uploadOptions: {module: 'STDTMP_FILE_05', sid: '${SID}'}">[选择文件]</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable(),
        readonly: ${@READONLY!'false'}
    };
    var settings = {
        htmleditSettings: {
            table: "STDTMP_FILE_05",
            field: 'C_CONTENT',
            sid: '${SID!}',
            readonly: extModel.readonly
        }
    };
    var events = {
        saveClick: function () {
            utils.messager.showProgress();
            $.post('${BASE_PATH}/save', model, function (result) {
                if (result.SID) {
                    settings.htmleditSettings.save(function (editorResult) {
                        $.messager.alert("提示", "保存成功", "info", function () {
                            utils.messager.closeProgress();
                            refreshPanel();
                        });
                    });
                } else {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存失败", "warning");
                }
            }, "json");
        }
    };
    var onPanelLoad = function () {
        ko.applyBindings($.extend({}, model, settings, extModel, events), document.getElementById("kocontainer"));
    };
</script>