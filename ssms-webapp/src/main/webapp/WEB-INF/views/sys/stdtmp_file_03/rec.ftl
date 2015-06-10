<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
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
            $.post('save', model, function (result) {
                if (result.SID) {
                    if (result.SID != settings.htmleditSettings.sid) settings.htmleditSettings.sid = result.SID;
                    settings.htmleditSettings.save(function (editorResult) {
                        utils.messager.closeProgress();
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = 'rec?sid=' + result.SID + "&backUrl=${backUrl!}";
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
<@layout.doLayout script>
<div class="z-toolbar">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a class="easyui-linkbutton"  plain="true" iconCls="icon-word" onclick="alert('还未开发')">导出</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '${backUrl}'">返回</a>
    <span><a class="easyui-linkbutton"  plain="true" iconCls="icon-temp"  onclick="alert('还未开发')">模板查看</a></span>
</div>
<form class="form" method="post" style="padding:10px 31px;">
    <table >
        <colgroup>
            <col class="label"/>
            <col/>
            <col class="label"/>
            <col/>
        </colgroup>
        <tr >
            <td>文件名称:</td>
            <td colspan="3">
                <input data-bind="textboxValue: C_NAME" required/>
            </td>
        </tr>
     </table>
     <table >
        <tr >
            <td colspan="4">
                <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
            </td>
        </tr>
    </table>
    
    <div>
    操作指南：
    </div>
</form>
</@>