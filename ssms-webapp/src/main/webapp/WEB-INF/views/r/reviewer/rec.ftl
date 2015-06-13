<#import "../../layout/_rec.ftl" as layout/> <#assign script>
<script type="text/javascript">
    var model = {
        C_NAME: ko.observable('杨建'),
        S_SEX: ko.observable('男'),
        T_BIRTH: ko.observable('1985-11-11'),
        C_DEPT: ko.observable('泉州市安全科学研究会'),
        C_POSITION: ko.observable('工程师'),
        C_TECHNOLOGY: ko.observable('工程师'),
        C_ADDR: ko.observable('泉州市惠安县'),
        C_ZIP: ko.observable('36200'),
        C_MOBILE: ko.observable('15064447154'),
        C_EMAIL: ko.observable('vip@qq.com'),
        C_CARD: ko.observable('350684185410124566'),
        C_CERTIFICATENO: ko.observable('ABC闽3838438'),
        C_INDUSTRY: ko.observable('轻工'),
        C_PROFESSION: ko.observable('基本规范评分细则'),
        C_SCHOOL: ko.observable('福州大学'),
        S_EDUCATION: ko.observable('研究生'),
        S_DEGREE: ko.observable('硕士'),
        S_MAJOR: ko.observable('环境工程'),
        C_ENGPROANDAGE: ko.observable('三年安全技术'),
        C_SUMMARY: ko.observable(),
        C_CLASSWORKER : ko.observable('01'),
        SID: ko.observable('1')
    };
    var settings={
        cycleSource:ko.observableArray([
    		                     {name:"专家",key:"01"},
    		                     {name:"评审员",key:"02"}
    		         ]),
     selectOptions : {
    		                 valueField: 'key',
    		                 textField: 'name'
    		             }
    };

    var events = {
        saveClick: function () {
            utils.messager.showProgress();
            $.post('save', model, function (result) {
                if (result.SID) {
                    if (result.SID != settings.htmleditSettings.sid) settings.htmleditSettings.sid = result.SID;
                    settings.htmleditSettings.save(function (editorResult) {
                        utils.messager.closeProgress();
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = window.location.href;
                        });
                    });
                } else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    });
                }
            }, "json");
        }
    };
    ko.applyBindings($.extend({}, model, events,settings));
</script>
<style type="text/css">
.form table {
    width: 100%;
    margin: 5px auto;
    table-layout: fixed;
}

.form table tr {
    height: 40px;
}

.form table td {
    text-align: right;
    padding: 5px;
}

.form table .label {
    width: 110px;
}

.layout {
    width: 900px;
    margin: 0 auto;
}
</style>
</#assign> <@layout.doLayout script>
<div class="easyui-panel" title="评审人员管理" fit="true">
    <div class="z-toolbar">
        <div class="layout">
            <a class="easyui-linkbutton" plain="true"
                iconCls="icon-save">修改</a> <a class="easyui-linkbutton"
                plain="true" iconCls="icon-cancel">删除</a> <a href="#"
                class="easyui-linkbutton" plain="true"
                iconCls="icon-undo"
                onclick="window.location.href = 'index'">返回列表</a>
        </div>
    </div>
    <div class="layout">
        <form class="form">
            <table>
                <colgroup>
                    <col class="label" />
                    <col />
                    <col class="label" />
                    <col />
                    <col class="label" />
                    <col />
                    <col />
                </colgroup>
                <tr>
                    <td>姓名:</td>
                    <td><input data-bind="textboxValue: C_NAME"
                        required /></td>
                    <td>性别:</td>
                    <td><input type="radio" name="S_SEX"
                        data-bind="checked: S_SEX" value="男" />男 <input
                        type="radio" name="S_SEX"
                        data-bind="checked: S_SEX" value="女" />女</td>
                    <td>出生日期:</td>
                    <td><input data-bind="dateboxValue: T_BIRTH" />
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>工作单位及部门:</td>
                    <td colspan="5"><input
                        data-bind="textboxValue: C_DEPT" /></td>
                    <td rowspan="3" align="center"><img alt="无法加载"
                        src="/resource/images/photo.png"></td>
                </tr>
                <tr>
                    <td>职务:</td>
                    <td><input data-bind="textboxValue: C_POSITION" />
                    </td>
                    <td>人员类别:</td>
                    <td><input
                        data-bind="comboboxSource:cycleSource,comboboxValue:C_CLASSWORKER,easyuiOptions:selectOptions" />
                    </td>
                    <td>专业技术职务:</td>
                    <td><input
                        data-bind="textboxValue: C_TECHNOLOGY" /></td>
                </tr>
                <tr>
                    <td>通讯地址:</td>
                    <td colspan="3"><input
                        data-bind="textboxValue: C_ADDR" /></td>
                    <td>邮编:</td>
                    <td><input data-bind="textboxValue: C_ZIP" />
                    </td>
                </tr>
                <tr>
                    <td>移动电话:</td>
                    <td><input data-bind="textboxValue: C_MOBILE" />
                    </td>
                    <td>E-mail:</td>
                    <td><input data-bind="textboxValue: C_EMAIL" />
                    </td>
                    <td>身份证号:</td>
                    <td colspan="2"><input
                        data-bind="textboxValue: C_CARD" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td>聘请证书编号:</td>
                    <td><input
                        data-bind="textboxValue: C_CERTIFICATENO" /></td>
                    <td>行业:</td>
                    <td><input data-bind="textboxValue: C_INDUSTRY" />
                    </td>
                    <td>专业:</td>
                    <td colspan="2"><input
                        data-bind="textboxValue: C_PROFESSION" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td>毕业学校:</td>
                    <td><input data-bind="textboxValue: C_SCHOOL" />
                    </td>
                    <td>学历:</td>
                    <td><input
                        data-bind="textboxValue: S_EDUCATION" /></td>
                    <td>学位:</td>
                    <td colspan="2"><input
                        data-bind="textboxValue: S_DEGREE" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td>所学专业:</td>
                    <td colspan="3"><input
                        data-bind="textboxValue: S_MAJOR" /></td>
                    <td>现从事专业及年限:</td>
                    <td colspan="2"><input
                        data-bind="textboxValue: C_ENGPROANDAGE" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="7" style="text-align: left">工作简历及主要成绩:</td>
                </tr>
                <tr>
                    <td colspan="7"><input
                        data-bind="textareaValue: C_SUMMARY"
                        style="width: 100%; height: 150px" /></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</@>
