<#import "_vars.ftl" as vars/>
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
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
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
${vars.COMPONENT_INIT_SCRIPTS}<#--输出组件初始化脚本-->
    </@base>
</#macro>
