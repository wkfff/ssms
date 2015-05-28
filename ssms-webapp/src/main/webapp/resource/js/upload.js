/**
 * 上传组件
 */
var Uploader = (function () {
    function Uploader(settings) {
        var self = this;
        this.settings = settings;
        var $uploader = this.uploader = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: settings.el.selectButtion,
            container: settings.el.container,
            url: '/sys/attachfile/upload',
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
                        $('<div>')
                            .attr('id', file.id)
                            .text("{0}({1})".format(file.name, plupload.formatSize(file.size)))
                            .append('<b>')
                            .append($('<a href="javascript:;"></a>').text('[删除]').click(function () {
                                up.removeFile(file);
                            }))
                            .appendTo(settings.el.list);
                    });
                },
                FilesRemoved: function (up, files) {
                    plupload.each(files, function (file) {
                        $('#' + file.id).remove();
                    });
                },
                UploadProgress: function (up, file) {
                    document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>{0}%</span>'.format(file.percent);
                },
                UploadComplete: function (up, file) {
                    $.messager.alert("提醒", "文件上传成功", "info", function () {
                        self.list();
                    })
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
        this.uploader.init();
        this.list();
    };
    Uploader.prototype.list = function () {
        this.settings.el.list.innerHTML = '正在从加载文件列表，请稍等...';
        var para = {
            module: this.settings.module,
            recordSid: this.settings.sid
        };
        $.post("/sys/attachfile/list", para, listCallback(this, this.settings)).fail(failCallback(this.settings));
    };
    function listCallback(uploader, settings) {
        return function (result) {
            settings.el.list.innerHTML = '';
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                var $div = $('<div>');
                $div.html("<a href='/sys/attachfile/down?id={0}'>{1}</a>{2}".format(item.id, item.outerFilename, plupload.formatSize(item.length)))
                    .append('<b>')
                    .append($('<a href="javascript:;"></a>').text('[删除]').click(delClick(uploader, item)))
                    .appendTo(settings.el.list);
            }
        }
    }

    function failCallback(settings) {
        return function (xhr) {
            settings.el.list.innerHTML = '加载文件列表时发生了异常，请报告管理员。';
        };
    }

    function delClick(uploader, item) {
        return function () {
            $.post('/sys/attachfile/del', {module: item.module, recordSid: item.recordSid}, function () {
                uploader.list();
            })
        }
    }

    return Uploader;
})();