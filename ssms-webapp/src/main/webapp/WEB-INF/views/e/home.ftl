<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/resource/css/base.css" />
    <link rel="stylesheet" href="/resource/css/home.css" />
    <title>安全生产标准化管理系统</title>
</head>

<body>
<div class="article toolbar">
    <div class="content" style="border: 1px solid #c1d3de;">
        <ul class="toollist ue-clear" >
            <li style="width:;">
                <a href="javascript:void;" class="img"><img src="/resource/images/icon01.png" /></a>
                <p><a href="javascript:void" >体系创建</a></p>
            </li>
            <li style="padding-top:15px;width:80px;margin-left:0px;margin-right:0px;"><p>完成:2000</p><p>合计:20000</p></li>
            <li style="padding-top:5px;margin-left:0px;"><img src="/resource/images/arrow_right.png" /></li>
            <li>
                <a href="javascript:void;" class="img"><img src="/resource/images/icon04.png" /></a>
                <p><a href="javascript:void;">在线自评</a></p>
            </li>
            <li style="padding-top:5px;"><img src="/resource/images/arrow_right.png" /></li>
            <li>
                <a href="javascript:void;" class="img"><img src="/resource/images/icon02.png" /></a>
                <p><a href="javascript:void;">申报评审</a></p>
            </li>
            <li style="padding-top:5px;"><img src="/resource/images/arrow_right.png" /></li>
            <li>
                <a href="javascript:void;" class="img"><img src="/resource/images/icon09.png" /></a>
                <p><a href="javascript:void;">评审管理</a></p>
            </li>
        </ul>
    </div>
</div>
<div class="article hleft notice">
    <div class="wrap-l">
        <div class="title ue-clear">
            <h2>通知公告</h2>
            <a href="/e/notice/index" class="more">更多</a>
        </div>
        <div class="content" >
            <ul class="notice-list">
                  <#if rs_notice?exists && rs_notice?size!=0>
                    <#list rs_notice as rs>
                     <li class="ue-clear">
                        <a href="javascript:nav('/e/notice/rec?sid=${rs.SID}');" class="notice-title">${rs.C_TITLE}</a>
                        <div class="notice-time">${rs.T_PUBLISH}</div>
                    </li>
                    </#list>
                    <#else>
                        <li class="ue-clear">
                        <span style="padding-left:5px;">暂时还没有通知公告。</span>
                        </li>
                  </#if>
            </ul>
        </div>
    </div>
    <div class="wrap-l">
        <div class="title ue-clear">
            <h2>待办</h2>
            <a href="/e/grade_m/index" class="more">更多</a>
        </div>
        <div class="content" style="height:300px;">
                <ul class="matter-list">
                    <#if rs_todo?exists && rs_todo?size!=0>
                    <#list rs_todo as rs>
                     <li class="ue-clear">
                        <span class="matter-time">${rs.T_BEGIN}</span>
                        <a href="javascript:nav('${rs.C_URL!}');" class="matter-title">${rs.C_TITLE}</a>
                    </li>
                    </#list>
                    <#else>
                        <li class="ue-clear">
                        <span style="padding-left:5px;">暂时还没有待办。</span>
                        </li>
                    </#if>
                </ul>
        </div>
    </div>
</div>
<div class="article hright matter">
    <div class="wrap-r">
    <div class="article toolbar">
    <div class="title ue-clear">
        <h2>统计分析</h2>
        <a href="/e/statistics/index" class="more">更多</a>
    </div>
    <div class="content" style="height:200px;">
        <iframe src="/charts.html" frameborder="0" width="100%" height="100%"></iframe>
    </div>
    </div>
</div>

</div>

</body>

<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
    var aIndex = 0;
    $(".title-list ul").on("click","li",function(){
        aIndex = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $(".matter-content").removeClass("current").eq(aIndex).addClass("current");
    });

    $(".duty").find("tbody").find("tr:even").css("backgroundColor","#eff6fa");

    $("#more").on("click",function(){
        window.location.href = "/e/"+(aIndex==0?"todo":"done")+"/index";
    });

    function nav(url){
        window.location.href = url;
    }

    function doCreate(){
        window.location.href = '/e/grade_m/rec_new';
    }

    function doChangePwd(){

    }

    function doConfig(){

    }
</script>
</html>
