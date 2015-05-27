<#import "_layout.ftl" as layout/>

<#macro doLayout script header="">
    <#local _header>
        <#if (header?length>0)>${header}</#if>
    </#local>

    <@layout.doLayout header=_header footer=script>
        <#nested />
    </@>
</#macro>