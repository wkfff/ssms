<#import "_vars.ftl" as vars/>
<#macro doLayout header="" footer="">
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
    <link rel="stylesheet" type="text/css" href="/resource/css/navigation.css">
    <#--easyui-->
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
	<style>
        body{margin:0px;padding:0px;}
        .header{height:80px;background-color:#FAFAFA;color:#000;padding-left:10px;overflow:hidden;}
        a:link, a:visited{color:#005EAC;text-decoration:none}
    </style>
    <#if (header?length>0)>${header}</#if>
</head>
<body>
    <#nested/>
    <#if (footer?length>0)>${footer}</#if>${vars.COMPONENT_INIT_SCRIPTS}<#--输出组件初始化脚本-->
</body>
</html>
</#macro>

<#macro indexLayout menuID header="" footer="">
    <#--设置菜单-->
    <#assign SELECTED_MENU=menuID!"工作中心" in vars/>

    <@doLayout _header footer>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north'" class="header">
            <span style="float:left;">
                 <h2>${_TITLE_!"安全生产标准化管理系统"}</h2>
            </span>
            <span style="float:right;padding:20px;">
                <a>欢迎您，${LANSTAR_IDENTITY.identityName!}</a>
                <a href="javascript:changePwd();">修改密码</a>
                <a>&nbsp;|&nbsp;</a>
                <a href="javascript:window.location.href='/logout';">注销</a>
            </span>

            <div class="menu3">
                <#include "${LANSTAR_IDENTITY.tanentType}_menu.ftl">
             </div>
        </div>  
        <div data-options="region:'center'">
           <#nested/>
        </div>
        <div data-options="region:'south'" style="height:40px;">
           <div style="text-align:center;line-height:100%;width:100%;padding-top:10px;overflow:hidden;">© 2015 福州蓝石电子有限公司</div>
        </div>
    </div>
    </@doLayout>
</#macro>