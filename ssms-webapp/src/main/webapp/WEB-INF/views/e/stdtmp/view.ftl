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
        <tbody><@buildTree tree 1 true/></tbody>
    </table>

    <script><#if sid??>location.href = "#tr_${sid!}";</#if></script>
    </@>
</@>

<#macro buildTree list level root=false>
    <#if (list?size>0)>
        <#list list as folder>
        <tr>
            <td><#list 1..level as i>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                <img src="/resource/images/foot02.png" align="bottom"/>${folder.name}
            </td>
            <td class="td_center">${folder.fileCount}</td>
            <td class="td_center"></td>
            <td class="td_center"></td>
            <td class="td_center"></td>
        </tr>
            <#if folder.files?size gt 0>
                <#list folder.files as file>
                <tr id="tr_${file.id}" <#if file.id==sid!"">class="activerow"</#if>>
                    <td><#list 1..level+1 as i>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                        <img src="/resource/images/rmail.png" align="bottom"/>
                        <a href="/e/stdtmp/see/${file.templateFileCode}-${version!0}-${file.id}">${file.name}</a>
                    </td>
                    <td class="td_center">${file.count}</td>
                    <td class="td_center"><#if file.count==0>未创建<#else>已创建</#if></td>
                    <td class="td_center"><#if file.count gt 0>${file.lastUpdate}</#if></td>
                    <td class="td_center">
                        <a href="/e/stdtmp/see/${file.templateFileCode}-${version!0}-${file.id}">查看</a>
                    </td>
                </tr>
                </#list>
            </#if>
            <@buildTree folder.children level+1/>
        </#list>
    </#if>
</#macro>