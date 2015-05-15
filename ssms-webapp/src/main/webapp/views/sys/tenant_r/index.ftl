<#import "/layout/_list.ftl" as layout/>
<#assign script>
    <script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>
</#assign> 
<@layout.doLayout script>
<div id="toolbar" class="z-toolbar">
            单位名称: <input style="width: 120px" data-bind="textboxValue: C_NAME"/>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: searchClick">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">注册评审单位</a>
</div>
<table id="dg_index" data-bind="datagridValue:selectItem,easyuiOptions:gridSettings"></table>
</@>
