<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    window.location.href="/e/stdtmp_file_${P_TMPFILE}/rec.html?sid=${R_TMPFILE}&backURL=${Referer!}"
</script>
</#assign>
<@layout.doLayout script=script>
</@>