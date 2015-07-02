<@layout.extends name="../../_layouts/stdtmpfile.ftl">
<@layout.put block="head">
</@>
<@layout.put block="panel_content">
<div style="width:100%;height:685px;">
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table>
</div>
</@>
<@layout.put block="footer">
<script type="text/javascript" src="${BASE_PATH}/index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel(${file.id},'${BASE_PATH}'));
</script>
</@>
</@layout.extends>



