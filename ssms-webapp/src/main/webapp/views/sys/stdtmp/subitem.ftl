<#import "/layout/_rec.ftl" as layout/>

<#assign script>
<script type="text/javascript">
    var setting = {
        sid: $url.getUrlParam("sid"),
        dataUrl: "rec.json",
        saveUrl: "save.json",
        delUrl: "del.json",
        backUrl: '${referer!}' // 设置回退的URL。referer是请求头，里面含有请求当前页面的页面地址。
    };

    $(document).ready(function () {
        $form.init(setting);
    });
</script>
<style type="text/css">
    body {
        margin: 0;
    }
</style>
</#assign>
<@layout.doLayout script>
<div class="navbar navbar-default">
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
            <legend>基本信息</legend>
            <div class="row-fluid">
                <div class="span12">
                    <@layout.textbox "C_NAME" "名称" "目录名称"/>
                    <@layout.textarea "C_DESC" "描述" "目录描述"/>
                </div>
            </div>

            <input type="hidden" name="R_SID" value="${pid!}"/>
            <input type="hidden" name="P_PROFESSION" value="${P_PROFESSION!}"/>
            <input type="hidden" name="S_PROFESSION" value="${S_PROFESSION!}"/>
        </fieldset>
    </form>
</div>
</@layout.doLayout>