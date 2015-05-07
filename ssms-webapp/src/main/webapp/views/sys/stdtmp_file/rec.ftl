<#import "/layout/_rec.ftl" as layout/>

<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_DESC: ko.observable('${C_DESC!}'),
        N_CYCLE: ko.observable(${N_CYCLE!}),
        P_CYCLE: ko.observable(${P_CYCLE!}),
        S_CYCLE: ko.observable('${S_CYCLE!}'),
        B_REMIND: ko.observable(${B_REMIND!}),
        P_TMPFILE: ko.observable(${P_TMPFILE!}),
        S_TMPFILE: ko.observable('${S_TMPFILE!}'),
        C_EXPLAIN: ko.observable('${C_EXPLAIN!}')
    };
    var settings = {
        cycleSource: ko.observableArray(${json(_SYS_CYCLE_)}),
        tmpfilesSource: ko.observableArray(${json(tmpfiles)}),
        paramViewSettings: {
            valueField: 'key',
            textField: 'value'
        }
    };
    var events = {
        saveClick: function () {
            $.post('save.do', model, function (result) {
                $.messager.alert("提示", "保存成功", "info", function () {
                    window.location.href = window.location.href;
                });
            })
        }
    };

    ko.applyBindings($.extend({}, model, settings, events));
</script>
<style type="text/css">
    td {
        vertical-align: top;
        line-height: 30px;
    }
</style>
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
            <input data-bind="textboxValue: C_NAME"/>
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
            <input data-bind="comboboxSource:tmpfilesSource,comboboxValue:P_TMPFILE,comboboxText:S_TMPFILE,easyuiOptions:paramViewSettings"/>
            <#if P_TMPFILE??>
                <a href="/sys/stdtmp_file_${P_TMPFILE}/rec.html?pid=${SID}&backURL=${backURL!referer!}">[配置模板]</a></#if>
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