/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：htmleditor.js
 * 创建时间：2015-07-03
 * 创建用户：张铮彬
 */

(function () {
    function getEditor(element, valueAccessor, allBindingsAccessor) {
        var editor = $.data(element, 'htmledit');
        if (editor == null) {
            editor = KindEditor.create($(element), {
                uploadUrl: '',
                allowFileManager: false,
                allowUpload: false,
                autoHeightMode: true,
                items: [
                    'fontname',
                    'fontsize',
                    '|',
                    'forecolor',
                    'hilitecolor',
                    'bold',
                    'italic',
                    'underline',
                    'removeformat',
                    '|',
                    'justifyleft',
                    'justifycenter',
                    'justifyright',
                    'justifyfull',
                    'insertorderedlist',
                    'insertunorderedlist',
                    '|',
                    'table',
                    '|',
                    'fullscreen'
                ],
                afterChange: function () {
                    if (ko.isObservable(valueAccessor())) {
                        valueAccessor()(this.html());
                    }
                }
            });
            $.data(element, 'htmledit', editor);
        }
        return editor;
    }

    ko.bindingHandlers.htmleditOptions = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
        },
        update: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
        }
    };
    ko.bindingHandlers.htmleditValue = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var editor = getEditor(element, valueAccessor, allBindingsAccessor);
            var value = ko.unwrap(valueAccessor());
            editor.html(value);
        },
        update: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var editor = getEditor(element, valueAccessor, allBindingsAccessor);
            var value = ko.unwrap(valueAccessor());
            if (value != editor.html()) {
                editor.html(value);
            }
        }
    };
})();