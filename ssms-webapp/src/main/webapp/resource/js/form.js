$form = {
	setting : {},
	editors : {},
	sourceData : null,
	init : function(set) {
		setting = set;
		//标准按钮
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

		//富文本编辑器
		$.each($(".ui-editor"),function(index,e){
			var editor = KindEditor.create('textarea[id="' + e.id + '"]', {
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
			$form.editors[e.id] = editor;
		});
		//日期
		$.each($(".ui-date"),function(index,e){
			$(e).datepicker({
				inline: false
			});
		});
		//日期时间
		$.each($(".ui-date"),function(index,e){
			$(e).datetimepicker({
				 timeFormat: "HH:mm:ss",
	             dateFormat: "yy-mm-dd"
			});
		});
		//标签
		$.each($(".ui-tabs"),function(index,e){
			$(e).tabs();
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
		var e = $("#" + field);
		//选择框
		if (e.hasClass("ui-checkbox")){
			var values = value.split(",");
			$.each($("input[type='checkbox']",e), function(i, chk) {
				if ($.inArray(chk.value,values)>-1) chk.checked = true;
			});
		}
		//单选
		else if (e.hasClass("ui-radio")){
			$("input[name="+field+"][value="+value+"]").attr("checked",true);
		}
		//富文本编辑器
		else if (e.hasClass("ui-editor"))
			$form.editors[field].html(value);
		else
			$("#" + field).val(value);
	},
	getValue : function(field) {
		var value;
		var e = $("#" + field);
		//选择框
		if (e.hasClass("ui-checkbox")){
			var values = [];
			$.each($(":checked",e), function(i, chk) {
				values.push(chk.value);
			});
			value = values.join(",");
		}
		//单选
		else if (e.hasClass("ui-radio")){
			value = $(":checked",e).val();
		}
		//富文本编辑器
		else if (e.hasClass("ui-editor"))
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
				if (v != value && typeof (v) != "undefined") {
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
		// 如果有定义回退的URL，那么就使用这个URL进行跳转，否则用默认的浏览器调整规则。				by 张铮彬#2015-4-20
		if (setting.backUrl) $win.navigate(setting.backUrl);
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