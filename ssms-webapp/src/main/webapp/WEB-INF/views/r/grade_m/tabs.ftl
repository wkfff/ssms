<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
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
        $.parser.onComplete = function(){
            $("#tt").tabs("select",0);
        }
    </script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div id="tb">
        <a href="#" onclick="doBack();" style="width:90px;font-size:14px;text-decoration: none;">返回企业列表</a>
    </div>
    <div data-options="region:'center',tools:'#tb'" style="overflow:hidden;padding:0;" title="企业详细信息" iconCls="icon-star" >
        <div id="tt" class="easyui-tabs" style="width:100%;height:auto" data-options="fit:true,border:false, onSelect: doSelect">
            <div id="tab0" title="基本信息" style="overflow:hidden;" url="/sys/tenant_e/view?sid=${sid!}">
                <#--<iframe frameborder="0" width="100%" height="100%" src="/sys/tenant_e/view?sid=${sid!}" ></iframe>-->
            </div>
            <div id="tab1" title="体系查看" style="overflow:hidden;" url="/r/stdtmp/query"></div>
            <div id="tab2" title="自评报告" style="overflow:hidden;" url="/e/grade_m/history_rep?sid=${gradeid!}"></div>
            <div id="tab3" title="评审方案" style="overflow:hidden;" url="/static/reviewplan.html"></div>
            <div id="tab4" title="在线评审" style="overflow:hidden;" url="/r/grade_m/rec?sid=${graderid!}" ></div>
            <div id="tab5" title="评审报告" style="overflow:hidden;" url="/r/grade_m/report_rec?sid=${graderid!}"></div>
            <div id="tab6" title="证书管理" style="overflow:hidden;" url="/static/cart.html"></div>
        </div>
    </div>
</div>
</body>
</html>

