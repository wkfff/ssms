<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_NUMBER: ko.observable('${C_NUMBER!}'),
        B_CONTROL: ko.observable('${B_CONTROL!}'),
        C_DEPT_01: ko.observable('${C_DEPT_01!}'),
        C_DEPT_02: ko.observable('${C_DEPT_02!}'),
        T_DATE_01: ko.observable('${T_DATE_01!}'),
        T_DATE_02: ko.observable('${T_DATE_02!}'),
        T_DATE_03: ko.observable('${T_DATE_03!}'),
        T_DATE_04: ko.observable('${T_DATE_04!}'),
        SID: '${SID!}',
        R_TMPFILE: '${R_TMPFILE!sid}'
    };
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "STDTMP_FILE_01",
            field: 'C_CONTENT',
            sid: '${SID!}'
        }
    };
    var events = {
        saveClick: function () {
            if ($form.validate('.form') != true) return;
            utils.messager.showProgress();
            $.post('save', model, function (result) {
                if (result.SID) {
                    if (result.SID != settings.htmleditSettings.sid) settings.htmleditSettings.sid = result.SID;
                    settings.htmleditSettings.save(function (editorResult) {
                        utils.messager.closeProgress();
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = 'index?SID=' + result.SID + "&backURL=${backURL!referer!}";
                        });
                    });
                } else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    });
                }
            }, "json");
        }
    };

    $(function () {
        ko.applyBindings($.extend({}, model, events, settings, extModel));
    });
</script>
<style type="text/css">
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
</#assign>
<@layout.doLayout header=script>
<div class="z-toolbar">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo"
       onclick="window.location.href = '${referer!}'">返回</a>
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
            <td>文件名称:</td>
            <td colspan="3">
                <input data-bind="textboxValue: C_NAME" required/>
            </td>
        </tr>

        <tr>
            <td>文件编号:</td>
            <td>
                <input data-bind="textboxValue: C_NUMBER"/>
            </td>

            <td>是否受控:</td>
            <td>
                <label><input type="radio" name="B_CONTROL" data-bind="checked: B_CONTROL" value="1"/>受控</label>
                <label><input type="radio" name="B_CONTROL" data-bind="checked: B_CONTROL" value="0"/>非受控</label>
            </td>
        </tr>

        <tr>
            <td>执行部门:</td>
            <td>
                <input data-bind="textboxValue: C_DEPT_01"/>
            </td>
            <td>监督部门:</td>
            <td>
                <input data-bind="textboxValue: C_DEPT_02"/>
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings"
                          style="width: 100%; min-height: 400px"></textarea>
            </td>
        </tr>

        <tr>
            <td>编制日期:</td>
            <td>
                <input data-bind="dateboxValue: T_DATE_01"/>
            </td>
            <td>审核日期:</td>
            <td>
                <input data-bind="dateboxValue: T_DATE_02"/>
            </td>
        </tr>

        <tr>
            <td>批准日期:</td>
            <td>
                <input data-bind="dateboxValue: T_DATE_03"/>
            </td>
            <td>生效日期:</td>
            <td>
                <input data-bind="dateboxValue: T_DATE_04"/>
            </td>
        </tr>
    </table>
</form>
</@>