/// <reference path="jquery.d.ts" />
/**
 * 上传组件
 */
var Uploader = (function () {
    function Uploader(settings) {
        this.settings = settings;
        var $uploader = this.uploader = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: settings.el.selectButtion,
            container: settings.el.container,
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
                    { title: "Image files", extensions: "jpg,gif,png" },
                    { title: "Zip files", extensions: "zip" }
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
                    if (el.innerHTML != null && el.innerHTML.length > 0)
                        el.innerHTML += '\n';
                    el.innerHTML += "Error #" + err.code + ": " + err.message;
                }
            }
        });
    }
    Uploader.prototype.init = function () {
        var _this = this;
        this.uploader.init();
        this.settings.el.list.innerHTML = '';
        $.post("/sys/attachfile/list.json", {
            module: this.settings.module,
            recordSid: this.settings.sid
        }, function (result) {
            _this.settings.el.list.innerHTML = '';
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                _this.settings.el.list.innerHTML += '<div>' + item.outerFilename + ' (' + plupload.formatSize(item.length) + ') <b></b></div>';
            }
        });
    };
    return Uploader;
})();
//# sourceMappingURL=upload.js.map