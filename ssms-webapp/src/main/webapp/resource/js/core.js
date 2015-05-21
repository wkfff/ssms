String.prototype.format = function () {
    var formatted = this;
    for (var i = 0; i < arguments.length; i++) {
        formatted = formatted.replace(RegExp("\\{" + i + "\\}", 'g'), arguments[i].toString());
    }
    return formatted;
};
String.prototype.replaceAll = function (searchValue, replaceValue) {
    return this.replace(new RegExp(searchValue, "gm"), replaceValue);
};
if (!String.prototype.startsWith) {
    String.prototype.startsWith = function (searchString, position) {
        position = position || 0;
        return this.lastIndexOf(searchString, position) === position;
    };
}
var utils;
(function (utils) {
    function uuid() {
        return "_" + new Date().valueOf();
    }
    utils.uuid = uuid;
    var messager;
    (function (messager) {
        function showProgress() {
            $.messager.progress({
                title: '请稍等',
                msg: '执行操作中...'
            });
        }
        messager.showProgress = showProgress;
        function closeProgress() {
            $.messager.progress('close');
        }
        messager.closeProgress = closeProgress;
    })(messager = utils.messager || (utils.messager = {}));
})(utils || (utils = {}));
//# sourceMappingURL=/typescript/maps/core.js.map