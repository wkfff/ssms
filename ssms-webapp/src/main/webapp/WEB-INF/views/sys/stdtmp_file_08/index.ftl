<@layout.extends name="../../_layouts/stdtmpfile.ftl">
<@layout.put block="head">
</@>
<@layout.put block="panel_content">
<div style="width:100%;height:685px;">
<div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click: gridEvents.refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: gridEvents.addClick">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: gridEvents.editClick">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: gridEvents.deleteClick">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click: gridEvents.saveClick">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-back" plain="true" data-bind="click:function(){window.location.href='${referer!}';}">返回</a>
</div>
<table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
</div>
</@>
<@layout.put block="footer">
<script type="text/javascript" src="${BASE_PATH}/index.js"></script>
<script type="text/javascript">
    $(function () {
        ko.applyBindings(new ViewModel(${file.id!},${file.templateId},'${BASE_PATH}'));
    });
</script>
</@>
</@layout.extends>





