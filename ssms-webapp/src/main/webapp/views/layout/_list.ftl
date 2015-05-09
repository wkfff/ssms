<#import "/layout/_layout.ftl" as layout/>
<#--<#include "/layout/_component.ftl"/>-->
<#include "_common_component.ftl"/>
<#include "/layout/_easyui.ftl"/>

<#macro doLayout script header="">
    <#local _header>
        <#if (header?length>0)>${header}</#if>
    </#local>

    <@layout.doLayout header=_header footer=script>
        <#nested />
    </@>
</#macro>