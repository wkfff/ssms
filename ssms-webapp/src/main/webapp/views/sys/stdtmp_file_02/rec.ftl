<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
        sid: $url.getUrlParam("sid"),
        dataUrl: "rec.json",
        saveUrl: "save.json",
        delUrl: "del.json",
        publishUrl:"publish.json"
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
                    <li class="active"><a href="#">目标></a></li>
                    <li class="active"><a href="#">公司总体安全生产目标的通知</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@layout.toolbar class="navbar-fixed-top" outer="<br><br>">
<input type="button" class="btn" name="btn_save" value="保存"/>
<input type="button" class="btn" name="btn_publish" value="发布"/>
<input type="button" class="btn" name="btn_back" value="返回"/>
</@layout.toolbar>

<div class="container">
    <@layout.form id="mainForm">
         <@layout.group title="概要">
            <@layout.row>
                <@layout.textbox name="C_NAME" title="通知标题" desc="通知标题" />
            </@layout.row>
           <@layout.row>
                <@layout.textbox name="C_NUMBER" title="通知编号" desc="通知编号" span=6 />
            </@layout.row>
        </@layout.group>
        <@layout.group title="正文">
           <@layout.doLayout script>
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
        <@layout.group title="发布信息">
          <@layout.row>
                <@layout.textbox name="C_DEPT_01" title="发布部门" desc="发布部门" span=6  />
                <@layout.textbox name="T_DATE_01" title="发布日期" desc="发布日期" span=6  readonly=true/>
           </@layout.row>
           <@layout.row>
                <@layout.textbox name="C_DEPT_02" title="主送部门" desc="主送部门" span=6 />
                <@layout.textbox name="C_DEPT_03" title="抄送部门" desc="抄送部门" span=6 />
           </@layout.row>
        </@layout.group>
    </@layout.form>
</div>
</@layout.doLayout>