function ViewModel() {
	var self = this;
	var model = {
		selectItem: ko.observable(),
        selectIndex: ko.pureComputed(function () {
            var row = model.selectItem();
            if (row) return settings.gridSettings.datagrid('getRowIndex', row);
        })
	};
	var settings = {
		gridSettings : {
			title : '评审员管理',
			url : 'list',
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
						field : 'C_SEX',
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
						field : 'S_CLASSWORKER',
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
					} 
					] ]
		},
		comboboxSettings : {
			valueField : 'key',
			textField : 'value'
		}
	};
	var events = {
			delClick : function(){
				var value = model.selectItem();
	            if (value == null) {
	                $.messager.alert("警告", "请先选择一行数据！", "warning");
	                return;
	            }
				$.messager.confirm("删除确认", "您确认删除选定的记录吗？", function(deleteAction) {
					if (deleteAction) {
						$.post('del', {sid: value.SID}, function () {
			                $.messager.alert('消息', '成功删除记录！', "info", function () {
			                    window.location.href=window.location.href;
			                });
			            });
					}
				});
			},
			editClick : function(){
				var value = model.selectItem();
	            if (value == null) {
	                $.messager.alert("警告", "请先选择一行数据！", "warning");
	                return;
	            }
				window.location.href = 'rec?sid='+value.SID;
			}
			
	};
	return $.extend(self, model, events, settings);
}

function doNew(pid){
	window.location.href = 'reg';
}

