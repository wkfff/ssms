<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"rec.json"		
	}
	$(document).ready(function(){
		$form.init(setting);			
	});
</script>
</#assign>
<@layout.doLayout script>
<div class="main">
		<table>		
			<tr><td>标题:</td><td><input type="text" id="C_TITLE" name="C_TITLE" /></td></tr>
			<tr><td>发布日期:</td><td><input type="text" id="T_BEGIN" name="T_BEGIN" /></td></tr>
			<tr><td>创建人:</td><td><input type="text" id="S_CREATOR" name="R_CREATE" /></td></tr>
            <tr><td>创建日期:</td><td><input type="text" id="T_CREATE" name="T_CREATE" /></td></tr>
            <tr><td>修改人:</td><td><input type="text" id="S_UPDATE" name="S_UPDATE" /></td></tr>
            <tr><td>修改日期:</td><td><input type="text" id="T_UPDATE" name="T_UPDATE" /></td></tr>
		</table>
</div>
</@layout.doLayout>