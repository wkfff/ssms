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
                    <li class="active"><a href="#">评审机构管理 ></a></li>
                    <li class="active"><a href="#">评审机构编辑</a></li>
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
                <@layout.textbox name="C_NUMBER" title="注册号" desc="注册号"  span=6 />
                <@layout.textbox name="C_ORG" title="确定评审业务机关" desc="确定评审业务机关" span=6 readonly=true/>
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_USER" title="用户名" desc="用户名"  span=6 />
                <@layout.textbox name="C_ORG" title="确定评审业务机关" desc="确定评审业务机关" span=6 readonly=true/>
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_BSINESS" title="评审业务范围" desc="评审业务范围"  span=6 readonly=true/>
                 <#--TODO缺少评审业务范围这个字段-->
                <@layout.textbox name="S_LEVEL" title="评审专业级别" desc="评审专业级别" span=6 readonly=true/>
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="N_FULLTIME" title="专职人员" desc="专职人员"  span=6 />
            </@layout.row>
        </@layout.group>

        <@layout.group title="联系方式">
             <@layout.row>
                <@layout.textbox name="C_EMAIL" title="电子邮箱" desc="电子邮箱"  span=6 />
                <@layout.textbox name="C_TEL" title="办公电话" desc="办公电话" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_FAX" title="传真" desc="传真"  span=6 />
                <@layout.textbox name="S_PROVINCE" title="省份" desc="省份" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_CITY" title="地市" desc="地市"  span=6 />
                <@layout.textbox name="S_COUNTY" title="县市" desc="县市" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_ZIP" title="邮政编码" desc="邮政编码"  span=6 />
                <@layout.textbox name="C_ADDR" title="地址" desc="地址" span=6 />
            </@layout.row>
        </@layout.group>

    </@layout.form>
</div>
</@>
