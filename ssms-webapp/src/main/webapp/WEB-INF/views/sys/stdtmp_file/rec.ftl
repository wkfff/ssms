<#import "../../layout/_rec.ftl" as layout/>

<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_DESC: ko.observable('${C_DESC!}'),
        N_CYCLE: ko.observable(${N_CYCLE!}),
        P_CYCLE: ko.observable(${P_CYCLE!}),
        S_CYCLE: ko.observable('${S_CYCLE!}'),
        B_REMIND: ko.observable(${B_REMIND!}),
        P_TMPFILE: ko.observable('${P_TMPFILE!}'),
        S_TMPFILE: ko.observable('${S_TMPFILE!}'),
        C_EXPLAIN: ko.observable('${C_EXPLAIN!}'),
        SID: ko.observable(${SID!}),
        R_SID: ko.observable(${R_SID!folder.SID}),
        S_NAME: ko.observable('${S_NAME!folder.C_NAME}')
    };
    var settings = {
        cycleSource: ko.observableArray(${json(SYS_CYCLE)}),
        tmpfilesSource: ko.observableArray(${json(tmpfiles)}),
        paramViewSettings: {
            valueField: 'code',
            textField: 'name'
        }
    };
    var events = {
        saveClick: function () {
            if ($form.validate($('.form')))
                $.post('save', model, function (result) {
                    if (result.SID)
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = 'rec?sid=' + result.SID + "&backURL=${backURL!referer!}";
                        });
                    else {
                        $.messager.alert("提示", "保存失败", "warning");
                    }
                }, "json")
        }
    };

    ko.applyBindings($.extend({}, model, settings, events));
</script>
</#assign>
<@layout.doLayout script>
<div class="z-toolbar">
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '${backURL!referer!}'">返回</a>
</div>
<form class="form" method="post">
    <p class="ue-clear">
        <label class="label">名称</label>
        <span class="control">
            <input data-bind="textboxValue: C_NAME" required="true"/>
        </span>
    </p>

    <p class="long-input ue-clear">
        <label class="label">描述</label>
        <span class="control">
            <input data-bind="textareaValue: C_DESC"/>
        </span>
    </p>

    <p class="long-input ue-clear">
        <label class="label">更新周期</label>
        <span class="control">
            <input data-bind="numberboxValue:N_CYCLE" style="width: 50px"/>
            <input data-bind="comboboxSource:cycleSource,comboboxValue:P_CYCLE,comboboxText:S_CYCLE,easyuiOptions:paramViewSettings" style="width: 50px"/>
            <label><input data-bind="booleanValue: B_REMIND"/>是否提醒</label>
        </span>
    </p>

    <p class="ue-clear">
        <label class="label">模板文件</label>
        <span class="control">
            <input data-bind="comboboxSource:tmpfilesSource,comboboxValue:P_TMPFILE,comboboxText:S_TMPFILE,easyuiOptions:paramViewSettings" required="true"/>
            <#if P_TMPFILE?? && R_TMPFILE??>
                <a href="/sys/stdtmp_file_${P_TMPFILE}/rec?sid=${R_TMPFILE}&backURL=${backURL!referer!}">[配置模板]</a></#if>
        </span>
    </p>

    <p class="ue-clear">
        <label>政策解读</label>
        <span class="control">
            <input data-bind="textareaValue: C_EXPLAIN"/>
        </span>
    </p>
</form>
</@>