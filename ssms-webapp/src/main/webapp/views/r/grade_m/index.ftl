<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
    请选择企业范围
        省：<input class="easyui-combobox" id="cb_pro" style="width:120px;" data-bind="comboboxValue:comboPro,easyuiOptions:comboProSettings">
        市：<input class="easyui-combobox" id="cb_city" style="width:140px;" data-bind="comboboxValue:comboCity,easyuiOptions:comboCitySettings">
        县：<input class="easyui-combobox" id="cb_county" style="width:160px;" data-bind="comboboxValue:comboCounty,easyuiOptions:comboCountySettings">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:gridEvents.refreshClick">刷新</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: gridSettings"></table>
</@>
