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
                        $('#'+file.id).remove();
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
        this.settings.el.list.innerHTML = '正在从加载文件列表，请稍等...';
        function list(){
            $.post("/sys/attachfile/list", {
                module: _this.settings.module,
                recordSid: _this.settings.sid
            }, function (result) {
                _this.settings.el.list.innerHTML = '';
                for (var i = 0; i < result.length; i++) {
                    var item = result[i];
                    var $div = $('<div>');
                    $div.text("{0}({1})".format(item.outerFilename, plupload.formatSize(item.length)))
                        .append('<b>')
                        .append($('<a href="javascript:;"></a>').text('[删除]').click(function () {
                            $.post('/sys/attachfile/del', {module: item.module, recordSid:item.recordSid}, function () {
                                list();
                            })
                        }))
                        .appendTo(_this.settings.el.list);
                    //_this.settings.el.list.innerHTML += '<div>' + item.outerFilename + ' (' + plupload.formatSize(item.length) + ') <b></b></div>';
                }
            }).fail(function (xhr) {
                _this.settings.el.list.innerHTML = '加载文件列表时发生了异常，请报告管理员。';
            });
        }
        list();
    };
    return Uploader;
})();