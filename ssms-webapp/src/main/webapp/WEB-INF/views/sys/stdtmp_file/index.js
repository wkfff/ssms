/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

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
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_NAME', title: '文件名'},
                    {field: 'C_DESC', title: '描述', width: 200},
                    {
                        field: 'N_CYCLE', title: '更新周期', align: 'center', width: 80,
                        formatter: function (value, row) {
                            if (value) return '{0}/{1}'.format(value, row.S_CYCLE);
                        }
                    },
                    {
                        field: 'B_REMIND', title: '是否提醒', align: 'center', width: 80,
                        formatter: function (value, row) {
                            if (value) return value == 1 ? "是" : "否"
                        }
                    },
                    {
                        field: 'N_INDEX', title: '排序', align: 'center', width: 50,
                        editor: {type: 'text', options: {validType: ['length[0, 300]']}}
                    },
                    {
                        field: 'SID', title: '操作', width: 130, align: 'center',
                        formatter: function (value, row) {
                            return "<a href='#' onclick='doEdit(" + value + ")'>编辑文件</a>&nbsp&nbsp<a href='#' onclick='configTemplate(" + row.R_TMPFILE + ',' + row.P_TMPFILE + ")'>配置模版</a>";
                        }
                    }

                ]
            ]
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
            model.editItem(model.selectItem());
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
        },
        saveClick: function () {
            model.editItem(null);
            if (model.editItem()) {
                $.messager.alert('警告', '当前编辑行数据不正确', 'warning');
                return;
            }
            var changes = settings.viewSettings.datagrid('getChanges');
            if (changes.length > 0) {
                $.post("batchSave", {data: $.toJSON(changes)}, function () {
                    $.messager.alert('消息', '成功保存记录！', "info", function () {
                        settings.viewSettings.datagrid('reload');
                    });
                });
            }
        }
    };

    $.extend(this, model, settings, events);
}
function doEdit(sid) {
    window.location.href = 'rec?SID=' + sid;
}
function configTemplate(R_TMPFILE, P_TMPFILE) {
    if (P_TMPFILE != null) {
        window.location.href = "/sys/stdtmp_file_0" + P_TMPFILE + "/rec?sid=" + R_TMPFILE;
    } else {
        $.messager.alert('警告', '没有配置模版', 'warning');
    }
}