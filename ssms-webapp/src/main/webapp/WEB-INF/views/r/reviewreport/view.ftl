<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css">
    <link rel="stylesheet" href="/resource/css/layout.css" />
    <link rel="stylesheet" href="/resource/css/icon.css" />

    <script type="text/javascript">
        function doExport(){
            window.location.href='${BASE_PATH}/export/${SID}';
        }
    
        function doBack(){
            window.location.href='${referer!}';
        }
    </script>
</head>
<body>
<div class="container">
     <div class="toolbar toolbar-bg">
             <a href="#" class="icon-pdf" onclick="doExport()">导出</a>
            <#if showback??>
                    <a href="#"  class="icon-back" onclick="doBack()">返回</a>
            </#if>
     </div>
     <div style="width:100%;height:100%;padding:10px;">${C_CONTENT!}</div>
</div>
</body>
</html>
