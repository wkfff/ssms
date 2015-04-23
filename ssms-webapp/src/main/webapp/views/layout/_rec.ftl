<#import "/layout/_layout.ftl" as layout/>
<#include "/layout/_component.ftl"/>

<#macro doLayout script="" header="">
    <#local _header>
        <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
        <!--[if lte IE 6]>
        <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-ie6.css">
        <![endif]-->
        <!--[if lte IE 7]>
        <link rel="stylesheet" type="text/css" href="/resource/css/ie.css">
        <![endif]-->
        <link rel="stylesheet" type="text/css" href="/resource/theme/bootstrap/jquery-ui-1.10.0.custom.css"/>
        <link rel="stylesheet" type="text/css" href="/resource/css/jquery-ui/jquery-ui-timepicker-addon.css"/>
        <script type="text/javascript" src="/resource/js/common.js"></script>
        <script type="text/javascript" src="/resource/js/form.js"></script>

        <script type="text/javascript" src="/resource/js/jquery-ui/jquery-ui.min.js"></script>
        <script type="text/javascript" src="/resource/js/jquery-ui/jquery.ui.datepicker-zh-CN.js"></script>
        <script type="text/javascript" src="/resource/js/jquery-ui/jquery-ui-timepicker-addon.min.js"></script>
        <script type="text/javascript" src="/resource/js/jquery-ui/jquery-ui-timepicker-zh-CN.js"></script>

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
        <link rel="stylesheet" type="text/css" href="/resource/theme/bootstrap/jquery-ui-1.10.0.custom.css"/>
        <link rel="stylesheet" type="text/css" href="/resource/css/jquery-ui/jquery-ui-timepicker-addon.css"/>
        <script type="text/javascript" src="/resource/js/common.js"></script>
        <script type="text/javascript" src="/resource/js/form.js"></script>

        <script type="text/javascript" src="/resource/js/jquery-ui/jquery-ui.min.js"></script>
        <script type="text/javascript" src="/resource/js/jquery-ui/jquery.ui.datepicker-zh-CN.js"></script>
        <script type="text/javascript" src="/resource/js/jquery-ui/jquery-ui-timepicker-addon.min.js"></script>
        <script type="text/javascript" src="/resource/js/jquery-ui/jquery-ui-timepicker-zh-CN.js"></script>

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