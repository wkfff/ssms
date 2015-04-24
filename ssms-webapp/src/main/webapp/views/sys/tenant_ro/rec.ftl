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

<div class="navbar navbar-default">
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
                        <label class="control-label" for="input01">单位名称:</label>
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
                        <label class="control-label" for="input01">营业执照注册地:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_PROVINCE"
                                name="S_PROVINCE" value="省"> <input
                                type="text" placeholder=""
                                class="input-xlarge" id="S_CITY"
                                name="S_CITY" value="市"> <input
                                type="text" placeholder=""
                                class="input-xlarge" id="S_COUNTY"
                                name="S_COUNTY" value="市辖区">
                            <p class="help-block">营业执照注册地</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">注册号:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_NUMBER"
                                name="C_NUMBER">
                            <p class="help-block">注册号</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">确定评审业务机关:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_ORG"
                                name="C_ORG" readonly="readonly">
                            <p class="help-block">确定评审业务机关</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">用户名:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_USER"
                                name="C_USER">
                            <p class="help-block">用户名</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12">
                    <div class="control-group">
                        <!-- Text input-->
                        <label class="control-label" for="input01">评审业务范围:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xxlarge" name="C_BSINESS"
                                id="C_BSINESS" readonly="readonly">
                            <#--TODO缺少评审业务范围这个字段-->
                            <p class="help-block">评审业务范围</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <!-- Text input-->
                        <label class="control-label" for="input01">评审专业级别:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_LEVEL"
                                name="S_LEVEL" readonly="readonly">
                            <p class="help-block">评审专业级别</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <!-- Text input-->
                        <label class="control-label" for="input01">专职人员:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="N_FULLTIME"
                                name="N_FULLTIME">
                            <p class="help-block">专职人员</p>
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
                        <label class="control-label" for="input01">电子邮箱:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xxlarge" id="C_EMAIL"
                                name="C_EMAIL">
                            <p class="help-block">电子邮箱</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">办公电话:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_TEL"
                                name="C_TEL">
                            <p class="help-block">办公电话</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">传真:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_FAX"
                                name="C_FAX">
                            <p class="help-block">传真</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">省份:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_PROVINCE"
                                name="S_PROVINCE">
                            <p class="help-block">省份</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">地市:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_CITY"
                                name="S_CITY">
                            <p class="help-block">地市</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">县市:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="S_COUNTY"
                                name="S_COUNTY">
                            <p class="help-block">县市</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">邮政编码:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xlarge" id="C_ZIP"
                                name="C_ZIP">
                            <p class="help-block">邮政编码</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12">
                    <div class="control-group">
                        <!-- Text input-->
                        <label class="control-label" for="input01">地址:</label>
                        <div class="controls">
                            <input type="text" placeholder=""
                                class="input-xxlarge" id="C_ADDR"
                                name="C_ADDR">
                            <p class="help-block">地址</p>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>

    </form>
</div>
</@>
