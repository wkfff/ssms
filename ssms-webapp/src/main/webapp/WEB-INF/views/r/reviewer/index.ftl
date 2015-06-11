<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
ko.applyBindings(new ViewModel());
</script>
</#assign>
<@layout.doLayout script>
<div id="toolbar" class="z-toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-new" plain="true" onclick="doNew()">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" >删除</a>
</div>
<table id="dg_index" data-bind="datagridValue:selectItem,easyuiOptions:gridSettings"></table>
</@>
