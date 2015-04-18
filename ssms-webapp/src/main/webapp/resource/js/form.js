$form = {
	setting : {},
	editors : {},
	sourceData : null,
	init : function(set) {
		setting = set;
		$("input[name='btn_save']").click(function() {
			$form.doSave();
		});
		$("input[name='btn_back']").click(function() {
			$form.doBack();
		});
		$("input[name='btn_del']").click(function() {
			$form.doDel();
		});
		if (setting.sid != null)
			$form.bindData(setting.sid);
		else
			$("input[name='btn_del']").hide();

		var es = setting.editor.split(",");
		$.each(es,function(index,field){
			var editor = KindEditor.create('textarea[name="' + field + '"]', {
				uploadUrl : '',
				allowFileManager :false,
				allowUpload:false,
				items : [
                    'fontname', 'fontsize', 
                    '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline','removeformat', 
                    '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist','insertunorderedlist',
                    '|', 'table','|','fullscreen'
					]
			});
			$form.editors[field] = editor;
		});
	},
	bindData : function(sid) {
		$.get(setting.dataUrl, {
			sid : setting.sid
		}, function(data) {
			sourceData = data;
			if (data == null)
				return;
			$.each(data, function(field, value) {
				$form.setValue(field, value);
			});
		}, "json");
	},
	setValue : function(field, value) {
		// 需要具体处理
		if ($form.editors[field])
			$form.editors[field].html(value);
		else
			$("#" + field).val(value);
	},
	getValue : function(field) {
		// 需要具体处理
		var value;
		if ($form.editors[field])
			value = $form.editors[field].html();
		else
			value = $("#" + field).val();
		return value;
	},
	doSave : function() {
		var postData = [];
		if (setting.sid != null) {
			$.each(sourceData, function(field, value) {
				var v = $form.getValue(field);
				if (v != value
						&& typeof (v) != "undefined") {
					if (field != "SID" && field !="sid")
						postData.push({
							"name" : field,
							"value" : v
					});
				}
			});
			postData.push({
				"name" : "sid",
				"value" : setting.sid
			});
		} else {
			postData = $("#mainForm").serializeArray();
		}
		$.post(setting.saveUrl, postData, function(data) {
			var sid = data["SID"];
			if (sid) setting.sid = sid;
			alert("保存成功！");
		}).fail(function(jqXHR) {
			alert(jqXHR.status + ":" + jqXHR.statusText);
			// TODO：服务端送来的具体错误内容
			alert(jqXHR.responseJSON);
		});
	},
	doBack : function() {
		window.history.go(-1);
	},
	doDel : function() {
		if (!confirm("确认要删除当前记录吗？"))
			return;
		$.post(setting.delUrl, {
			sid : setting.sid
		}, function(data) {
			$form.doBack();
		});
	}
}