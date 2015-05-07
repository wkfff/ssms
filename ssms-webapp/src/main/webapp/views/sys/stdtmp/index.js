/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-05-06
 * 创建用户：张铮彬
 */

function viewModel(queryParams) {
    var self = this;
    self.sid = ko.observable();
    self.selectNode = ko.observable();
    // 值改变的时候重新执行的行为
    self.sid.subscribe(function (newValue) {
        if (!newValue) return;
        self.rec.load();
        self.items.queryParams({R_SID: newValue});
        /*listChildren.load();
         listFile.load();*/
    });

    self.tree = {
        url: 'tree.json',
        queryParams: queryParams
    };
    self.tree.onSelect = function (node) {
        self.sid(node.id);
        self.selectNode(node);
    };
    self.tree.onLoadSuccess = function () {
        var node = self.selectNode();
        if (node) node = self.tree.tree('find', self.sid());
        if (!node) node = self.tree.tree('getRoot');
        self.sid(null);
        self.tree.tree('select', node.target);
    };
    self.tree.reload = function () {
        self.tree.tree('reload');
    };

    self.rec = {
        data: ko.observable({})
    };
    self.rec.load = function () {
        $.post('rec.json', {sid: self.sid()}, function (result) {
            self.rec.data(result);
        });
    };
    self.rec.saveClick = function () {
        self.rec.form('submit', {
            url: 'save.do',
            onSubmit: function () {
                return self.rec.form('validate');
            },
            success: function () {
                $.messager.alert('提示信息', '保存成功!', 'info', function () {
                    self.tree.reload();
                });
            }
        });
    };
    self.rec.removeClick = function () {
        $.post('del.do', {sid: self.sid}, function () {
            self.tree.reload();
        })
    };

    self.items = {
        url: "list.json",
        queryParams: ko.observable(),
        toolbar: '#items_toolbar'
    };
    self.items.onBeforeLoad = function () {
        return self.items.queryParams() != null;
    };
    self.items.refreshClick = function () {
        self.items.datagrid('reload');
    };
    self.items.addClick = function () {
        if (self.items.onClickRow()) {
            var row = {SID: utils.uuid(), R_SID: self.sid()};
            self.items.datagrid('appendRow', row);
            self.items.datagrid('selectRecord', row.SID);
            self.items.editClick();
        }
    };
    self.items.editClick = function () {
        var row = self.items.datagrid('getSelected');
        if (row) {
            var index = self.items.datagrid('getRowIndex', row);
            self.items.datagrid('beginEdit', index);
            self.items.edit_id = row.SID;
        }
    };
    self.items.deleteClick = function () {
        var row = self.items.datagrid('getSelected');
        if (row) {
            var index = self.items.datagrid('getRowIndex', row);
            self.items.datagrid('deleteRow', index);
            if (!row.SID.toString().startsWith("_")) $.post('del.do', {sid: row.SID}, function () {
                $.messager.alert('消息', '成功删除记录！');
            });
        }
    };
    self.items.saveClick = function () {
        if (!self.items.onClickRow()) {
            self.items.deleteClick();
            return;
        }
        var changes = self.items.datagrid('getChanges');
        if (changes.length > 0) {
            $.post("batchSave.do", {data: $.toJSON(changes)}, function () {
                $.messager.alert('消息', '成功保存记录！', "info", function () {
                    self.tree.reload();
                });
            });
        }
    };
    self.items.onDblClickRow = self.items.editClick;
    self.items.onClickRow = function () {
        var edit_id = self.items.edit_id;
        if (!!edit_id) {
            var index = self.items.datagrid('getRowIndex', edit_id);
            if (self.items.datagrid('validateRow', index)) { //通过验证
                self.items.datagrid('endEdit', index);
                self.items.edit_id = undefined;
            }
            else { //未通过验证
                self.items.datagrid('selectRecord', edit_id);
                return false;
            }
        }
        return true;
    };

    self.fileUrl = ko.pureComputed(function () {
        var sid = self.sid();
        if (!sid) return;
        return '/sys/stdtmp_file/index.html?R_SID={0}'.format(self.sid());
    })
}
