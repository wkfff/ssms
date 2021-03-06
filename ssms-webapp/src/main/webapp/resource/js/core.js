(function (functions) {
    for (var func in functions) {
        if (functions.hasOwnProperty(func)) {
            if (typeof String.prototype[func] === 'undefined') {
                String.prototype[func] = functions[func];
            }
        }
    }
})({
    format: function () {
        var formatted = this;
        for (var i = 0; i < arguments.length; i++) {
            formatted = formatted.replace(new RegExp("\\{" + i + "\\}", 'g'), arguments[i].toString());
        }
        return formatted;
    },
    replaceAll: function (searchValue, replaceValue) {
        return this.replace(new RegExp(searchValue, "gm"), replaceValue);
    },
    startsWith: function (searchString, position) {
        position = position || 0;
        return this.lastIndexOf(searchString, position) === position;
    }
});

var utils;
(function (utils) {
    utils.uuid = function () {
        return "_" + new Date().valueOf();
    };

    var asserts;
    (function (asserts) {
        asserts.notNull = function (value, msg) {
            if (value == null) throw msg;
        };
        asserts.notBlank = function (value, msg) {
            if (value == null || (typeof  value === "string" && value.length === 0)) {
                throw msg
            }
        };
    })(asserts = utils.asserts || (utils.asserts = {}));

    var messager;
    (function (messager) {
        var indexArray = [];

        messager.showProgress = function (title) {
            var index = top.layer.msg(title || '加载中', {icon: 16, time: 0, shade: [0.3, '#000']});
            indexArray.push(index);
        };
        messager.closeProgress = function () {
            var index = indexArray.pop();
            top.layer.close(index);
        };

        messager.alert = function (msg, callback, icon) {
            top.layer.alert(msg, {icon: icon || 0}, function (index) {
                if (typeof callback === 'function') callback(index);
                top.layer.close(index);
            });
        };

        messager.confirm = function (msg, callback) {
            top.layer.confirm(msg, {icon: 3}, callback);
        };
        
        messager.showDialog = function (type, width, height, title, id, callback) {
            top.layer.open({
                type: type, //page层
                area: [width, height],
                title: title,
                maxmin: false,
                btn: ['确认', '取消'],
                yes: callback,
                content: $('#' + id)
            });
        };

        messager.close = function (index) {
            top.layer.close(index);
        };
    })(messager = utils.messager || (utils.messager = {}));

    var dialog;
    (function (dialog) {
        var defaultOptions = {
            title: "信息编辑",
            template: undefined,
            templateId: undefined,
            loaded: function (element) {
            },
            ok: function () {
                return true;
            }
        };
        dialog.open = function (options) {
            var opts = $.extend({}, defaultOptions, options);
            var template = opts.templateId ? $(opts.templateId).html() : opts.template;
            top.layer.confirm(template, {
                title: opts.title,
                maxWidth: 980,
                success: function (layero, index) {
                    opts.loaded(layero);
                }
            }, function (index) {
                if (opts.ok() !== false) top.layer.close(index);
            });
        };
    })(dialog = utils.dialog || (utils.dialog = {}));
})(utils || (utils = {}));

if (typeof ko !== 'undefined' && ko.utils.copyToModel === undefined) {
    ko.utils.copyToModel = function (src, desc) {
        for (var field in src) {
            if (src.hasOwnProperty(field)) {
                // skip undefined field
                if (desc.hasOwnProperty(field) === false) continue;
                // copy value
                var srcValue = src[field];
                if (ko.isComputed(desc[field])) continue;
                if (ko.isWriteableObservable(desc[field])) desc[field](ko.unwrap(srcValue));
                else desc[field] = ko.unwrap(srcValue);
            }
        }
    };

    /*ko数组添加过滤方法，林峰 2015-07-02*/
    ko.observableArray.fn['filter'] = function (filterFunc) {
        var underlyingArray = this();
        var result = [];
        for (var i = 0; i < underlyingArray.length; i++) {
            var value = underlyingArray[i];
            if (filterFunc(value)) {
                result.push(value);
            }
        }
        return result;
    };
}
