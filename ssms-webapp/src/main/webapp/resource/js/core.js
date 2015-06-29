if (!String.prototype.format) {
    String.prototype.format = function () {
        var formatted = this;
        for (var i = 0; i < arguments.length; i++) {
            formatted = formatted.replace(RegExp("\\{" + i + "\\}", 'g'), arguments[i].toString());
        }
        return formatted;
    };
}

if (!String.prototype.replaceAll) {
    String.prototype.replaceAll = function (searchValue, replaceValue) {
        return this.replace(new RegExp(searchValue, "gm"), replaceValue);
    };
}

if (!String.prototype.startsWith) {
    String.prototype.startsWith = function (searchString, position) {
        position = position || 0;
        return this.lastIndexOf(searchString, position) === position;
    };
}

var utils;
(function (utils) {
    utils.uuid = function () {
        return "_" + new Date().valueOf();
    };

    var messager;
    (function (messager) {
        var indexArray = [];

        messager.showProgress = function () {
            var index = layer.msg('加载中', {icon: 16, time: 0});
            indexArray.push(index);
        };
        messager.closeProgress = function () {
            var index = indexArray.pop();
            layer.close(index);
        };

        messager.alert = function (msg, callback) {
            layer.alert(msg, callback);
        };

        messager.confirm = function (msg, callback) {
            layer.confirm(msg, callback);
        };
        
        messager.showDialog=function (type,width,height,title,id,callback){
        	layer.open({
        		type: type, //page层
        		area: [width, height],
        		title:title,
        		maxmin:true,
        		btn:['确认','取消'],
        		yes:callback,
        		content: $('#'+id)
            });
        };
    })(messager = utils.messager || (utils.messager = {}));

    var dialog;
    (function (dialog) {
        var defaultOptions = {
            title: "信息编辑",
            template: "",
            loaded: function (element) {
            },
            ok: function () {
                return true;
            }
        };
        dialog.open = function (options) {
            var opts = $.extend({}, defaultOptions, options);
            layer.confirm(opts.template, {
                title: opts.title,
                success: function (layero, index) {
                    opts.loaded(layero);
                }
            }, function (index) {
                if (opts.ok() != false) layer.close(index);
            });
        }
    })(dialog = utils.dialog || (utils.dialog = {}));
})(utils || (utils = {}));

if (ko && ko.utils.copyToModel == null) {
    ko.utils.copyToModel = function (src, desc) {
        for (var field in src) {
            if (src.hasOwnProperty(field) == false) return;
            if (desc.hasOwnProperty(field) == false) return;

            var srcValue = src[field];
            if (ko.isComputed(desc[field])) continue;
            if (ko.isWriteableObservable(desc[field])) desc[field](ko.unwrap(srcValue));
            else desc[field] = ko.unwrap(srcValue);
        }
    }
}