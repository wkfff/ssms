<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    function formatter(value, row) {
        if (row.SID === undefined || row.SID.toString().startsWith("_")) return;
        return "<a href='#' onclick='editSTDTMP({0})'>编辑达标体系模板</a>".format(row.SID);
    }
    ko.applyBindings(new viewModel());
</script>
</#assign>
<@layout.doLayout script>
    <@layout.group title="模板管理" border=true fit=true>
    <div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click:saveClick">保存</a>
    </div>
    <table id="dg" data-bind="datagrid: grid">
        <thead>
        <tr>
            <th field="C_NAME" width="400" editor="{ type:'validatebox', options:{ required:true, validType:['length[0, 300]'] }}">模板名称</th>
            <th field="_action" width="400" formatter="formatter">操作</th>
        </tr>
        </thead>
    </table>
    </@>
</@layout.doLayout>