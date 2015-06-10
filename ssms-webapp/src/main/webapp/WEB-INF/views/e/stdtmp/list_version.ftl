<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<style>
    .titlebar{background-color:#daeef5;
    border:1px solid #c1d3de;border-bottom:0px;
    padding:5px;font-weight:bold;font-size:13px;}
    .titlebar img{vertical-align:middle;width:16px;height:16px;} 
    .table{ width:100%;  border:1px solid #c1d3de; border-top:none;}
    .table thead tr th{ height:30px; line-height:35px; border:1px solid #c1d3de; background:url(/resource/images/tablelistbg.png) repeat-x; font-weight:bold;padding-left:2px;}
    .table tbody tr td{ border-left:1px dotted #222; line-height:30px;padding:5px;}
    .table tbody tr:first-child td{ padding-top:2px;}
    .table tbody tr td:first-child{ border-left:none;}
    .count{text-align:center;}
    .operate{text-align:center;}
    .nofile{color:#CCC;}
</style>
<script type="text/javascript">
    $("tbody").find("tr:odd").css("backgroundColor","#eff6fa");
    function nav(_url){
        var url = "/e/stdtmp/query?N_VERSION=";
        window.location.href = url;
    }
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">
    <div class="titlebar"><img src="/resource/images/blue/star.png"/>&nbsp;13要素版本列表</div>
    <table class="table">
    <thead>
        <th>达标体系版本</th>
        <th>操作</th>
    </thead>
    <tbody>

    <#list list as r>
    <tr>
        <td><a href="query?N_VERSION=${r.N_VERSION!}">版本 ${r.N_VERSION!}</a></td>
        <td class="operate"><a href="query?N_VERSION=${r.N_VERSION!}">查看</a></td>
    </tr> 
    </#list>
    
    </tbody>
    </table>
</div>
</@layout.doLayout>
