<!DOCTYPE HTML>
<HTML>
<HEAD>
	<META charset="utf-8">  
    <META http-equiv="X-UA-Compatible" content="IE=edge">
    <TITLE>安全生产标准化管理系统</TITLE>
    <LINK href="/resource/css/datagrid.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/js/common.js"></script>
	<script type="text/javascript" src="/resource/js/datagrid.js"></script>	
	<script>
		var setting = {
			gridId:"dg",
			dataUrl:"/sys/org/list.json",
			delUrl:"/sys/org/del.json",
			addUrl:"/sys/org/rec.html",
			openUrl:"/sys/org/rec.html"
		}
		
		var setting2 = {
			gridId:"dg2",
			dataUrl:"/sys/org/list.json",
			delUrl:"/sys/org/del.json",
			addUrl:"/sys/org/rec.html",
			openUrl:"/sys/org/rec.html"
		}
		
		$(document).ready(function(){
			var dg = new datagrid(setting);		
			dg.doQuery();
			
			var dg2 = new datagrid(setting2);		
			dg2.doQuery();
		});
	</script>
</HEAD>

<BODY>
<#list _USER_SEX_ as map>
    ${map.key}:${map.value}
</#list>
<div class="datagrid" id="dg">
	<div class="toolbar">
			<select class="filter_field">
				<option value="C_NAME">企业名称</option>
				<option value="C_ADDR">地址</option>
			</select>
			<input type="text" class="filter_value" />
			<input type="button" name="btn_query" value="查询">
			<input type="button" name="btn_add" value="新增">
			<input type="button" name="btn_open" value="打开">
			<input type="button" name="btn_del" value="删除">
	</div>
	<table>
		<thead>
			<tr>
				<td class="cell-chk"><input class="chk" type="checkbox" /></td>
				<td id="C_NAME">企业名称</td>
				<td id="C_ADDR">地址</td>
			<tr>
		</thead>
		<tbody/>
	</table>
</div>	

<div class="datagrid" id="dg2">
	<div class="toolbar">
			<select class="filter_field">
				<option value="C_NAME">企业名称</option>
				<option value="C_ADDR">地址</option>
			</select>
			<input type="text" class="filter_value" />
			<input type="button" name="btn_query" value="查询">
			<input type="button" name="btn_add" value="新增">
			<input type="button" name="btn_open" value="打开">
			<input type="button" name="btn_del" value="删除">
	</div>
	<table>
		<thead>
			<tr>
				<td class="cell-chk"><input class="chk" type="checkbox" /></td>
				<td id="C_NAME">企业名称</td>
				<td id="C_ADDR">地址</td>
			<tr>
		</thead>
		<tbody/>
	</table>
</div>
</BODY>
</HTML>
