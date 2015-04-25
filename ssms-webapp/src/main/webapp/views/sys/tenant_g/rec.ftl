<#--缺少上传这个控件--> 
<#import "/s/home/settings.ftl" as layout/> <#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript">
    var setting = {
        sid : $url.getUrlParam("sid"),
        dataUrl : "rec.json",
        saveUrl : "save.json",
        delUrl : "del.json",
        editor : "C_SUMMARY"
    }

    $(document).ready(function() {
        $form.init(setting);
    });
</script>
</#assign>
 <@layout.recIndex script>

<!--导航栏-->
<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">管理中心 ></a></li>
                    <li class="active"><a href="#">政府机构管理 ></a></li>
                    <li class="active"><a href="#">政府机构编辑</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@layout.toolbar class="navbar-fixed-top" outer="<br><br>">
                <input type="button" class="btn" name="btn_save"
                    value="保存"> <input type="button" class="btn"
                    name="btn_del" value="删除"> <input
                    type="button" class="btn" name="btn_back" value="返回">
</@layout.toolbar>

<div class="container">
    <!--表单-->
    <@layout.form id="mainForm">
        <@layout.group title="基本信息">
            <@layout.row>
                <@layout.textbox name="C_NAME" title="单位名称" desc="单位名称" />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_PROVINCE" title="省" desc="省"  span=6 />
                <@layout.textbox name="S_CITY" title="市" desc="市" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_COUNTY" title="县" desc="县"  span=6 />
            </@layout.row>
        </@layout.group>
        <@layout.group title="联系方式">
            <@layout.row>
                <@layout.textbox name="C_ADDRE" title="通讯地址" desc="通讯地址"  />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_FAX" title="传真" desc="传真"  span=6 />
                <@layout.textbox name="C_TEL" title="固话" desc="固话" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_EMAIL" title="电子邮箱" desc="电子邮箱"  span=6 />
                <@layout.textbox name="C_ZIP" title="邮政编码" desc="邮政编码" span=6 />
            </@layout.row>
        </@layout.group>

    </@layout.form>
</div>
</@>
