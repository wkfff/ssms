<#import "/layout/_layout.ftl" as layout/>
<#import "/layout/_list.ftl" as list/>
<#import "/layout/_rec.ftl" as rec/>
<#include "/layout/_component.ftl"/>

<#macro listIndex script header="">
    <#local _header>
    <style type="text/css">
        .side {
            position: absolute;
            left: 0;
            top: 0;
            bottom: 0;
            width: 260px;
            overflow: auto;
            border-right: dashed 1px #cccccc
        }

        .main {
            position: absolute;
            left: 270px;
            top: 0;
            right: 0;
        }
    </style>
        <#if (header?length>0)>${header}</#if>
    </#local>
    <@list.doIndexLayout menuID="设置" script=script header=_header>
    <div class="easyui-layout" data-options="fit: true">
        <div data-options="region:'west'" style="width:220px;">
            <ul>
                <li><a href="/sys/tenant_e/index.html">企业信息管理</a></li>
                <li><a href="/sys/tenant_eu/index.html">企业用户信息管理</a></li>
                <li><a href="/sys/tenant_r/index.html">评审用户信息管理</a></li>
                <li><a href="/sys/tenant_ro/index.html">评审机构信息管理</a></li>
                <li><a href="/sys/tenant_g/index.html">政府机构信息管理</a></li>
                <li><a href="/sys/tenant_gu/index.html">政府用户信息管理</a></li>
            </ul>
        </div>
        <div data-options="region:'center',title:'center title'">
            <#nested />
        </div>
    </div>
    </@>
</#macro>

<#macro recIndex script header="">
    <#local _header>
    <style type="text/css">
        .side {
            position: absolute;
            left: 0;
            top: 0;
            bottom: 0;
            width: 260px;
            overflow: auto;
            border-right: dashed 1px #cccccc
        }

        .main {
            position: absolute;
            left: 270px;
            top: 0;
            right: 0;
        }
    </style>
        <#if (header?length>0)>${header}</#if>
    </#local>

    <@rec.doIndexLayout menuID="设置" script=script header=_header>
    <div class="side">
        <ul>
            <li><a href="/sys/tenant_e/index.html">企业信息管理</a></li>
            <li><a href="/sys/tenant_eu/index.html">企业用户信息管理</a></li>
            <li><a href="/sys/tenant_r/index.html">评审用户信息管理</a></li>
            <li><a href="/sys/tenant_ro/index.html">评审机构信息管理</a></li>
            <li><a href="/sys/tenant_g/index.html">政府机构信息管理</a></li>
            <li><a href="/sys/tenant_gu/index.html">政府用户信息管理</a></li>
        </ul>
    </div>
    <div class="main">
        <#nested />
    </div>
    </@>
</#macro>