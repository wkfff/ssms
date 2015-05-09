<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="select.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
        省：<input class="easyui-combobox" id="cb_pro" style="width:100px;" data-bind="comboboxValue:comboPro,easyuiOptions:comboProSettings">
        市：<input class="easyui-combobox" id="cb_city" style="width:100px;" data-bind="easyuiOptions:comboCitySettings">
        县：<input class="easyui-combobox" id="cb_county" style="width:100px;" data-bind="easyuiOptions:comboCountySettings">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click:gridEvents.queryClick">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" data-bind="click:gridEvents.doClear" title="清空查询条件">重置</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click:gridEvents.editClick">评审</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: gridSettings"></table>
</@>
