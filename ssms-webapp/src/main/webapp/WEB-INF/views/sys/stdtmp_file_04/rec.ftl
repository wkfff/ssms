<@layout.extends name="../../_layouts/stdtmpfile.ftl">
<@layout.put block="head">
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
</@>
<@layout.put block="panel_content">
<div class="z-toolbar" data-bind="visible: !readonly">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '/sys/stdtmp/file/${R_TMPFILE!pid}'">返回</a>
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
                <input data-bind="disable: readonly,textboxValue: C_TIME"/>
            </td>

            <td>记录人:</td>
            <td>
                <input data-bind="disable: readonly,textboxValue: C_USER_02"/>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <textarea data-bind="htmleditValue: htmlContent" style="width: 100%; min-height: 400px"></textarea>
            </td>
        </tr>
    </table>
</form>
</@>
<@layout.put block="footer">
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        T_TIME: ko.observable('${T_TIME!}'),
        C_USER_01: ko.observable('${C_USER_01!}'),
        C_ADDR: ko.observable('${C_ADDR!}'),
        S_TYPE: ko.observable('${S_TYPE!}'),
        C_TIME: ko.observable('${C_TIME!}'),
        C_USER_02: ko.observable('${C_USER_02!}'),
        htmlContent: ko.observable(${json(C_CONTENT)}),
        SID: '${SID!}',
        R_TEMPLATE: '${file.templateId}',
        R_TMPFILE: '${R_TMPFILE!pid}'
    };
    var extModel = {
        readonly: ${@READONLY!'false'}
    };
    var settings = {
        htmleditSettings: {
            table: "SYS_STDTMP_FILE_04",
            field: 'C_CONTENT',
            sid: '${SID!}',
            readonly: extModel.readonly
        }
    };
    var events = {
        saveClick: function () {
            if ($form.validate('.form') != true) return;
            utils.messager.showProgress();
            $.post('${BASE_PATH}/save', model, function (result) {
                if (result.SID) {
                        utils.messager.closeProgress();
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = 'rec?sid=' + result.SID +'&pid=${pid}'+ "&backURL=${backURL!referer!}";
                        });
                }else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    });
                }
            }, "json");
        }
    };

    $(function () {
        ko.applyBindings($.extend({}, model, events, extModel));
    });
</script>
</@>
</@layout.extends>




