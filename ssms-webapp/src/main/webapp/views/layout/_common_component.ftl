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