<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    var vm = new ViewModel();
    ko.applyBindings(vm);
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
     请选择企业范围:
    <input class="easyui-combobox" id="cb_pro" style="width:120px;" data-bind="comboboxValue:comboPro,easyuiOptions:comboProSettings">&nbsp;省&nbsp;&nbsp;
    <input class="easyui-combobox" id="cb_city" style="width:140px;" data-bind="comboboxValue:comboCity,easyuiOptions:comboCitySettings">&nbsp;市&nbsp;&nbsp;
    <input class="easyui-combobox" id="cb_county" style="width:160px;" data-bind="comboboxValue:comboCounty,easyuiOptions:comboCountySettings">&nbsp;区/县&nbsp;&nbsp;
         企业名称：<input class="easyui-textbox" id="txt_name" style="width:80px;" data-bind="textboxValue:txtName">
    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:gridEvents.refreshClick">查询</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: gridSettings"></table>
</@>
