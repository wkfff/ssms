/// <reference path="jquery.d.ts" />

declare module plupload {
    class Uploader {
        constructor(settings);

        start();
    }

    function each(array:any[], callback:(item:any)=>void);

    function formatSize(size:number);
}

interface UploaderSettings {
    el: {
        container:HTMLElement;
        list: HTMLElement
        selectButtion: HTMLElement
        uploadButtion: HTMLElement
        console: HTMLElement
    }
    module:string;
    sid:number
}

/**
 * 上传组件
 */
class Uploader {
    private uploader;
    private settings:UploaderSettings;

    constructor(settings:UploaderSettings) {
        this.settings = settings;
        var $uploader = this.uploader = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: settings.el.selectButtion, // you can pass in id...
            container: settings.el.container, // ... or DOM Element itself
            url: 'upload.json',
            flash_swf_url: '/resource/js/plupload/Moxie.swf',
            silverlight_xap_url: '/resource/js/plupload/Moxie.xap',
            multipart_params: {
                module: settings.module,
                recordSid: settings.sid
            },
            filters: {
                max_file_size: '10mb',
                mime_types: [
                    {title: "Image files", extensions: "jpg,gif,png"},
                    {title: "Zip files", extensions: "zip"}
                ]
            },

            init: {
                PostInit: function () {
                    settings.el.uploadButtion.onclick = function () {
                        $uploader.start();
                        return false;
                    };
                },

                FilesAdded: function (up, files) {
                    plupload.each(files, function (file) {
                        settings.el.list.innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
                    });
                },

                UploadProgress: function (up, file) {
                    document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
                },

                Error: function (up, err) {
                    var el = settings.el.console;
                    if (el.innerHTML != null && el.innerHTML.length > 0) el.innerHTML += '\n';
                    el.innerHTML += "Error #" + err.code + ": " + err.message;
                }
            }
        });
    }

    public init() {
        this.uploader.init();
        this.settings.el.list.innerHTML = '正在从加载文件列表，请稍等...';

        $.post("/sys/attachfile/list.json", {
            module: this.settings.module,
            recordSid: this.settings.sid
        }, (result)=> {
            this.settings.el.list.innerHTML = '';
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                this.settings.el.list.innerHTML += '<div>' + item.outerFilename + ' (' + plupload.formatSize(item.length) + ') <b></b></div>';
            }
        }).fail((xhr)=>{
            this.settings.el.list.innerHTML = '加载文件列表时发生了异常，请报告管理员。';
        });
    }
}