<#macro upload module sid id="" readonly=false>
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
</#macro>

<#macro texteditor table field sid name="" id="" readonly=false>
<textarea id="${id}_editor" name="${name}"></textarea>
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
</#macro>