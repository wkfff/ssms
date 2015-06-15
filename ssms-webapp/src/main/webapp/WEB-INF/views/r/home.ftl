<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css" />
    <link rel="stylesheet" href="/resource/css/home.css" />
    <script type="text/javascript" src="home.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <style>

    </style>
</head>

<body>
<div class="search" align="right" style="background:url(/resource/images/tu15.jpg);padding-right:60px;">
	<div style="background-color:rgb(104,183,209);padding:3px 3px 2px 5px;width:700px;" align="center">
       <input id="cb_city" style="width:80px;" data-bind="comboboxValue:comboCity,easyuiOptions:comboCitySettings">&nbsp;市&nbsp;&nbsp;
       <input class="easyui-combobox" id="cb_county" style="width:80px;" data-bind="comboboxValue:comboCounty,easyuiOptions:comboCountySettings">&nbsp;区/县&nbsp;&nbsp;
       <input id="txt_name" style="width:380px;height:24px;line-height:24px;" data-bind="textboxValue:txtName" placeholder="请输入要查找的企业">
       <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click:gridEvents.refreshClick">搜索企业</a>
    </div>


</div>
<div class="article half notice">
    <div class="wrap-l">
        <div class="title ue-clear">
            <h2>接收公告</h2>
            <a href="/r/notice/publics" class="more">更多</a>
        </div>
        <div class="content">
            <ul class="notice-list">
                  <#if rs_notice?exists && rs_notice?size!=0>
                    <#list rs_notice as rs>
                     <li class="ue-clear">
                        <a href="javascript:nav('/r/notice/view?sid=${rs.SID}');" class="notice-title">${rs.C_TITLE}</a>
                        <div class="notice-time">${rs.T_PUBLISH}</div>
                    </li>
                    </#list>
                    <#else>
                        <li class="ue-clear">
                        <span style="padding-left:5px;">没有接收公告。</span>
                        </li>
                  </#if>
            </ul>
        </div>
    </div>
</div>

<div class="article half matter">
    <div class="wrap-r">
        <div class="title ue-clear">
            <h2>已发公告</h2>
            <a href="/r/grade_m/index" class="more">更多</a>
        </div>
        <div class="content">
                <ul class="matter-list">
                    <#if rs_notice2?exists && rs_notice2?size!=100>
                    <#list rs_notice2 as rs>
                     <li class="ue-clear">
                        <span class="matter-time">${rs.T_PUBLISH!}</span>
                        <a href="javascript:nav('${rs.C_URL!}');" class="matter-title">${rs.C_TITLE!}</a>
                    </li>
                    </#list>
                    <#else>
                        <li class="ue-clear">
                        <span style="padding-left:5px;">没有已发待办。</span>
                        </li>
                    </#if>
                </ul>
        </div>
    </div>
</div>

<div class="article half notice">
    <div class="wrap-l">
        <div class="title ue-clear">
            <h2>正在评审</h2>
            <a href="/r/notice/index" class="more">更多</a>
        </div>
        <div class="content">
            <ul class="notice-list">
                  <#if rs_todo?exists && rs_todo?size!=0>
                    <#list rs_todo as rs>
                     <li class="ue-clear">
                        <a href="javascript:nav('${rs.C_URL!}');" class="notice-title">${rs.C_TITLE}</a>
                        <div class="notice-time">${rs.T_BEGIN!}</div>
                    </li>
                    </#list>
                    <#else>
                        <li class="ue-clear">
                        <span style="padding-left:5px;">没有正在评审的企业。</span>
                        </li>
                  </#if>
            </ul>
        </div>
    </div>
</div>

<div class="article half matter">
    <div class="wrap-r">
        <div class="title ue-clear">
            <h2>评审完成</h2>
            <a href="/r/grade_m/index" class="more">更多</a>
        </div>
        <div class="content">
                <ul class="matter-list">
                    <#if rs_done?exists && rs_done?size!=0>
                    <#list rs_done as rs>
                     <li class="ue-clear">
                        <span class="matter-time">${rs.T_END}</span>
                        <a href="javascript:nav('${rs.C_URL!}');" class="matter-title">${rs.C_TITLE}</a>
                    </li>
                    </#list>
                    <#else>
                        <li class="ue-clear">
                        <span style="padding-left:5px;">没有评审完成的企业。</span>
                        </li>
                    </#if>
                </ul>
        </div>
    </div>
</div>

<!-- <div id="win_select" class="easyui-window" title="企业评审" data-options="onLoad:winSelectOnLoad,iconCls:'icon-add',modal:true,minimizable:false,closed:true,href:'/r/grade_m/select'" style="width:900px;height:500px;padding:6px;" >

</div> -->

</body>



<script type="text/javascript">
    var aIndex = 0;
    $(".title-list ul").on("click","li",function(){
        aIndex = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $(".matter-content").removeClass("current").eq(aIndex).addClass("current");
    });

    $(".duty").find("tbody").find("tr:even").css("backgroundColor","#eff6fa");

    $("#more").on("click",function(){
        window.location.href = "/r/"+(aIndex==0?"todo":"done")+"/index";
    });

    function nav(url){
        window.location.href = url;
    }

    function doCreate(){
        window.location.href = '/r/grade_m/select2';
    }

    function doChangePwd(){

    }

    function doConfig(){

    }
</script>

<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>

</html>
