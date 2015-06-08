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
            url: "list",
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
                    {field: 'C_NAME', title: '执行文件标题', width: 150}
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
            window.location.href = 'rec?pid=' + catalogId;
        },
        editClick: function () {
            var value = model.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            window.location.href = 'rec?sid=' + value.SID;
        },
        deleteClick: function () {
            var value = model.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            $.post('del', {sid: value.SID}, function () {
                $.messager.alert('消息', '成功删除记录！', "info", function () {
                    events.refreshClick();
                });
            });
        }
    };

    $.extend(this, model, settings, events);
}