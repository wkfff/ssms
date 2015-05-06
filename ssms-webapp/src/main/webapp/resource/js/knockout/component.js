/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：component.js
 * 创建时间：2015-05-05
 * 创建用户：张铮彬
 */

ko.bindingHandlers.datagrid = {
    DefaultConfig: {
        idField: 'SID',
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        striped: true,
        border: false,
        fit: true
    },
    init: function (element, valueAccessor) {
        var config = valueAccessor();
        config.$element = function () {
            return $(element);
        };
        config.datagrid = function () {
            return $.fn.datagrid.apply($(element), arguments);
        };
        config = $.extend({}, ko.bindingHandlers.datagrid.DefaultConfig, config);
        $(element).addClass('easyui-datagrid').datagrid(config);
    },
    update: function (element, valueAccessor) {
    }
};