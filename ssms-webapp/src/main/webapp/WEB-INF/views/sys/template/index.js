/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-05-22
 * 创建用户：张铮彬
 */

function viewModel() {
    var self = this;
    self.grid = {
    	title: "模板管理",
        url: "list",
        toolbar: '#toolbar'
    };
    self.refreshClick = function () {
        self.grid.datagrid('reload');
    };
    self.addClick = function () {
        if (self.grid.onClickRow()) {
            var row = {SID: utils.uuid(), C_NAME: ""};
            self.grid.datagrid('appendRow', row);
            self.grid.datagrid('selectRecord', row.SID);
            self.editClick();
        }
    };
    self.editClick = function () {
        var row = self.grid.datagrid('getSelected');
        if (row) {
            var index = self.grid.datagrid('getRowIndex', row);
            self.grid.datagrid('beginEdit', index);
            self.edit_id = row.SID;
        }
    };
    self.deleteClick = function () {
        var row = self.grid.datagrid('getSelected');
        if (row) {
            var index = self.grid.datagrid('getRowIndex', row);
            self.grid.datagrid('deleteRow', index);
            if (!row.SID.toString().startsWith("_")) $.post('del', {sid: row.SID}, function () {
                $.messager.alert('消息', '成功删除记录！');
            });
        }
    };
    self.grid.onDblClickRow = self.editClick;
    self.grid.onClickRow = function () {
        var edit_id = self.edit_id;
        if (!!edit_id) {
            var index = self.grid.datagrid('getRowIndex', edit_id);
            if (self.grid.datagrid('validateRow', index)) { //通过验证
                self.grid.datagrid('endEdit', index);
                self.edit_id = undefined;
            }
            else { //未通过验证
                self.grid.datagrid('selectRecord', edit_id);
                return false;
            }
        }
        return true;
    };
    self.saveClick = function () {
        if (!self.grid.onClickRow()) {
            self.deleteClick();
            return;
        }
        var changes = self.grid.datagrid('getChanges');
        if (changes.length > 0) {
            $.post("batchSave", {data: $.toJSON(changes)}, function () {
                $.messager.alert('消息', '成功保存记录！', "info", function () {
                    self.grid.datagrid('reload');
                });
            });
        }
    };
}

var editSTDTMP = function (sid) {
    window.location.href = '/sys/stdtmp/index?R_SID='+sid;
};
var editGRADETMP = function (sid) {
    window.location.href = '/sys/stdtmp_grade/index?R_SID='+sid;
};
var editREPTMP = function (sid) {
    window.location.href = '/sys/stdtmp_rep/rec?R_SID='+sid;
};