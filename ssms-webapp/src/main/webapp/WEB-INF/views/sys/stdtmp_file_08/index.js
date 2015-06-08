function ViewModel(templateId) {
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
            title:'特种设备台账及定期检验记录',
            idField: 'SID',
            url: "/sys/stdtmp_file_08/list",
            queryParams: {
                R_TMPFILE: templateId
            },
            rownumbers: false,
            pagination: false,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_NAME', title: '设备名称', width: 120,
                        editor: {type: 'textbox', options: {validType: ['length[0, 300]']}}},
                    {field: 'C_NO_REG', title: '登记证号', width: 100,
                        editor: {type: 'textbox', options: {validType: ['length[0, 100]']}}},
                    {field: 'C_SPEC', title: '规格/型号', width: 300,
                        editor: {type: 'textbox', options: {}}},
                    {field: 'C_MAKE_UNIT', title: '制造单位',width: 100,
                        editor: {type: 'textbox', options: {}}},
                    {field: 'C_NO_FACTORY', title: '出厂编号', width: 200,
                        editor: {type: 'textbox', options: {validType: ['length[0, 200]']}}},
                    {field: 'C_DEPT', title: '使用部门', width: 200,
                        editor: {type: 'textbox', options: {validType: ['length[0, 200]']}}},
                    {field: 'T_TEST_LAST', title: '最新检验日期', width: 100,
                        editor: {type: 'datebox', options: {}}},
                    {field: 'C_NO_REP', title: '检验报告编号', width: 100,
                        editor: {type: 'textbox', options: {}}},
                    {field: 'T_TEST_NEXT', title: '下次检验日期', width: 100,
                        editor: {type: 'datebox', options: {}}},
                    {field: 'C_TEST_CON', title: '检验结论',width: 100,
                        editor: {type: 'textbox', options: {}}},
                    {field: 'C_TEST_UNIT', title: '检验单位',width: 100,
                        editor: {type: 'textbox', options: {}}},
                    {field: 'N_INDEX', title: '排序', width: 100,hidden:true,
                        editor: {type: 'numberbox', options: {}}}
                ]
            ]
        }
    };

    var events = {
            gridEvents: {
                refreshClick: function () {
                    settings.gridSettings.datagrid('reload');
                }, addClick: function () {
                    var row = {SID: utils.uuid(), R_TMPFILE: templateId};
                    if(model.selectIndex() && model.selectIndex()!=-1){
                        var index = model.selectIndex();
                        if (settings.gridSettings.datagrid('validateRow', index)) {
                            index++;
                            settings.gridSettings.datagrid('insertRow', {
                                index: index,
                                row: row
                            });
                        }
                    }
                    else{
                        settings.gridSettings.datagrid('appendRow', row);
                    }
                    model.selectItem(row);
                    model.editItem(row);
                    
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
                }, upClick: function () {
                    events.gridEvents.sort(model.selectIndex(), 'up');
                    
                }, downClick: function () {
                    events.gridEvents.sort(model.selectIndex(), 'down');
                },sort:function(index,type){
                    if (typeof(index)=="undefined") {$.messager.alert('提示', '请先选择要移动的记录行！', 'info');return;};
                    if ("up" == type) {
                        if (index != 0) {
                            var toup = settings.gridSettings.datagrid('getData').rows[index];
                            var todown = settings.gridSettings.datagrid('getData').rows[index - 1];
                            settings.gridSettings.datagrid('getData').rows[index] = todown;
                            settings.gridSettings.datagrid('getData').rows[index - 1] = toup;
                            settings.gridSettings.datagrid('refreshRow', index);
                            settings.gridSettings.datagrid('refreshRow', index - 1);
                            settings.gridSettings.datagrid('selectRow', index - 1);
                            var d = [{'SID':toup.SID,'N_INDEX':todown.N_INDEX},{'SID':todown.SID,'N_INDEX':toup.N_INDEX}];
                            $.post("batchSave", {data:$.toJSON(d)}, function () {});
                        }
                    } else if ("down" == type) {
                        var rows = settings.gridSettings.datagrid('getRows').length;
                        if (index != rows - 1) {
                            var todown = settings.gridSettings.datagrid('getData').rows[index];
                            var toup = settings.gridSettings.datagrid('getData').rows[index + 1];
                            settings.gridSettings.datagrid('getData').rows[index + 1] = todown;
                            settings.gridSettings.datagrid('getData').rows[index] = toup;
                            settings.gridSettings.datagrid('refreshRow', index);
                            settings.gridSettings.datagrid('refreshRow', index + 1);
                            settings.gridSettings.datagrid('selectRow', index + 1);                            
                            var d = [{'SID':todown.SID,'N_INDEX':toup.N_INDEX},{'SID':toup.SID,'N_INDEX':todown.N_INDEX}];
                            $.post("batchSave", {data:$.toJSON(d)}, function () {});
                        }
                    }
                }
            }
    };

    $.extend(self, model, settings, events);
}