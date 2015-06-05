<style type="text/css">
    #kocontainer {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        overflow: auto;
    }

    .kowrap, .tbwarp {
        width: 800px;
        margin: 0 auto;
    }

    .lager .item {
        margin: 10px;
        border: 1px solid #CCCCCC;
        background: #FAFAFA;
    }

    .lager .item table {
        margin: 5px auto;
    }

    .lager .item table tr {
        height: 40px;
    }

    .lager .item table td.label {
        padding-left: 10px;
    }
</style>
<script type="text/javascript">
    var items = ${json(list.rows)};

    function LedgerModel(items, total, pid) {
        var self = this;
        self.dataViewModel = new ko.dataPager.viewModel({
            data: items,
            total: total,
            src: '${BASE_PATH}/recJson',
            params: {pid: pid},
            pageSize: 3
        });
        self.levelSource = ko.observableArray([{code: '01', name: '一般隐患'}, {code: '02', name: '重大隐患'}]);
        self.levelSettings = {valueField: 'code', textField: 'name'};

        self.addItem = function () {
            self.dataViewModel.data.push(ko.mapping.fromJS({
                SID: null,
                C_NAME: null,
                C_EXAMINER: null,
                T_EXAMINE: null,
                P_LEVEL: '01',
                C_MEASURE: null,
                C_RESPONSIBLE: null,
                T_RECTIFICATION: null,
                C_ACCEPTANCE: null
            }));
        };

        self.save = function (item, event, index) {
            if ($form.validate($('.lager .item[index=' + index + ']')) == false) return;
            utils.messager.showProgress();
            $.post('${BASE_PATH}/save', ko.utils.extend({R_SID: pid}, ko.mapping.toJS(item)), function (result) {
                utils.messager.closeProgress();
                if (result.SID) {
                    item.SID(result.SID);
                    $.messager.alert("提醒", "保存成功");
                }
                else $.messager.alert("提醒", "保存失败");
            }, 'json');
        };

        self.remove = function (item) {
            if (item.SID() == null) self.dataViewModel.data.remove(item);
            else {
                utils.messager.showProgress();
                $.post('${BASE_PATH}/del', {sid: item.SID()}, function (result) {
                    utils.messager.closeProgress();
                    if (result) {
                        self.dataViewModel.data.remove(item);
                        $.messager.alert("提醒", "删除成功");
                    }
                    else $.messager.alert("提醒", "删除失败");
                });
            }
        }
    }

    var onPanelLoad = function () {
        ko.applyBindings(new LedgerModel(items, ${list.total!0}, ${pid}), document.getElementById('kocontainer'));
    };
</script>
<div id="kocontainer">
    <h1 style="font-size: xx-large;text-align: center;">${template.C_NAME}</h1>

    <div class="z-toolbar">
        <div class="tbwarp">
            <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-bind="click: addItem">添加隐患项目</a>
        </div>
    </div>
    <div class="kowrap">
        <div class="lager" data-bind="foreach: dataViewModel.data">
            <div class="item" data-bind="attr: {index: $index}">
                <div class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: function(data, event){ $root.save(data, event, $index()) }">保存</a>
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-bind="click: $root.remove">删除</a>
                </div>
                <table>
                    <tr>
                        <td class="label">存在问题</td>
                        <td colspan="5"><input data-bind="textboxValue: C_NAME" required/></td>
                    </tr>
                    <tr>
                        <td class="label">检查人</td>
                        <td><input data-bind="textboxValue: C_EXAMINER" required/></td>
                        <td class="label">检查时间</td>
                        <td><input data-bind="dateboxValue: T_EXAMINE" required/></td>
                        <td class="label">隐患等级</td>
                        <td>
                            <input data-bind="comboboxSource: $root.levelSource, comboboxValue: P_LEVEL, easyuiOptions: $root.levelSettings" required/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">整改措施</td>
                        <td colspan="5"><input data-bind="textboxValue: C_MEASURE" required/></td>
                    </tr>
                    <tr>
                        <td class="label">整改负责人</td>
                        <td><input data-bind="textboxValue: C_RESPONSIBLE" required/></td>
                        <td class="label">要求整改日期</td>
                        <td><input data-bind="dateboxValue: T_RECTIFICATION" required/></td>
                        <td class="label">验收人</td>
                        <td><input data-bind="textboxValue: C_ACCEPTANCE" required/></td>
                    </tr>
                    <tr data-bind="visible: SID">
                        <td colspan="6" style="padding: 10px;">
                            <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'STDTMP_FILE_06', sid: $data.SID}">[选择文件]</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div data-bind="dataPager: dataViewModel"></div>
    </div>
</div>