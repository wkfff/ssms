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
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: saveClick">发布</a>
        <#if SID??>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-pdf" data-bind="click: function(){ window.location.href='${BASE_PATH}/export/${SID}' }">导出</a>
        </#if>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-back" data-bind="click: function(){panelLoad('${BASE_PATH}/?fileid=${file.id}')}">返回列表</a>
        <#if file.templateModel??>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: function(){window.open('/sys/stdtmp/file/view/${file.sourceFile.id}')}">查看模板</a>
        </#if>

        <span style="position: absolute; right: 30px;">
            编辑状态：<span style="font-size: 12px; font-weight: bold; color: red"><#if SID??>编辑<#else>新增</#if></span>
        </span>
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
                <td>通知标题:</td>
                <td colspan="3">
                    <input data-bind="disable: readonly,textboxValue: C_NAME" required/>
                </td>
            </tr>

            <tr>
                <td>通知编号:</td>
                <td colspan="3">
                    <input data-bind="disable: readonly,textboxValue: C_NUMBER" required/>
                </td>
            </tr>

            <tr>
                <td colspan="4">
                    <textarea data-bind="htmleditValue: htmlContent" style="width: 100%; min-height: 400px"></textarea>
                </td>
            </tr>

            <tr>
                <td>发布部门:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_DEPT_01" required/>
                </td>
                <td>发布日期:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_DATE_01" required/>
                </td>
            </tr>

            <tr>
                <td>主送部门:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_DEPT_02"/>
                </td>

                <td>抄送部门:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_DEPT_03"/>
                </td>
            </tr>
            <#if file.explain?? && file.explain?length!=0>
            <tr>
            <td colspan="4">
                <div style="border: 1px dashed  #ccc; margin-bottom: 5px;position: relative;">
                <div style="background-color: #CCCCCC;">政策解读:</div>
                <table style="table-layout: auto;"></table>
                ${file.explain}
                </div>
            </td>
            </tr>
            </#if>
            <tr>
                <td colspan="4" data-bind="disable: readonly,visible: SID">
                    <a href="javascript:void(0);" data-bind="disable: readonly,uploadOptions: {module: 'STDTMP_FILE_02', sid: '${SID!}'}">[选择文件]</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_NUMBER: ko.observable('${C_NUMBER!}'),
        C_DEPT_01: ko.observable('${C_DEPT_01!}'),
        T_DATE_01: ko.observable('${T_DATE_01!}'),
        C_DEPT_02: ko.observable('${C_DEPT_02!}'),
        C_DEPT_03: ko.observable('${C_DEPT_03!}'),
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
                        panelLoad('${BASE_PATH}/rec?SID=' + result.SID);
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
