<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        C_EMAIL: ko.observable('${C_EMAIL!}'),
        P_PROVINCE: ko.observable('${P_PROVINCE!}'),
        S_PROVINCE: ko.observable('${S_PROVINCE!}'),
        P_CITY: ko.observable('${P_CITY!}'),
        S_CITY: ko.observable('${S_CITY!}'),
        P_COUNTY: ko.observable('${P_COUNTY!}'),
        S_COUNTY: ko.observable('${S_COUNTY!}'),
        C_ADDR: ko.observable('${C_ADDR!}'),
        C_TEL: ko.observable('${C_TEL!}'),
        C_FAX: ko.observable('${C_FAX!}'),
        C_ZIP: ko.observable('${C_ZIP!}'),
        P_LEVEL : ko.observable('${P_LEVEL!}'),
        S_LEVEL : ko.observable('${S_LEVEL!}'),
        SID: '${SID!}'
    };
    var extModel = {
    };
    model.P_PROVINCE.subscribe(function (newValue) {
        settings.citySetting.combobox({
            url: '/sys/para_area/list',
            queryParams: {R_CODE: newValue}
        });
    });
    model.P_CITY.subscribe(function (newValue) {
        settings.countySetting.combobox({
            url: '/sys/para_area/list',
            queryParams: {R_CODE: newValue}
        });
    });

    var settings = {
    		cycleSource:ko.observableArray([{key:"1",value:"省级"},
                                            {key:"2",value:"市级"},
                                            {key:"3",value:"县级"}]),
            selectOptions : {
               valueField: 'key',
               textField: 'value'
           },
            professionSetting: {
            valueField: 'SID',
            textField: 'C_NAME'
        },
        provinceSetting: {
            url: '/sys/para_area/list',
            queryParams: {N_LEVEL: 1},
            valueField: 'C_CODE',
            textField: 'C_VALUE'
        },
        citySetting: {
            <#if P_PROVINCE!="">
            url: '/sys/para_area/list',
            queryParams: {R_CODE: '${P_PROVINCE}'},
            </#if>
            valueField: 'C_CODE',
            textField: 'C_VALUE'
        },
        countySetting: {
            <#if P_CITY!="">
            url: '/sys/para_area/list',
            queryParams: {R_CODE: '${P_CITY}'},
            </#if>
            valueField: 'C_CODE',
            textField: 'C_VALUE'
        }
    };

    var events = {
        saveClick: function () {
            if ($form.validate($('.form'))) {
                $.post('save', $.extend({}, model, extModel), function (result) {
                    if (result.SID)
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = 'rec?sid=' + result.SID + "&backURL=${backURL!referer!}";
                        });
                    else {
                        $.messager.alert("提示", "保存失败", "warning");
                    }
                }, 'json');
            }
        },
        deleteClick: function () {
            $.messager.confirm("删除确认", "您确认删除当前的信息吗？", function (deleteAction) {
                if (deleteAction) {
                    $.get("del", {sid: '${sid!}'}, function (data) {
                        if (data == "true" || data == "\"\"") {
                            $.messager.alert("提示", "删除成功");
                            window.location.href = 'index';
                        }
                        else {
                            $.messager.alert("提示", data);
                        }
                    });
                }
            });
        }
    };

    ko.applyBindings($.extend({}, model, extModel, settings, events));
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" title="政府信息">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-bind="click: deleteClick">删除</a>
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="window.location.href = '${backURL!referer!}'">返回</a>
    </div>

    <form class="form" style="padding: 20px">
        <div class="easyui-panel form" style="padding:10px" title="基本信息">
            <p class="ue-clear">
                <label>租户编码</label>
                <span class="control">
                    <input class="readonly" type="text" value="${C_CODE}" readonly/>
                </span>
            </p>
            <p class="long-input ue-clear">
                <label>单位名称</label>
                <span class="control">
                    <input data-bind="textboxValue: C_NAME" required/>
                </span>
            </p>
            
            <p class="long-input ue-clear">
                <label>行政等级</label>
                <span class="control">
                    <input data-bind="comboboxSource:cycleSource,comboboxValue:P_LEVEL,comboboxText:S_LEVEL,easyuiOptions:selectOptions" required/>
                </span>
            </p>
            
        </div>
        <div class="easyui-panel form" style="padding:10px" title="联系方式">
            <p class="long-input ue-clear">
                <label>电子邮箱</label>
                <span class="control">
                    <input data-bind="textboxValue: C_EMAIL" required/>
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>地址</label>
                <span class="control">
                    <input style="width: 60px" data-bind="comboboxValue:P_PROVINCE,comboboxText:S_PROVINCE,easyuiOptions:provinceSetting" required/>省
                    <input style="width: 60px" data-bind="comboboxValue:P_CITY,comboboxText:S_CITY,easyuiOptions:citySetting" required/>市
                    <input style="width: 60px" data-bind="comboboxValue:P_COUNTY,comboboxText:S_COUNTY,easyuiOptions:countySetting" required/>区(县)
                    <input style="width: 400px" data-bind="textboxValue: C_ADDR" required/>
                </span>
            </p>

            <p class="ue-clear">
                <label>固话</label>
                <span class="control">
                    <input data-bind="textboxValue: C_TEL" required/>
                </span>
            </p>

            <p class="ue-clear">
                <label>传真</label>
                <span class="control">
                    <input data-bind="textboxValue: C_FAX"/>
                </span>
            </p>

            <p class="ue-clear">
                <label>邮政编码</label>
                <span class="control">
                    <input data-bind="textboxValue: C_ZIP"/>
                </span>
            </p>
        </div>
    </form>
</div>
</@layout.doLayout>