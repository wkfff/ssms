/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：upload.js
 * 创建时间：2015-06-02
 * 创建用户：张铮彬
 */

(function () {
    var DATA_KEY = "PLUPLOAD_INST";
    var DEFAULT_OPTIONS = {
        runtimes: 'html5,flash,silverlight,html4',
        listUrl: '/sys/attachfile/list',
        url: '/sys/attachfile/upload',
        flash_swf_url: '/resource/js/plupload/Moxie.swf',
        silverlight_xap_url: '/resource/js/plupload/Moxie.xap',
        filters: {
            max_file_size: '3mb',
            mime_types: [
                {title: "Image files", extensions: "jpg,gif,png"},
                {title: "Zip files", extensions: "zip"}
            ]
        }
    };

    var Uploader = (function () {
        function Uploader(element, options) {
            var self = this;
            this.element = element;
            var $element = $(element).addClass("btn_choose");
            // 为父元素添加样式
            $element.parent().addClass("uploader");
            // 添加容器元素
            var $container = $('<div class="container">');
            $element.before($container);
            // 添加上传按钮
            if ($element.is(':visible')) {
                var $uploadBtn = $('<a class="btn_upload" href="javascript:void(0);">[上传文件]</a>');
                $element.after($uploadBtn);
            }
            // 添加控制台输出元素
            var $console = $('<div class="console">');
            $uploadBtn.after($console);

            // 存放文件元素的map
            var fileMap = {};

            this.options = $.extend({}, DEFAULT_OPTIONS, {
                browse_button: element,
                container: $element.parent()[0],
                multipart_params: {
                    module: options.module,
                    recordSid: options.sid
                }
            }, options);

            // 实例化uploader
            this.uploader = new plupload.Uploader(this.options);
            // 绑定各种事件
            this.uploader.bind("PostInit", function (up) {
                $uploadBtn.click(function () {
                    up.start();
                });
            }, this);
            this.uploader.bind("FilesAdded", function (up, files) {
                plupload.each(files, function (file) {
                    fileMap[file.id] = $('<div class="file">')
                        .append("<span class='filename'>" + file.name + "</span>")
                        .append("<span class='filesize'>(" + plupload.formatSize(file.size) + ")</span>")
                        .append($('<a class="delete" href="javascript:void(0);">[删除]</a>').click(function () {
                            up.removeFile(file);
                        })).appendTo($container);
                });
            });
            this.uploader.bind("FilesRemoved", function (up, files) {
                plupload.each(files, function (file) {
                    fileMap[file.id].remove();
                });
            });
            this.uploader.bind("UploadProgress", function (up, file) {
                fileMap[file.id].find('b').html($("<span>").append(file.percent).append('%'));
            });
            this.uploader.bind("UploadComplete", function (up, files) {
                if (files.length > 0) $.messager.alert("提醒", "文件上传成功", "info", function () {
                    self.list();
                })
            });
            this.uploader.bind("Error", function (up, err) {
                $('<p>')
                    .append($("<span class='errorCode'>").text("ERROR" + err.code + ":"))
                    .append($("<span class='errorMsg'>").text(err.message))
                    .appendTo($console);
            });
            // 初始化uploader
            this.uploader.init();
            this.list();
        }

        Uploader.prototype.list = function () {
            var $container = $(this.element).parent().find(".container");
            $container.html("<span class='loading'>正在从加载文件列表，请稍等...</span>");
            var para = {
                module: this.options.module,
                recordSid: this.options.sid
            };
            $.post(this.options.listUrl, para, listCallback(this, this.options)).fail(failCallback(this));
        };

        function listCallback(uploader, options) {
            var $container = $(uploader.element).parent().find(".container").html("");
            return function (result) {
                for (var i = 0; i < result.length; i++) {
                    var item = result[i];
                    $('<div class="file">')
                        .append("<span class='filename'>" + item.outerFilename + "</span>")
                        .append("<span class='filesize'>(" + plupload.formatSize(item.length) + ")</span>")
                        .append($("<a class='download'>[下载]</a>").attr("href", "/sys/attachfile/down?id=" + item.id))
                        .append($('<a class="delete" href="javascript:void(0);">[删除]</a>').click(delClick(uploader, item)))
                        .appendTo($container);
                }
            }
        }

        function failCallback(uploader) {
            return function (xhr) {
                var $container = $(uploader.element).parent().find(".container");
                $container.html("<span class='loadingError'>加载文件列表时发生了异常，请报告管理员。</span>");
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

    ko.bindingHandlers.uploadOptions = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var uploader = $(element).data(DATA_KEY);
            if (uploader == null) {
                var options = ko.unwrap(valueAccessor());
                for (var item in options) {
                    if (typeof options[item] === 'function') options[item] = options[item]();
                }
                uploader = new Uploader(element, options);

                $(element).data(DATA_KEY, uploader);
                options.uploader = uploader;
            }
        },
        update: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
        }
    };
})();