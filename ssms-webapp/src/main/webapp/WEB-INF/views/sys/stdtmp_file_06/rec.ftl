<@layout.extends name="../../_layouts/stdtmpfile.ftl">
<@layout.put block="head">
<style type="text/css">
    .kocontainer {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        overflow: auto;
    }

    .form table {
        margin: 5px auto;
        width: 800px;
    }

    .form table tr {
        height: 40px;
    }

    .form table td.label {
        padding-left: 10px;
        text-align: left;
        vertical-align: top;
        line-height: 35px;
        height: 35px;
    }
</style>
</@>
<@layout.put block="panel_content">
    <div class="z-toolbar" data-bind="visible: !readonly">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-bind="click: addItem">新建隐患项目</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-bind="click: remove">删除</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '/sys/stdtmp/file/${R_TMPFILE!pid}'">返回列表</a>

        <a class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="">上一条</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="">下一条</a>
    </div>
    <form class="form">
        <table>
            <tr>
                <td class="label">存在问题</td>
                <td colspan="5"><input data-bind="disable: readonly, textboxValue: C_NAME" required/></td>
            </tr>
            <tr>
                <td class="label">检查人</td>
                <td><input data-bind="disable: readonly, textboxValue: C_EXAMINER" required/></td>
                <td class="label">检查时间</td>
                <td><input data-bind="disable: readonly, dateboxValue: T_EXAMINE" required/></td>
                <td class="label">隐患类型</td>
                <td>
                    <input data-bind="disable: readonly, comboboxSource: typeSource, comboboxValue: P_TYPE, easyuiOptions: levelSettings" required/>
                </td>
            </tr>
            <tr>
                <td class="label">隐患所在区域/部门</td>
                <td colspan="3">
                    <input data-bind="disable: readonly, textboxValue: C_DEPT" required/>
                </td>
                <td class="label">隐患等级</td>
                <td>
                    <input data-bind="disable: readonly, comboboxSource: levelSource, comboboxValue: P_LEVEL, easyuiOptions: levelSettings" required/>
                </td>
            </tr>

            <tr>
                <td class="label">整改措施（包括<br/>工程技术措施、<br/>管理措施、<br/>教育措施、<br/>防护措施、<br/>应急措施）</td>
                <td colspan="5">
                    <textarea data-bind="disable: readonly, textareaValue: C_MEASURE, easyuiOptions: {validType:['length[0,2000]']}" required style="height: 300px;"></textarea>
                </td>
            </tr>
            <tr>
                <td class="label">整改负责人</td>
                <td><input data-bind="disable: readonly, textboxValue: C_RESPONSIBLE" required/></td>
                <td class="label">要求整改日期</td>
                <td><input data-bind="disable: readonly, dateboxValue: T_RECTIFICATION" required/></td>
            </tr>
            <tr>
                <td class="label">治理方案</td>
                <td colspan="5">
                    <textarea data-bind="disable: readonly, textareaValue: C_PLANT, easyuiOptions: {validType:['length[0,2000]']}" required style="height: 300px;"></textarea>
                </td>
            </tr>
            <tr>
                <td class="label">验收部门</td>
                <td><input data-bind="disable: readonly, textboxValue: C_ACCEPTANCE_DEPT"/></td>
                <td class="label">验收人</td>
                <td><input data-bind="disable: readonly, textboxValue: C_ACCEPTANCE"/></td>
                <td class="label">验收时间</td>
                <td><input data-bind="disable: readonly, dateboxValue: T_ACCEPTANCE"/></td>
            </tr>
        <#--<tr>
            <td class="label">隐患闭环情况</td>
            <td>
                <label><input type="radio" name="B_FINISH" data-bind="checked: B_FINISH, enable: allValueOk" value="1"/>已闭环</label>
                <label><input type="radio" name="B_FINISH" data-bind="checked: B_FINISH" value="0"/>未闭环</label>
            </td>
        </tr>-->
        <#--<tr data-bind="visible: SID">
            <td colspan="6" style="padding: 10px;">
                <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'STDTMP_FILE_06', sid: $data.SID}">[选择文件]</a>
            </td>
        </tr>-->
        </table>
    </form>
</@>
<@layout.put block="footer">
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_EXAMINER: ko.observable('${C_EXAMINER!}'),
        T_EXAMINE: ko.observable('${T_EXAMINE!}'),
        P_TYPE: ko.observable('${P_TYPE!}'),
        C_DEPT: ko.observable('${C_DEPT!}'),
        P_LEVEL: ko.observable('${P_LEVEL!}'),
        C_MEASURE: ko.observable('${C_MEASURE!}'),
        C_RESPONSIBLE: ko.observable('${C_RESPONSIBLE!}'),
        T_RECTIFICATION: ko.observable('${T_RECTIFICATION!}'),
        C_PLANT: ko.observable('${C_PLANT!}'),
        C_ACCEPTANCE_DEPT: ko.observable('${C_ACCEPTANCE_DEPT!}'),
        C_ACCEPTANCE: ko.observable('${C_ACCEPTANCE!}'),
        T_ACCEPTANCE: ko.observable('${T_ACCEPTANCE!}'),
        B_FINISH: ko.observable('${B_FINISH!0}'),
        SID: ko.observable('${SID!}'),
        R_TEMPLATE: '${file.templateId}',
        R_TMPFILE: '${R_TMPFILE!pid}'
    };
    var settings = {
        levelSource: ko.observableArray([{code: '01', name: '一般隐患'}, {code: '02', name: '重大隐患'}]),
        typeSource: ko.observableArray([{code: '01', name: '预防'}, {code: '02', name: '纠正'}]),
        levelSettings: {valueField: 'code', textField: 'name'}
    };
    var extModel = {
        allValueOk: ko.computed(function () {
            var hasnovalue;
            for (var obj in model) {
                var value = model[obj];
                if (typeof value === 'function') {
                    if (value() == null || value().length == 0) {
                        hasnovalue = true;
                    }
                }
            }
            return !hasnovalue;
        }),
        readonly: ${@READONLY!'false'}
    };
    var events = {
        saveClick: function () {
            if ($form.validate('.form') == false) return;
            utils.messager.showProgress();
            $.post('save', model, function (result) {
                if (result.SID) {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存成功", "info", function () {
                        model.SID(result.SID);
                    });
                } else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    });
                }
            }, "json");
        },
        addItem: function () {
            window.location.href = "${BASE_PATH}/rec?pid=${R_TMPFILE!pid}";
        },
        remove: function () {
            utils.messager.showProgress();
            $.post('${BASE_PATH}/del', {sid: model.SID()}, function (result) {
                utils.messager.closeProgress();
                $.messager.alert('提示', result == true ? '删除成功' : '删除失败');
                window.location.href = '/sys/stdtmp/file/${R_TMPFILE!pid}';
            }, 'json')
        }
    };
    $(function () {
        ko.applyBindings($.extend({}, model, extModel, settings, events));
    });
</script>
</@>
</@layout.extends>
