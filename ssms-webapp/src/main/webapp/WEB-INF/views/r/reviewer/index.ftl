<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
ko.applyBindings(new ViewModel());
$('#dg_index').datagrid({onDblClickRow : function(rowIndex, rowData){
    window.location.href = 'rec?sid='+rowData.SID;
}})
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel"  fit="true">
<div id="toolbar" class="z-toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-new" plain="true" onclick="doNew()">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click :editClick">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-bind="click : delClick">删除</a>
</div>
<table id="dg_index" data-bind="datagridValue:selectItem,easyuiOptions:gridSettings"></table>
</div>
</@>
