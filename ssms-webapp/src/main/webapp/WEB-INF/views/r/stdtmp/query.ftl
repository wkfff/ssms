<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<style>
    .titlebar{background-color:#daeef5;border:1px solid #c1d3de;padding:5px;font-weight:bold;font-size:14px;}
    .titlebar img{vertical-align:middle;width:16px;height:16px;} 
    .table{ width:100%;  border:1px solid #c1d3de; border-top:none;}
    .table thead tr th{ height:35px; line-height:35px; border:1px solid #c1d3de; background:url(/resource/images/tablelistbg.png) repeat-x; font-weight:bold;padding-left:2px;}
    .table tbody tr td{ border-left:1px dotted #222; line-height:30px;}
    .table tbody tr:first-child td{ padding-top:2px;}
    .table tbody tr td:first-child{ border-left:none;}
    .count{text-align:center;}
    .operate{text-align:center;}
    .nofile{color:#CCC;}
    .back{float:right;padding-right:5px;}
</style>
<script type="text/javascript">
    $("tbody").find("tr:odd").css("backgroundColor","#eff6fa");
    function nav(_url){
        var url = "/r/stdtmp/see?URL="+ encodeURIComponent(_url);
        window.location.href = url;
    }
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">
    <div class="titlebar">
        <img src="/resource/images/blue/flag.png"/>&nbsp;13要素列表
        <#if showback??>
            <div id="tb" style="float:right;padding-right:5px;">
            <a href="#" onclick="window.location.href='${referer!}';" style="width:90px;font-size:14px;text-decoration: none;">返回列表</a>
            </div>
        </#if>
    </div>
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
                    <a href="/r/stdtmp/see?URL=${map.attributes.C_URL!}">
                        <#list 1..map.level as i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                        <#if map.level=1><img src="/resource/images/listico.png" />&nbsp;</#if>${map.text}
                    <a>
                <#else>
                    <#list 1..map.level as i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                    <#if map.level=1><img src="/resource/images/foot02.png" />&nbsp;</#if>${map.text}
                </#if>
                </td>
                <td class="count">${map.attributes.N_COUNT!}</td>
                <td class="count">
                    <#if map.attributes.C_URL??>${map.attributes.S_STATE!}</#if>
                </td>
                <td class="count">${map.attributes.T_UPDATE!}</td>
                <td class="operate">
                    <#if map.attributes.C_URL??>
                        <a href="javascript:nav('${map.attributes.C_URL!}')">查看</a>&nbsp;&nbsp;
                        <!-- <a href="javascript:nav('${map.attributes.C_URL!}&_R_=0')">修改</a>&nbsp;&nbsp; -->
                    </#if>
                </td>
            </tr> 
           <@buildTree map.children/>
        </#list>
    </#if>
</#macro>