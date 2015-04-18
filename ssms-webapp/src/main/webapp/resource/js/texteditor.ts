/// <reference path="jquery.d.ts" />
declare module KindEditor {
    function create(el:HTMLElement, options:any):any;
}

interface TextEditorSettings {
    el:HTMLElement
    table:string;
    field:string;
    sid:number;
}

class texteditor {
    private editor;
    private settings:TextEditorSettings;
    public static DATA_KEY = "$$$_texteditor";

    constructor(settings:TextEditorSettings) {
        this.settings = settings;
        this.editor = texteditor.getEditor(settings.el);
        if (this.editor != null) return this.editor;

        this.editor = KindEditor.create(settings.el, {
            uploadUrl: '',
            allowFileManager: false,
            allowUpload: false,
            items: [
                'fontname', 'fontsize',
                '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'removeformat',
                '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist',
                '|', 'table', '|', 'fullscreen'
            ]
        });
        $.data(settings.el, texteditor.DATA_KEY, this);
    }

    public init() {
        $.post("/sys/attachtext/get.json", {
            table: this.settings.table,
            field: this.settings.field,
            sid: this.settings.sid
        }, (result) => {
            this.editor.html(result);
        });
    }

    public getValue() {
        return this.editor.html();
    }

    public save() {
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
    }

    public static getEditor(el:Element) {
        return $.data(el, texteditor.DATA_KEY)
    }
}