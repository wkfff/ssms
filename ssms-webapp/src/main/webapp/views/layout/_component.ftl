<!--suppress HtmlFormInputWithoutLabel -->
<#import "_vars.ftl" as vars />

<#--
    上传组件
    module:     模块名称
    sid:        表单SID
    id:         组件ID
    readonly:   是否只读
-->
<#macro upload module sid id="" readonly=false>
<#--组件脚本-->
<#local script>
<script type="text/javascript">
    $(function () {
        new Uploader({
            el: {
                container: document.getElementById("${id}_container"),
                list: document.getElementById("${id}_filelist"),
                selectButtion: document.getElementById("${id}_pickfiles"),
                uploadButtion: document.getElementById("${id}_uploadfiles"),
                console: document.getElementById("${id}_console")
            },
            module: "${module}",
            sid: "${sid}"
        }).init();
    });
</script>
</#local>
<#--定义组件内容-->
<@vars.component_base script>
<div id="${id}_filelist">Your browser doesn't have Flash, Silverlight or HTML5 support.</div>
<br/>
<#if !readonly>
<div id="${id}_container">
    <a id="${id}_pickfiles" href="javascript:;">[选择文件]</a>
    <a id="${id}_uploadfiles" href="javascript:;">[上传文件]</a>
</div>
<br/>
<pre id="${id}_console"></pre>
</#if>
</@vars.component_base>
</#macro>


<#--
    文本编辑器
    table:      对应的表
    field:      对应的字段
    sid:        sid
    name:       组件的name
    id:         组件的id
    readonly:   是否只读
-->
<#macro texteditor table field sid name="" id="" readonly=false>
<#local script>
<script type="text/javascript">
    $(function () {
        var editor = new texteditor({
            el: document.getElementById('${id}_editor'),
            table: "${table}",
            field: "${field}",
            sid: "${sid}",
        });
        editor.init();
    })
</script>
</#local>
<@vars.component_base script>
<textarea id="${id}_editor" name="${name}" style="width: 100%"></textarea>
</@vars.component_base>
</#macro>

<#--
    菜单项
-->
<#macro menu items>
<ul class="js-component-tab tz0 nui-tabs" id="tabs">
    <#list items as item>
        <#local class>nui-tabs-item<#if item.title=vars.SELECTED_MENU>-selected</#if></#local>
        <li value='0' class='js-component-tabitem tA0 oZ0 ${class}' title='${item.title}'>
            <div class='kA0'></div>
            <div class='mE0'></div>
            <a href="${item.url}">
                <div class='nui-tabs-item-text'>${item.title}</div>
            </a>
        </li>
    </#list>
</ul>
</#macro>


<#macro _edit_component name title="" desc="" span=12>
<div class="control-group <#if (span>0)>span${span}</#if>">
    <#if (title?length>0)>
        <label class="control-label" for="${name}">${title}:</label>
    </#if>
    <div <#if (title?length>0)>class="controls"</#if>>
        <#nested />
        <#if (desc?length>0)>
            <p class="help-block">${desc}</p>
        </#if>
    </div>
</div>
</#macro>

<#macro textbox name title="" desc="" value="" readonly=false span=12>
<@_edit_component name title desc span>
<input type="text" placeholder="" class="ui-text input-x<#if (span>6)>x</#if>large" id="${name}" name="${name}" <#if readonly>readonly="readonly"</#if> value="${value}">
</@_edit_component>
</#macro>

<#macro textarea name title="" desc="" span=12>
<@_edit_component name title desc span>
<textarea id="${name}" name="${name}" rows=5 class="input-x<#if (span>6)>x</#if>large"> </textarea>
</@_edit_component>
</#macro>

<#macro editor name title="" desc="" span=12 rows=10>
<@_edit_component name title desc span>
<textarea id="${name}" name="${name}" rows=${rows} class="ui-editor" style="width:100%;"></textarea>
</@_edit_component>
</#macro>

<#macro radio name title="" desc="" span=12>
<@_edit_component name title desc span>
<input type="radio" placeholder="" class="input-xlarge" id="${name}" name="${name}" value="男">男
<input type="radio" placeholder="" class="input-xlarge" id="${name}" name="${name}" value="女">女
</@_edit_component>
</#macro>

<#macro parameter keyField valueField src="" items=[] title="" desc="" span=12>
<@_edit_component keyField title desc span>
<input type="hidden" id="${valueField}" name="${valueField}"/>
<select id="${keyField}" class="ui-parameter input-x<#if (span>6)>x</#if>large" src="${src}">
    <option value=""></option>
<#list items as Parameter>
    <option value=${Parameter.key}>${Parameter.value}</option>
</#list>
</select>
</@>
</#macro>

<#macro toolbar class="" outer="">
<div class="navbar navbar-default ${class}">${outer}
    <div class="navbar-inner">
        <div class="container">
            <div class="nav pull-right">
                <#nested />
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro form id title="">
<form class="form-horizontal" id="${id}">
<#if (title?length>0)>
    <@group title>
        <#nested />
    </@group>
<#else>
    <#nested />
</#if>
</form>
</#macro>

<#macro group title>
<fieldset>
    <legend>${title}</legend>
    <#nested />
</fieldset>
</#macro>

<#macro row>
<div class="row-fluid">
    <#nested />
</div>
</#macro>

<#macro table_repeat items>
<table style="table-layout: fixed" width="100%">
    <tbody>
        <#list items as item>
        <tr>
            <#nested item/>
        </tr>
        </#list>
    </tbody>
</table>
</#macro>

<#--日期-->
<#macro date name title="" desc="" value="" readonly=false span=6>
<@_edit_component name title desc span>
<input type="text" placeholder="" class="ui-date input-x<#if span==12>x</#if>large" id="${name}" name="${name}" <#if readonly>readonly="readonly"</#if> value="${value}">
</@_edit_component>
</#macro>

<#--日期时间-->
<#macro datetime name title="" desc="" value="" readonly=false span=6>
<@_edit_component name title desc span>
<input type="text" placeholder="" class="ui-datetime input-x<#if span==12>x</#if>large" id="${name}" name="${name}" <#if readonly>readonly="readonly"</#if> value="${value}">
</@_edit_component>
</#macro>

<#--翻页-->
<#macro pagingbar>
<div class="paging nav-bar">
    <form class="form-search">
        <label class="checkbox"> 每页显示 <select
            name="_pageSize" class="input-mini">
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="30">30</option>
                <option value="50">50</option>
        </select> 共<span name="_recCount" class="navbar-text"></span>条,<span
            name="_pageCount" class="navbar-text"></span>页 <input
            type="button" name="btn_first" value="首页"
            class="btn navbar-btn"> <input type="button"
            name="btn_prev" value="上页" class="btn"> <input
            type="text" name="_pageIndex"
            class="input-mini navbar-text" value="1" /> <input
            type="button" name="btn_next" value="下页" class="btn">
            <input type="button" name="btn_last" value="末页"
            class="btn">
        </label>
    </form>
</div>
</#macro>

