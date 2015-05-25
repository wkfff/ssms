<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    $(function () {
        $('#save').click(function () {
            texteditor.getEditor(document.getElementById("_editor")).save();
        });
    })
</script>
</#assign>
<@layout.doLayout script>
<input id="save" type="button" class="btn" value="保存"/>
<@layout.texteditor table="demoTable" field="demoTable" sid=0/>
</@layout.doLayout>