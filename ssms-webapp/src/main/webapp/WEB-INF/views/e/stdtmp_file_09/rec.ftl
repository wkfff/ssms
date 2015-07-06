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
        padding-right:10px;
    }
    

    .form table .label {
        width: 90px;
    }
</style>
<div id="kocontainer">
    <div class="z-toolbar" data-bind="visible:!readonly">
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="panelLoad('${BASE_PATH}/?fileid=${file.id}');">返回</a>
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
                <td>安全附件名称:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NAME" required/>
                </td>
                 <td>型号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_SPEC"/>
                </td>
            </tr>

            <tr>
                <td>出厂编号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NO_FACTORY"/>
                </td>
                <td>所属特种设备安装位置:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_POSITION"/>
                </td>
            </tr>
           
            <tr>
                <td>检验日期:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_TEST_LAST"/>
                </td>
                <td>下次检验日期:</td>
                <td>
                    <input data-bind="disable: readonly,dateboxValue: T_TEST_NEXT"/>
                </td>
            </tr>
            
            <tr>
                <td>检验报告编号:</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_NO_REP"/>
                </td>
                <td>检验单位</td>
                <td>
                    <input data-bind="disable: readonly,textboxValue: C_TEST_UNIT"/>
                </td>
            </tr>
             <tr>
                <td>检验结论</td>
                <td colspan=3><input data-bind="disable: readonly,textboxValue: C_TEST_CON"/></td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_SPEC: ko.observable('${C_SPEC!}'),
        C_NO_FACTORY: ko.observable('${C_NO_FACTORY!}'),
        C_POSITION: ko.observable('${C_POSITION!}'),
        T_TEST_LAST: ko.observable('${T_TEST_LAST!}'),
        T_TEST_NEXT: ko.observable('${T_TEST_NEXT!}'),
        C_NO_REP: ko.observable('${C_NO_REP!}'),
        C_TEST_CON: ko.observable('${C_TEST_CON!}'),
        C_TEST_UNIT: ko.observable('${C_TEST_UNIT!}'),
        SID: '${SID!}',
        R_TMPFILE: '${file.id}'
    };
    var extModel = {
        readonly: ${@READONLY!'false'}
    };
    var settings = {
        
    };
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