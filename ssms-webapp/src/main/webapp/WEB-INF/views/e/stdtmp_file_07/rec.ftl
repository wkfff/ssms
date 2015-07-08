<style type="text/css">
    .form table {
        width: 100%;
        margin: 5px auto;
        table-layout: fixed;
    }

    .form table tr {
        height: 40px;
    }

    .form table td {
        padding-right: 10px;
    }

    .form table .label {
        width: 90px;
    }
</style>
<div id="kocontainer">
    <div class="z-toolbar" data-bind="visible:!readonly">
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <#if !todo??>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="panelLoad('${BASE_PATH}/?fileid=${file.id}');">返回</a>
        </#if>
        <#if file.templateModel??>
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-search" data-bind="click: function(){window.open('/sys/stdtmp/file/view/${file.sourceFile.id}')}">查看模板</a>
        </#if>
    </div>

    <form class="form" method="post" style="padding:10px 31px;">
        <table cellspadding="10" cellspading="10" border="1">
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>姓名:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NAME" required/>
                </td>
                <td>性别:</td>
                <td>
                    <label><input type="radio" name="P_SEX" data-bind="disable: readonly,checked: P_SEX" value="1"/>男</label>
                    <label><input type="radio" name="P_SEX" data-bind="disable: readonly,checked: P_SEX" value="2"/>女</label>
                </td>
            </tr>

            <tr>
                <td>身份证号码:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_CARD"/>
                </td>
                <td>所在部门:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_DEPT"/>
                </td>
            </tr>

            <tr>
                <td>工种:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_WORKTYPE"/>
                </td>
                <td>发证机关:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_AUTH"/>
                </td>
            </tr>

            <tr>
                <td>证书编号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_CERT"/>
                </td>
                <td>取证时间:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_CERT_GET"/>
                </td>
            </tr>

            <tr>
                <td>复审时间:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_CERT_REVIEW"/>
                </td>
                <td></td>
                <td></td>
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
        </table>
    </form>
</div>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        P_SEX: ko.observable('${P_SEX!1}'),
        C_CARD: ko.observable('${C_CARD!}'),
        C_DEPT: ko.observable('${C_DEPT!}'),
        C_WORKTYPE: ko.observable('${C_WORKTYPE!}'),
        C_AUTH: ko.observable('${C_AUTH!}'),
        T_CERT_GET: ko.observable('${T_CERT_GET!}'),
        C_CERT: ko.observable('${C_CERT!}'),
        T_CERT_REVIEW: ko.observable('${T_CERT_REVIEW!}'),
        SID: '${SID!}',
        R_TMPFILE: '${file.id}'
    };
    var extModel = {
        readonly: ${@READONLY!'false'}
    };
    var settings = {};
    var events = {
        saveClick: function () {
            if ($form.validate('.form') != true) return;
            utils.messager.showProgress();
            $.post('${BASE_PATH}/save', model, function (result) {
                if (result.SID) {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存成功", "info");
                } else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    });
                }
            }, "json");
        }
    };

    var onPanelLoad = function () {
        ko.applyBindings($.extend({}, model, events, settings, extModel), document.getElementById('kocontainer'));
    };
</script>