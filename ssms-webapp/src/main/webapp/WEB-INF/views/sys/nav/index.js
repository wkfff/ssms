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
        selectedNode: ko.observable(),
        selectItem: ko.observable(),
        selectIndex: ko.pureComputed(function () {
            var row = model.selectItem();
            if (row) return settings.gridSettings.datagrid('getRowIndex', row);
        }),
        editItem: ko.observable(),
        sid: ko.observable(),
        rec: ko.mapping.fromJS({
            C_NAME: null,
            C_ICON: null,
            C_URL: null,
            C_DESC: null,
            R_SID: null,
            SID: null
        })
    };
    model.selectedNode.subscribe(function (newValue) {
        if (!newValue) return;
        model.sid(newValue.id);
    });
    model.sid.subscribe(function (newValue) {
        $.post('rec', {sid: model.sid()}, function (result) {
            for (var obj in model.rec) {
                var field = model.rec[obj];
                var value = null;
                if (obj in result) value = result[obj] ;
                if (typeof field == 'function') field(value);
                else field = value;
            }
        });
        if (settings.gridSettings.datagrid)
            settings.gridSettings.datagrid({
                url: "list",
                queryParams: {R_SID: newValue}
            })
    });

    var settings = {
        treeSettings: {
            url: 'tree',
            onLoadSuccess: function () {
                var node = settings.treeSettings.tree('getRoot');
                if (node.id !== model.sid()) {
                    node = settings.treeSettings.tree('find', model.sid()) || node;
                }
                model.selectedNode(node);
            }
        },
        gridSettings: {
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {
                        field: 'C_NAME',
                        title: '名称',
                        width: 100,
                        editor: {type: 'validatebox', options: {required: true, validType: ['length[0, 300]']}}
                    },
                    {
                        field: 'C_ICON',
                        title: '名称',
                        width: 100,
                        editor: {type: 'validatebox', options: {validType: ['length[0, 64]']}}
                    },
                    {
                        field: 'C_URL',
                        title: 'URL',
                        width: 100,
                        editor: {type: 'validatebox', options: {validType: ['length[0, 300]']}}
                    },
                    {
                        field: 'C_DESC',
                        title: '描述',
                        width: 200,
                        editor: {type: 'textarea', options: {validType: ['length[0, 1000]']}}
                    },
                    {
                        field: 'N_INDEX',
                        title: '排序',
                        width: 80,
                        align: "center",
                        editor: {type: 'numberbox', options: {required: true}}
                    }
                ]
            ]
        }
    };

    var events = {
        recEvents: {
            saveClick: function () {
                if ($form.validate($('.form'))) {
                    var value = ko.mapping.toJS(model.rec);
                    $.post('save', value, function (result) {
                        if (result.SID)
                            $.messager.alert("提示", "保存成功", "info", function () {
                                settings.treeSettings.tree('reload');
                            });
                        else {
                            $.messager.alert("提示", "保存失败", "warning");
                        }
                    }, "json")
                }
            },
            removeClick: function () {
                $.post('del', {sid: model.sid()}, function () {
                    settings.treeSettings.tree('reload');
                })
            }
        },
        gridEvents: {
            refreshClick: function () {
                settings.gridSettings.datagrid('reload');
            }, addClick: function () {
                if (settings.gridSettings.datagrid('validateRow', model.selectIndex())) {
                    var row = {SID: utils.uuid(), R_SID: model.sid()};
                    settings.gridSettings.datagrid('appendRow', row);
                    model.editItem(row);
                }
            }, editClick: function () {
                model.editItem(model.selectItem());
            }, deleteClick: function () {
                var row = model.selectItem();
                if (row) {
                    if (!row.SID.toString().startsWith("_"))
                        $.post('del', {sid: row.SID}, function () {
                            $.messager.alert('消息', '成功删除记录！', 'info', function () {
                                settings.gridSettings.datagrid('reload');
                            });
                        });
                    else {
                        settings.gridSettings.datagrid('deleteRow', model.selectIndex());
                    }
                }
            }, saveClick: function () {
                model.editItem(null);
                if (model.editItem()) {
                    $.messager.alert('警告', '当前编辑行数据不正确', 'warning');
                    return;
                }
                var changes = settings.gridSettings.datagrid('getChanges');
                if (changes.length > 0) {
                    $.post("batchSave", {data: $.toJSON(changes)}, function () {
                        $.messager.alert('消息', '成功保存记录！', "info", function () {
                            settings.treeSettings.tree('reload');
                        });
                    });
                }
            }
        }
    };

    $.extend(self, model, settings, events);
}

