<!--suppress HtmlUnknownTarget -->
<#import "/layout/_rec.ftl" as layout/>
<#assign header>
<style type="text/css">
    body {
        margin-top: 0;
    }
</style>
</#assign>
<#assign script>
<script type="text/javascript">
    var setting = {
        sid: $url.getUrlParam("sid"),
        dataUrl: "rec.json",
        saveUrl: "save.json",
        delUrl: "del.json"
    };

    $(document).ready(function () {
        $form.init(setting);
        $('#btn_add').click(function () {
            $win.navigate("subitem.html?pid=${sid!}");
            return false;
        });
    });
</script>
</#assign>
<@layout.doLayout script=script header=header>
<div class="container">
    <@layout.form id="mainForm" title="[${C_NAME}]属性配置">
        <@layout.textbox "C_NAME" "名称" "目录名称"/>
        <@layout.textarea "C_DESC" "描述" "目录描述"/>
        <@layout.toolbar>
            <input type="button" class="btn" name="btn_save" value="保存">
            <#if R_SID!=0>
                <input type="button" class="btn" name="btn_del" value="删除">
            </#if>
        </@layout.toolbar>
    </@layout.form>

    <@layout.form id="subForm" title="[${C_NAME}]子类型配置">
        <table style="table-layout: fixed" width="100%">
            <tbody>
                <#list subitems as item>
                <tr>
                    <td>${item.C_NAME}</td>
                    <td width="70"><a href="subitem.html?sid=${item.SID}&pid=${sid!}">[编辑]</a></td>
                </tr>
                </#list>
            </tbody>
        </table>
        <@layout.toolbar>
            <input type="button" class="btn" id="btn_add" value="增加子分类">
        </@layout.toolbar>
    </@layout.form>
</div>
</@layout.doLayout>