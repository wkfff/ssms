<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel(${R_SID}));
</script>
</#assign>
<@layout.doLayout script=script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'功能导航', split:true" style="width: 200px">
        <ul data-bind="treeValue:selectedNode,easyuiOptions:treeSettings"></ul>
    </div>
    <div data-options="region:'center', border:false">
        <div class="easyui-tabs" fit="true">
            <div title="概要" iconCls="icon-details">
                <div class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: recEvents.saveClick">保存</a>
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-bind="click: recEvents.removeClick">删除</a>
                </div>
                <form class="form" method="post" data-bind="with:rec">
                    <p class="ue-clear">
                        <label>名称：</label>
                        <span class="control">
                            <input data-bind="textboxValue:C_NAME"/>
                        </span>
                    </p>

                    <p class="ue-clear">
                        <label>描述：</label>
                        <span class="control">
                            <input data-bind="textareaValue:C_DESC"/>
                        </span>
                    </p>
                </form>
            </div>
            <div title="子分类" iconCls="icon-table">
                <div id="toolbar" class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click: gridEvents.refreshClick">刷新</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: gridEvents.addClick">添加</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: gridEvents.editClick">编辑</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: gridEvents.deleteClick">删除</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click: gridEvents.saveClick">保存</a>
                </div>
                <table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
            </div>
            <div title="文件" iconCls="icon-table" style="padding: 2px">
                <iframe id="iframe" frameborder="0" width="100%" height="99%" data-bind="attr: {src: fileUrl}"></iframe>
            </div>
        </div>
    </div>
</@>