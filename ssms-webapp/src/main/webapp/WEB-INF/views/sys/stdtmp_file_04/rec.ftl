<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        T_TIME: ko.observable('${T_TIME!}'),
        C_USER_01: ko.observable('${C_USER_01!}'),
        C_ADDR: ko.observable('${C_ADDR!}'),
        S_TYPE: ko.observable('${S_TYPE!}'),
        N_TIME: ko.observable('${N_TIME!}'),
        C_USER_02: ko.observable('${C_USER_02!}'),
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "STDTMP_FILE_04",
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
        ko.applyBindings($.extend({}, model, settings, extModel, events));
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
            <td>培训日期:</td>
            <td>
                <input data-bind="dateboxValue: T_TIME"/>
            </td>
        </tr>

        <tr>
            <td>教育人:</td>
            <td>
                <input data-bind="textboxValue: C_USER_01"/>
            </td>
        </tr>

        <tr>
            <td>培训地址:</td>
            <td>
                <input data-bind="textboxValue: C_ADDR"/>
            </td>
        </tr>

        <tr>
            <td>培训种类:</td>
            <td>
                <input data-bind="textboxValue: S_TYPE"/>
            </td>
        </tr>

        <tr>
            <td>学时:</td>
            <td>
                <input data-bind="textboxValue: N_TIME"/>
            </td>
        </tr>

        <tr>
            <td>记录人:</td>
            <td>
                <input data-bind="textboxValue: C_USER_02"/>
            </td>
        </tr>

        <tr>
             <td colspan="4">
                  <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
             </td>
        </tr>
    </table>
</form>
</@>