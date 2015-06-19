<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable(),
        R_INDUSTRY: ko.observable(),
        C_EMAIL: ko.observable(),
        P_PROVINCE: ko.observable(),
        S_PROVINCE: ko.observable(),
        P_CITY: ko.observable(),
        S_CITY: ko.observable(),
        P_COUNTY: ko.observable(),
        S_COUNTY: ko.observable(),
        P_AT_PROVINCE: ko.observable(),
        S_AT_PROVINCE: ko.observable(),
        P_AT_CITY: ko.observable(),
        S_AT_CITY: ko.observable(),
        P_AT_COUNTY: ko.observable(),
        S_AT_COUNTY: ko.observable(),
        C_ADDR: ko.observable(),
        C_CONTACT: ko.observable(),
        C_TEL: ko.observable(),
        C_FAX: ko.observable(),
        C_ZIP: ko.observable(),
        C_NUMBER: ko.observable(),
        C_SCOPE: ko.observable(),
        N_INCOME: ko.observable(),
        N_ASSETS: ko.observable(),
        N_SPECIAL: ko.observable(),
        N_EMPLOYEE: ko.observable(),
        C_SUMMARY: ko.observable(),
        T_EXAMINE: ko.observable(),
        T_EXAMINE_NEXT: ko.observable()
    };
    var extModel = {
        professionValues: ko.observableArray()
    };
    model.R_INDUSTRY.subscribe(function (newValue) {
        settings.professionSetting.combobox({
            url: '/sys/profession/list',
            queryParams: {R_INDUSTRY: newValue}
        })
    });
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
    model.P_AT_PROVINCE.subscribe(function (newValue) {
        settings.at_citySetting.combobox({
            url: '/sys/para_area/list',
            queryParams: {R_CODE: newValue}
        });
    });
    model.P_AT_CITY.subscribe(function (newValue) {
        settings.at_countySetting.combobox({
            url: '/sys/para_area/list',
            queryParams: {R_CODE: newValue}
        });
    });

    var settings = {
        industrySetting: {
            url: '/sys/industry/list',
            valueField: 'SID',
            textField: 'C_NAME'
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
            valueField: 'C_CODE',
            textField: 'C_VALUE'
        },
        countySetting: {
            valueField: 'C_CODE',
            textField: 'C_VALUE'
        },
        at_provinceSetting: {
            url: '/sys/para_area/list',
            queryParams: {N_LEVEL: 1},
            valueField: 'C_CODE',
            textField: 'C_VALUE'
        },
        at_citySetting: {
            valueField: 'C_CODE',
            textField: 'C_VALUE'
        },
        at_countySetting: {
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
        }
    };

    ko.applyBindings($.extend({}, model, extModel, settings, events));
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" title="企业信息">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="window.location.href='${Referer!}';">返回</a>
    </div>

    <form class="form" style="padding: 20px">
        <div class="easyui-panel form" style="padding:10px" title="基本信息">
            <p class="long-input ue-clear">
                <label>企业名称</label>
                <span class="control">
                    <input data-bind="textboxValue: C_NAME" required/>
                </span>
            </p>

            <p class="ue-clear">
                <label>行业</label>
                <span class="control">
                    <input data-bind="comboboxValue:R_INDUSTRY,easyuiOptions:industrySetting" required/>
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>专业</label>
                <span class="control">
                    <input data-bind="comboboxValues:professionValues,easyuiOptions:professionSetting" required/>
                </span>
            </p>
            
            <p class="ue-clear">
                <label>所属辖区</label>
                <span class="control">
                    <input style="width: 60px" data-bind="comboboxValue:P_AT_PROVINCE,comboboxText:S_AT_PROVINCE,easyuiOptions:at_provinceSetting" required/>省
                    <input style="width: 60px" data-bind="comboboxValue:P_AT_CITY,comboboxText:S_AT_CITY,easyuiOptions:at_citySetting" required/>市
                    <input style="width: 60px" data-bind="comboboxValue:P_AT_COUNTY,comboboxText:S_AT_COUNTY,easyuiOptions:at_countySetting" required/>区(县)
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
                <label>联系人</label>
                <span class="control">
                    <input data-bind="textboxValue: C_CONTACT" required/>
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
        <div class="easyui-panel form" style="padding:10px" title="扩展信息">
            <p class="long-input ue-clear">
                <label>营业执照注册号</label>
                <span class="control">
                    <input data-bind="textboxValue: C_NUMBER"/>
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>营业范围</label>
                <span class="control">
                    <input data-bind="textboxValue: C_SCOPE"/>
                </span>
            </p>

            <p class="ue-clear">
                <label>主营业务收入</label>
                <span class="control">
                    <input data-bind="numberboxValue: N_INCOME"/>
                </span>
            </p>

            <p class="ue-clear">
                <label>固定资产</label>
                <span class="control">
                    <input data-bind="numberboxValue: N_ASSETS"/>
                </span>
            </p>

            <p class="ue-clear">
                <label>特种作业人员</label>
                <span class="control">
                    <input data-bind="numberboxValue: N_SPECIAL"/>
                </span>
            </p>

            <p class="ue-clear">
                <label>专职管理人员</label>
                <span class="control">
                    <input data-bind="numberboxValue: N_EMPLOYEE"/>
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>企业概述</label>
                <span class="control">
                    <input data-bind="textareaValue: C_SUMMARY"/>
                </span>
            </p>
        </div>

        <div class="easyui-panel" style="padding:10px;" title="达标信息">
            <p class="ue-clear">
                <label>最后一次评审日期</label>
                <span class="control">
                    <input data-bind="dateboxValue: T_EXAMINE"/>
                </span>
            </p>

            <p class="ue-clear">
                <label>下次复审时间</label>
                <span class="control">
                    <input data-bind="dateboxValue: T_EXAMINE_NEXT"/>
                </span>
            </p>
        </div>
    </form>
</div>
</@layout.doLayout>