<div id="kocontainer" class="easyui-panel" border="false" fit="true">
    <div id="z-toolbar" >
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
                toolbar: '#z-toolbar',
                columns: [
                    [
                        {field: 'C_NAME', title: '姓名', align:'center', width: 120},
                        {field: 'P_SEX', title: '性别', align:'center', width: 60,
                            formatter:function(value,row){
                                return value=='1'?'男':'女';
                            }},
                        {field: 'C_CARD', title: '身份证号码', width: 140},
                        {field: 'C_DEPT', title: '所在部门', align:'center', width: 300},
                        {field: 'C_WORKTYPE', title: '工种', align:'center', width: 60},
                        {field: 'C_AUTH', title: '发证机关', align:'center', width: 200},
                        {field: 'T_CERT_GET', title: '取证时间', align:'center', width: 100},
                        {field: 'C_CERT', title: '证书编号', width: 140},
                        {field: 'T_CERT_REVIEW', title: '复审时间', align:'center', width: 100}
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
    };
</script>