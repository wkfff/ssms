<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>
</#assign>
<@layout.doLayout script>
<div id="toolbar" class="z-toolbar">
    <#--区域:
    <input style="width: 80px" data-bind="comboboxSource:proSource, comboboxText: S_PROVINCE, easyuiOptions: comboboxSettings"/>省<input style="width: 80px" data-bind="comboboxSource:citySource, comboboxText: S_CITY, easyuiOptions: comboboxSettings"/>市<input style="width: 80px" data-bind="comboboxCounty:proSource,comboboxText: S_COUNTY, easyuiOptions: comboboxSettings"/>县
    <span style="width: 45px;display: inline-block">&nbsp;</span>-->
    企业名称: <input style="width: 120px" data-bind="textboxValue: C_NAME"/>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: searchClick">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">注册新企业</a>
</div>
<table id="dg_index" data-bind="datagridValue:selectItem,easyuiOptions:gridSettings"></table>
</@>
