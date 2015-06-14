<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<style>
    .titlebar{background-color:#daeef5;border:1px solid #c1d3de;padding:5px;font-weight:bold;font-size:13px;}
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
    .activeRow{background-color:#ADBDFF;}
</style>
<script type="text/javascript">
    $("tbody").find("tr:odd").css("backgroundColor","#eff6fa");
    <#if sid??>location.href = "#tr_${sid!}";</#if>
    $("tr[id='tr_${sid!}']").css("backgroundColor","#ADBDFF");
    
    function nav(_url,sid){
        var url = "/e/stdtmp/see?sid="+sid+"&N_VERSION=${N_VERSION!}&URL="+ encodeURIComponent(_url);
        window.location.href = url;
    }
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">
    <div class="titlebar">
        <img src="/resource/images/blue/star.png"/>&nbsp;企业安全标准化体系创建13要素表 --- <#if N_VERSION=='0'>当前版本<#else>版本(${N_VERSION!})</#if>
        <span class="back"><a href="list_version">返回列表</a></span>
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
            <tr id="tr_${map.attributes.SID!}" >
                <td>
                <#if map.attributes.C_URL??>
                    <a href="/e/stdtmp/see?URL=${map.attributes.C_URL!}">
                        <#list 1..map.level as i>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                        <#if map.level=1><img src="/resource/images/listico.png" />
                        <#else><img src="/resource/images/rmail.png" align="bottom"/></#if>
                    <a>
                <#else>
                    <#list 1..map.level as i>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                    <img src="/resource/images/foot02.png" align="bottom"/>
                </#if>&nbsp;${map.text}
                </td>
                <td class="count">${map.attributes.N_COUNT!}</td>
                <td class="count"><#if map.attributes.C_URL??>${map.attributes.S_STATE!}</#if></td>
                <td>${map.attributes.T_UPDATE!}</td>
                <td class="operate">
                    <#if map.attributes.C_URL??>
                        <a href="javascript:nav('${map.attributes.C_URL!}','${map.attributes.SID!}')">查看</a>&nbsp;&nbsp;
                    </#if>
                </td>
            </tr>
           <@buildTree map.children/>
        </#list>
    </#if>
</#macro>