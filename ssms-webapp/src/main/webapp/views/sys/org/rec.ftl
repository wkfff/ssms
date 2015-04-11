<!DOCTYPE HTML>
<HTML>
<HEAD>
	<META charset="utf-8">  
    <META http-equiv="X-UA-Compatible" content="IE=edge">
    <TITLE>安全生产标准化管理系统</TITLE>
	<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
	<script type="text/javascript" src="/resource/js/common.js"></script>
	<script type="text/javascript" src="/resource/js/form.js"></script>
	<style>
		.main{padding:5px;}		
	</style>
	<script>
		var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"/sys/org/rec.json",			
			saveUrl:"/sys/org/save.json"
		}
		
		$(document).ready(function(){
			$form.init(setting);			
		});				
	</script>
</HEAD>

<BODY>
<div class="main">
	<div class="toolbar">
		<input type="button" name="btn_save" value="保存">	
		<input type="button" onclick="window.close()" value="关闭">	
	</div>
	<form id="mainForm" >
		<table>		
			<tr><td>企业名称:</td><td><input type="text" id="C_NAME" name="C_NAME" /></td></tr>
			<tr><td>地址:</td><td><input type="text" id="C_ADDR" name="C_ADDR" /></td></tr>
		</table>
	</form>
</div>	
</BODY>

</HTML>
