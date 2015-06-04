/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：rec.js
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

function LedgerModel(items) {
    var self = this;
    self.items = ko.mapping.fromJS(items);
    self.levelSource = ko.observableArray([{code: '01', name: '一般隐患'}, {code: '02', name: '重大隐患'}]);
    self.levelSettings = {valueField: 'code', textField: 'name'};

    self.addItem = function () {
        self.items.push(ko.mapping.fromJS({
            SID: null,
            C_NAME: null,
            C_EXAMINER: null,
            T_EXAMINE: null,
            P_LEVEL: '01',
            C_MEASURE: null,
            C_RESPONSIBLE: null,
            T_RECTIFICATION: null,
            C_ACCEPTANCE: null
        }));
    };

    self.save = function (item, event) {
        if ($form.validate('.item') == false) return;
        utils.messager.showProgress();
        $.post('save', ko.mapping.toJS(item), function (result) {
            utils.messager.closeProgress();
            if (result.SID) {
                item.SID(result.SID);
                $.messager.alert("提醒", "保存成功");
            }
            else $.messager.alert("提醒", "保存失败");
        }, 'json');
    };

    self.remove = function (item) {
        if (item.SID() == null) self.items.remove(item);
        else {
            utils.messager.showProgress();
            $.post('del', {sid: item.SID()}, function (result) {
                utils.messager.closeProgress();
                if (result) {
                    self.items.remove(item);
                    $.messager.alert("提醒", "删除成功");
                }
                else $.messager.alert("提醒", "删除失败");
            });
        }
    }
}
