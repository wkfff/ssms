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
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-pdf"
           data-bind="click: function(){}">导出</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-back"
           onclick="panelLoad('${BASE_PATH}/?fileid=${file.id}');">返回列表</a>
        <#if file.templateModel.id??>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: function(){window.open('/sys/stdtmp_file_04/view?sid=${file.templateModel.id}')}">查看模板</a>
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
                <td>主题:</td>
                <td colspan="3">
                    <input data-bind="disable: readonly,textboxValue: C_NAME" required/>
                </td>
            </tr>

            <tr>
                <td>培训日期:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_TIME"/>
                </td>

                <td>培训地址:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_ADDR"/>
                </td>
            </tr>

            <tr>
                <td>讲师:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_USER_01"/>
                </td>

                <td>培训类型:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: S_TYPE"/>
                </td>
            </tr>

            <tr>
                <td>学时:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: N_TIME"/>
                </td>

                <td>记录人:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_USER_02"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea data-bind="htmleditValue: htmlContent"
                              style="width: 100%; min-height: 400px"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="4" data-bind="disable: readonly,visible: SID">
                    <a href="javascript:void(0);" data-bind="disable: readonly,uploadOptions: {module: 'STDTMP_FILE_04', sid: '${SID!}'}">[选择文件]</a>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div>
                        <div style="background-color: #CCCCCC; margin-top: 5px">操作指南：</div>
                        <span style="color: #ff0000; font-weight: bold">请根据要求将本要素相关记录完成并上传.(可将参考模板在线修改下载执行)</span>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        T_TIME: ko.observable('${T_TIME!}'),
        C_USER_01: ko.observable('${C_USER_01!}'),
        C_ADDR: ko.observable('${C_ADDR!}'),
        S_TYPE: ko.observable('${S_TYPE!}'),
        N_TIME: ko.observable('${N_TIME!}'),
        C_USER_02: ko.observable('${C_USER_02!}'),
        htmlContent: ko.observable(${json(C_CONTENT)}),
        SID: '${SID!}',
        R_TMPFILE: '${file.id}'
    };
    var extModel = {
        readonly: ${@READONLY!'false'}
    };
    var events = {
        saveClick: function () {
            if ($form.validate('.form') != true) return;
            utils.messager.showProgress();
            $.post('${BASE_PATH}/save', model, function (result) {
                if (result.SID) {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存成功", "info");
                }else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    }); 
                }
            }, "json");
        }
    };

    var onPanelLoad = function () {
        var vm = $.extend({}, model, extModel, events);
        ko.applyBindings(vm, document.getElementById('kocontainer'));
    }
</script>