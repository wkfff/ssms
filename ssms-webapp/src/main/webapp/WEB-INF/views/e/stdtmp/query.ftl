<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<style>
    .title{font-size:18px !important;font-weight:bold;text-align:center;}
    .table{ width:100%;  border:1px solid #c1d3de; border-top:none;}
    .table thead tr th{ height:35px; line-height:35px; border:1px solid #c1d3de; background:url(/resource/images/tablelistbg.png) repeat-x; font-weight:bold;padding-left:2px;}
    .table tbody tr td{ border-left:1px dotted #222; line-height:30px;}
    .table tbody tr:first-child td{ padding-top:2px;}
    .table tbody tr td:first-child{ border-left:none;}
    .count{text-align:center;}
    .operate{text-align:center;}
    .nofile{color:#CCC;}
</style>
<script type="text/javascript">
    $("tbody").find("tr:odd").css("backgroundColor","#eff6fa");
    function nav1(_url){
        var url = "/e/stdtmp/see?URL="+ encodeURIComponent(_url+'&R=1');
        window.location.href = url;
    }
    function nav(_url){
        var url = "/e/stdtmp/see?URL="+ encodeURIComponent(_url);
        window.location.href = url;
    }
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">
    <h1 class="title">13要素</h1>
    <table class="table">
    <thead>
        <th>体系目录名称</th>
        <th>记录数</th>
        <th>状态</th>
        <th>最后修改时间</th>
        <th>操作</th>
    </thead>
    <tbody>

    <#list tree as map>
        <@buildTree map.children true/>
    </#list>
   
    </tbody>
    </table>
</div>
</@layout.doLayout>

<#macro buildTree list root=false>
    <#if (list?size>0)>
        <#list list as map>
            <tr>
                <td>
                <#if map.attributes.C_URL??>
                    <a href="/e/stdtmp/see?URL=${map.attributes.C_URL!}">
                        <#list 1..map.level as i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                        <#if map.level=1><img src="/resource/images/listico.png" />&nbsp;</#if>${map.text}
                    <a>
                <#else>
                    <#list 1..map.level as i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                    <#if map.level=1><img src="/resource/images/foot02.png" />&nbsp;</#if>${map.text}
                </#if>
                </td>
                <td class="count">${map.attributes.N_COUNT!}</td>
                <td></td>
                <td></td>
                <td class="operate">
                    <#if map.attributes.C_URL??>
                        <a href="javascript:nav1('${map.attributes.C_URL!}')">查看</a>&nbsp;&nbsp;
                        <a href="javascript:nav('${map.attributes.C_URL!}')">修改</a>&nbsp;&nbsp;
                    <#else>
                        <span class="nofile"> 当前目录下还没有文件</span>
                    </#if>
                </td>
            </tr> 
           <@buildTree map.children/>
        </#list>
    </#if>
</#macro>