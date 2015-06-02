<#import '../../layout/_layout.ftl' as layout/>
<#assign _header>
<link rel="stylesheet" href="/resource/css/base.css"/>
<link rel="stylesheet" href="/resource/css/common.css"/>
<link rel="stylesheet" href="/resource/css/index.css"/>
<style type="text/css">
    .content {
        height: 100%;
    }
</style>
</#assign>
<#assign _footer>
<#--easyui-->
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<#--首页脚本-->
<script type="text/javascript">
    $(window).resize(function (e) {
        var $bd = $("#bd");
        var $hd = $("#hd");
        var $ft = $("#ft");
        $bd.height($(window).height() - $hd.height() - $ft.height() - 6);
        $(".wrap").height($bd.height() - 6);
        $(".nav").css("minHeight", $(".sidebar").height() - $(".sidebar-header").height() - 1);
    }).resize();

    $('.exit').click(function () {
        $.messager.confirm('提示', '是否要退出系统？', function (r) {
            if (r) {
                window.location.href = 'logout';
            }
        });
    });

    $(".nav>li").css({"borderColor": "#dbe9f1"});
    $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
    $(".nav").on("click", "li", function (e) {
        var aurl = $(this).find("a").attr("data-src");
        var title = $(this).find("a").find("span").text();
        if (aurl == "") return;
        $('#p').panel('setTitle', title).panel('refresh', aurl);
        $(".nav>li").css({"borderColor": "#dbe9f1"});
        $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
        return false;
    });
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
                    <span>欢迎您,</span><a href="javascript:void(0);" class="user-name">${LANSTAR_IDENTITY.name}(${LANSTAR_IDENTITY.tenantName})</a>
                </div>
            <#--<div class="login-msg ue-clear">
                <a href="javascript:;" class="msg-txt">消息</a>
                <a href="javascript:;" class="msg-num">10</a>
            </div>-->
            </div>
            <div class="tool_bar ue-clear">
                <a href="/" class="home-btn">首页</a>
                <a href="javascript:void(0);" class="quit-btn exit"></a>
            </div>
        </div>
    </div>
    <div id="bd">
        <div class="wrap ue-clear">
            <div class="sidebar">
                <h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav">
                    <li class="nav-info current">
                        <div class="nav-header">
                            <a href="javascript:void(0);" data-src="/r/e/stdtmp/" class="ue-clear"><span>企业达标体系</span><i class="icon"></i></a>
                        </div>
                    </li>

                    <li class="agency">
                        <div class="nav-header">
                            <a href="javascript:void(0);" data-src="/r/e/stdtmp/rec" class="ue-clear"><span>企业自评情况</span><i class="icon"></i></a>
                        </div>
                    </li>

                    <li class="office">
                        <div class="nav-header">
                            <a href="javascript:void(0);" data-src="/test.html" class="ue-clear"><span>历史评审情况</span><i class="icon"></i></a>
                        </div>
                    </li>

                    <li class="gongwen">
                        <div class="nav-header">
                            <a href="javascript:void(0);" data-src="/test.html" class="ue-clear"><span>填写评审表格</span><i class="icon"></i></a>
                        </div>
                    </li>

                    <li class="konwledge">
                        <div class="nav-header">
                            <a href="javascript:void(0);" data-src="/test.html" class="ue-clear"><span>填写评审报告</span><i class="icon"></i></a>
                        </div>
                    </li>

                    <li class="email">
                        <div class="nav-header">
                            <a href="javascript:void(0);" data-src="/test.html" class="ue-clear"><span>上传评审结果</span><i class="icon"></i></a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="content">
                <div id="p" title="在线评审" class="easyui-panel" style="padding:10px;" fit="true">
                    
                </div>
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
</@>