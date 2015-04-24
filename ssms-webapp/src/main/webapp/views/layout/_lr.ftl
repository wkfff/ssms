<#import "/layout/_layout.ftl" as layout/>
<#macro doLayout script="" header="">
<#assign _header>
<LINK id="patternCss" href="/resource/css/standard.css" rel="stylesheet" type="text/css">
<LINK title="16" id="styleCss" href="/resource/theme/skyblue/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/common.js"></script>

<#if (header?length>0)>${header}</#if>
</#assign>
<@layout.doLayout header=_header footer=script>
    <#nested />
</@layout.doLayout>
</#macro>