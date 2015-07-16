/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：dateeditor.js
 * 创建时间：2015-07-16
 * 创建用户：张铮彬
 */

(function () {
    ko.bindingHandlers.dateValue = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var value = ko.unwrap(valueAccessor());
            if (value != null) element.value = value;
            ko.utils.toggleDomNodeCssClass(element, "Wdate", true);
            ko.utils.registerEventHandler(element, 'focus', function () {
                WdatePicker({
                    dateFmt: 'yyyy-MM-dd',
                    autoUpdateOnChanged: true,
                    onpicked: function (dp) {
                        if (ko.isObservable(valueAccessor()))
                            valueAccessor()(dp.cal.getDateStr());
                    },
                    onclearing: function (dp) {
                        if (ko.isObservable(valueAccessor()))
                            valueAccessor()(null);
                    }
                });
            });
        },
        update: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var value = ko.unwrap(valueAccessor());
            if (typeof value !== 'undefined') element.value = value;
        }
    };

    ko.bindingHandlers.datetimeValue = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var value = ko.unwrap(valueAccessor());
            if (value != null) element.value = value;
            ko.utils.toggleDomNodeCssClass(element, "Wdate", true);
            ko.utils.registerEventHandler(element, 'focus', function () {
                WdatePicker({
                    dateFmt: 'yyyy-MM-dd HH:mm:ss',
                    autoUpdateOnChanged: true,
                    onpicked: function (dp) {
                        if (ko.isObservable(valueAccessor()))
                            valueAccessor()(dp.cal.getDateStr());
                    },
                    onclearing: function (dp) {
                        if (ko.isObservable(valueAccessor()))
                            valueAccessor()(null);
                    }
                });
            });
        },
        update: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var value = ko.unwrap(valueAccessor());
            if (typeof value !== 'undefined') element.value = value;
        }
    };

    if (ko.validation) {
        ko.validation.makeBindingHandlerValidatable('dateValue');
        ko.validation.makeBindingHandlerValidatable('datetimeValue');
    }
})();