<#import "/layout/_list.ftl" as layout/>
<#assign script>
<style>
    .title{font-size:18px !important;font-weight:bold;text-align:center;}
    .table{width:1040px !important;}
    .c0{width:10px;text-align:center;}
    .c1{width:100px;}
    .c2{width:100px;}
    .c3{width:450px;}
    .c4{width:320px;}
    .c5{width:60px;text-align:center;}
</style>
<script type="text/javascript">
    $("tbody").find("tr:odd").css("backgroundColor","#eff6fa");
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">
    <h1 class="title">自评扣分点及原因说明汇总表</h1>
        企业名称：${S_TENANT!""}
    <table class="table">
    <thead>
        <th class="c0">序号</th>
        <th class="c1">一级要素</th>
        <th class="c2">二级要素</th>
        <th class="c3">企业达标标准</th>
        <th class="c4">扣分说明</th>
        <th class="c5">扣分分值</th>
    </thead>
    <tbody>
    <#list list as r>
    <tr>
        <td class="c0">${r_index+1}</td>
        <td class="c1">${r.S_CATEGORY}</td>
        <td class="c2">${r.S_PROJECT}</td>
        <td class="c3">${r.C_CONTENT}</td>
        <td class="c4">${r.C_DESC}</td>
        <td class="c5">${r.N_DED}</td>
    </tr> 
    </#list>
    </tbody>
    </table>
</div>
</@layout.doLayout>