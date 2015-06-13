<style type="text/css">
    .form {
        margin: 0 auto;
        width: 80%;
    }

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
<div id="kocontainer">
    <div class="z-toolbar" data-bind="visible:!readonly">
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a class="easyui-linkbutton"  plain="true" iconCls="icon-word" data-bind="click: function(){}">导出</a>
        <#if TEMPLATE_ID??>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: function(){window.open('/sys/stdtmp_file_03/view?sid=${TEMPLATE_ID}')}">查看模板</a>
        </#if> 
    </div>
    <form class="form" method="post" style="padding:10px 31px;">
        <table>
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>文件名称</td>
            <td colspan="3">
                <input data-bind="disable: readonly,textboxValue: C_NAME"/>
            </td>
            </tr>
        
        <tr>
            <td colspan="4">
            <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="4" data-bind="visible: SID">
            <a href="javascript:void(0);" data-bind="disable: readonly,uploadOptions: {module: 'STDTMP_FILE_03', sid: '${SID!}'}">[选择文件]</a>
            </td>
        </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
function ViewModel(catalogId){
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        R_TMPFILE: '${R_TMPFILE!sid}',
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable(),
        readonly: ${@READONLY!'false'}
    };
    var settings = {
        htmleditSettings: {
            table: "SSM_STDTMP_FILE_03",
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
        $.extend(this, model, settings, extModel,events);
}
var onPanelLoad = function () {
    ko.applyBindings(new ViewModel(${sid}), document.getElementById('kocontainer'));
};
</script>
