/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-05-11
 * 创建用户：张铮彬
 */

function ViewModel() {
    var self = this;

    var model = {
        selectItem: ko.observable()
    };
    var search = {
        C_NAME: ko.observable(),
        S_PROVINCE: ko.observable(),
        S_CITY: ko.observable(),
        S_COUNTY: ko.observable()
    };

    var settings = {
        gridSettings: {
            title: '企业列表',
            url: 'list.json',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_NAME', title: '企业名称', width: 200},
                    {field: 'S_PROFESSION', title: '专业', width: 70},
                    {field: 'T_PAY', title: '缴费日期', width: 100},
                    {field: 'T_PAY_NEXT', title: '下次缴费日期', width: 100},
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
            window.location.href = 'reg.html';
        }
    };

    return $.extend(self, model, search, events, settings);
}


function doDel(sid) {
    $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
        if (deleteAction) {
            $.get("del.do", {sid: sid}, function (data) {
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
    window.location.href = 'rec.html?refer=edit&sid=' + sid;
}
function doListUser(sid) {
    window.location.href = '/sys/tenant_eu/index.html?pid=' + sid;
}