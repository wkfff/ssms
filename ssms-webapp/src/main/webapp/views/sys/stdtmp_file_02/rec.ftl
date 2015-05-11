<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
var  model={
	 C_NAME: ko.observable('${C_NAME!}'),
	 C_NUMBER: ko.observable('${C_NUMBER!}'),
	 C_DEPT_01: ko.observable('${C_DEPT_01!}'),
	 T_DATE_01: ko.observable('${T_DATE_01!}'),
	 C_DEPT_02: ko.observable('${C_DEPT_02!}'),
	 C_DEPT_03: ko.observable('${C_DEPT_03!}'),
	 SID: '${SID!}'
};
var extModel = {
        htmlContent: ko.observable()
};
var settings = {
        htmleditSettings: {
            table: "STDTMP_FILE_02",
            field: 'C_CONTENT',
            sid: '${SID!}',
        }
    };
var events={
	   saveClick: function(){
		   $.post('save.do',model,function(result){
			   if(result.SID){
				   $.messager.alert("提示","保存成功","info",function(){
					   window.location.href = 'rec.html?sid=' + result.SID + "&backURL=${backURL!referer!}";
				   });
			   } else {
                   $.messager.alert("提示", "保存失败", "warning");
               }
		   },"json");
	
       settings.htmleditSettings.save();
	   }
};

ko.applyBindings($.extend({},model,events,settings, extModel));
</script>
</#assign>
<@layout.doLayout script>
<div class="z-toolbar">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a class="easyui-linkbutton" onclick="window.location.href='${referer}&backURL=${backURL}'" plain="true" iconCls="icon-undo">返回</a>
</div>
<form class="form" method="post" style="padding:10px 31px;">
    <div class="easyui-panel" title="概要" style="padding-bottom: 10px;">
        <p class="long-input ue-clear">
            <label>通知标题</label>
            <span class="control">
                <input data-bind="textboxValue: C_NAME"/>
            </span>
        </p>
        <p class="long-input ue-clear">
            <label>通知编号</label>
            <span class="control">
                <input data-bind="textboxValue: C_NUMBER"/>
            </span>
        </p>
    </div>
    <div class="easyui-panel" title="正文" style="padding: 6px">
        <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
    </div>
    <div class="easyui-panel" title="发布信息" style="padding-bottom: 10px;">
        <p class="ue-clear">
            <label>发布部门</label>
            <span class="control">
                <input data-bind="textboxValue: C_DEPT_01"/>
            </span>
        </p>
        <p class="ue-clear">
            <label>发布日期</label>
            <span class="control">
                <input data-bind="dateboxValue: T_DATE_01"/>
            </span>
        </p>
        <p class="ue-clear">
            <label>主送部门</label>
            <span class="control">
                <input data-bind="textboxValue: C_DEPT_02"/>
            </span>
        </p>
        <p class="ue-clear">
            <label>抄送部门</label>
            <span class="control">
                <input data-bind="textboxValue: C_DEPT_03"/>
            </span>
        </p>
    </div>
</form>
</@>