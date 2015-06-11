<#import "../../layout/_list.ftl" as layout>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    $(function () {
        ko.applyBindings(new ViewModel());
    });
</script>
</#assign>
<@layout.doLayout script=script>
<div id="navlyt" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', split:true" title="导航树" style="width: 200px">
        <ul data-bind="treeValue:selectedNode,easyuiOptions:treeSettings"></ul>
    </div>
    <div data-options="region:'center', border:false">
        <div class="easyui-tabs" fit="true">
            <div title="概要" iconCls="icon-details">
                <div class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: recEvents.saveClick">保存</a>
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-bind="click: recEvents.removeClick">删除</a>
                </div>
                <form class="form" data-bind="with:rec">
                    <p class="ue-clear">
                        <label>导航名称</label>
                        <span class="control">
                            <input data-bind="textboxValue: C_NAME"/>
                        </span>
                    </p>

                    <p class="ue-clear">
                        <label>图标</label>
                        <span class="control">
                            <input data-bind="textboxValue: C_ICON"/>
                        </span>
                    </p>

                    <p class="ue-clear">
                        <label>URL</label>
                        <span class="control">
                            <input data-bind="textboxValue: C_URL"/>
                        </span>
                    </p>

                    <p class="ue-clear">
                        <label>描述</label>
                        <span class="control">
                            <input data-bind="textareaValue: C_DESC"/>
                        </span>
                    </p>
                </form>
            </div>
            <div title="子功能" iconCls="icon-table">
                <div id="toolbar" class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click: gridEvents.refreshClick">刷新</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: gridEvents.addClick">添加</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: gridEvents.editClick">编辑</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: gridEvents.deleteClick">删除</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click: gridEvents.saveClick">保存</a>
                </div>
                <table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
            </div>
        </div>
    </div>
</div>
</@>