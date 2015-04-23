<#import "/layout/_layout.ftl" as layout/>


<@layout.indexLayout menuID="工作中心">
</@>

<#macro aaa>
<!DOCTYPE HTML>
<HTML>
<HEAD>
    <META charset="utf-8">
    <META content="IE=11.0000" http-equiv="X-UA-Compatible">
    <META http-equiv="X-UA-Compatible" content="IE=edge">
    <TITLE id="title">${_TITLE_!"安全生产标准化管理系统"}</TITLE>
    <LINK href="favicon.ico" type="image/x-icon" rel=icon>
    <LINK id="patternCss" href="/resource/css/standard.css" rel="stylesheet" type="text/css">
    <LINK id="styleCss" href="/resource/theme/skyblue/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/index.js"></script>
    <style>
        .nav__menu {
            line-height: 45px;
            font-weight: 700;
            text-transform: uppercase;
        }

        .nav__menu-item {
            display: inline-block;
            position: relative;
        }

        .nav__menu-item:hover .nav__submenu {
            display: block;
        }

        .nav__submenu {
            font-weight: 300;
            text-transform: none;
            display: none;
            position: absolute;
            top: 20px;
            width: 220px;
            background-color: #FAFAFA;
        }

        .nav__submenu-item:hover {
            background: #FFF;
        }
    </style>
    <script>
        var tabs, fm;
        var activeIndex = 0;

        function changeTab(index) {
            var url = tabs.eq(index).attr("url");
            tabs.eq(activeIndex).attr("class", "js-component-tabitem tA0 oZ0 nui-tabs-item");
            tabs.eq(index).attr("class", "js-component-tabitem tA0 oZ0 nui-tabs-item-selected");
            activeIndex = index;
            fm.attr("src", url);
        }

        $(document).ready(function () {
            fm = $("#frame_main");
            tabs = $('#tabs li');
            changeTab(activeIndex);
        });
    </script>
</HEAD>

<body class="skyblue frame-default frame-auto">
<div class="frame-head">
    <div class="tY0">
        <h1 class="tG0 sj0" id="h1Logo" style="width:236px;">
            <a class="rP0" href="javascript:;"><img id="imgLogo" alt="${_TITLE_!"安全生产标准化管理系统"}"
                                                    src="/resource/images/logo.gif"></a>
        </h1>
        <ul class="js-component-component tO0">
            <li class="js-component-component rZ0">
                <a title="" class="sh0  kl0 nui-txt-link" href="javascript:;">欢迎您，<span
                        id="username">${_USERNAME_!""}</span></a>
            </li>
            <li class="js-component-component rZ0 pn1">|</li>
            <li class="js-component-component rZ0 nav__menu-item" id="config">
                <a title="设置" class="sh0  kl0 nui-txt-link" href="javascript:;">设置<b
                        class="js-component-icon kJ0 nui-ico nui-ico-dArr"></b>
                </a>

                <ul class="nav__submenu nui-menu">
                    <li class="nav__submenu-item nui-menu-item-link"><a>主题风格</a></li>
                    <li class="nav__submenu-item nui-menu-item-link"><a>更改密码</a></li>
                </ul>

            </li>
            <li class="js-component-component rZ0 pn1">|</li>
            <li class="js-component-component rZ0">
                <a title="帮助" class="sh0  kl0 nui-txt-link" href="javascript:;">帮助<b
                        class="js-component-icon kJ0 nui-ico nui-ico-dArr"></b></a>
            </li>
            <li class="js-component-component rZ0 pn1">|</li>
            <li class="js-component-component rZ0"><a title="" class="sh0 kl0 nui-txt-link"
                                                      href="javascript:window.location.href='/logout';">退出</a>
            </li>
        </ul>
    </div>

    <div class="tV0" id="dvMultiTabWrapper" role="navigation">
        <div class="wo0" id="dvMultiTab">
            <ul class="js-component-tab tz0 nui-tabs " id="tabs">${_TABS_}</ul>
        </div>
    </div>
    <div class="sJ0"></div>
</div>

<div id="dvContentContainer" class="frame-main-outer">
    <div id="dvContainer">
        <div class="frame-main frame-main-noNav">
            <iframe name="frame_main" class="frame-main-cont-iframe" id="frame_main" src="/s/home/task.html"
                    frameborder="0" style="width:100%;height:100%;" allowtransparency="allowtransparency"
                    scrolling="auto"></iframe>
        </div>
    </div>
</div>


<div class="skin">
    <DIV class="skin-item skin-top">
        <DIV class="skin-top-inner"></DIV>
        <DIV class="skin-top-inner2"></DIV>
    </DIV>
    <DIV class="skin-item skin-top-left"></DIV>
    <DIV class="skin-item skin-top-right"></DIV>
</div>
</BODY>
</HTML>
</#macro>