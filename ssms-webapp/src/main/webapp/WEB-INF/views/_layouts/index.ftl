<@layout.extends name="base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/index.css"/>
    <link rel="stylesheet" href="/resource/css/kefu.css"/>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/jquery-powerFloat.js"></script>
    <link rel="stylesheet" href="/resource/css/powerFloat.css"/>
    </@>

    <#if LANSTAR_IDENTITY.tenantType='E' && LANSTAR_IDENTITY.enterpriseService.professionService??>
        <@layout.put block="head">
        <style type="text/css">
            .professions {
            }

            .target_box {
                display: none;
                border: 1px solid #aaa;
                background-color: #000000;
                opacity: 0.7;
            }

            .target_box li {
                border-bottom: 1px dotted #CCCCCC;
                padding: 10px;
                text-align: center;
            }

            .target_box li:hover {
                background-color: #58595b;
            }

            .target_box a {
                color: #FFFFFF;
                width: 100%;
                display: block;
            }
        </style>
        </@>
        <@layout.put block="profession">
        <li class="split">|</li>
        <li class="action">
            <a href="#" class="chooseProfession">${LANSTAR_IDENTITY.enterpriseService.professionService.name}专业
                <b class="c-icon c-icon-triangle-down"></b></a>
        </li>
        </@layout.put>
        <@layout.put block="contents">
        <ul class="professions target_box">
            <#list LANSTAR_IDENTITY.enterpriseService.professions as map>
                <li><a href="/e/setTemplate?profession=${map.id}">${map.name}专业</a></li>
            </#list>
        </ul>
        </@layout.put>
        <@layout.put block="footer">
        <script type="text/javascript">
            $(function () {
                $(".chooseProfession").powerFloat({
                    target: $('.professions'),
                    // eventType: 'click',
                    position: "7-5"
                });
            });
        </script>
        </@layout.put>
    </#if>

    <@layout.put block="contents" type="PREPEND">
    <div id="container">
        <div id="hd">
            <div class="hd-wrap ue-clear">
                <div class="top-light"></div>
                <h1 class="logo"></h1>

                <div class="client${LANSTAR_IDENTITY.tenantType!}"></div>
                <div class="login-info ue-clear">

                </div>
                <div class="actionbar">
                    <ul class="actionbar">
                        <li class="action"><a href="#" class="exit" title="退出系统">退出</a></li>
                        <li class="split">|</li>
                        <li class="action"><a href="/">首页</a></li>
                        <@layout.block name="profession"/>
                        <li class="split">|</li>
                        <li class="action">${LANSTAR_IDENTITY.tenantName}(${LANSTAR_IDENTITY.name})</a></li>
                    </ul>
                </div>
                <div style="position:absolute;right:20px;top:40px;">
                    <span>在线客服：</span>
                    <a target="_blank" href="http://wpa.qq.com/msgrd?v=1&uin=2055419486&site=&Menu=yes">
                        <img border="0" align="absmiddle" src="/resource/images/icon_kefu.png" alt="点击这里获取客户服务" title="点击这里获取客户服务">
                    </a>
                    <span style="padding-left:30px;">热线电话：0591-87762196  传真：0591-87275136</span>
                </div>
            </div>
        </div>
        <div id="bd">
            <@layout.block name="containers"/>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript">
        $(function () {
            $(window).resize(function (e) {
                $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height() - 6);
            }).resize();
            $('.exit').click(function () {
                utils.messager.confirm('是否要退出系统？', function () {
                    window.location.href = '/logout';
                });
            });
        });
    </script>
    </@>
</@>