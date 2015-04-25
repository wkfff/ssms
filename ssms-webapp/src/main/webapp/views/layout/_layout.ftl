<#import "_vars.ftl" as vars/>
<#macro doLayout header="" footer="">
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <#--easyui-->
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
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
        <div data-options="region:'north'" style="height: 80px">
            <#include 'parts/header.ftl'/>
        </div>
        <div data-options="region:'center'">
            <#nested/>
        </div>
        <div data-options="region:'south'" style="height: 50px">
            <#include 'parts/copyright.ftl'/>
        </div>
    </div>
    </@doLayout>
</#macro>