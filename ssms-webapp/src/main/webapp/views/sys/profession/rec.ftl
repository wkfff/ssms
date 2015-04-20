<#import "/layout/_rec.ftl" as layout/>

<#assign script>
<script type="text/javascript">
    var setting = {
        sid: $url.getUrlParam("sid"),
        dataUrl: "rec.json",
        saveUrl: "save.json",
        delUrl: "del.json",
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
                    <li class="active">
                        <a href="#">首页 ></a>
                    </li>
                    <li class="active">
                        <a href="#">管理中心 ></a>
                    </li>
                    <li class="active">
                        <a href="#">专业信息管理 ></a>
                    </li>
                    <li class="active">
                        <a href="#">专业信息编辑</a>
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
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">专业编码:</label>

                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_CODE" name="C_CODE">

                            <p class="help-block">专业编码</p>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group">
                        <label class="control-label" for="input01">专业名称:</label>

                        <div class="controls">
                            <input type="text" placeholder="" class="input-xlarge" id="C_VALUE" name="C_VALUE">

                            <p class="help-block">专业名称</p>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</@layout.doLayout>