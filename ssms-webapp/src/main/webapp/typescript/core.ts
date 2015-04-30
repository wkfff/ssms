interface String {
    format(): string;
    replaceAll(searchValue:RegExp|string, replaceValue):string;
}

String.prototype.format = function ():string {
    var formatted = this;
    for (var i = 0; i < arguments.length; i++) {
        formatted = formatted.replace(
            RegExp("\\{" + i + "\\}", 'g'), arguments[i].toString());
    }
    return formatted;
};

String.prototype.replaceAll = function (searchValue:string, replaceValue) {
    return this.replace(new RegExp(searchValue, "gm"), replaceValue);
};