function ViewModel(pid) {
	var self = this;

	var model = {
		selectItem : ko.observable()
	};
	var settings = {
		gridSettings : {
			title : '评审用户管理',
			url : 'list?R_SID='+pid,
			idField : 'SID',
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			fitColumns : false,
			fit : true,
			border : false,
			toolbar : '#toolbar',
			columns : [ [
					{
						field : 'C_USER',
						title : '用户名称',
						width : 200
					},
					{
						field : 'C_NAME',
						title : '用户姓名',
						width : 200
					},
					{
						field : 'C_POSITION',
						title : '职务',
						width : 100
					},
					{
						field : 'S_TENANT',
						title : '所属机构',
						width : 100
					},
					{
						field : 'T_BIRTH',
						title : '出生日期',
						width : 70
					},
					{
						field : 'SID',
						title : '操作',
						width : 160,
						align : 'center',
						formatter : function(value, row) {
							if (row.C_USER == 'admin') {
								return "<a href='#' onclick='doEdit("
										+ value
										+ "," + pid + ")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doUpPsw("
										+ value + ")'>修改密码</a>"
							} else {
								return "<a href='#' onclick='doEdit("
										+ value
										+ "," +  pid + ")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("
										+ value
										+ ")'>删除</a>&nbsp;&nbsp;<a href='#' onclick='doUpPsw("
										+ value + ")'>修改密码</a>"
							}
						}
					} ] ]
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

function doUpPsw(sid) {
	window.location.href = 'psw?sid=' + sid;
}

function doEdit(sid,pid) {
	window.location.href = 'rec?sid=' + sid + '&pid='+pid;
}

function doNew(pid){
	window.location.href = 'reg?pid='+pid;
}

