<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">

</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" style="padding: 10px">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save">保存</a>
    <a class="easyui-linkbutton" onclick="window.location.href='${referer}&backURL=${backURL}'" plain="true" iconCls="icon-undo">返回</a>
    <div class="hr"></div>

    <table>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>
</@layout.doLayout>