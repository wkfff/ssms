<#macro base header="" footer="">
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
${header}
</head>
<body>
    <#nested/>
</body>
${footer}</html>
</#macro>

<#macro doLayout header="" footer="">
    <#local _header>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/fix.css"/>
    <#--knockout-->
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <#--jquery-->
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
    <#--easyui-->
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    ${header}</#local>
    <#local _footer>${footer}</#local>
    <@base header=_header footer=_footer>
        <#nested/>
    </@base>
</#macro>

<#macro indexLayout header="" footer="">
    <#local _header>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/index.css"/>
    <link rel="stylesheet" href="/resource/css/kefu.css"/>
    <#--easyui-->
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/jquery-powerFloat.js"></script>
    <link rel="stylesheet" href="/resource/css/powerFloat.css"/>
    <#--
    <script type="text/javascript" src="/resource/js/kefu.js"></script>
    -->
    ${header}</#local>
    <#local _footer>
    <#--首页脚本-->
    <script type="text/javascript">
        $(function () {
            $(window).resize(function (e) {
                $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height() - 6);
            }).resize();
            $('.exit').click(function () {
                $.messager.confirm('提示', '是否要退出系统？', function (r) {
                    if (r) {
                        window.location.href = '/logout';
                    }
                });
            });
        });
    </script>
    ${footer}
    </#local>
    <@base header=_header footer=_footer>
    <div id="container">
        <div id="hd">
            <div class="hd-wrap ue-clear">
                <div class="top-light"></div>
                <h1 class="logo"></h1>
                <div class="client${LANSTAR_IDENTITY.tenantType!}">
                </div>
                <div class="login-info ue-clear">

                </div>
                <div class="actionbar">
                    <ul class="actionbar">
                        <li class="action"><a href="#" class="exit" title="退出系统">退出</a></li>
                        <li class="split">|</li>
                        <li class="action"><a href="/"> 首页 </a></li>

                        <#if LANSTAR_IDENTITY.tenantType='E' && LANSTAR_IDENTITY.enterpriseService.professionService??>
                        <li class="split">|</li>
                        <li class="action">
                        	<#-- 暂时先用工贸企业，以后再修改
                            <a href="#" onclick="opChoose()" title="点击切换专业">专业:${LANSTAR_IDENTITY.enterpriseService.professionService.name}  <b class="c-icon c-icon-triangle-down"></b></a>
                            -->
                            <a href="#" id="chooseProfession" rel="professions">${LANSTAR_IDENTITY.enterpriseService.professionService.name}专业 <b class="c-icon c-icon-triangle-down"></b></a>
                                <#--专业<select style="border:0; background: none; color: #FFF;font-family: 微软雅黑;font-weight: bold;">
                                <#list LANSTAR_IDENTITY.enterpriseService.professions as map>
                                    <option value="${map.id}" <#if LANSTAR_IDENTITY.enterpriseService.professionService.id == map.id>selected</#if>>${map.name}</option>
                                </#list>
                            </select>-->
                        </li>
                        </#if>
                        <li class="split">|</li>
                        <li class="action">${LANSTAR_IDENTITY.tenantName}(${LANSTAR_IDENTITY.name})</a></li>
                    </ul>
                </div>
                <div style="position:absolute;right:20px;top:40px;">
                	<span>在线客服：</span>
                	<a target="_blank" href="http://wpa.qq.com/msgrd?v=1&uin=2055419486&site=&Menu=yes" >
                		<#--<img border="0" src="http://wpa.qq.com/pa?p=41:38142116:41" alt="点击这里获取客户服务" title="点击这里获取客户服务">-->
                		<#-- <img border="0" src="http://wpa.qq.com/pa?p=20:38142116:20" alt="点击这里获取客户服务" title="点击这里获取客户服务"> -->
                		<#-- <img border="0" src="http://wpa.qq.com/pa?p=1:38142116:1" alt="点击这里获取客户服务" title="点击这里获取客户服务"> -->
                		<img border="0" align="absmiddle" src="/resource/images/icon_kefu.png" alt="点击这里获取客户服务" title="点击这里获取客户服务">
                	</a>
                	<#--
                	<a target=blank valign="middle" style="color:white;" href="http://wpa.qq.com/msgrd?V=1&Uin=99536247&Site=http://www.mudiao.com&Menu=yes">
                	<img border="0" align="absmiddle" SRC=http://wpa.qq.com/pa?p=1:50858745:4 alt="技术支持"> 在线客服
                	</a>
                	-->
                	<span style="padding-left:30px;">热线电话：0591-87762196  传真：0591-87275136</span>
                </div>
            </div>
        </div>
        <div id="bd">
            <#nested />
        </div>
        <#if LANSTAR_IDENTITY.tenantType='E' && LANSTAR_IDENTITY.enterpriseService.professionService??>
        <style type="text/css">
            .target_box {
                border: 1px solid #aaa;
                background-color: #FDFDFE;
                width: 150px;
            }
            .target_box p {
                border-bottom: 1px dotted #CCCCCC;
                padding: 2px;
            }
            .target_box p:hover {
                background: #87ceeb;
            }
            .target_box a {
                color: #000;
            }
            .target_box a:hover {
                color: #ff6f37;
            }
        </style>
        <div id="professions" class="shadow target_box dn" style="display: none">
            <#list LANSTAR_IDENTITY.enterpriseService.professions as map>
                <p><a href="/e/setTemplate?profession=${map.id}">${map.name}专业</a></p>
            </#list>
        </div>
        <script type="text/javascript">
            $(function() {
                $("#chooseProfession").powerFloat({ eventType: 'click', position: "3-2", zIndex: 2000});
            });
        </script>
        </#if>
        <#--  取消底部栏目
        <div id="ft" class="ue-clear" style="text-align:center;">
            <span style="color:#EEE;">copyright @2015 福建永创意信息科技有限公司,福州蓝石电子有限公司</span>
        </div>
        -->
    </div>
    </@>
</#macro>