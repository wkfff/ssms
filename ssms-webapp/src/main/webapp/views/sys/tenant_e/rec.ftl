<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
    		C_CODE:ko.observable('${C_CODE!}'),
            C_NAME: ko.observable('${C_NAME!}'),
            C_EMAIL: ko.observable('${C_EMAIL!}'),
            P_PROVINCE: ko.observable('${P_PROVINCE!}'),
            S_PROVINCE: ko.observable('${S_PROVINCE!}'),
            P_CITY: ko.observable('${P_CITY!}'),
            S_CITY: ko.observable('${S_CITY!}'),
            P_COUNTY: ko.observable('${P_COUNTY!}'),
            S_COUNTY: ko.observable('${S_COUNTY!}'),
            C_ADDR: ko.observable('${C_ADDR!}'),
            C_CONTACT: ko.observable('${C_CONTACT!}'),
            C_TEL: ko.observable('${C_TEL!}'),
            C_FAX: ko.observable('${C_FAX!}'),
            C_ZIP: ko.observable('${C_ZIP!}'),
            C_NUMBER: ko.observable('${C_NUMBER!}'),
            C_SCOPE: ko.observable('${C_SCOPE!}'),
            N_INCOME: ko.observable('${N_INCOME!}'),
            N_ASSETS: ko.observable('${N_ASSETS!}'),
            N_SPECIAL: ko.observable('${N_SPECIAL!}'),
            N_EMPLOYEE: ko.observable('${N_EMPLOYEE!}'),
            C_SUMMARY: ko.observable('${C_SUMMARY!}'),
            T_EXAMINE: ko.observable('${T_EXAMINE!}'),
            T_EXAMINE_NEXT: ko.observable('${T_EXAMINE_NEXT!}'),
            SID:'${SID!}'
        };
        var extModel = {
            industryValue: ko.observable(),
            professionValues: ko.observableArray()
        };
        extModel.industryValue.subscribe(function (newValue) {
            settings.professionSetting.combobox({
                url: '/sys/profession/list.json',
                queryParams: {R_INDUSTRY: newValue}
            })
        });
        model.P_PROVINCE.subscribe(function (newValue) {
            settings.citySetting.combobox({
                url: '/sys/para_area/list.json',
                queryParams: {R_CODE: newValue}
            });
        });
        model.P_CITY.subscribe(function (newValue) {
            settings.countySetting.combobox({
                url: '/sys/para_area/list.json',
                queryParams: {R_CODE: newValue}
            });
        });
        
        var settings = {
            industrySetting: {
                url: '/sys/industry/list.json',
                valueField: 'SID',
                textField: 'C_NAME'
            },
            professionSetting: {
                valueField: 'SID',
                textField: 'C_NAME'
            },
            provinceSetting: {
                url: '/sys/para_area/list.json',
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
            }
        };

        var events = {
            saveClick: function () {
                if ($form.validate($('.form'))) {
                    $.post('save.do', $.extend({}, model, extModel), function (result) {
                        if (result.SID)
                            $.messager.alert("提示", "保存成功", "info", function () {
                                window.location.href = 'rec.html?sid=' + result.SID + "&backURL=${backURL!referer!}";
                            });
                        else {
                            $.messager.alert("提示", "保存失败", "warning");
                        }
                    }, 'json');
                }
            },
          deleteClick: function (){
              $.messager.confirm("删除确认", "您确认删除当前的信息吗？", function (deleteAction) {
                if (deleteAction) {
                    $.get("del.do", {sid: '${sid!}'}, function (data) {
                        if (data == "true" || data == "\"\"") {
                            $.messager.alert("提示", "删除成功");
                            window.location.href = 'index.html';
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
<div class="easyui-panel" title="企业信息">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save"  data-bind="click: saveClick">保存</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-bind="click: deleteClick">删除</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '${backURL!referer}'">返回</a>
    </div>
     <form class="form"  style="padding: 20px">
        <div class="easyui-panel form" style="padding:10px" title="基本信息">
            <p class="long-input ue-clear">
                    <label>租户编码</label>
                    <span class="control outline">${C_CODE}</span>
            </p>
            <p class="long-input ue-clear">
                <label>企业名称</label>
                <span class="control">
                    <input data-bind="textboxValue: C_NAME" required/>
                </span>
            </p>
             <p class="ue-clear">
                <label>行业</label>
                <span class="control">
                    <input data-bind="comboboxValue:industryValue,easyuiOptions:industrySetting" required/>
                </span>
            </p>

            <p class="long-input ue-clear">
                <label>专业</label>
                <span class="control">
                    <input data-bind="comboboxValues:professionValues,easyuiOptions:professionSetting" required/>
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
        <div class="easyui-panel" border="false" style="padding:10px;" title="企业达标情况">
            <p class="ue-clear">
                <label>达标等级</label>
                <span class="control">
                    <input data-bind="textareaValue: T_EXAMINE"/>
                </span>
            </p>
            <p class="ue-clear">
                <label>下次复审时间</label>
                <span class="control">
                    <input data-bind="dateboxValue: T_EXAMINE_NEXT"/>
                </span>
            </p>
        </div>
        <div class="easyui-panel" border="false" style="padding:10px;" title="费用信息">
             <p class="long-input ue-clear">
                    <label>缴费日期</label>
                    <span class="control outline">${T_PAY!}</span>
            </p>
             <p class="long-input ue-clear">
                    <label>下次缴费日期</label>
                    <span class="control outline">${T_PAY_NEXT!}</span>
            </p>
             <p class="long-input ue-clear">
                    <label>状态</label>
                    <span class="control outline">${S_STATE!}</span>
            </p>
        </div>
    </form>
</div>
</@>