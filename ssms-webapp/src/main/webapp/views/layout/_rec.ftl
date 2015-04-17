<#import "/layout/_layout.ftl" as layout/>

<#assign header>

  <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
  <!--[if lte IE 6]>
  <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-ie6.css">
  <![endif]-->
  <!--[if lte IE 7]>
  <link rel="stylesheet" type="text/css" href="/resource/css/ie.css">
  <![endif]-->
  

<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
<script type="text/javascript" src="/resource/js/common.js"></script>
<script type="text/javascript" src="/resource/js/form.js"></script>
<style>
	body{margin-top:100px;}
	.help-block{visibility: ;color:#EEE;}
</style>
</#assign>

<#macro doLayout script>
    <@layout.doLayout header=header footer=script>
        <#nested />
    </@layout.doLayout>
</#macro>