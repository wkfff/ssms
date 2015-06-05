<#import "_layout.ftl" as layout/>

<#macro doLayout script="" header="">
    <#local _header>
    <script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    <#if DEV_MODE>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/plugins/autoheight/autoheight.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>
    <#else>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    </#if>${header}</#local>
    <#local _script>${script}</#local>
    <@layout.doLayout header=_header footer=_script>
        <#nested />
    </@layout.doLayout>
</#macro>
