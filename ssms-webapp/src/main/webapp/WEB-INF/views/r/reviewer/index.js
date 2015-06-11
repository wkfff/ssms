function ViewModel() {
	var self = this;
	var model = {
		selectItem : ko.observable()
	};
	var settings = {
		gridSettings : {
			title : '评审员管理',
			url : '/static/reviewers.json',
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
						field : 'C_NAME',
						title : '姓名',
						width : 50
					},
					{
						field : 'S_SEX',
						title : '性别',
						width : 30
					},
					{
						field : 'T_BIRTH',
						title : '出生日期',
						width : 80
					},
					{
						field : 'C_POSITION',
						title : '职务',
						width : 60
					},
					{
						field : 'C_TECHNOLOGY',
						title : '专业技术职务',
						width : 100
					},
					{
						field : 'C_DEPT',
						title : '工作单位及部门',
						width : 150
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

function doEdit() {
	window.location.href = 'rec' ;
}

function doNew(pid){
	window.location.href = 'reg';
}

