<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="/resource/js/plupload/plupload.full.min.js"></script>
</head>
<body>
<div id="filelist">Your browser doesn't have Flash, Silverlight or HTML5 support.</div>
<br/>

<div id="container">
    <a id="pickfiles" href="javascript:;">[选择文件]</a>
    <a id="uploadfiles" href="javascript:;">[上传文件]</a>
</div>


<br/>
<pre id="console"></pre>


<script type="text/javascript">
    // Custom example logic

    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'pickfiles', // you can pass in id...
        container: document.getElementById('container'), // ... or DOM Element itself
        url: 'upload.json',
        flash_swf_url: '/resource/js/plupload/Moxie.swf',
        silverlight_xap_url: '/resource/js/plupload/Moxie.xap',
        multipart_params: {
            module: "abc",
            recordSid: 1
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
                document.getElementById('filelist').innerHTML = '';

                document.getElementById('uploadfiles').onclick = function () {
                    uploader.start();
                    return false;
                };
            },

            FilesAdded: function (up, files) {
                plupload.each(files, function (file) {
                    document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
                });
            },

            UploadProgress: function (up, file) {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            },

            Error: function (up, err) {
                document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
            }
        }
    });

    uploader.init();
</script>
</body>
</html>