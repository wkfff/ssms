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
        striped: false,
        border: false,
        fit: true
    },
    init: function (element, valueAccessor) {
        var config = ko.unwrap(valueAccessor());
        config.$element = function () {
            return $(element);
        };
        config.datagrid = function () {
            return $.fn.datagrid.apply($(element), arguments);
        };
        if (ko.isObservable(config.queryParams)) {
            config.queryParams.subscribe(function (newValue) {
                $(element).datagrid({
                    queryParams: newValue
                })
            });
        }
        var _config = config = $.extend({}, ko.bindingHandlers.datagrid.DefaultConfig, config);
        if (ko.isObservable(config.queryParams))
            _config = $.extend({}, config, {queryParams: ko.unwrap(config.queryParams) || null});
        $(element).addClass('easyui-datagrid').datagrid(_config);
    },
    update: function (element, valueAccessor) {
    }
};

ko.bindingHandlers.tree = {
    DefaultConfig: {},
    init: function (element, valueAccessor) {
        var config = ko.unwrap(valueAccessor());
        config.$element = function () {
            return $(element);
        };
        config.tree = function () {
            return $.fn.tree.apply($(element), arguments);
        };
        if (ko.isObservable(config.queryParams)) {
            config.queryParams.subscribe(function (newValue) {
                $(element).tree({
                    queryParams: newValue
                })
            });
        }
        var _config = config = $.extend({}, ko.bindingHandlers.tree.DefaultConfig, config);
        if (ko.isObservable(config.queryParams))
            _config = $.extend({}, config, {queryParams: ko.unwrap(config.queryParams) || null});
        $(element).tree(_config);
    },
    update: function (element, valueAccessor) {
    }
};

ko.bindingHandlers.form = {
    init: function (element, valueAccessor) {
        var config = ko.unwrap(valueAccessor());
        config.$element = function () {
            return $(element);
        };
        config.form = function () {
            return $.fn.form.apply($(element), arguments);
        };
    },
    update: function (element, valueAccessor) {
    }
};
ko.bindingHandlers.formValue = {
    init: function (element, valueAccessor) {
    },
    update: function (element, valueAccessor) {
        $(element).form('load', ko.unwrap(valueAccessor()));
    }
};