<style type="text/css">
    .form table {
        width: 100%;
        margin: 5px auto;
        table-layout: fixed;
    }

    .form table tr {
        height: 40px;
    }

    .form table td {
        padding-right: 10px;
    }

    .form table .label {
        width: 90px;
    }
</style>
<div id="kocontainer">
    <div class="z-toolbar" data-bind="visible:!readonly">
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="panelLoad('${BASE_PATH}/?fileid=${file.id}');">返回</a>
        <#if file.templateModel??>
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-search" data-bind="click: function(){window.open('/sys/stdtmp/file/view/${file.sourceFile.id}')}">查看模板</a>
        </#if>
    </div>

    <form class="form" method="post" style="padding:10px 31px;">
        <table cellspadding="10" cellspading="10" border="1">
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>

            <tr>
                <td>设备名称:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NAME" required/>
                </td>
                <td>登记证号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NO_REG"/>
                </td>
            </tr>

            <tr>
                <td>规格/型号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_SPEC"/>
                </td>
                <td>制造单位:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_MAKE_UNIT"/>
                </td>
            </tr>

            <tr>
                <td>出厂编号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NO_FACTORY"/>
                </td>
                <td>使用部门:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_DEPT"/>
                </td>
            </tr>

            <tr>
                <td>检验报告编号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NO_REP"/>
                </td>
                <td>检验单位:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_TEST_UNIT"/>
                </td>
            </tr>

            <tr>
                <td>最新检验日期:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_TEST_LAST" required/>
                </td>
                <td>下次检验日期:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_TEST_NEXT" required/>
                </td>
            </tr>
            <tr>
                <td>检验结论</td>
                <td colspan=3><input data-bind="disable: readonly,textboxValue: C_TEST_CON"/></td>
            </tr>
            <#if file.explain?? && file.explain?length!=0>
            <tr>
            <td colspan="4">
                <div style="border: 1px dashed  #ccc; margin-bottom: 5px;position: relative;">
                <div style="background-color: #CCCCCC;">政策解读:</div>
                <table style="table-layout: auto;"></table>
                ${file.explain}
                </div>
            </td>
            </tr>
            </#if>
        </table>
    </form>
</div>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_NO_REG: ko.observable('${C_NO_REG!}'),
        C_SPEC: ko.observable('${C_SPEC!}'),
        C_MAKE_UNIT: ko.observable('${C_MAKE_UNIT!}'),
        C_NO_FACTORY: ko.observable('${C_NO_FACTORY!}'),
        C_DEPT: ko.observable('${C_DEPT!}'),
        T_TEST_LAST: ko.observable('${T_TEST_LAST!}'),
        C_NO_REP: ko.observable('${C_NO_REP!}'),
        T_TEST_NEXT: ko.observable('${T_TEST_NEXT!}'),
        C_TEST_CON: ko.observable('${C_TEST_CON!}'),
        C_TEST_UNIT: ko.observable('${C_TEST_UNIT!}'),
        SID: '${SID!}',
        R_TMPFILE: '${file.id}'
    };
    var extModel = {
        readonly: ${@READONLY!'false'}
    };
    var settings = {};
    var events = {
        saveClick: function () {
            if ($form.validate('.form') != true) return;
            utils.messager.showProgress();
            $.post('${BASE_PATH}/save', model, function (result) {
                if (result.SID) {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存成功", "info");
                } else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    });
                }
            }, "json");
        }
    };

    var onPanelLoad = function () {
        ko.applyBindings($.extend({}, model, events, settings, extModel), document.getElementById('kocontainer'));
    };
</script>