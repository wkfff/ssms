<#import "/layout/_layout.ftl" as layout/>


<#macro doLayout script header="">
    <#local _header>
        <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
        <!--[if lte IE 6]>
        <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-ie6.css">
        <![endif]-->
        <!--[if lte IE 7]>
        <link rel="stylesheet" type="text/css" href="/resource/css/ie.css">
        <![endif]-->

        <link href="/resource/css/datagrid.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="/resource/js/common.js"></script>
        <script type="text/javascript" src="/resource/js/datagrid.js"></script>
        <style>body{margin-top:80px;}</style>
        <#if (header?length>0)>${header}</#if>
    </#local>

    <@layout.doLayout header=_header footer=script>
        <#nested />
    </@>
</#macro>





<#macro doIndexLayout menuID script header="">
    <#local _header>
    <link href="/resource/css/datagrid.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/datagrid.js"></script>
    <#if (header?length>0)>${header}</#if>
    </#local>

    <@layout.indexLayout menuID=menuID header=_header footer=script>
        <#nested />
    </@>
</#macro>