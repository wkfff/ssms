<#import "/layout/_layout.ftl" as layout/>
<#include "/layout/_component.ftl"/>

<#macro doLayout script header="">
    <#local _header>
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