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

<div class="navbar navbar-default">
      <div class="navbar-inner">
        <div class="container">
            <div class="nav pull-right">
               <input type="button" class="btn" onclick="svaeRInfo()" value="保存">
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
                        <label class="control-label" for="C_POSITION">职务:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_POSITION" name="C_POSITION">
                            <p class="help-block">职务</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="T_BIRTH">出生日期:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="T_BIRTH" name="T_BIRTH">
                            <p class="help-block">出生日期</p>
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_CERT">聘请证书编号:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_CERT" name=C_CERT">
                            <p class="help-block">聘请证书编号</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_INSTUDRY">行业:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_INSTUDRY" name="C_INSTUDRY">
                            <p class="help-block">行业</p>
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                    <!-- Text input-->
                      <label class="control-label" for="S_SEX">性别:</label>
                      <div class="controls">
                        <input type="radio" placeholder="" class="input-xlarge" value="男" name="S_SEX" id="S_SEX" value="男">男
                        <input type="radio" placeholder="" class="input-xlarge" value="女" name="S_SEX" id="S_SEX" value="女">女
                        <p class="help-block">性别</p>
                      </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="S_PROFESSION">专业 :</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xlarge" id="S_PROFESSION" name="S_PROFESSION">
                        <p class="help-block">专业 </p>
                      </div>
                    </div>
                </div>
            </div>
    
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="N_YEAR">现从事专业年限:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xlarge" id="N_YEAR" name="N_YEAR" >
                        <p class="help-block">现从事专业年限</p>
                      </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                    <!-- Text input-->
                      <label class="control-label" for="S_PROFESSION_L">所学专业:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xlarge" id="S_PROFESSION_L" name="S_PROFESSION_L">
                        <p class="help-block">所学专业</p>
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
                      <label class="control-label" for="C_ADDRE">通讯地址:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xxlarge" id="C_ADDRE" name="C_ADDRE">
                        <#--TODO缺少通讯地址字段-->
                        <p class="help-block">通讯地址</p>
                      </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="control-group">
                      <!-- Text input-->
                      <label class="control-label" for="C_COMPANY">工作单位及部门:</label>
                      <div class="controls">
                        <input type="text" placeholder="" class="input-xxlarge" id="C_COMPANY" name="C_COMPANY">
                        <#--TODO缺少工作单位及部门字段-->
                        <p class="help-block">工作单位及部门</p>
                      </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_POSTCODS">邮编:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_POSTCODS" name="C_POSTCODS">
                             <#--TODO缺少邮编字段-->
                            <p class="help-block">邮编</p>
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
        <legend>工作简历</legend>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="control-group">
                    <div class="controls">
                        <textarea id="C_RESUME" name="C_RESUME" rows=5 class="ui-editor" > </textarea>
                        <#--TODO更新完代码后没有效果没了-->
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
                            <input type="text" placeholder="" class="input-xlarge" id="C_PASSWD" name="C_PASSWD">
                            <p class="help-block">密码</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" >重复密码:</label>
                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_PASSWD1" name="C_PASSWD1">
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
