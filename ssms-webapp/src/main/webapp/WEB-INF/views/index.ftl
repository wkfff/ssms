<#import 'layout/_layout.ftl' as layout/>
<#assign _header>
<link rel="stylesheet" href="/resource/css/base.css"/>
<link rel="stylesheet" href="/resource/css/common.css"/>
<link rel="stylesheet" href="/resource/css/index.css"/>
</#assign>
<#assign _footer>
<#--easyui-->
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<#--首页脚本-->
<script type="text/javascript">
    $(".nav").on("click", "li", function () {
        $(this).siblings().removeClass("current").removeClass("hasChild");
        var hasChild = !!$(this).find(".subnav").size();
        if (hasChild) {
            $(this).toggleClass("hasChild");
        }
        $(this).addClass("current");
    });


    $(window).resize(function (e) {
        $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height() - 6);
        $(".wrap").height($("#bd").height() - 6);
        $(".nav").css("minHeight", $(".sidebar").height() - $(".sidebar-header").height() - 1);
        $("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height() - 12);
    }).resize();

    $(".nav>li").css({"borderColor": "#dbe9f1"});
    $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
    $(".nav").on("click", "li", function (e) {
        var aurl = $(this).find("a").attr("date-src");
        if (aurl == "") return;
        $("#iframe").attr("src", aurl);
        $(".nav>li").css({"borderColor": "#dbe9f1"});
        $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
        return false;
    });

    $('.exit').click(function () {
        $.messager.confirm('提示', '是否要退出系统？', function (r) {
            if (r) {
                window.location.href = 'logout';
            }
        });
    });
        <#if LANSTAR_IDENTITY.tenantType='E'>
        function choose() {
            var value = $('#cc').combobox('getValue');
            $.post("/e/setTemplate", {profession: value}, function () {
                window.location.href = "/";
            });
        }
        function opChoose(){
            $('#dd').dialog('open');
        }
            <#if needChooseProfessions!>$(function () {
                $('#dd').dialog('open');
            })</#if>
        </#if>
</script>
</#assign>
<@layout.base header=_header footer=_footer>
<div id="container">
    <div id="hd">
        <div class="hd-wrap ue-clear">
            <div class="top-light"></div>
            <h1 class="logo"></h1>

            <div class="login-info ue-clear">
                <div class="welcome ue-clear">
                    <span>欢迎您,</span><a href="javascript:;" class="user-name">${LANSTAR_IDENTITY.name}(${LANSTAR_IDENTITY.tenantName})</a>
                    <#if LANSTAR_IDENTITY.tenantType='E' && profession??>
                        <span style="float: left">|</span>
                        <span style="float: left">当前专业:${profession.name}</span>
                        <a href="javascript:;" class="user-name" onclick="opChoose()">(切换专业)</a>
                    </#if>
                </div>
            <#--<div class="login-msg ue-clear">
                <a href="javascript:;" class="msg-txt">消息</a>
                <a href="javascript:;" class="msg-num">10</a>
            </div>-->
            </div>
            <div class="tool_bar ue-clear">
                <a href="index" class="home-btn">首页</a>
                <a href="javascript:;" class="quit-btn exit"></a>
            </div>
        </div>
    </div>
    <div id="bd">
        <div class="wrap ue-clear">
            <div class="sidebar">
                <h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav">
                    <li class="office current">
                        <div class="nav-header">
                            <a href="javascript:;" date-src="home.html" class="ue-clear"><span>日常办公</span><i class="icon"></i></a>
                        </div>
                    </li>
                    <#list nav as map>
                        <li class="<#if map.attributes.C_ICON?length=0>office<#else>${map.attributes.C_ICON}</#if>">
                            <div class="nav-header">
                                <a href="javascript:;" date-src="${map.attributes.C_URL!""}" class="ue-clear"><span>${map.text}</span><i class="icon"></i></a>
                            </div>
                            <#if (map.children?size>0)>
                                <ul class="subnav">
                                    <#list map.children as item>
                                        <li>
                                            <a href="javascript:;" date-src="${item.attributes.C_URL}">${item.text}</a>
                                        </li>
                                    </#list>
                                </ul>
                            </#if>
                        </li>
                    </#list>
                </ul>
            </div>
            <div class="content">
                <iframe src="${HOME_PAGE!"home.html"}" id="iframe" width="100%" height="100%" frameborder="0"></iframe>
            </div>
        </div>
    </div>
    <div id="ft" class="ue-clear">
        <div class="ft-left">
            <span>福建永创意信息科技有限公司,福州蓝石电子有限公司</span>
            <em>版权所有</em>
        </div>
        <div class="ft-right">
            <span>福州蓝石电子有限公司</span>
            <em>技术支持</em>
        </div>
    </div>
</div>
    <#if LANSTAR_IDENTITY.tenantType='E'>
    <div id="dd" class="easyui-dialog" title="选择专业" style="width:400px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true, buttons:'#bb', closable:false, closed:true">
        <div style="position: absolute; top: 80px; left: 80px;">
            选择专业
            <input id="cc" class="easyui-combobox" name="dept"
                   data-options="valueField:'SID',textField:'C_NAME',url:'/e/getProfessions'">
        </div>
    </div>
    <div id="bb">
        <a href="#" class="easyui-linkbutton" onclick="choose()">确定</a>
    </div>
    </#if>
</@>