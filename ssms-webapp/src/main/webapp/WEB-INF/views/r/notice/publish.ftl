<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_TITLE: ko.observable('${C_TITLE!}'),
        <#--C_SENDEE: ko.observable('${SENDEE!}'),-->
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "SSM_NOTICE",
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
                            window.location.href = 'rec?sid=' + result.SID + "&backURL=${backURL!referer!}";
                        });
                    });
                } else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    });
                }
            }, "json");
        },
        draftsClick: function () {
            if ($form.validate('.form') != true) return;
            utils.messager.showProgress();
            $.post('save?drafts=1', model, function (result) {
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
    <a href="#" class="easyui-linkbutton" plain="true"  data-bind="click: draftsClick">存为草稿</a>
    <a href="#" class="easyui-linkbutton" plain="true">关闭</a>
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
            <td>接收单位:</td>
            <td colspan="3">
                <div style="display:inline">
                    <span style="float:left">
                        <input type="checkbox" name="qx" value="1"/>所有辖区评审单位
                        <input type="checkbox" name="qx" value="2"/>所有辖区政府
                        <input type="checkbox" name="qx" value="3"/>所有辖区企业
                    </span>
                    <#--<span style="float:left;"><a href="#" class="easyui-linkbutton" plain="true">添加接收单位</a></span>-->
                </div>
            </td>
        </tr>
        <#--<tr>-->
            <#--<td></td>-->
            <#--<td colspan="3">-->
                <#--<textarea data-bind="textboxValue: C_SENDEE"-->
                          <#--style="width: 100%; min-height: 80px"></textarea>-->
            <#--</td>-->
        <#--</tr>-->
        <tr>
            <td>主题:</td>
            <td colspan="3">
                <input data-bind="textboxValue: C_TITLE" required/>
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings"
                          style="width: 100%; min-height: 400px"></textarea>
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <a href="javascript:void(0);"
                   data-bind="uploadOptions: {module: 'SSM_NOTICE', sid: '${SID!}'}">[选择文件]</a>
            </td>
        </tr>
    </table>
</form>
</@>