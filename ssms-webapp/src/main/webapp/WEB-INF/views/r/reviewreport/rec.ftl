<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css">
    <link rel="stylesheet" href="/resource/css/common.css">
    <link rel="stylesheet" href="/resource/js/layui/skin/layer.css" />
    <link rel="stylesheet" href="/resource/css/layout.css" />
    <link rel="stylesheet" href="/resource/css/icon.css" />
    
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>

    <script type="text/javascript">
        var editor;
        function doSave(){
            utils.messager.showProgress('正在保存中，请稍后....');
            editor.sync();
            $.post('save',$("#formMain").serialize(),function(result){
                 utils.messager.closeProgress();
                 utils.messager.alert('保存成功！');
                 $("#SID").attr("value",result);
                 $('#btn_exp').show();
            });
        }
    
        function doExport(){
            window.location.href='${BASE_PATH}/export/'+$("#SID").attr("value");
        }
    
        function doBack(){
            window.location.href='${referer!}';
        }
    
        window.onresize = function(){
            var h= document.documentElement.clientHeight-$('#toolbar').height()-4;
            editor.resize('100%',h);
        } 
            
        $(function () {
             KindEditor.ready(function(K) {
                    var h= document.documentElement.clientHeight-$('#toolbar').height()-4;
                    editor = K.create('#C_CONTENT', {
                        width:'100%',
                        height:h,
                        border:0,
                        themeType :'simple',
                        resizeType:0,
                        items : [
                        'fontname', 'fontsize',
                        '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline','removeformat',
                        '|', 'justifyleft', 'justifycenter', 'justifyright',
                        'justifyfull', 'insertorderedlist','insertunorderedlist',
                        '|', 'table','|','fullscreen'
                        ]
                     });
            });
        });
    </script>
</head>
<body>
<div class="container">
     <div id="toolbar" class="toolbar toolbar-bg">
            <a href="#" class="icon-save" onclick="doSave();">保存</a>
            <a id="btn_exp" href="#" class="icon-pdf" onclick="doExport()" <#if !SID??>style="display:none;"</#if>>导出</a>
            <!-- <a href="#" class="icon-back" onclick="doBack()">返回</a> -->
     </div>
     <form id="formMain" method="post" style="padding:0 2 0 0;">
            <textarea id="C_CONTENT" name="C_CONTENT" style="display:none;">${C_CONTENT!}</textarea>
            <input type="hidden" name="R_SID" value="${R_SID!}" />
            <input type="hidden" id="SID" name="SID" value="${SID!}" />
     </form>
</div>
</body>
</html>
