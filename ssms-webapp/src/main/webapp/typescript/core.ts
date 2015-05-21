interface String {
    format(): string;
    replaceAll(searchValue:RegExp|string, replaceValue):string;
    startsWith(searchValue:RegExp|string, replaceValue):boolean;
}
interface JQueryStatic{
    messager:any;
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

if (!String.prototype.startsWith) {
    String.prototype.startsWith = function(searchString, position) {
        position = position || 0;
        return this.lastIndexOf(searchString, position) === position;
    };
}


module utils {
    export function uuid() {
        return "_" + new Date().valueOf();
    }

    export module messager{
        export function showProgress(){
            $.messager.progress({
                title:'请稍等',
                msg:'执行操作中...'
            });
        }

        export function closeProgress(){
            $.messager.progress('close');
        }
    }
}