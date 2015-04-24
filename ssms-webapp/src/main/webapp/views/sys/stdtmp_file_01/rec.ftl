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
                    <li class="active"><a href="#">安全生产目标管理制度</a></li>
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
                <@layout.textbox name="C_NAME" title="文件名称" desc="文件名称" />
            </@layout.row>
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                    <!-- Text input-->
                      <label class="control-label" for="B_CONTROL">是否受控:</label>
                      <div class="controls">
                        <input type="radio" placeholder="" class="input-xlarge"   name="B_CONTROL" id="B_CONTROL">受控
                        <input type="radio" placeholder="" class="input-xlarge"   name="B_CONTROL" id="B_CONTROL">非受控
                        <p class="help-block">是否受控</p>
                      </div>
                    </div>
                </div>
            </div>
           <@layout.row>
                <@layout.textbox name="C_NUMBER" title="文件编号" desc="文件编号" span=6 />
                 <@layout.textbox name="C_DEPT_01" title="执行部门" desc="执行部门" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_DEPT_02" title="监督部门" desc="监督部门" span=6 />
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
        <@layout.group title="编制信息">
          <@layout.row>
                <@layout.textbox name="T_DATE_01" title="编制日期" desc="编制日期" span=6  readonly=true/>
                <@layout.textbox name="T_DATE_02" title="审核日期" desc="审核日期" span=6  />
           </@layout.row>
           <@layout.row>
                <@layout.textbox name="T_DATE_03" title="评审日期" desc="评审日期" span=6 />
           </@layout.row>
        </@layout.group>
    </@layout.form>
</div>
</@layout.doLayout>