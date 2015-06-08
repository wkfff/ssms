<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel(${sid}));
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table>
</@>