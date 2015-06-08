<#import "../../layout/_list.ftl" as layout/>
<#assign script>

<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel(${sid!}));
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click: gridEvents.refreshClick">刷新</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: gridEvents.addClick">添加</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: gridEvents.editClick">编辑</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: gridEvents.deleteClick">删除</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click: gridEvents.saveClick">保存</a>
                    <!-- <a href="#" class="easyui-linkbutton" iconCls="icon-up" plain="true" data-bind="click: gridEvents.upClick">上移</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-down" plain="true" data-bind="click: gridEvents.downClick">下移</a> -->
                    <a href="#" class="easyui-linkbutton" iconCls="icon-back" plain="true" data-bind="click:function(){window.location.href='${referer}';}">返回</a>
</div>
<table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
</@>
