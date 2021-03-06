function ViewModel(recId) {
    var self = this;
    var editIndex;
    
    var model = {
        selectedNode: ko.observable(),
        selectItem: ko.observable(),
        selectIndex: ko.pureComputed(function () {
            var row = model.selectItem();
            if (row) return settings.gridSettings.datagrid('getRowIndex', row);
        }),
        editItem: ko.observable(),
        sid: ko.observable(),
        noComplete:ko.observable(0)
    };
    
    model.noComplete.subscribe(function (newValue) {
        var url = '/e/gradecontent/list?R_SID='+recId;
        url += (newValue == 1? "&NOCOMPLETE=1":"");
        settings.gridSettings.datagrid({url:url});
    });
    
    var events = {
            recEvents: {
                saveClick: function () {
                    events.gridEvents.endEditing();
                    if ($form.validate($('.form'))) {
                        var value = ko.mapping.toJS(model.rec);
                        $.post('save', value, function (result) {
                            if (result.SID)
                                $.messager.alert("提示", "保存成功", "info", function () {
                                    
                                });
                            else {
                                $.messager.alert("提示", "保存失败", "warning");
                            }
                        }, "json")
                    }
                },
                removeClick: function () {
                    $.post('del', {sid: recId}, function () {
                        window.location.href = "/e/gradeplan/index";
                    })
                },
                backClick: function () {
                   window.location.href = '/e/gradeplan/index'; 
                }, completeClick: function () {
                    $.getJSON("/e/gradeplan/check", {sid:recId}, function (data) {
                        if (data && data.N>0) {
                            $.messager.alert("提示", "自评还有未填写的项，请填写后再完成自评！");
                        }
                        else {
                            $.messager.confirm("完成确认", "您确认完成当前的自评吗？", function (action) {
                                if (action) {
                                    $.getJSON("/e/gradeplan/complete", {sid:recId}, function (data) {
                                        if (data.result == "OK") {
                                            $.messager.alert("提示", "自评完成");
                                        }
                                        else {
                                            $.messager.alert("提示", data);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            },
            gridEvents: {
                fullScreenClick:function(){
                    var e = $('#full');
                    if (e.attr('title')=='最大化'){
                        $('#north').panel('collapse');
                        $('#center').panel('maximize');
                        $('.layout-panel-north').hide();
                        //e.removeClass('panel-tool-max');
                        //e.addClass('panel-tool-min');
                        e.attr('title','还原');
                        e.text('还原');
                    }else{
                        $('#north').panel('expand');
                        $('#center').panel('restore');
                        $('.layout-panel-north').show();
                        //e.addClass('panel-tool-max');
                        //e.removeClass('panel-tool-min');
                        e.attr('title','最大化');
                        e.text('最大化');
                    }
                },
                refreshClick: function () {
                    settings.gridSettings.datagrid('reload');
                }, addClick: function () {
                    if (settings.gridSettings.datagrid('validateRow', model.selectIndex())) {
                        var row = {SID: utils.uuid(), R_SID: model.sid(), R_TEMPLATE: recId};
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
                }, noCompleteClick: function () {
                    model.noComplete(model.noComplete()==0?1:0);
                },endEditing: function(){
                    if (editIndex == undefined){return true}
                    if (settings.gridSettings.datagrid('validateRow', editIndex)){
                        var row = settings.gridSettings.datagrid('getRows')[editIndex];
                        var v1 = row['N_SCORE'];
                        var ed = settings.gridSettings.datagrid('getEditor', {index:editIndex,field:'N_SCORE_REAL'});
                        var v2 = ed?ed.target.val():0;
                        if (v1<v2){
                            $.messager.alert("提示", "实际得分不能大于标准分值！", "warning");
                            return false;
                        }
                        
                        settings.gridSettings.datagrid('endEdit', editIndex);
                        editIndex = undefined;

                        var opts = settings.gridSettings.datagrid('options');
                        var url = opts.updateUrl;
                        if (url){
                                $.post(url, {"SID":row.SID,"R_SID":row.R_SID,"C_DESC":row.C_DESC,"B_BLANK":row.B_BLANK,"N_SCORE_REAL":row.N_SCORE_REAL}, function(data){});
                        }
                        return true;
                    } else {
                        return false;
                    }
                },onClickRow: function(index){
                    var v = settings.gridSettings.datagrid('getRows')[index]['C_PROJECT'];
                    if (v=='小计' || v=='总计'){
                        return;
                    }
                    if (editIndex != index){
                        if (events.gridEvents.endEditing()){
                            settings.gridSettings.datagrid('selectRow', index)
                                    .datagrid('beginEdit', index);
                            editIndex = index;
                        } else {
                            settings.gridSettings.datagrid('selectRow', editIndex);
                        }
                        
                        var ed = settings.gridSettings.datagrid('getEditor', {index:index,field:'N_SCORE_REAL'});
                        var ed1 = settings.gridSettings.datagrid('getEditor', {index:index,field:'C_DESC'});
                        var ed2 = settings.gridSettings.datagrid('getEditor', {index:index,field:'B_BLANK'});
                        $(ed2.target).bind("change",function(e){
                            var chk = $(ed2.target)[0].checked;
                            if (chk){
                                $(ed.target).numberbox("setValue",'');
                                $(ed.target).numberbox("disable",true);
                                $(ed1.target)[0].value = '';
                            }
                            else
                                $(ed.target).numberbox("enable",true);
                            $(ed1.target)[0].disabled = chk;
                        });
                          
                        var chk = $(ed2.target)[0].checked;
                        if (chk){
                            $(ed.target).numberbox("setValue",'');
                            $(ed.target).numberbox("disable",true);
                            $(ed1.target)[0].value = '';
                        }
                        else
                            $(ed.target).numberbox("enable",true);
                        $(ed1.target)[0].disabled = chk;
                    }
                }
            }
        };
    
    var settings = {
        gridSettings: {
            url: '/e/gradecontent/list?R_SID='+recId,
            updateUrl:'/e/gradecontent/save',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            autoSave:true,
            fit:true,
            border:false,
            striped: false,
            //maximizable:true,
            //tools:'#db_tb',
            columns: [[
                {field: 'C_CATEGORY', title: '类目', width: 100},
                {field: 'C_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 65},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 65,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            var s = row['C_PROJECT'];
                            if (s=='小计' || s == '总计') return '';
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 65,editor:'numberbox'}
            ]],
            onLoadSuccess: function(data){
                
            },
            onClickRow:events.gridEvents.onClickRow,
            rowStyler: function(index,row){
                if (row.C_PROJECT == '小计' || row.C_PROJECT == '总计'){
                    return 'background-color:#FAFAFA;color:#000;font-weight:bold;';
                }
            }
        }
    };

    $.extend(self, model, settings, events);
}

