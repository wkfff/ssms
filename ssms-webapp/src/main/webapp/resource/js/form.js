$form = {		
		setting:{},
		sourceData:null,
		init: function(set){
			setting = set;
			$("input[name='btn_save']").click(function(){$form.doSave();});
			if(setting.sid!=null) $form.bindData(setting.sid);
		},		
		bindData: function(sid){
			$.get(setting.dataUrl, {sid:setting.sid},function (data) {      
				sourceData = data;
				if (data == null) return;					
				$.each(data, function(field, value){
					$form.setValue(field,value);
				});	
		    },"json"); 
		},
		setValue:function(field,value){
			//需要具体处理
			$("#"+field).val(value);
		},
		getValue:function(field){
			//需要具体处理
			return $("#"+field).val();
		},
		doSave: function(){
			var postData = [];			
			if (setting.sid!=null){
				$.each(sourceData, function(field,value){
					if ($("#"+field).val()!=value && typeof(value)!="undefined"){
						var value = $form.getValue(field); 
						if (field != "SID" && typeof(value)!="undefined") 
							postData.push({"name":field,"value":value});
					}
				});
				postData.push({"name":"sid","value":setting.sid});
			}else{
				postData =$("#mainForm").serializeArray();
			}
			$.post(setting.saveUrl,postData,function (data) {      
				alert("保存成功！");
				window.close();
		    });
		}
}