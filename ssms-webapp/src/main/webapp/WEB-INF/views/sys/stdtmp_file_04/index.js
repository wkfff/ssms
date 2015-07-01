/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-06-08
 * 创建用户：张铮彬
 */

function ViewModel(catalogId,path) {
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
            url: path+"/list",
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
                    {field: 'T_TIME', title: '培训日期', width: 150},
                    {field: 'C_USER_01', title: '讲师', width: 80},
                    {field: 'C_ADDR', title: '培训地点', width: 80},
                    {field: 'S_TYPE', title: '培训种类', width: 80},
                    {field: 'N_TIME', title: '学时', width: 80}
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
            window.location.href = path+'/rec?pid=' + catalogId;
        },
        editClick: function () {
            var value = model.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            window.location.href = path+'/rec?sid=' + value.SID;
        },
        deleteClick: function () {
            var value = model.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            $.post(path+'/del', {sid: value.SID}, function () {
                $.messager.alert('消息', '成功删除记录！', "info", function () {
                    events.refreshClick();
                });
            });
        }
    };

    $.extend(this, model, settings, events);
}