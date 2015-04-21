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

    <@layout.toolbar class="navbar-fixed-top">
    <input type="button" class="btn" name="btn_save" value="保存">
    <input type="button" class="btn" name="btn_del" value="删除">
    <input type="button" class="btn" name="btn_back" value="返回">
    </@>

<div class="container">
    <@layout.form id="mainForm" title="基本信息">
        <@layout.row>
            <@layout.textbox name="C_NAME" title="名称" desc="目录名称" span=6/>
            <@layout.textbox name="" title="所属分类" desc="" value=folder.C_NAME readonly=true span=6/>
        </@layout.row>
        <@layout.row>
            <@layout.textarea "C_DESC" "描述" "目录描述"/>
        </@layout.row>
        <@layout.row>
            <@layout.textbox name="N_CYCLE" title="更新周期" desc="更新周期配置" span=6/>
            <@layout.parameter keyField="P_CYCLE" valueField="S_CYCLE" title="周期单位" items=_S_CYCLE_ span=6/>
        </@layout.row>
        <@layout.row>
            <div class="control-group span12">
                <div class="controls ui-checkbox">
                    <label class="checkbox inline">
                        <input type="checkbox" value="1" name="B_REMIND"> 是否提醒
                    </label>
                </div>
            </div>
        </@layout.row>
        <@layout.row>
            <@layout.textbox name="S_TMPFILE" title="模板文件" desc="配置模板文件" readonly=true/>
        </@layout.row>
        <@layout.row>
            <@layout.editor name="C_EXPLAIN" title="政策解读" desc="配置政策解读信息" />
        </@layout.row>
        <input type="hidden" name="R_SID" value="${pid!}"/>
    </@layout.form>
</div>
</@layout.doLayout>