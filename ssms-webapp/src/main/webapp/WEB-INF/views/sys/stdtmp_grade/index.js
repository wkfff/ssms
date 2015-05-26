function ViewModel(catalogId) {
    var self = this;

    var model = {
        selectedNode: ko.observable(),
        selectItem: ko.observable(),
        selectIndex: ko.pureComputed(function () {
            var row = model.selectItem();
            if (row) return settings.gridSettings.datagrid('getRowIndex', row);
        }),
        editItem: ko.observable(),
        sid: ko.observable()
    };
    
    var settings = {
        gridSettings: {
            title:'评分标准',
            idField: 'SID',
            url: "/sys/stdtmp_grade/list",
            queryParams: {
                R_SID: catalogId
            },
            rownumbers: false,
            pagination: true,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_CATEGORY', title: '一级要素', width: 120,
                        editor: {type: 'textarea', options: {validType: ['length[0, 300]']}}},
                    {field: 'C_PROJECT', title: '二级要素', width: 120,
                        editor: {type: 'textarea', options: {validType: ['length[0, 300]']}}},
                    /*{field: 'C_REQUEST', title: '基本规范要求', width: 200,
                        editor: {type: 'textarea', options: {validType: ['length[0, 2000]']}}},*/
                    {field: 'C_CONTENT', title: '企业达标标准', width: 300,
                        editor: {type: 'textarea', options: {validType: ['length[0, 2000]']}}},
                    {field: 'N_SCORE', title: '标准分值',align:'center',width: 60,
                        editor: {type: 'numberbox', options: {required: true}}},
                    {field: 'C_METHOD', title: '评分方式', 
                        editor: {type: 'textarea', options: {validType: ['length[0, 2000]']}}}
                ]
            ]
        }
    };

    var events = {
            gridEvents: {
                refreshClick: function () {
                    settings.gridSettings.datagrid('reload');
                }, addClick: function () {
                    if (settings.gridSettings.datagrid('validateRow', model.selectIndex())) {
                        var row = {SID: utils.uuid(), R_SID: catalogId};
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
                                settings.gridSettings.datagrid('reload');
                            });
                        });
                    }
                }
            }
    };

    $.extend(self, model, settings, events);
}