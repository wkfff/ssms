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
                    <li class="active"><a href="#">培训教育></a></li>
                    <li class="active"><a href="#">教育培训管理></a></li>
                    <li class="active"><a href="#">年安全教育培训计划</a></li>
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
                <@layout.textbox name="S_CREATE" title="创建人" desc="创建人" span=6  readonly=true/>
                <@layout.textbox name="T_CREATE" title="创建日期" desc="创建日期" span=6  readonly=true/>
            </@layout.row>
             <@layout.row>
                <@layout.textbox name="S_UPDATE" title="更新人" desc="更新人" span=6 readonly=true/>
                <@layout.textbox name="T_UPDATE" title="更新日期" desc="更新日期" span=6  readonly=true/>
            </@layout.row>
        </@layout.group>
    </@layout.form>
</div>
</@layout.doLayout>