<div id="kocontainer" class="easyui-panel" border="false" fit="true">
    <div id="toolbar" class="z-toolbar" data-bind="visible:!readonly">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
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
            }),
            readonly: ${@READONLY!'false'}
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
                        {field: 'C_NAME', title: '通知标题', width: 150},
                        {field: 'C_NUMBER', title: '通知编号', width: 150},
                        {field: 'C_DEPT_01', title: '发布部门', align: 'center', width: 80},
                        {field: 'T_DATE_01', title: '发布日期', align: 'center', width: 80},
                        {field: 'C_DEPT_02', title: '主送部门', align: 'center', width: 80},
                        {field: 'C_DEPT_03', title: '抄送部门', align: 'center', width: 80}
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
                panelLoad('${BASE_PATH}/rec?fileId=' + fileId);
            },
            editClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                panelLoad('${BASE_PATH}/rec<#if @READONLY== 'true' >_view</#if>?sid=' + value.SID);
            },
            deleteClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                $.messager.confirm('确认', '是否确认要删除选中数据？', function (r) {
                    if (r) {
                        $.post('${BASE_PATH}/del', {sid: value.SID}, function () {
                            $.messager.alert('消息', '成功删除记录！', "info", function () {
                                events.refreshClick();
                            });
                        });
                    }
                });
            }
        };

        $.extend(this, model, settings, events);
    }
    var onPanelLoad = function () {
        ko.applyBindings(new ViewModel(${fileid}), document.getElementById('kocontainer'));
    };
</script>