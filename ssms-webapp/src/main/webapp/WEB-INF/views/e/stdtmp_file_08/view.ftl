<div id="kocontainer" class="easyui-panel" border="false" fit="true">
    <div id="toolbar" class="z-toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click: editClick">查看</a>
    </div>
    <table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table>
</div>
<script type="text/javascript">
    function ViewModel(fileId) {
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
                    fileId: fileId
                },
                idField: 'SID',
                rownumbers: true,
                pagination: true,
                fit: true,
                pageSize: 50,
                toolbar: '#toolbar',
                columns: [
                    [
                        {field: 'C_NAME', title: '设备名称', width: 120},
                        {field: 'C_NO_REG', title: '登记证号', width: 100},
                        {field: 'C_SPEC', title: '规格/型号', width: 300},
                        {field: 'C_MAKE_UNIT', title: '制造单位', width: 100},
                        {field: 'C_NO_FACTORY', title: '出厂编号', width: 200},
                        {field: 'C_DEPT', title: '使用部门', align: 'center', width: 200},
                        {field: 'T_TEST_LAST', title: '最新检验日期', align: 'center', width: 100},
                        {field: 'C_NO_REP', title: '检验报告编号', width: 100},
                        {field: 'T_TEST_NEXT', title: '下次检验日期', align: 'center', width: 100},
                        {field: 'C_TEST_CON', title: '检验结论', width: 100},
                        {field: 'C_TEST_UNIT', title: '检验单位', width: 100}
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
            editClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                panelLoad('${BASE_PATH}/detail?sid=' + value.SID);
            }
        };

        $.extend(this, model, settings, events);
    }
    var onPanelLoad = function () {
        ko.applyBindings(new ViewModel(${fileid}), document.getElementById('kocontainer'));
    };
</script>