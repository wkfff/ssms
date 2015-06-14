<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_TITLE: ko.observable('${C_TITLE!}'),
        C_SENDEE: ko.observable('${C_SENDEE!}'),
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable(),
        readonly: 'true'
    };
    var settings = {
        htmleditSettings: {
            table: "SSM_NOTICE",
            field: 'C_CONTENT',
            sid: '${SID!}',
            readonly: extModel.readonly
        }
    };
    var events = {

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

    <#--<a href="#" class="easyui-linkbutton" plain="true">关闭</a>-->
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
                <textarea data-bind="disable: readonly,textboxValue: C_SENDEE"
                          style="width: 100%; min-height: 80px"></textarea>
            </td>
        </tr>
        <tr>
            <td>主题:</td>
            <td colspan="3">
                <input data-bind="disable: readonly,textboxValue: C_TITLE" required/>
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