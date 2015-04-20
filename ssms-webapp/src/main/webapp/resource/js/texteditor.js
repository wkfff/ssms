/// <reference path="jquery.d.ts" />
var texteditor = (function () {
    function texteditor(settings) {
        this.settings = settings;
        this.editor = texteditor.getEditor(settings.el);
        if (this.editor != null)
            return this.editor;
        this.editor = KindEditor.create(settings.el, {
            uploadUrl: '',
            allowFileManager: false,
            allowUpload: false,
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
            ]
        });
        $.data(settings.el, texteditor.DATA_KEY, this);
    }
    texteditor.prototype.init = function () {
        var _this = this;
        $.post("/sys/attachtext/get.json", {
            table: this.settings.table,
            field: this.settings.field,
            sid: this.settings.sid
        }, function (result) {
            _this.editor.html(result);
        });
    };
    texteditor.prototype.getValue = function () {
        return this.editor.html();
    };
    texteditor.prototype.save = function () {
        var postData = {
            table: this.settings.table,
            field: this.settings.field,
            sid: this.settings.sid,
            content: this.getValue()
        };
        $.post('/sys/attachtext/save.json', postData, function () {
            alert("保存成功！");
        }).fail(function (jqXHR) {
            alert(jqXHR.status + ":" + jqXHR.statusText);
            // TODO：服务端送来的具体错误内容
            alert(jqXHR.responseJSON);
        });
    };
    texteditor.getEditor = function (el) {
        return $.data(el, texteditor.DATA_KEY);
    };
    texteditor.DATA_KEY = "$$$_texteditor";
    return texteditor;
})();
//# sourceMappingURL=texteditor.js.map