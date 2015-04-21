<#--缺少上传这个控件-->
<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
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
</script>
</#assign>
<@layout.doLayout script>

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
               <input type="button" class="btn" name="btn_save" value="保存">
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
                      <label class="control-label" for="input01">用户名:</label>
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
                        <label class="control-label" for="input01">出生日期:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="T_BIRTH" name="T_BIRTH">
                            <p class="help-block">出生日期</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">身份证:</label>
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
                        <label class="control-label" for="input01">性别:</label>
                        <div class="controls">
                            <input type="radio" placeholder="" class="input-xlarge" value="男"id="S_SEX" name="S_SEX">男
                            <input type="radio" placeholder="" class="input-xlarge" value="男" id="S_SEX" name="S_SEX">女
                            <p class="help-block">性别</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">部门:</label>
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
                      <label class="control-label" for="input01">职位:</label>
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
                <div class="span12">
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="input01">通讯地址:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xxlarge" id="C_ADDRE" name="C_ADDRE">
                         <#--TODO缺少通讯地址字段-->
                        <p class="help-block">通讯地址</p>
                      </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">手机:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_MOBILE" name="C_MOBILE">
                             <#--TODO缺少邮编字段-->
                            <p class="help-block">手机</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">固话:</label>
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
                      <label class="control-label" for="input01">电子邮箱:</label>
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
                        <label class="control-label" for="input01">毕业学校:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_SCHOOL" name="C_SCHOOL">
                            <p class="help-block">毕业学校</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">学位:</label>
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
                        <label class="control-label" for="input01">学历:</label>
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
                        <label class="control-label" for="input01">密码:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_PASSWD" name="C_PASSWD">
                            <p class="help-block">密码</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">重复密码:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_PASSWD" name="C_PASSWD">
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
</@layout.doLayout>