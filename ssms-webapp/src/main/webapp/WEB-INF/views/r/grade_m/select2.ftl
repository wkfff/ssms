<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="select.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
        企业范围:
    <!-- <input class="easyui-combobox" id="cb_pro" style="width:120px;" data-bind="comboboxValue:comboPro,easyuiOptions:comboProSettings">&nbsp;省&nbsp;&nbsp; -->
    <input class="easyui-combobox" id="cb_city" style="width:120px;" data-bind="comboboxValue:comboCity,easyuiOptions:comboCitySettings">&nbsp;市&nbsp;&nbsp;
    <input class="easyui-combobox" id="cb_county" style="width:140px;" data-bind="comboboxValue:comboCounty,comboboxText:comboCountyText,easyuiOptions:comboCountySettings">&nbsp;区/县&nbsp;&nbsp;
         企业名称：<input class="easyui-textbox" id="txt_name" style="width:80px;" data-bind="textboxValue:txtName">
    <input class="easyui-checkbox" id="chk" data-bind="booleanValue:chkNoComplete">只显示未评审的企业
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click:gridEvents.refreshClick">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-back" plain="true"  onclick="window.location.href='/r/home'">返回</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: gridSettings" ></table>
</@>
