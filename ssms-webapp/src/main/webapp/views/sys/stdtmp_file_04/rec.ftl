<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        T_TIME: ko.observable('${T_TIME!}'),
        C_USER_01: ko.observable('${C_USER_01!}'),
        C_ADDR: ko.observable('${C_ADDR!}'),
        S_TYPE: ko.observable('${S_TYPE!}'),
        N_TIME: ko.observable('${N_TIME!}'),
        C_USER_02: ko.observable('${C_USER_02!}'),
        SID: '${SID!}'
    };
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "STDTMP_FILE_04",
            field: 'C_CONTENT',
            sid: '${SID!}'
        }
    };
    var events = {
        saveClick: function () {
            $.messager.progress();
            $.post('save.do', model, function (result) {
                if (result.SID) {
                    settings.htmleditSettings.save(function (editorResult) {
                        $.messager.alert("提示", "保存成功", "info", function () {
                            $.messager.progress('close');
                            window.location.href = 'rec.html?sid=' + result.SID + "&backURL=${backURL!referer!}";
                        });
                    });
                } else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        $.messager.progress('close');
                    });
                }
            }, "json");
        }
    };
    $(function () {
        ko.applyBindings($.extend({}, model, settings, extModel, events));
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="z-toolbar">
    <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '${backURL!referer}'">返回</a>
</div>
<form class="form" method="post" style="padding:10px 31px;">
    <div class="easyui-panel" title="概要" style="padding-bottom: 10px;">
        <p class="long-input ue-clear">
            <label>文件名称</label>
            <span class="control">
                <input data-bind="textboxValue: C_NAME"/>
            </span>
        </p>

        <p class="long-input ue-clear">
            <label>培训日期</label>
            <span class="control">
                <input data-bind="dateboxValue: T_TIME"/>
            </span>
        </p>

        <p class="long-input ue-clear">
            <label>教育人</label>
            <span class="control">
                <input data-bind="textboxValue: C_USER_01"/>
            </span>
        </p>

        <p class="long-input ue-clear">
            <label>培训地址</label>
            <span class="control">
                <input data-bind="textboxValue: C_ADDR"/>
            </span>
        </p>

        <p class="long-input ue-clear">
            <label>培训种类</label>
            <span class="control">
                <input data-bind="textboxValue: S_TYPE"/>
            </span>
        </p>

        <p class="long-input ue-clear">
            <label>学时</label>
            <span class="control">
                <input data-bind="textboxValue: N_TIME"/>
            </span>
        </p>

        <p class="long-input ue-clear">
            <label>记录人</label>
            <span class="control">
                <input data-bind="textboxValue: C_USER_02"/>
            </span>
        </p>
    </div>
    <div class="easyui-panel" title="正文" style="padding: 6px">
        <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
    </div>
</form>
</@>