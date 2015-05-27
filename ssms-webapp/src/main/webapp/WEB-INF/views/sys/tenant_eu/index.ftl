<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
ko.applyBindings(new ViewModel('${pid}'));
</script>
</#assign>
<@layout.doLayout script>
<div id="toolbar" class="z-toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-new" plain="true" onclick="doNew('${pid}')">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="window.location.href='/sys/tenant_e/index'">返回</a>
</div>
<table id="dg_index" data-bind="datagridValue:selectItem,easyuiOptions:gridSettings"></table>
</@>
