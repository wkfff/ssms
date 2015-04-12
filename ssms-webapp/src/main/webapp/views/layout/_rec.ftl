<#import "/layout/_layout.ftl" as layout/>

<#assign header>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
<script type="text/javascript" src="/resource/js/common.js"></script>
<script type="text/javascript" src="/resource/js/form.js"></script>
<style>
    .main{padding:5px;}
</style>
</#assign>

<#macro doLayout script>
    <@layout.doLayout header=header footer=script>
        <#nested />
    </@layout.doLayout>
</#macro>