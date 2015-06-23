<div id="kocontainer" class="easyui-panel" border="false" fit="true">
    <div id="toolbar" class="z-toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click: editClick">查看</a>
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
                    R_SID: catalogId
                },
                idField: 'SID',
                rownumbers: true,
                pagination: true,
                fit: true,
                pageSize: 50,
                toolbar: '#toolbar',
                columns: [
                    [
                        {field: 'C_NAME', title: '通知标题', width: 150},
                        {field: 'C_NUMBER', title: '通知编号', width: 150},
                        {field: 'C_DEPT_01', title: '发布部门', align: 'center', width: 80},
                        {field: 'T_DATE_01', title: '发布日期', align: 'center', width: 80},
                        {field: 'C_DEPT_02', title: '主送部门', align: 'center', width: 80},
                        {field: 'C_DEPT_03', title: '抄送部门', align: 'center', width: 80}
                    ]
                ],
                onDblClickRow: function (rowIndex, rowData) {
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
        ko.applyBindings(new ViewModel(${sid}), document.getElementById('kocontainer'));
    };
</script>