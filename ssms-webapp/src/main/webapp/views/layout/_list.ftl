<#import "/layout/_layout.ftl" as layout/>
<#include "/layout/_component.ftl"/>
<#macro doLayout script header="">
<#assign _header>
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
<!--[if lte IE 6]>
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-ie6.css">
<![endif]-->
<!--[if lte IE 7]>
<link rel="stylesheet" type="text/css" href="/resource/css/ie.css">
<![endif]-->

<link href="/resource/css/datagrid.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/common.js"></script>
<script type="text/javascript" src="/resource/js/datagrid.js"></script>
<style>body{margin-top:80px;}</style>
<#if (header?length>0)>${header}</#if>
</#assign>
<@layout.doLayout header=_header footer=script>
    <#nested />
</@layout.doLayout>
</#macro>