<div id="kocontainer" class="easyui-panel" border="false" fit="true">
    <div class="z-toolbar" >
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
                        {field: 'C_NAME', title: '存在问题', width: 150},
                        {
                            field: 'P_LEVEL', title: '隐患等级', width: 150, formatter: function (value, row, index) {
                            if (value == '01') return '一般隐患';
                            if (value == '02') return '重大隐患';
                        }
                        },
                        {field: 'C_RESPONSIBLE', title: '整改部门', width: 80},
                        {field: 'T_RECTIFICATION', title: '要求整改日期', width: 80},
                        {
                            field: 'B_FINISH', title: '隐患闭环情况', width: 80, formatter: function (value) {
                            if (value == '1') return '已闭环';
                            else return '未闭环';
                        }
                        }
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
        ko.applyBindings(new ViewModel(${sid}), document.getElementById('kocontainer'));
    }
</script>