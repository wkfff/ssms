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
        }
    })(messager = utils.messager || (utils.messager = {}));

})(utils || (utils = {}));