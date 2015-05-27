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
    $(function () {
        $('#tt').tabs({
            border:false,
            onSelect:function(title,index){
                if (index==1){
                    $('#tab'+index).html('<iframe frameborder="0" width="100%" height="100%" src="/e/grade_m/sum_ded?sid=${sid!}" ></iframe>');
                }
                if (index==2){
                    $('#tab'+index).html('<iframe frameborder="0" width="100%" height="100%" src="/e/grade_m/history_rep?sid=${sid!}" ></iframe>');
                }
            }
        });
        
        $('#tab0').html('<iframe frameborder="0" width="100%" height="100%" src="/e/grade_m/sum?sid=${sid!}" ></iframe>');
    })
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'north'" border="false" class="toolbar" style="overflow:hidden;">
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack();">返回</a>
    </div>
    <div data-options="region:'center'" style="overflow:hidden;padding:0px;">
    <!--暂时先用框架页-->
    <div id="tt" class="easyui-tabs" style="width:100%;height:auto" data-options="fit:true,border:false">
        <div id="tab0" title="评分汇总" style="overflow:hidden;">
           
        </div>
        <div id="tab1" title="扣分汇总" style="overflow:hidden;" >
           
        </div>
        <div id="tab2" title="自评报告" style="overflow:hidden;">
           
        </div>
    </div>
    </div>
</div>
</body>
</html>

