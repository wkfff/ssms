<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        B_CONTROL: ko.observable('${B_CONTROL!0}'),
        C_NUMBER: ko.observable('${C_NUMBER!}'),
        C_DEPT_01: ko.observable('${C_DEPT_01!}'),
        C_DEPT_02: ko.observable('${C_DEPT_02!}'),
        T_DATE_01: ko.observable('${T_DATE_01!}'),
        T_DATE_02: ko.observable('${T_DATE_02!}'),
        T_DATE_03: ko.observable('${T_DATE_03!}'),
        SID: '${SID!}'
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
            utils.messager.showProgress();
            $.post('save', model, function (result) {
                if (result.SID) {
                    if (result.SID != settings.htmleditSettings.sid) settings.htmleditSettings.sid = result.SID;
                    settings.htmleditSettings.save(function (editorResult) {
                        utils.messager.closeProgress();
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = 'rec?sid=' + result.SID + "&backURL=${backURL!referer!}";
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
</#assign>
<@layout.doLayout script>
<div class="z-toolbar">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '${backURL!referer}'">返回</a>
</div>
<form class="form" method="post" style="padding:10px 31px;">
    <table>
        <tr>
             <td>文件名称:</td>
             <td>
                <input data-bind="textboxValue: C_NAME"/>
             </td>
        </tr>
       
        <tr>
            <td>是否受控:</td>
            <td>
                <label><input type="radio" name="B_CONTROL" data-bind="checked: B_CONTROL" value="1"/>受控</label>
                <label><input type="radio" name="B_CONTROL" data-bind="checked: B_CONTROL" value="0"/>非受控</label>
            </td>
        </tr>

        <tr>
            <td>文件编号:</td>
            <td>
                <input data-bind="textboxValue: C_NUMBER"/>
            </td>
        </tr>

        <tr>
            <td>执行部门:</td>
            <td>
                <input data-bind="textboxValue: C_DEPT_01"/>
            </td>
        </tr>

        <tr>
            <td>监督部门:</td>
            <td>
                <input data-bind="textboxValue: C_DEPT_02"/>
            </td>
        </tr>

        <tr>
        <td colspan="4">
        <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
        </td>
        </tr>

        <tr>
            <td>编制日期:</td>
            <td>
                <input data-bind="dateboxValue: T_DATE_01"/>
            </td>
        </tr>

        <tr>
            <td>审核日期:</td>
            <td>
                <input data-bind="dateboxValue: T_DATE_02"/>
            </td>
        </tr>

        <tr>
            <td>评审日期:</td>
            <td>
                <input data-bind="dateboxValue: T_DATE_03"/>
            </td>
        </tr>
    </table>
</form>
</@layout.doLayout>