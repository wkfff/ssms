<@layout.extends name="../../../_layouts/todo.ftl">

    <@layout.put block="contents">
    <div id="kocontainer" class="easyui-panel" border="false" fit="true">
        <div id="toolbar" class="z-toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">打开</a>
        </div>
        <table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript">
        function ViewModel() {
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
                    title: "特种人员列表",
                    url: "${BASE_PATH}/list",
                    idField: 'id',
                    rownumbers: true,
                    pagination: true,
                    fit: true,
                    pageSize: 50,
                    toolbar: '#toolbar',
                    columns: [
                        [
                            {field: 'title', title: '标题', width: 350},
                            {field: 'notifyTime', title: '提醒时间', width: 150}
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
                    panelLoad('${BASE_PATH}/rec?sid=' + value.relationalId);
                }
            };

            $.extend(this, model, settings, events);
        }
        var onPanelLoad = function () {
            ko.applyBindings(new ViewModel(), document.getElementById('kocontainer'));
        };
    </script>
    </@>
</@>