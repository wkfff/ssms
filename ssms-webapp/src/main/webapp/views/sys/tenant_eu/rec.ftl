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
<div class="navbar navbar-inverse navbar-fixed-top">
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

<div class="navbar navbar-default navbar-fixed-top"><br><br>
      <div class="navbar-inner">
        <div class="container">
            <div class="nav pull-right">
               <input type="button" class="btn"  onclick="svaeInfo()" value="保存">
               <input type="button" class="btn" name="btn_del" value="删除">
               <input type="button" class="btn" name="btn_back" value="返回">
            </div>
        </div>
      </div>
</div>

<div class="container">
<!--表单-->
<form class="form-horizontal" id="mainForm">
        <fieldset>
            <div id="legend">
            <legend>基本信息</legend>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="C_NAME">用户名:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xxlarge" id="C_NAME" name="C_NAME">
                        <p class="help-block">用户名</p>
                      </div>
                    </div>
                </div>
            </div>
    
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="T_BIRTH">出生日期:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="T_BIRTH" name="T_BIRTH">
                            <p class="help-block">出生日期</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_CARD">身份证:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_CARD" name="C_CARD">
                            <p class="help-block">身份证</p>
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="S_SEX">性别:</label>
                        <div class="controls">
                            <input type="radio" placeholder="" class="input-xlarge" id="S_SEX" name="S_SEX" value="男">男
                            <input type="radio" placeholder="" class="input-xlarge"  id="S_SEX" name="S_SEX" value="女">女
                            <p class="help-block">性别</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_DEPT">部门:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_DEPT" name="C_DEPT">
                            <p class="help-block">部门</p>
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                    <!-- Text input-->
                      <label class="control-label" for="C_POSITION">职位:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xlarge" id="C_POSITION" name="C_POSITION">
                        <p class="help-block">职位</p>
                      </div>
                    </div>
                </div>
            </div>

            </fieldset>
            <fieldset>
            <div id="legend">
            <legend>联系方式</legend>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_MOBILE">手机:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_MOBILE" name="C_MOBILE">
                            <p class="help-block">手机</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_TEL">固话:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_TEL" name="C_TEL">
                            <p class="help-block">固话</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12">
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="C_EMAIL">电子邮箱:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xxlarge" id="C_EMAIL" name="C_EMAIL">
                        <p class="help-block">电子邮箱</p>
                      </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset>
        <div id="legend">
        <legend>学历</legend>
        </div>
        <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_SCHOOL">毕业学校:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_SCHOOL" name="C_SCHOOL">
                            <p class="help-block">毕业学校</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="S_DEGREE">学位:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="S_DEGREE" name="S_DEGREE">
                            <p class="help-block">学位</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="S_EDUCATION">学历:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="S_EDUCATION" name="S_EDUCATION">
                            <p class="help-block">学历</p>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>

        <fieldset>
        <div id="legend">
        <legend>密码设置</legend>
        </div>
        <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_PASSWD">密码:</label>
                        <div class="controls">
                            <input type="password" placeholder="" class="input-xlarge" id="C_PASSWD" name="C_PASSWD">
                            <p class="help-block">密码</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_PASSWD1">重复密码:</label>
                        <div class="controls">
                            <input type="password" placeholder="" class="input-xlarge" id="C_PASSWD1" name="C_PASSWD1">
                            <p class="help-block">重复密码</p>
                        </div>
                    </div>
                </div>
                <div class="nav pull-right">
                <input type="button" class="btn" name="btn_reset" value="快速重置密码">
                <#--TODO功能没写-->
                </div>
            </div>
        </fieldset>
</form>
</div>
</@>