<#import "/layout/_layout.ftl" as layout/>

<#assign header>
    <link href="/resource/css/datagrid.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/datagrid.js"></script>
</#assign>

<#macro doLayout script>
<@layout.doLayout header=header footer=script>
    <#nested />
</@layout.doLayout>
</#macro>