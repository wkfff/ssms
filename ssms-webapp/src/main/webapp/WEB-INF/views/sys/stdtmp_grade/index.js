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
            pagination: false,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_CATEGORY', title: '一级要素', width: 120,
                        editor: {type: 'textarea', options: {validType: ['length[0, 300]']}}},
                    {field: 'C_PROJECT', title: '二级要素', width: 120,
                        editor: {type: 'textarea', options: {validType: ['length[0, 300]']}}},
                    {field: 'C_REQUEST', title: '基本规范要求', width: 200,
                        editor: {type: 'textarea', options: {validType: ['length[0, 2000]']}}},
                    {field: 'C_CONTENT', title: '企业达标标准', width: 300,
                        editor: {type: 'textarea', options: {validType: ['length[0, 2000]']}}},
                    {field: 'N_SCORE', title: '标准分值',align:'center',width: 80,
                        editor: {type: 'numberbox', options: {required: true}}},
                    {field: 'C_METHOD', title: '评分方式', width: 200,
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
                    var row = {SID: utils.uuid(), R_SID: catalogId};
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
                        utils.messager.confirm('是否确认删除选中的记录？',function(){
                            if (row.SID && !row.SID.toString().startsWith("_")){
                                $.post('del', {sid: row.SID}, function () {
                                    utils.messager.alert('成功删除记录！', function () {
                                        settings.gridSettings.datagrid('reload');
                                    });
                                });
                            }else {
                                settings.gridSettings.datagrid('deleteRow', model.selectIndex());
                            }
                        });
                    }else
                        utils.messager.alert('请选择要删除的记录！');
                }, saveClick: function () {
                    model.editItem(null);
                    if (model.editItem()) {
                        utils.messager.alert('当前编辑行数据不正确！');
                        return;
                    }
                    var changes = settings.gridSettings.datagrid('getChanges');
                    if (changes.length > 0) {
                        $.post("batchSave", {data: $.toJSON(changes)}, function () {
                            utils.messager.alert('成功保存记录！', function () {
                                settings.gridSettings.datagrid('reload');
                            });
                        });
                    }
                }, upClick: function () {
                    events.gridEvents.sort(model.selectIndex(), 'up');
                }, downClick: function () {
                    events.gridEvents.sort(model.selectIndex(), 'down');
                },sort:function(index,type){
                    if (typeof(index)=="undefined") {utils.messager.alert('请先选择要移动的记录行！');return;};
                    if ("up" == type) {
                        if (index != 0) {
                            var toup = settings.gridSettings.datagrid('getData').rows[index];
                            var todown = settings.gridSettings.datagrid('getData').rows[index - 1];
                            var index1 = todown.N_INDEX;
                            var index2 = toup.N_INDEX;
                            settings.gridSettings.datagrid('getData').rows[index] = todown;
                            settings.gridSettings.datagrid('getData').rows[index - 1] = toup;
                            settings.gridSettings.datagrid('refreshRow', index);
                            settings.gridSettings.datagrid('refreshRow', index - 1);
                            settings.gridSettings.datagrid('selectRow', index - 1);
                            var d = [{'SID':toup.SID,'N_INDEX':todown.N_INDEX},{'SID':todown.SID,'N_INDEX':toup.N_INDEX}];
                            $.post("batchSave", {data:$.toJSON(d)}, function () {
                                todown.N_INDEX = index2;
                                toup.N_INDEX = index1;
                            });
                        }
                    } else if ("down" == type) {
                        var rows = settings.gridSettings.datagrid('getRows').length;
                        if (index != rows - 1) {
                            var todown = settings.gridSettings.datagrid('getData').rows[index];
                            var toup = settings.gridSettings.datagrid('getData').rows[index + 1];
                            var index1 = todown.N_INDEX;
                            var index2 = toup.N_INDEX;
                            settings.gridSettings.datagrid('getData').rows[index + 1] = todown;
                            settings.gridSettings.datagrid('getData').rows[index] = toup;
                            settings.gridSettings.datagrid('refreshRow', index);
                            settings.gridSettings.datagrid('refreshRow', index + 1);
                            settings.gridSettings.datagrid('selectRow', index + 1);
                            var d = [{'SID':todown.SID,'N_INDEX':toup.N_INDEX},{'SID':toup.SID,'N_INDEX':todown.N_INDEX}];
                            $.post("batchSave", {data:$.toJSON(d)}, function () {
                                todown.N_INDEX = index2;
                                toup.N_INDEX = index1;
                            });
                        }
                    }
                }
            }
    };

    $.extend(self, model, settings, events);
}