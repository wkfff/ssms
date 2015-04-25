<#import "/layout/_layout.ftl" as layout/>
<#include "/layout/_component.ftl"/>

<#macro doLayout script="" header="">
    <#local _header>
        <#--上传组件-->
        <script type="text/javascript" src="/resource/js/plupload/plupload.full.min.js"></script>
        <script type="text/javascript" src="/resource/js/plupload/zh_CN.js"></script>
        <script type="text/javascript" src="/resource/js/upload.js"></script>
        <#--富文本编辑器-->
        <script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
        <script type="text/javascript" src="/resource/js/texteditor.js"></script>
        <style>
            body{margin-top:100px;}
            .help-block{visibility: ;color:#EEE;}
        </style>
        <#if (header?length>0)>${header}</#if>
    </#local>
    <@layout.doLayout header=_header footer=script>
        <#nested />
    </@layout.doLayout>
</#macro>

<#macro doIndexLayout menuID script="" header="">
    <#local _header>
        <#--上传组件-->
        <script type="text/javascript" src="/resource/js/plupload/plupload.full.min.js"></script>
        <script type="text/javascript" src="/resource/js/plupload/zh_CN.js"></script>
        <script type="text/javascript" src="/resource/js/upload.js"></script>
        <#--富文本编辑器-->
        <script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
        <script type="text/javascript" src="/resource/js/texteditor.js"></script>
        <style>
            .help-block{visibility: ;color:#EEE;}
        </style>
        <#if (header?length>0)>${header}</#if>
    </#local>
    <@layout.indexLayout menuID=menuID header=_header footer=script>
        <#nested />
    </@>
</#macro>