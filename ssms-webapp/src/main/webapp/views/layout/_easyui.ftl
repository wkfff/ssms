<#import "_vars.ftl" as vars/>

<#macro group title icon="">
<div class="easyui-panel" title="${title}" data-options="border:false, <#if (icon?length > 0)>iconCls: 'icon-${icon}</#if>'">
    <#nested />
</div>
</#macro>

<#macro toolbar id="">
<div id=${id} class="easyui-panel" style="border:0;background-color:#FAFAFA;padding:5px;">
    <#nested />
</div>
</#macro>

<#macro button title desc="" id="" click="" icon="" plain=true>
<#local options>
plain: ${plain?string}<#if (icon?length>0)>, iconCls: 'icon-${icon}'</#if>
</#local>
<#if (desc?length > 0)><#local desc=title/></#if>
<a id="${id}" href="#" class="easyui-linkbutton" data-options="${options}" onclick="${click}" title="${desc}">${title}</a>
</#macro>

<#macro form id>
<form id="${id}" method="post">
    <#nested />
</form>
</#macro>

<#macro hidden name>
<input type="hidden" name="${name}"/>
</#macro>

<#macro label title for="">
<label for="${for}">${title}</label>
</#macro>

<#macro textbox name id="" title="" must=false fit=false>
<#if (title?length>0)><@label title=title/></#if>
<#if (id?length>0)><#local _id=id/><#else><#local _id="${name}"/></#if>
<input class="easyui-textbox" id="${_id}" name="${name}" type="text" data-options="required:${must?string}, fit:${fit?string}"/>
</#macro>

<#macro textarea name id="" title="" must=false fit=false>
<#if (title?length>0)><@label title=title/></#if>
<#if (id?length>0)><#local _id=id/><#else><#local _id="${name}"/></#if>
<input class="easyui-textbox" id="${_id}" name="${name}" type="text" data-options="required:${must?string}, multiline:true, fit:${fit?string}"/>
</#macro>

<#macro td_textbox name title must=false span=4>
<td class="span2"><@label title=title for=name/></td>
<td class="span${span}"><@textbox name=name must=must fit=true/></td>
</#macro>

<#macro td_textarea title name must=false span=4>
<td class="span2"><@label title=title for=name/></td>
<td class="span${span}"><@textarea name=name must=must fit=true/></td>
</#macro>

<#macro dialog id okClick>
<div id="${id}" class="easyui-dialog" data-options="closed:true, modal:true, buttons:'#${id}-buttons'">
    <#nested />
</div>
<div id="${id}-buttons">
    <@button id="" title="保存" icon="ok" click="${okClick}" plain=false/>
    <@button id="" title="取消" icon="cancel" click="$('#${id}').dialog('close')" plain=false/>
</div>
</#macro>
<#--
<#macro table id toolbar>
<table id="${id}"></table>
<#assign COMPONENT_INIT_SCRIPTS in vars>
<script type="text/javascript">
    $(function () {
        $('${id}').datagrid({
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            striped: true,
            toolbar: '#${toolbar}',
            columns: [
                [
                    <#nested />
                ]
            ]
        });
    });
</script>
</#assign>
</#macro>
-->
