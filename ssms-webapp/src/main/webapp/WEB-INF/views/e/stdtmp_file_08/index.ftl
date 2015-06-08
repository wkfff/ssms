<div id="kocontainer" class="easyui-panel" border="false" fit="true">
    <div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
    </div>
    <table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table>
</div>
<script type="text/javascript">
    function ViewModel(catalogId) {
        var model = {
            editItem: ko.observable(),
            selectItem: ko.observable(),
            selectIndex: ko.pureComputed(function () {
                var row = model.selectItem();
                if (row) return settings.gridSettings.datagrid('getRowIndex', row);
            })
        };
        var settings = {
            viewSettings: {
                url: "${BASE_PATH}/list",
                queryParams: {
                    R_TMPFILE: catalogId
                },
                idField: 'SID',
                rownumbers: true,
                pagination: true,
                fit: true,
                pageSize: 50,
                toolbar: '#toolbar',
                columns: [
                    [
                        {field: 'C_NAME', title: '设备名称', width: 120,
                        editor: {type: 'textbox', options: {validType: ['length[0, 300]']}}},
                        {field: 'C_NO_REG', title: '登记证号', width: 100,
                            editor: {type: 'textbox', options: {validType: ['length[0, 100]']}}},
                        {field: 'C_SPEC', title: '规格/型号', width: 300,
                            editor: {type: 'textbox', options: {}}},
                        {field: 'C_MAKE_UNIT', title: '制造单位',width: 100,
                            editor: {type: 'textbox', options: {}}},
                        {field: 'C_NO_FACTORY', title: '出厂编号', width: 200,
                            editor: {type: 'textbox', options: {validType: ['length[0, 200]']}}},
                        {field: 'C_DEPT', title: '使用部门', width: 200,
                            editor: {type: 'textbox', options: {validType: ['length[0, 200]']}}},
                        {field: 'T_TEST_LAST', title: '最新检验日期', width: 100,
                            editor: {type: 'datebox', options: {}}},
                        {field: 'C_NO_REP', title: '检验报告编号', width: 100,
                            editor: {type: 'textbox', options: {}}},
                        {field: 'T_TEST_NEXT', title: '下次检验日期', width: 100,
                            editor: {type: 'datebox', options: {}}},
                        {field: 'C_TEST_CON', title: '检验结论',width: 100,
                            editor: {type: 'textbox', options: {}}},
                        {field: 'C_TEST_UNIT', title: '检验单位',width: 100,
                            editor: {type: 'textbox', options: {}}}
                        ]
                ],
                onDblClickRow: function (index, row) {
                    events.editClick();
                }
            }
        };
        var events = {
            refreshClick: function () {
                settings.viewSettings.datagrid('reload');
            },
            addClick: function () {
                panelLoad('${BASE_PATH}/rec?pid=' + catalogId);
            },
            editClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                panelLoad('${BASE_PATH}/rec?sid=' + value.SID);
            },
            deleteClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                $.post('${BASE_PATH}/del', {sid: value.SID}, function () {
                    $.messager.alert('消息', '成功删除记录！', "info", function () {
                        events.refreshClick();
                    });
                });
            }
        };

        $.extend(this, model, settings, events);
    }
    var onPanelLoad = function () {
        ko.applyBindings(new ViewModel(${sid}), document.getElementById('kocontainer'));
    };
</script>