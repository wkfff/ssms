<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    window.location.href="/e/stdtmp_file_${P_TMPFILE}/rec?sid=${R_TMPFILE}"
</script>
</#assign>
<@layout.doLayout script=script>
</@>