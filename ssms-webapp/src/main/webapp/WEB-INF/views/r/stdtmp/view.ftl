<!doctype html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="/resource/css/layout.css"/>
    </head>
<body>
        <div class="titlebar">
            <img src="/resource/images/blue/star.png"/>&nbsp;企业安全标准化体系13要素查看</span>
            <#if showBack?? && showBack==1>
            <span class="backing">
                &nbsp;&nbsp;<a href="/r/grade/index">返回列表</a>
            </span>
            </#if>
        </div>
        <table class="table">
            <thead>
                <th class="th_left">体系目录名称</th>
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
        
        <script>
            <#if sid??>location.href = "#tr_${sid!}";</#if>
        </script>
</body>
</html>

<#macro buildTree list root=false>
    <#if (list?size>0)>
        <#list list as r>
            <tr id="tr_${r.attributes.ID!}" 
                 <#if r.attributes.ID?? && r.attributes.ID==sid!-1>
                        class="activerow"
                  </#if>
                ><td>
                <#if r.attributes.C_URL??>
                    <a href="/r/stdtmp/see/${viewUrl!}-${r.attributes.P_TMPFILE!0}-${version!0}-${r.attributes.ID!0}">
                        <#list 1..r.level as i>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                        <#if r.level=1><img src="/resource/images/listico.png" />
                        <#else><img src="/resource/images/rmail.png" align="bottom"/></#if>
                        ${r.text}
                    <a>
                <#else>
                    <#list 1..r.level as i>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                    <img src="/resource/images/foot02.png" align="bottom"/>
                    ${r.text}
                </#if>
                </td>
                <td class="td_center">${r.attributes.N_COUNT!}</td>
                <td class="td_center"><#if r.attributes.C_URL??>${r.attributes.S_STATE!}</#if></td>
                <td class="td_center">${r.attributes.T_UPDATE!}</td>
                <td class="td_center">
                    <#if r.attributes.C_URL??>
                        <a href="/r/stdtmp/see/${viewUrl!}-${r.attributes.P_TMPFILE!0}-${version!0}-${r.attributes.ID!0}">查看</a>
                    </#if>
                </td>
            </tr>
           <@buildTree r.children/>
        </#list>
    </#if>
</#macro>