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
			rownumbers: true,
            pagination: true,
            fit: true,
            pageSize: 50,
			toolbar : '#toolbar',
			columns : [ [
					{
						field : 'C_NAME',
						align : 'center',
						title : '姓名',
						width : 50
					},
					{
						field : 'S_SEX',
						align : 'center',
						title : '性别',
						width : 30
					},
					{
						field : 'T_BIRTH',
						align : 'center',
						title : '出生日期',
						width : 80
					},
					{
						field : 'C_CLASSWORKER',
						align : 'center',
						title : '人员类别',
						width : 60,
						formatter: function (value, row) {
                            if (value) return value == '01' ? "专家" : "评审员"
                        }
					},
					{
						field : 'C_POSITION',
						align : 'center',
						title : '职务',
						width : 60
					},
					{
						field : 'C_TECHNOLOGY',
						align : 'center',
						title : '专业技术职务',
						width : 100
					},
					{
						field : 'C_DEPT',
						align : 'center',
						title : '工作单位及部门',
						width : 350
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

