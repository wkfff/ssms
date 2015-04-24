<#import "_vars.ftl" as vars/>
<#macro doLayout header="" footer="">
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-ie6.css">
    <![endif]-->
    <!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="/resource/css/ie.css">
    <![endif]-->
    <link id="patterncss" href="/resource/css/standard.css" rel="stylesheet" type="text/css">
    <link id="stylecss" href="/resource/theme/skyblue/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <#if (header?length>0)>${header}</#if>
</head>
<body class="skyblue frame-default frame-auto">
    <#nested/>
    <#if (footer?length>0)>${footer}</#if>${vars.COMPONENT_INIT_SCRIPTS}<#--输出组件初始化脚本-->
</body>
</html>
</#macro>

<#macro indexLayout menuID header="" footer="">
    <#--设置菜单-->
    <#assign SELECTED_MENU=menuID!"工作中心" in vars/>

    <#local _header>
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
        <#if (header?length>0)>${header}</#if>
    </#local>

    <@doLayout _header footer>
    <div class="frame-head">
        <div class="tY0">
            <h1 class="tG0 sj0" id="h1Logo" style="width:236px;">
                <a class="rP0" href="javascript:;"><img id="imgLogo" alt="${_TITLE_!"安全生产标准化管理系统"}"
                                                        src="/resource/images/logo.gif"></a>
            </h1>
            <ul class="js-component-component tO0">
                <li class="js-component-component rZ0">
                    <a title="" class="sh0  kl0 nui-txt-link" href="javascript:;">欢迎您，<span
                            id="username">${LANSTAR_IDENTITY.identityName}</span></a>
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
                <#include "${LANSTAR_IDENTITY.tanentType}_menu.ftl">
            </div>
        </div>
        <div class="sJ0"></div>
    </div>

    <div id="dvContentContainer" class="frame-main-outer">
        <div id="dvContainer">
            <div class="frame-main frame-main-noNav">
                <#nested/>
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
    </@doLayout>
</#macro>