<#import "/layout/_list.ftl" as layout/>
<#assign script>
<style>
    .title{font-size:18px !important;font-weight:bold;text-align:center;}
    .c1{width:10%;}
    .c2{width:45%;}
    .c3{width:45%;}
</style>
<script type="text/javascript">
    $("tbody").find("tr:odd").css("backgroundColor","#eff6fa");
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">
    <h1 class="title">不符合项扣分汇总表</h1>
        企业名称：${S_TENANT!""}
    <table class="table">
    <thead>
        <th class="c1">一级要素</th>
        <th class="c2">扣分项说明</th>
        <th class="c3">整改建议</th>
    </thead>
    <tbody>
    <#list list as r>
    <tr>
        <td>${r.S_PROJECT}</td>
        <td>${r.C_CONTENT}</td>
        <td>${r.C_DESC}</td>
    </tr> 
    </#list>
    </tbody>
    </table>
</div>
</@layout.doLayout>