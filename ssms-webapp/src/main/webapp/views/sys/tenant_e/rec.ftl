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
                    <li class="active"><a href="#">管理中心 ></a></li>
                    <li class="active"><a href="#">企业信息管理 ></a></li>
                    <li class="active"><a href="#">企业信息编辑</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@layout.toolbar class="navbar-fixed-top" outer="<br><br>">
<input type="button" class="btn" name="btn_save" value="保存"/>
<input type="button" class="btn" name="btn_del" value="删除"/>
<input type="button" class="btn" name="btn_back" value="返回"/>
</@layout.toolbar>

<div class="container">
    <@layout.form id="mainForm">
        <@layout.group title="基本信息">
            <@layout.row>
                <@layout.textbox name="C_NAME" title="企业名称" desc="企业的详细名称" />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_STATE" title="企业状态" desc="企业状态" readonly=true span=6 />
                <@layout.textbox name="S_PROVINCE" title="省份" desc="省份" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_CITY" title="地市" desc="地市" span=6 />
                <@layout.textbox name="S_COUNTY" title="县市" desc="县市" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_NUMBER" title="注册号" desc="注册号" span=6 />
                <@layout.textbox name="S_PROFESSION" title="所属专业" desc="所属专业" readonly=true span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_NATURE" title="企业性质" desc="企业性质" span=6 />
                <@layout.textbox name="N_SAFETY" title="专职安全管理员" desc="专职安全管理员" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="N_EMPLOYEE" title="企业员工总数" desc="企业员工总数" span=6 />
                <@layout.textbox name="N_INCOME" title="主营业务收入" desc="主营业务收入" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="N_ASSETS" title="固定资产" desc="固定资产" span=6 />
                <@layout.textbox name="N_SPECIAL" title="特种作业人员数" desc="特种作业人员数" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_SCOPE" title="营业范围" desc="营业范围" span=6 />
            </@layout.row>
        </@layout.group>

        <@layout.group title="联系方式">
            <@layout.row>
                <@layout.textbox name="C_ADDR" title="地址" desc="地址" />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_TEL" title="固话" desc="固话" readonly=true span=6 />
                <@layout.textbox name="C_EMAIL" title="电子邮件" desc="电子邮件" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_FAX" title="传真" desc="传真" span=6 />
                <@layout.textbox name="C_ZIP" title="邮政编码" desc="邮政编码" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_WEBSITE" title="企业网站" desc="企业网站" span=6 />
            </@layout.row>
        </@layout.group>

        <@layout.group title="企业概述">
            <@layout.editor name="C_SUMMARY" title="" desc="" />
        </@layout.group>

        <@layout.group title="企业达标情况">
            <@layout.row>
                <@layout.textbox name="S_LEVEL" title="达标等级" desc="达标等级" readonly=true span=6/>
                <@layout.textbox name="T_EXAMINE_NEXT" title="下次复审时间" desc="下次复审时间" readonly=true span=6/>
            </@layout.row>
        </@layout.group>
    </@layout.form>
</div>
</@layout.doLayout>