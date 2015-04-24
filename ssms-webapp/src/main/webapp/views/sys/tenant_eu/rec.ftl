<#--缺少上传这个控件-->
<#import "/s/home/settings.ftl" as layout/>
<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript">
    var setting = {
            sid:$url.getUrlParam("sid"),
            dataUrl:"rec.json",
            saveUrl:"save.json",
            delUrl: "del.json",
            editor: "C_SUMMARY"
    }
    $(document).ready(function(){
        $form.init(setting);
    });
     function svaeInfo(){
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
                <a href="#">企业用户管理 ></a>
              </li>
              <li class="active">
                <a href="#">企业用户编辑</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
</div>

<@layout.toolbar>
               <input type="button" class="btn"  onclick="svaeInfo()" value="保存">
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
               <@layout.textbox name="T_BIRTH" title="企业状态" desc="出生日期" span=6 />
               <@layout.textbox name="C_CARD" title="身份证" desc="身份证" span=6 />
       </@layout.row>
       <@layout.row>
               <@layout.radio name="S_SEX" title="性别" desc="性别" span=6 />
               <@layout.textbox name="C_DEPT" title="部门" desc="部门" span=6 />
       </@layout.row>
       <@layout.row>
               <@layout.textbox name="C_POSITION" title="职位" desc="职位" span=6 />
       </@layout.row>
     </@layout.group>
     
     <@layout.group title="联系方式">
       <@layout.row>
               <@layout.textbox name="C_MOBILE" title="手机" desc="手机" span=6 />
               <@layout.textbox name="C_TEL" title="固话" desc="固话" span=6 />
       </@layout.row>
       <@layout.row>
               <@layout.textbox name="C_EMAIL" title="电子邮箱" desc="电子邮箱"/>
       </@layout.row>
     </@layout.group>
     
     <@layout.group title="学历">
       <@layout.row>
               <@layout.textbox name="C_SCHOOL" title="毕业学校" desc="毕业学校" span=6 />
               <@layout.textbox name="S_DEGREE" title="学位" desc="学位" span=6 />
       </@layout.row>
       <@layout.row>
               <@layout.textbox name="S_EDUCATION" title="学位" desc="学历" span=6 />
       </@layout.row>
     </@layout.group>
     <@layout.group title="密码设置">
       <@layout.row>
               <@layout.textbox name="C_PASSWD" title="密码" desc="密码"  span=6/>
               <@layout.textbox name="C_PASSWD1" title="重复密码" desc="重复密码"  span=6/>
       </@layout.row>
       <input type="button" class="btn" name="btn_reset" value="快速重置密码"/>
     </@layout.group>
    
</@layout.form>
</div>
</@>
