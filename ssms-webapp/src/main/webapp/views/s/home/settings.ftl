<#import "/layout/_layout.ftl" as layout/>
<#import "/layout/_list.ftl" as list/>
<#import "/layout/_rec.ftl" as rec/>
<#include "/layout/_component.ftl"/>

<#macro listIndex script header="">
    <#local _header>
    <style type="text/css">
        .side { position:absolute; left:0; top:60px; bottom:0; width:260px; overflow:auto; border-right: dashed 1px #cccccc}
        .main { position:absolute; left:270px; top:60px; right:0;}
    </style>
    <#if (header?length>0)>${header}</#if>
    </#local>
    <@list.doIndexLayout menuID="设置" script=script header=_header>
        <div class="side">
            左边
        </div>
        <div class="main">
            <#nested />
        </div>
    </@>
</#macro>

<#macro recIndex script header="">
    <#local _header>
    <style type="text/css">
        .side { position:absolute; left:0; top:60px; bottom:0; width:260px; overflow:auto; border-right: dashed 1px #cccccc}
        .main { position:absolute; left:270px; top:60px; right:0;}
    </style>
        <#if (header?length>0)>${header}</#if>
    </#local>

    <@rec.doIndexLayout menuID="设置" script=script header=_header>
        <div class="side">
            左边
        </div>
        <div class="main">
            <#nested />
        </div>
    </@>
</#macro>