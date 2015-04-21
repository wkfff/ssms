<#import "/layout/_rec.ftl" as layout/>

<#assign script>
<script type="text/javascript">
    var setting = {
        sid: $url.getUrlParam("sid"),
        dataUrl: "rec.json",
        saveUrl: "save.json",
        delUrl: "del.json",
        backUrl: '${referer!}' // 设置回退的URL。referer是请求头，里面含有请求当前页面的页面地址。
    };

    $(document).ready(function () {
        $form.init(setting);
    });
</script>
<style type="text/css">
    body {
        margin: 0;
    }
</style>
</#assign>
<@layout.doLayout script>

<#--工具栏-->
    <@layout.toolbar class="navbar-fixed-top">
    <input type="button" class="btn" name="btn_save" value="保存">
    <input type="button" class="btn" name="btn_del" value="删除">
    <input type="button" class="btn" name="btn_back" value="返回">
    </@layout.toolbar>


<div class="container">
    <@layout.form id="mainForm" title="基本信息">
        <@layout.row>
            <@layout.textbox name="C_NAME" title="名称" desc="目录名称" span=6/>
            <@layout.textbox name="" title="父类型" desc="" value=C_NAME readonly=true span=6/>
        </@layout.row>
        <@layout.row>
            <@layout.textarea "C_DESC" "描述" "目录描述"/>
        </@layout.row>
        <input type="hidden" name="R_SID" value="${pid!}"/>
        <input type="hidden" name="P_PROFESSION" value="${P_PROFESSION!}"/>
        <input type="hidden" name="S_PROFESSION" value="${S_PROFESSION!}"/>
    </@layout.form>
</div>
</@layout.doLayout>