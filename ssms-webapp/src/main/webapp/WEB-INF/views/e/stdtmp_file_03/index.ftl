<div id="kocontainer">
    <div class="z-toolbar">
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a class="easyui-linkbutton"  plain="true" iconCls="icon-word" onclick="alert('还未开发')">导出(WORD)</a>
        <span><a class="easyui-linkbutton"  plain="true"  onClick="alert('还未开发')">模板查看</a></span>
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
        <div class="easyui-panel" title="正文" style="padding: 6px">
            <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
        </div>

        <div class="easyui-panel" title="附件" style="padding-bottom: 10px;">
            <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'STDTMP_FILE_03', sid: '${SID!}'}">[选择文件]</a>
        </div>
    </form>
</div>
<script type="text/javascript">
function ViewModel(catalogId){
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        R_TMPFILE: '${R_TMPFILE!}',
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "STDTMP_FILE_03",
            field: 'C_CONTENT',
            sid: '${SID!}'
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
        $.extend(this, model, settings, extModel,events);
}
var onPanelLoad = function () {
    ko.applyBindings(new ViewModel(${sid}), document.getElementById('kocontainer'));
};
</script>