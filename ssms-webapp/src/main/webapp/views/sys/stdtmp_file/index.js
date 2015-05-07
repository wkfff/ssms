/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-05-06
 * 创建用户：张铮彬
 */

function viewModel(parentId) {
    var self = this;
    self.R_SID = parentId;
    self.grid = {
        url: "list.json",
        queryParams: {
            R_SID: parentId
        },
        toolbar: '#toolbar'
    };
    self.refreshClick = function () {
        self.grid.datagrid('reload');
    };
    self.addClick = function () {
        window.location.href = 'rec.html?pid={0}'.format(self.R_SID);
    };
    self.editClick = function () {
        var row = self.grid.datagrid('getSelected');
        if (!row) {
            $.messager.alert("警告", "请先选择一行数据！", "warning");
            return;
        }
        window.location.href = 'rec.html?sid={0}'.format(row.SID);
    };
    self.deleteClick = function () {
        var row = self.grid.datagrid('getSelected');
        if (row) {
            var index = self.grid.datagrid('getRowIndex', row);
            self.grid.datagrid('deleteRow', index);
            if (!row.SID.toString().startsWith("_")) $.post('del.do', {sid: row.SID}, function () {
                $.messager.alert('消息', '成功删除记录！');
            });
        }
    };
    self.grid.onDblClickRow = self.editClick;
}