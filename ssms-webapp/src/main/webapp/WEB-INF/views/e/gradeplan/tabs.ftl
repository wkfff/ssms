<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" type="text/css" href="/resource/css/layout.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
   
    <script type="text/javascript">
    function doBack(){
       window.location.href='/e/gradeplan/index';
    }
    
    function doSelect(title,index){
        var e = $('#tab'+index);
        e.html('<iframe frameborder="0" width="100%" height="100%" src="'+e.attr("url")+'" ></iframe>');
    }
    
    function doComplete(){
        utils.messager.confirm("是否确认完成自评？",function(){
            $.getJSON("/e/gradeplan/complete", {sid:${sid!}}, function (data) {
                utils.messager.alert(data.msg,function(){
                    window.location.href = '/e/gradeplan/';
                });
                
            }); 
        });
    }
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'north'" style="overflow:hidden;padding:0;">
        <div id="tb" class="titlebar_noborder">
                <img src="/resource/images/blue/star.png"/>&nbsp;自评
                <span class="backing">
                    <a href="javascript:doComplete();">完成自评</a>&nbsp;&nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack();">返回</a>
                </span>
        </div>
    </div>
    
    <div data-options="region:'center'" style="overflow:hidden;padding:0px;">
        <div id="tt" class="easyui-tabs" style="width:100%;height:auto" data-options="fit:true,border:false,onSelect: doSelect">
            <div id="tab0" title="自评方案" style="overflow:hidden;" url="/e/gradeplan/rec?sid=${sid!}"></div>
            <div id="tab1" title="自评内容" style="overflow:hidden;" url="/e/gradecontent/index?R_SID=${sid!}"></div>
            <div id="tab2" title="自评报告" style="overflow:hidden;" url="/e/gradereport/rec?R_SID=${sid!}"></div>
        </div>
    </div>
</div>
</body>
</html>

