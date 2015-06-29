<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/layout.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
   
    <script type="text/javascript">
    function doBack(){
       window.location.href='${referer!}';
    }
    function doSelect(title,index){
            var e = $('#tab'+index);
            e.html('<iframe frameborder="0" width="100%" height="100%" src="'+e.attr("url")+'" ></iframe>');
    }
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'north'" border="false" class="toolbar" style="overflow:hidden;">
        <div id="tb" class="titlebar_noborder">
                <img src="/resource/images/blue/star.png"/>&nbsp;${title!}评审情况
                <span class="backing">
                    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack();">返回</a>
                </span>
        </div>
    </div>
    <div data-options="region:'center'" style="overflow:hidden;padding:0px;">

    <div id="tt" class="easyui-tabs" style="width:100%;height:auto" data-options="fit:true,border:false,onSelect: doSelect">
        <div id="tab0" title="评分汇总" style="overflow:hidden;" url="/r/reviewhistory/sum?sid=${sid!}"></div>
        <div id="tab1" title="扣分汇总" style="overflow:hidden;" url="/r/reviewhistory/sum_ded?sid=${sid!}"></div>
        <div id="tab2" title="评审报告" style="overflow:hidden;" url="/r/reviewreport/view?sid=${sid!}"></div>
        <div id="tab3" title="评审证书" style="overflow:hidden;" url="/r/reviewcert/view?R_SID=${sid!}"></div>
    </div>
    </div>
</div>
</body>
</html>

