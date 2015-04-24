<#import "/s/home/settings.ftl" as layout/>
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
<@layout.recIndex script>
<!--导航栏-->
<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">管理中心 ></a></li>
                    <li class="active"><a href="#">政府用户管理 ></a></li>
                    <li class="active"><a href="#">政府用户信息编辑</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@layout.toolbar class="navbar-fixed-top">
<input type="button" class="btn" name="btn_save" value="保存"/>
<input type="button" class="btn" name="btn_del" value="删除"/>
<input type="button" class="btn" name="btn_back" value="返回"/>
</@layout.toolbar>

<div class="container">
    <@layout.form id="mainForm">
        <@layout.group title="基本信息">
            <@layout.row>
                <@layout.textbox name="C_NAME" title="用户名" desc="用户名" />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_TENANT" title="所属政府机构" desc="所属政府机构"  span=6 />
                <@layout.textbox name="T_BIRTH" title="出生日期" desc="出生日期" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_POSITION" title="职务" desc="职务" span=6 />
                <@layout.textbox name="S_STATE" title="状态" desc="状态" readonly=true span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.radio name="S_SEX" title="性别" desc="性别" span=6 />
            </@layout.row>
        </@layout.group>

        <@layout.group title="联系方式">
            <@layout.row>
                <@layout.textbox name="C_TEL" title="固话" desc="固话"  span=6 />
                <@layout.textbox name="C_MOBILE" title="手机号" desc="手机号" span=6 />
            </@layout.row>
        </@layout.group>

        <@layout.group title="学历">
            <@layout.row>
                <@layout.textbox name="C_SCHOOL" title="毕业学校" desc="毕业学校" span=6/>
                <@layout.textbox name="S_EDUCATION" title="学历" desc="学历" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_DEGREE" title="学位" desc="学位" span=6/>
            </@layout.row>
        </@layout.group>

        <@layout.group title="工作简历">
            <@layout.editor name="C_RESUME" title="" desc="" />
        </@layout.group>

        <@layout.group title="密码设置">
            <@layout.row>
                <@layout.textbox name="C_PASSWD" title="密码" desc="密码"  span=6/>
                <@layout.textbox name="C_PASSWD" title="重复密码" desc="重复密码"  span=6/>
            </@layout.row>
            <input type="button" class="btn" name="btn_reset" value="快速重置密码"/>
        </@layout.group>
    </@layout.form>
</div>
</@>