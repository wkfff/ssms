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
//# sourceMappingURL=/typescript/maps/core.js.map