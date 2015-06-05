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
    <script type="text/javascript" src="/resource/js/kefu.js"></script>
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
    <#local EnterpriseProcession>
    <div id="dd" class="easyui-dialog" title="选择专业" style="width:400px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true, buttons:'#bb', closable:true<#if LANSTAR_IDENTITY.tenantType='E'&&needChooseProfessions=false>, closed:true</#if>">
        <div style="position: absolute; top: 80px; left: 80px;">
            选择专业
            <input id="cc" class="easyui-combobox" name="dept" required data-options="valueField:'SID',textField:'C_NAME',url:'/e/getProfessions',width:160">
        </div>
    </div>
    <div id="bb">
        <a href="#" class="easyui-linkbutton" onclick="choose()">确定</a>
        <a href="#" class="easyui-linkbutton" onclick="opClose()">取消</a>
    </div>
    <script type="text/javascript">
        function choose() {
            var value = $('#cc').combobox('getValue');
            if (value == null || value.length == 0) return;
            $.post("/e/setTemplate", {profession: value}, function () {
                window.location.href = "/";
            });
        }
        function opChoose() {
            $('#dd').dialog('open');
        }
        function opClose(){
            $('#dd').dialog('close');
        }
    </script>
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
                        <li><a href="#" class="exit" title="退出系统">退出</a></li>
                        
                        <#if LANSTAR_IDENTITY.tenantType='E' && profession??>
                        <li>|</li>
                        <li>
                            <a href="#" onclick="opChoose()" title="点击切换专业">专业:${profession.name}  <b class="c-icon c-icon-triangle-down"></b></a>
                        </li>
                        </#if>
                        
                        <li>|</li>
                        <li>${LANSTAR_IDENTITY.tenantName}</a>
                    </ul>
                </div>
            </div>
        </div>
        <div id="bd">
            <#nested />
        </div>
        <div id="ft" class="ue-clear" style="text-align:center;">
            <span style="color:#EEE;">copyright @2015 福建永创意信息科技有限公司,福州蓝石电子有限公司</span>
        </div>
    </div>
        <#if LANSTAR_IDENTITY.tenantType='E'>${EnterpriseProcession!}</#if>
    </@>
</#macro>