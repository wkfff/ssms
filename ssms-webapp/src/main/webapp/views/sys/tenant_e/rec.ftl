<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"rec.json",			
			saveUrl:"save.json"
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
		<input type="button" onclick="window.history.go( -1 );" value="返回">	
	</div>
	<form id="mainForm" >
		<table>		
			<tr><td>企业名称:</td><td><input type="text" id="C_NAME" name="C_NAME" /></td></tr>
			<tr><td>地址:</td><td><input type="text" id="C_ADDR" name="C_ADDR" /></td></tr>
		</table>
	</form>
</div>

</@layout.doLayout>