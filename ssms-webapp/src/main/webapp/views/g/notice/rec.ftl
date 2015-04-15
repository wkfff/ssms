<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"rec.json",			
			saveUrl:"save.json",
			delUrl:"del.json"
	}
	$(document).ready(function(){
		$form.init(setting);			
	});
</script>
</#assign>
<@layout.doLayout script>

<div class="main">
	<div class="toolbar">
		<input type="button" name="btn_save" value="保存">	
        <input type="button" name="btn_del" value="删除">
        <input type="button" name="btn_publice" value="发布">
		<input type="button" onclick="window.history.go( -1 );" value="返回">	
	</div>
	<form id="mainForm" >
		<table>		
			<tr><td>标题:</td><td><input type="text" id="C_TITLE" name="C_TITLE" /></td></tr>
			<tr><td>发布日期:</td><td><input type="text" id="T_BEGIN" name="T_BEGIN" /></td></tr>
			<tr><td>创建人:</td><td><input type="text" id="S_CREATOR" name="S_CREATOR" /></td></tr>
            <tr><td>创建日期:</td><td><input type="text" id="T_CREATE" name="T_CREATE" /></td></tr>
            <tr><td>修改人:</td><td><input type="text" id="S_UPDATE" name="S_UPDATE" /></td></tr>
            <tr><td>修改日期:</td><td><input type="text" id="T_UPDATE" name="T_UPDATE" /></td></tr>
		</table>
	</form>
</div>

</@layout.doLayout>