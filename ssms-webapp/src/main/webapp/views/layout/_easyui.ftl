<#import "_vars.ftl" as vars/>

<#macro group title>
<div class="easyui-panel" title="${title}" data-options="border:false">
    <#nested />
</div>
</#macro>

<#macro toolbar id="">
<div id=${id} class="easyui-panel" style="border:0;background-color:#FAFAFA;padding:5px;">
    <#nested />
</div>
</#macro>

<#macro button id title click icon="" plain="true">
<#local options>
plain: ${plain}<#if (icon?length>0)>, iconCls: 'icon-${icon}'</#if>
</#local>
<a id="${id}" href="#" class="easyui-linkbutton" data-options="${options}" onclick="${click}">${title}</a>
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

<#macro textbox name must=false>
<input class="easyui-textbox" id="${name}" name="${name}" type="text" data-options="required:${must?string}, fit:true"/>
</#macro>

<#macro textarea name must=false>
<input class="easyui-textbox" id="${name}" name="${name}" type="text" data-options="required:${must?string}, multiline:true, fit:true"/>
</#macro>

<#macro td_textbox name title must=false span=4>
<td class="span2"><@label title=title for=name/></td>
<td class="span${span}"><@textbox name=name must=must/></td>
</#macro>

<#macro td_textarea title name must=false span=4>
<td class="span2"><@label title=title for=name/></td>
<td class="span${span}"><@textarea name=name must=must/></td>
</#macro>

<#macro dialog id okClick>
<div id="${id}" class="easyui-dialog" data-options="closed:true, modal:true, buttons:'#${id}-buttons'">
    <#nested />
</div>
<div id="${id}-buttons">
    <@button id="" title="保存" icon="ok" click="${okClick}"/>
    <@button id="" title="取消" icon="cancel" click="$('#${id}').dialog('close')"/>
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
