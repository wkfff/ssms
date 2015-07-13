<@layout.extends name="../../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <style type="text/css">
        .icon-title {
            background: url('/resource/images/blue/star.png') no-repeat 0;
            background-size: 16px;
            padding-left: 20px;
        }
    </style>
    </@>

    <@layout.put block="contents">
    <div class="titlebar">
        <span class="icon-title">企业安全标准化体系创建13要素</span>
        <span style="color:red;">${version_name!}</span>
        <span class="backing">
        <#--<#if version==0><a href="/e/stdtmp/archive">归档当前版本</a></#if>-->
            <!-- <a href="/e/stdtmp/versions">返回列表</a> -->
        </span>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th class="th_left">体系目录名称</th>
            <th>记录数</th>
            <th>状态</th>
            <th>最后修改时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#list tree as map><@buildTree map.children true/></#list>
        </tbody>
    </table>

    <script><#if sid??>location.href = "#tr_${sid!}";</#if></script>
    </@>
</@>

<#macro buildTree list root=false>
    <#if (list?size>0)>
        <#list list as r>
        <tr id="tr_${r.attributes.ID!}" <#if r.attributes.ID==sid!"">class="activerow"</#if>>
            <td>
                <#if r.attributes.C_URL??>
                <a href="/e/stdtmp/see/${r.attributes.P_TMPFILE!0}-${version!0}-${r.attributes.ID!0}">
                    <#list 1..r.level as i>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                    <#if r.level=1><img src="/resource/images/listico.png"/>
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
                    <a href="/e/stdtmp/see/${r.attributes.P_TMPFILE!0}-${version!0}-${r.attributes.ID!0}">查看</a>
                </#if>
            </td>
        </tr>
            <@buildTree r.children/>
        </#list>
    </#if>
</#macro>