function ViewModel(pid) {
	var self = this;

	var model = {
		selectItem : ko.observable()
	};
	var settings = {
		gridSettings : {
			 title: '企业用户管理',
	            url: 'list?R_SID=' +pid,
	            idField: 'SID',
	            rownumbers: true,
	            pagination: true,
	            singleSelect: true,
	            fitColumns: false,
	            fit: true,
	            border: false,
	            toolbar: '#toolbar',
	            columns: [
	                [
	                    {field: 'C_USER', title: '用户名', width: 100},
	                    {field: 'C_NAME', title: '姓名', width: 100},
	                    {field: 'C_POSITION', title: '职务', width: 160},
	                    {field: 'S_TENANT', title: '所属机构', width: 100},
	                    {field: 'C_DEPT', title: '部门', width: 160},
	                    {field: 'S_DEGREE', title: '学位', width: 100},
	                    {
	                        field: 'SID', title: '操作', width: 130, align: 'center',
	                        formatter: function (value, row) {
	                            if (row.C_USER == 'admin') {
	                                return "<a href='#' onclick='doEdit(" + value + "," +pid+ ")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='resetPsw(" + value + ")'>重置密码</a>"
	                            } else {
	                                return "<a href='#' onclick='doEdit(" + value +  "," +pid+ ")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel(" + value + ")'>删除</a>&nbsp;&nbsp;<a href='#' onclick='resetPsw(" + value + ")'>重置密码</a>"
	                            }
	                        }
	                    }
	                ]
	            ]
		},
		comboboxSettings : {
			valueField : 'key',
			textField : 'value'
		}
	};

	var events = {
	};
	return $.extend(self, model, events, settings);

}

function doDel(sid) {
	$.messager.confirm("删除确认", "您确认删除选定的记录吗？", function(deleteAction) {
		if (deleteAction) {
			$.get("del", {
				sid : sid
			}, function(data) {
				if (data == "true" || data == "\"\"") {
					$.messager.alert("提示", "删除选定的记录成功");
					$("#dg_index").datagrid("reload");
					$("#dg_index").datagrid("clearSelections");
				} else {
					$.messager.alert("提示", data);
				}
			});
		}
	});

}

function resetPsw(sid) {
	$.get("resetPsw",{sid : sid
		},function(result){
			if(result)
				$.messager.alert("提示","密码重置成功","info",function(){
					 window.location.href= window.location.href;
				});
			else{
				 $.messager.alert("提示", "密码重置失败", "warning");
			}
		})
}

function doEdit(sid,pid) {
	window.location.href = 'rec?sid=' + sid + '&pid='+pid;
}

function doNew(pid){
	window.location.href = 'reg?pid='+pid;
}

