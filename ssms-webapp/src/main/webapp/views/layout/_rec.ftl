<#import "/layout/_layout.ftl" as layout/>
<#--<#include "/layout/_component.ftl"/>-->
<#include "_easyui.ftl" />

<#macro doLayout script="" header="">
    <#local _header><script type="text/javascript" src="/resource/js/plupload/plupload.full.min.js"></script>
    <script type="text/javascript" src="/resource/js/plupload/zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/upload.js"></script>
    <#--富文本编辑器-->
    <script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="/resource/js/texteditor.js"></script>
    <style type="text/css">
        td {
            padding-top: 5px;
            padding-bottom: 5px;
        }
    </style>${header}</#local>
    <#local _script>${script}</#local>
    <@layout.doLayout header=_header footer=_script>
        <#nested />
    </@layout.doLayout>
</#macro>
