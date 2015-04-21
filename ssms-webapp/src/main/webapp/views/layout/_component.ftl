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
<textarea id="${id}_editor" name="${name}"></textarea>
</@vars.component_base>
</#macro>

<#macro textbox name title="" desc="" readonly=false span=12>
<div class="control-group span${span}">
    <#if (title?length>0)>
        <label class="control-label" for="${name}">${title}:</label>
    </#if>
    <div class="controls">
        <input type="text" placeholder="" class="ui-text input-x<#if span==12>x</#if>large" id="${name}" name="${name}" <#if readonly>readonly="readonly"</#if>>
        <#if (desc?length>0)>
        <p class="help-block">${desc}</p>
        </#if>
    </div>
</div>
</#macro>

<#macro textarea name title="" desc="" span=12>
<div class="control-group span${span}">
    <#if (title?length>0)>
    <label class="control-label" for="${name}">${title}:</label>
    </#if>
    <div class="controls">
        <textarea id="${name}" name="${name}" rows=5> </textarea>
        <#if (desc?length>0)>
        <p class="help-block">${desc}</p>
        </#if>
    </div>
</div>
</#macro>

<#macro editor name title="" desc="" span=12>
<#assign ROW_COUNT=vars.ROW_COUNT+span in vars/>
<div class="control-group span${span}">
    <#if (title?length>0)>
    <label class="control-label" for="${name}">${title}:</label>
    </#if>
    <div class="controls">
        <textarea id="${name}" name="${name}" rows=5 class="ui-editor"> </textarea>
        <#if (desc?length>0)>
        <p class="help-block">${desc}</p>
        </#if>
    </div>
</div>
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