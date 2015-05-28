function ViewModel() {
    var self=this;
    
    var model={
    		selectItem :ko.observable()
    };
    
    var search={
    		C_NAME :ko.observable() 
    };
    
    var settings={
    		gridSettings:{
    			title: '专业管理',
    			url: 'list',
                idField: 'SID',
                iconCls: 'icon-star',
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                striped: true,
                toolbar: '#dg_index_tb',
                border: false,
                fit: true,
                columns: [
                    [
                        {field: 'C_NAME', title: '专业名称', width: 150},
                        {field: 'SID', title: '操作', width: 150,align:'center',
                            formatter:function(value,row){
                                    return "<a href='#' onclick='doEdit("+value+")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'>删除</a>";
                                }}
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
            }
        };
    return $.extend(self, model, search, events, settings);
}

function doDel(sid) {
    $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
                if (deleteAction) {
                    $.get("del", {sid:sid}, function (data) {
                        if (data == "true" || data== "\"\"") {
                            $.messager.alert("提示", "删除选定的记录成功");
                            $("#dg_index").datagrid("reload");
                            $("#dg_index").datagrid("clearSelections");
                        }
                        else {
                            $.messager.alert("提示", data);
                        }
                    });
                }
    });
}
function doEdit(sid) {
    window.location.href = 'rec?sid='+sid;
}
function doNew(){
    window.location.href = 'reg';
}