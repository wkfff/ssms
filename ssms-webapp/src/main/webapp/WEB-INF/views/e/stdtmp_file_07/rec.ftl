<style type="text/css">
    .form table tr {
        height: 40px;
    }

    .form table td {
        padding-right: 30px;
    }
</style>
<div id="kocontainer">
    <div class="z-toolbar">
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: saveClick, visible: isNew() || editing()">保存</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-edit" data-bind="click: editClick, visible: readonly">修改</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-back" data-bind="click: cancelClick, visible: editing">取消修改</a>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: reviewClick, visible: readonly">复审</a>


    <#if !todo??>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="panelLoad('${BASE_PATH}/?fileid=${file.id}');">返回</a>
    </#if>
    <#if file.templateModel??>
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-search" data-bind="click: function(){window.open('/sys/stdtmp/file/view/${file.sourceFile.id}')}">查看模板</a>
    </#if>

        <span style="position: absolute; right: 30px;">
            编辑状态：<span style="font-size: 12px; font-weight: bold; color: red" data-bind="text: status"></span>
        </span>
    </div>

    <form class="form" method="post" data-bind="with: formModel" style="padding:10px 31px; width: 740px">
        <table cellspadding="10" cellspading="10" border="1" style="width: 100%; table-layout: fixed;">
            <colgroup>
                <col style="width: 100px"/>
                <col style="width: 255px"/>
                <col style="width: 100px"/>
                <col style="width: 255px"/>
            </colgroup>
            <tr>
                <td><span class="must">*</span>姓名</td>
                <td>
                    <input data-bind="disable: $parent.readonly, value: C_NAME"/>
                </td>
                <td>性别</td>
                <td>
                    <label><input type="radio" name="P_SEX" data-bind="disable: $parent.readonly,checked: P_SEX" value="1"/>男</label>
                    <label><input type="radio" name="P_SEX" data-bind="disable: $parent.readonly,checked: P_SEX" value="2"/>女</label>
                </td>
            </tr>

            <tr>
                <td>身份证号码</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_CARD"/>
                </td>
                <td>所在部门</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_DEPT"/>
                </td>
            </tr>

            <tr>
                <td>工种</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_WORKTYPE"/>
                </td>
                <td>发证机关</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_AUTH"/>
                </td>
            </tr>

            <tr>
                <td>证书编号</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_CERT"/>
                </td>
                <td>取证时间</td>
                <td>
                    <input data-bind="disable: $parent.readonly,dateValue: T_CERT_GET"/>
                </td>
            </tr>

            <tr>
                <td><span class="must">*</span>复审时间</td>
                <td>
                    <input data-bind="disable: $parent.readonly,dateValue: T_CERT_REVIEW"/>
                </td>
                <td></td>
                <td></td>
            </tr>
        <#if file.explain?? && file.explain?length!=0>
            <tr>
                <td colspan="4">
                    <div style="border: 1px dashed  #ccc; margin-bottom: 5px;position: relative;">
                        <div style="background-color: #CCCCCC;">政策解读</div>
                    ${file.explain}
                    </div>
                </td>
            </tr>
        </#if>
        </table>
    </form>
</div>

<script type="text/html" id="reviewTemplate">
    <label for="nextReviewTime">下次复审时间:</label>
    <input type="text" data-bind="dateValue: nextTime" id="nextReviewTime" style="width: 160px"/>
</script>
<script type="text/javascript">
    function ViewModel() {
        function FormModel() {
            this.C_NAME = ko.observable('${C_NAME!}').extend({required: true});
            this.P_SEX = ko.observable('${P_SEX!1}');
            this.C_CARD = ko.observable('${C_CARD!}');
            this.C_DEPT = ko.observable('${C_DEPT!}');
            this.C_WORKTYPE = ko.observable('${C_WORKTYPE!}');
            this.C_AUTH = ko.observable('${C_AUTH!}');
            this.T_CERT_GET = ko.observable('${T_CERT_GET!}');
            this.C_CERT = ko.observable('${C_CERT!}');
            this.T_CERT_REVIEW = ko.observable('${T_CERT_REVIEW!}').extend({required: true});
            this.SID = ${SID!"undefined"};
            this.R_TMPFILE = '${file.id}';
        }

        var self = this;
        this.formModel = ko.observable(new FormModel());

        this.readonly = ko.observable(<#if SID??>true<#else>false</#if>);

        this.isNew = ko.computed(function () {
            return this.formModel().SID === undefined;
        }, this);

        this.status = ko.computed(function () {
            if (this.isNew()) return '新增';
            if (this.readonly()) return '只读';
            return '编辑'
        }, this);

        this.editing = ko.computed(function () {
            return this.isNew() == false && !this.readonly();
        }, this);

        this.saveClick = function () {
            if (isValid(self.formModel)) {
                utils.messager.showProgress();
                $.post('${BASE_PATH}/save', self.formModel(), function (result) {
                    utils.messager.closeProgress();
                    if (result.SID) {
                        utils.messager.alert("保存成功", function () {
                            panelLoad('${BASE_PATH}/rec?SID=' + result.SID);
                        });
                    } else {
                        utils.messager.alert("保存失败");
                    }
                }, "json");
            }
        };
        this.editClick = function () {
            self.readonly(false);
        };
        this.cancelClick = function () {
            self.readonly(true);
            self.formModel(new FormModel());
        };
        this.reviewClick = function () {
            var model = {nextTime: ko.observable().extend({required: true})};
            utils.dialog.open({
                title: "请填写复审信息",
                templateId: '#reviewTemplate',
                loaded: function (element) {
                    ko.applyBindings(model, element[0]);
                },
                ok: function () {
                    if (isValid(model)) {
                        utils.messager.showProgress();
                        $.post('${BASE_PATH}/review', {id: ${SID}, next: model.nextTime}, function (result) {
                            utils.messager.closeProgress();
                            if (result.SID) {
                                utils.messager.alert("保存成功", function () {
                                    panelLoad('${BASE_PATH}/rec?SID=' + result.SID);
                                });
                            } else {
                                utils.messager.alert("保存失败");
                            }
                        }, "json");
                        return true;
                    }
                    return false;
                }
            })
        };

        function isValid(model) {
            var errors = ko.validation.group(model);
            if (errors().length == 0) return true;
            errors.showAllMessages();
            return false;
        }
    }

    var onPanelLoad = function () {
        ko.applyBindings(new ViewModel(), document.getElementById('kocontainer'));
    };

    //@ sourceURL=filename.js
</script>