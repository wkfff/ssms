<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>
</#assign>
<@layout.doLayout script=script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'分类', split:true" style="width: 200px">
        <ul data-bind="treeValue:selectedNode,easyuiOptions:treeSettings"></ul>
    </div>
    <div data-options="region:'center', border:false">
        <div title="文件" iconCls="icon-table">
                <div id="toolbar" class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click: gridEvents.refreshClick">刷新</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: gridEvents.addClick">添加</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: gridEvents.editClick">编辑</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: gridEvents.deleteClick">删除</a>
                </div>
                <table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
        </div>
    </div>
</@>