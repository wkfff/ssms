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
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript">
        function doSelect(title,index){
            var e = $('#tab'+index);
            e.html('<iframe frameborder="0" width="100%" height="100%" src="'+e.attr("url")+'" ></iframe>');
        }

        function doComplete(){
           utils.messager.confirm("是否确认完成评审？",function(){
                $.post('/r/reviewplan/complete', {sid:'${sid!}'}, function (data) {
                        if(data.msg=='评审完成！'){
                            utils.messager.alert(data.msg,function(){
                                window.location.href = '/e/gradeplan/';
                            });
                        }else
                            utils.messager.alert(data.msg);
                }, 'json'); 
            });
        }
    </script>
</head>
<body>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'north'" style="overflow:hidden;padding:0;">
        <div id="tb" class="titlebar_border">
                <img src="/resource/images/blue/star.png"/>&nbsp;评审
                <span class="backing">
                <#if !err??>
                    <a href="javascript:doComplete();">完成评审</a>&nbsp;&nbsp;&nbsp;
                </#if>
                    <a href="/r/grade/index">返回列表</a>
                 </span>
        </div>
    </div>
    <div data-options="region:'center'">
        <#if err??>
            <div style="padding:15px;">${err!}</div>
        <#else>
        <div id="tt" class="easyui-tabs" style="width:100%;height:auto;" data-options="fit:true,border:false,onSelect: doSelect">
                <div id="tab0" title="评审方案" style="overflow:hidden;" url="/r/reviewplan/rec?sid=${sid!}"></div>
                <div id="tab1" title="在线评审" style="overflow:hidden;" url="/r/reviewcontent/index?sid=${sid!}" ></div>
                <div id="tab2" title="评审报告" style="overflow:hidden;" url="/r/reviewreport/rec?R_SID=${sid!}"></div>
                <div id="tab3" title="证书管理" style="overflow:hidden;" url="/r/reviewcert/rec?R_SID=${sid!}"></div>
                
                <div id="tab4" title="企业基本信息" style="overflow:hidden;" url="/sys/tenant_e/view?sid=${eid!}"></div>
                <div id="tab5" title="达标体系查看" style="overflow:hidden;" url="/r/stdtmp/view/${eid!}-${pro}-0"></div>
                <div id="tab6" title="企业自评报告" style="overflow:hidden;" url="/r/reviewreport/rep?sid=${gradePlanId!}"></div>
                
                <div id="tab7" title="通知公告" style="overflow:hidden;" url="/r/review_notice/index?eid=${eid!}&pro=${pro}"></div>
         </div>
         </#if>
     </div>
</div>
</body>
</html>

