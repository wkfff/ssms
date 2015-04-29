<#--缺少上传这个控件-->
<#import "/s/home/settings.ftl" as layout/>
<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript">
    var setting = {
            sid:$url.getUrlParam("sid"),
            dataUrl:"rec.json",
            delUrl : "del.json",
            saveUrl:"save.json"
    }
    $(document).ready(function(){
        $form.init(setting);
    });
    function svaeRInfo(){
        if(($("#C_PASSWD").val())!=(($("#C_PASSWD1").val()))){
            alert("两次输入密码一致");
             return;
        }
        $("#C_PASSWD").val($.md5($("#C_PASSWD").val()));
        $("#C_PASSWD1").val($.md5($("#C_PASSWD1").val()));
        $("#C_PASSWD1").attr("disabled", "true")
        $form.doSave();
    }
</script>
</#assign>
<@layout.recIndex script>

<!--导航栏-->
<div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <div class="container">
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active">
                <a href="#">首页 ></a>
              </li>
              <li class="active">
                <a href="#">管理中心 ></a>
              </li>
              <li class="active">
                <a href="#">评审用户管理 ></a>
              </li>
              <li class="active">
                <a href="#">评审用户编辑</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
</div>

<@layout.toolbar class="navbar-fixed-top" outer="<br><br>">
               <input type="button" class="btn" onclick="svaeRInfo()" value="保存">
               <input type="button" class="btn" name="btn_del" value="删除"> 
               <input type="button" class="btn" name="btn_back" value="返回">
</@layout.toolbar>

<div class="container">
<!--表单-->
    <@layout.form id="mainForm">
        <@layout.group title="基本信息">
            <@layout.row>
                <@layout.textbox name="C_NAME" title="用户名" desc="用户名" />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_POSITION" title="职务" desc="职务"  span=6 />
                <@layout.textbox name="T_BIRTH" title="出生日期" desc="出生日期" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_CERT" title="聘请证书编号" desc="聘请证书编号"  span=6 />
                <@layout.textbox name="C_INSTUDRY" title="行业" desc="行业" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.radio name="S_SEX" title="性别" desc="性别"  span=6 />
                <@layout.textbox name="S_PROFESSION" title="专业" desc="专业" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="N_YEAR" title="现从事专业年限" desc="现从事专业年限"  span=6 />
                <@layout.textbox name="S_PROFESSION_L" title="所学专业" desc="所学专业" span=6 />
            </@layout.row>
        </@layout.group>
        
        <@layout.group title="联系方式">
            <@layout.row>
                <@layout.textbox name="C_ADDRE" title="通讯地址" desc="通讯地址" />
                <#--TODO缺少通讯地址字段-->
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_COMPANY" title="工作单位及部门" desc="工作单位及部门"  span=6 />
                <#--TODO缺少工作单位及部门字段-->
                <@layout.textbox name="C_POSTCODS" title="邮编" desc="邮编" span=6 />
                 <#--TODO缺少邮编字段-->
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_TEL" title="固话" desc="固话" span=6 />
            </@layout.row>
        </@layout.group>
 
        <@layout.group title="学历">
            <@layout.row>
                <@layout.textbox name="C_SCHOOL" title="毕业学校" desc="毕业学校"  span=6 />
                <@layout.textbox name="S_DEGREE" title="学位" desc="学位" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="S_EDUCATION" title="学历" desc="学历"  span=6 />
            </@layout.row>
        </@layout.group>

        <@layout.group title="工作简历及主要成绩">
                <@layout.editor name="C_RESUME" title="" desc="" />
        </@layout.group>
        
        <@layout.group title="密码设置">
           <@layout.row>
               <@layout.textbox name="C_PASSWD" title="密码" desc="密码"  span=6/>
               <@layout.textbox name="C_PASSWD1" title="重复密码" desc="重复密码"  span=6/>
           </@layout.row>
       <input type="button" class="btn" name="btn_reset" value="快速重置密码"/>
      </@layout.group>
        <input type="hidden" name="R_SID" value="${tenant.SID}"/>
        <input type="hidden" name="S_NAME" value="${tenant.C_NAME}"/>
    </@layout.form>
</div>
</@>
