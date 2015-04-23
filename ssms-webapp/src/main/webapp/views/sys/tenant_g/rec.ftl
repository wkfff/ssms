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
<div class="navbar navbar-inverse navbar-fixed-top">
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

<div class="navbar navbar-default navbar-fixed-top">
    <br>
    <br>
    <div class="navbar-inner">
        <div class="container">
            <div class="nav pull-right">
                <input type="button" class="btn" name="btn_save"
                    value="保存"> <input type="button" class="btn"
                    name="btn_del" value="删除"> <input
                    type="button" class="btn" name="btn_back" value="返回">
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
                        <label class="control-label" for="C_NAME">单位名称:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xxlarge" id="C_NAME"
                                name="C_NAME">
                            <p class="help-block">单位名称</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="S_PROVINCE">省:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_PROVINCE"
                                name="S_PROVINCE">
                            <p class="help-block">省</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="S_CITY">市:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_CITY"
                                name="S_CITY">
                            <p class="help-block">市</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="S_COUNTY">县:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_COUNTY"
                                name="S_COUNTY">
                            <p class="help-block">县</p>
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
                            <input type="text" placeholder=""
                                class="input-xxlarge" id="C_ADDRE"
                                name="C_ADDRE">
                            <p class="help-block">通讯地址</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_FAX">传真:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_FAX"
                                name="C_FAX">
                            <p class="help-block">传真</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="C_TEL">固话:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_TEL"
                                name="C_TEL">
                            <p class="help-block">固话</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <!-- Text input-->
                        <label class="control-label" for="C_EMAIL">电子邮箱:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_EMAIL"
                                name="C_EMAIL">
                            <p class="help-block">电子邮箱</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <!-- Text input-->
                        <label class="control-label" for="C_ZIP">邮政编码:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_ZIP"
                                name="C_ZIP">
                            <p class="help-block">邮政编码</p>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>

    </form>
</div>
</@>
