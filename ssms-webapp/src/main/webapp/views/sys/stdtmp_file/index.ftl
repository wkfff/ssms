<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new viewModel(${R_SID}));

    function cycleFormatter(value, row) {
        if (value) {
            return '{0}/{1}'.format(value, row.S_CYCLE);
        }
    }
    function remindFormatter(value, row) {
        if (value) {
            return value === 1 ? "是" : "否"
        }
    }
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
</div>
<table data-bind="datagrid:grid">
    <thead>
    <tr>
        <th field="C_NAME" width="100">文件名</th>
        <th field="C_DESC" width="200">描述</th>
        <th field="N_CYCLE" align="center" width="80" formatter="cycleFormatter">更新周期</th>
        <th field="B_REMIND" align="center" width="80" formatter="remindFormatter">是否提醒</th>
    </tr>
    </thead>
</table>
</@>