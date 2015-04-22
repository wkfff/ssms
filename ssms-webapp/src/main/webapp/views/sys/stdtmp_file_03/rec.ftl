<#import "/layout/_rec.ftl" as layout/>
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
    });
</script>
</#assign>
<@layout.doLayout script>
<!--导航栏-->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">达标创建 ></a></li>
                    <li class="active"><a href="#">安全生产目标></a></li>
                    <li class="active"><a href="#">监测与考核></a></li>
                    <li class="active"><a href="#">安全生产表目标与指标讨论调查</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@layout.toolbar class="navbar-fixed-top" outer="<br><br>">
<input type="button" class="btn" name="btn_save" value="保存"/>
<input type="button" class="btn" name="btn_back" value="返回"/>
</@layout.toolbar>

<div class="container">
    <@layout.form id="mainForm">
         <@layout.group title="概要">
            <@layout.row>
                <@layout.textbox name="C_NAME" title="文件名称" desc="文件名称" />
            </@layout.row>
           <@layout.row>
                <@layout.textbox name="T_CREATE" title="创建日期" desc="创建日期" span=6  readonly=true/>
                <@layout.textbox name="T_UPDATE" title="修改日期" desc="修改日期" span=6  readonly=true/>
            </@layout.row>
        </@layout.group>
        <@layout.group title="正文">
           <@layout.doLayout script>
               <input id="download" type="button" class="btn" value="下载"/>
               <input id="import_template" type="button" class="btn" value="导入模版"/>
               <input id="open_template" type="button" class="btn" value="打开模版"/>
           <@layout.texteditor table="demoTable" field="demoTable" sid=0/>
           </@layout.doLayout>
        </@layout.group>
        <@layout.group title="政策解读">
          <@layout.row>
            <#if fileInfo??>
            <div>${fileInfo.C_EXPLAIN}</div>
            </#if>
          </@layout.row>
        </@layout.group>
    </@layout.form>
</div>
</@layout.doLayout>