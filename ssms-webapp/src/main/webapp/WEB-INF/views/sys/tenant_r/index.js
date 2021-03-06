function ViewModel() {
    var self = this;

    var model = {
        selectItem: ko.observable()
    };
    var search = {
        C_NAME: ko.observable()
    };

    var settings = {
        gridSettings: {
            title: '评审机构列表',
            url: 'list',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_NAME', title: '单位名称', width: 200},
                    {field: 'C_CODE', title: '租户编码', width: 100},
                    {field: 'C_NUMBER', title: '营业执照注册号', width: 100},
                    {field: 'C_ADDR', title: '地址', width: 150},
                    {
                        field: 'SID', title: '操作', width: 130, align: 'center',
                        formatter: function (value, row) {
                            return "<a href='#' onclick='doEdit(" + value + ")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel(" + value + ")'>删除</a>&nbsp;&nbsp;<a href='#' onclick='doListUser(" + value + ")'>用户列表</a>";
                        }
                    }
                ]
            ]
        },
        comboboxSettings: {
            valueField: 'key',
            textField: 'value'
        }
    };

    var events = {
        searchClick: function () {
            settings.gridSettings.datagrid({
                queryParams: search
            })
        },
        addClick: function () {
            window.location.href = 'reg';
        }
    };

    return $.extend(self, model, search, events, settings);
}


function doDel(sid) {
    $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
        if (deleteAction) {
            $.get("del", {sid: sid}, function (data) {
                if (data == "true" || data == "\"\"") {
                    $.messager.alert("提示", "删除选定的记录成功");
                    $("#dg_index").datagrid("reload");
                }
                else {
                    $.messager.alert("提示", data);
                }
            });
        }
    });

}
function doEdit(sid) {
    window.location.href = 'rec?refer=edit&sid='+sid;
}
function doListUser(sid) {
    window.location.href = '/sys/tenant_ru/index?pid='+sid;
}