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
    </div>
    <form class="form" method="post" style="padding: 10px 31px;">
        <table>
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>通知标题:</td>
                <td colspan="3"><input data-bind="disable: readonly, textboxValue: C_NAME" required/></td>
            </tr>

            <tr>
                <td>通知编号:</td>
                <td colspan="3"><input data-bind="disable: readonly, textboxValue: C_NUMBER" required/></td>
            </tr>

            <tr>
                <td colspan="4">
                    <textarea data-bind="htmleditValue: htmlContent" style="width: 100%; min-height: 400px"></textarea>
                </td>
            </tr>

            <tr>
                <td>发布部门:</td>
                <td><input data-bind="disable: readonly, textboxValue: C_DEPT_01" required/></td>
                <td>发布日期:</td>
                <td><input data-bind="disable: readonly, dateboxValue: T_DATE_01" required/></td>
            </tr>

            <tr>
                <td>主送部门:</td>
                <td><input data-bind="disable: readonly, textboxValue: C_DEPT_02"/>
                </td>

                <td>抄送部门:</td>
                <td><input data-bind="disable: readonly, textboxValue: C_DEPT_03"/>
                </td>
            </tr>
        </table>
    </form>
    </@>
    <@layout.put block="footer">
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
            R_TEMPLATE: '${file.templateId}',
            R_TMPFILE: '${R_TMPFILE!file.id}'
        };
        var extModel = {
            readonly: ${@READONLY!'false'}
        };
        var events = {
            saveClick: function () {
                if ($form.validate('.form') != true) return;
                utils.messager.showProgress();
                $.post('${BASE_PATH}/save', model, function (result) {
                    utils.messager.closeProgress();
                    if (result.SID) {
                        utils.messager.alert("保存成功", function () {
                            window.location.href = "/sys/stdtmp/file/${file.id}";
                        });
                    } else utils.messager.alert("保存失败");
                }, "json");
            }
        };

        $(function () {
            ko.applyBindings($.extend({}, model, events, extModel));
        });
    </script>
    </@>
</@layout.extends>