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
        $('#btn_addfile').click(function () {
            $win.navigate("/sys/stdtmp_file/rec.html?&pid=${sid!}");
            return false;
        });
    });
</script>
</#assign>
<@layout.doLayout script=script header=header>
<div class="container">
    <@layout.form id="mainForm" title="[${C_NAME}]属性配置">
        <@layout.toolbar>
            <input type="button" class="btn" name="btn_save" value="保存">
            <#if R_SID!=0><input type="button" class="btn" name="btn_del" value="删除"></#if>
        </@layout.toolbar>
        <@layout.row>
            <@layout.textbox name="C_NAME" title="名称" desc="目录名称"/>
        </@layout.row>
        <@layout.row>
            <@layout.textarea name="C_DESC" title="描述" desc="目录描述"/>
        </@>
    </@layout.form>

    <@layout.group "[${C_NAME}]子类型配置">
        <@layout.toolbar>
            <input type="button" class="btn" id="btn_add" value="增加子分类">
        </@layout.toolbar>
        <@layout.table_repeat subitems; item>
            <td>${item.C_NAME}</td>
            <td width="70"><a href="subitem.html?sid=${item.SID}&pid=${sid!}">[编辑]</a></td>
        </@layout.table_repeat>
    </@layout.group>

    <@layout.group "[${C_NAME}]文件模板配置">
        <@layout.toolbar>
            <input type="button" class="btn" id="btn_addfile" value="增加子分类">
        </@layout.toolbar>
        <@layout.table_repeat fileitems; item>
            <td>${item.C_NAME}</td>
            <td width="70"><a href="/sys/stdtmp_file/rec.html?sid=${item.SID}&pid=${sid!}">[编辑]</a></td>
        </@layout.table_repeat>
    </@layout.group>
</div>
</@layout.doLayout>