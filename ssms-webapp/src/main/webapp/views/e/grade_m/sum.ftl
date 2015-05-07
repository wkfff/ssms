<#import "/layout/_list.ftl" as layout/>
<#assign script>
<style>
    .title{font-size:18px !important;font-weight:bold;text-align:center;}
</style>
<script type="text/javascript">
    $("tbody").find("tr:odd").css("backgroundColor","#eff6fa");
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">
    <h1 class="title">评分汇总表</h1>
        企业名称：${S_TENANT!""}
    <table class="table">
    <thead>
        <th>元素名称</th>
        <th>标准分值</th>
        <th>空项分</th>
        <th>应得分</th>
        <th>实际得分</th>
        <th>百分制得分</th>
    </thead>
    <tbody>
    <#list list as r>
    <tr>
        <td>${r.S_PROJECT}</td>
        <td>${r.N_SUBTOTAL}</td>
        <td>${r.N_BLANK}</td>
        <td>${r.N_GET}</td>
        <td>${r.N_REAL}</td>
        <td>${r.N_P}</td>
    </tr> 
    </#list>
    </tbody>
    </table>
</div>
</@layout.doLayout>