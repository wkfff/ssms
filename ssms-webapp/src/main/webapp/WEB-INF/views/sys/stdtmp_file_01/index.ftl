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
                    <input data-bind="disable: readonly, textboxValue: C_NAME" required/>
                </td>
            </tr>

            <tr>
                <td>文件编号:</td>
                <td>
                    <input data-bind="disable: readonly, textboxValue: C_NUMBER"/>
                </td>

                <td>是否受控:</td>
                <td>
                    <label><input type="radio" name="B_CONTROL" data-bind="disable: readonly, checked: B_CONTROL" value="1"/>受控</label>
                    <label><input type="radio" name="B_CONTROL" data-bind="disable: readonly, checked: B_CONTROL" value="0"/>非受控</label>
                </td>
            </tr>

            <tr>
                <td>执行部门:</td>
                <td>
                    <input data-bind="disable: readonly, textboxValue: C_DEPT_01"/>
                </td>
                <td>监督部门:</td>
                <td>
                    <input data-bind="disable: readonly, textboxValue: C_DEPT_02"/>
                </td>
            </tr>

            <tr>
                <td colspan="4">
                <textarea data-bind="htmleditValue: htmlContent"
                          style="width: 100%; min-height: 400px"></textarea>
                </td>
            </tr>

            <tr>
                <td>编制日期:</td>
                <td>
                    <input data-bind="disable: readonly, dateboxValue: T_DATE_01"/>
                </td>
                <td>审核日期:</td>
                <td>
                    <input data-bind="disable: readonly, dateboxValue: T_DATE_02"/>
                </td>
            </tr>

            <tr>
                <td>批准日期:</td>
                <td>
                    <input data-bind="disable: readonly, dateboxValue: T_DATE_03"/>
                </td>
                <td>生效日期:</td>
                <td>
                    <input data-bind="disable: readonly, dateboxValue: T_DATE_04"/>
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
            B_CONTROL: ko.observable('${B_CONTROL!}'),
            C_DEPT_01: ko.observable('${C_DEPT_01!}'),
            C_DEPT_02: ko.observable('${C_DEPT_02!}'),
            T_DATE_01: ko.observable('${T_DATE_01!}'),
            T_DATE_02: ko.observable('${T_DATE_02!}'),
            T_DATE_03: ko.observable('${T_DATE_03!}'),
            T_DATE_04: ko.observable('${T_DATE_04!}'),
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
                    if (result.SID) {
                            utils.messager.closeProgress();
                            $.messager.alert("提示", "保存成功", "info", function () {
                                window.location.href = "/sys/stdtmp/file/"+file.id;
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