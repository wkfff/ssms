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
        <a class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: testClick, visible: readonly">检验</a>
        
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="panelLoad('${BASE_PATH}/?fileid=${file.id}');">返回</a>
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
                <col style="width: 155px"/>
                <col style="width: 255px"/>
                <col style="width: 155px"/>
                <col style="width: 255px"/>
            </colgroup>

            <tr>
                <td><span class="must">*</span>安全附件名称:</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_NAME"/>
                </td>
                 <td>型号:</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_SPEC"/>
                </td>
            </tr>

            <tr>
                <td>出厂编号:</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_NO_FACTORY"/>
                </td>
                <td>所属特种设备安装位置:</td>
                <td>
                    <input data-bind="disable: $parent.readonly, value: C_POSITION"/>
                </td>
            </tr>
           
            <tr>
                <td><span class="must">*</span>检验日期:</td>
                <td>
                    <input data-bind="disable: $parent.readonly, dateValue: T_TEST_LAST"/>
                </td>
                <td><span class="must">*</span>下次检验日期:</td>
                <td>
                    <input data-bind="disable: $parent.readonly, dateValue: T_TEST_NEXT" />
                </td>
            </tr>
            
            <tr>
                <td>检验报告编号:</td>
                <td>
                    <input data-bind="disable: $parent.readonly,value: C_NO_REP"/>
                </td>
                <td>检验单位</td>
                <td>
                    <input data-bind="disable: $parent.readonly, value: C_TEST_UNIT"/>
                </td>
            </tr>
             <tr>
                <td>检验结论</td>
                <td colspan=3><input data-bind="disable: $parent.readonly, value: C_TEST_CON"/></td>
            </tr>
            <#if file.explain?? && file.explain?length!=0>
            <tr>
            <td colspan="4">
                <div style="border: 1px dashed  #ccc; margin-bottom: 5px;position: relative;">
                <div style="background-color: #CCCCCC;">政策解读:</div>
                <table style="table-layout: auto;"></table>
                ${file.explain}
                </div>
            </td>
            </tr>
            </#if>
        </table>
    </form>
</div>
<script type="text/html" id="testTemplate">
    <p>
    <label for="nextTestTime">下次检验时间:</label>
    <input type="text" data-bind="dateValue: nextTime" id="nextTestTime" style="width: 160px"/>
    </p>
    <p>
    <label for="lastTestTime" style="margin-right:28px">检验时间:</label>
    <input type="text" data-bind="dateValue: lastTime" id="lastTestTime" style="width: 160px"/>
    </p>
</script>
<script type="text/javascript">
function ViewModel() {
    function FormModel() {
        this.C_NAME= ko.observable('${C_NAME!}').extend({required:true});
        this.C_SPEC= ko.observable('${C_SPEC!}');
        this.C_NO_FACTORY= ko.observable('${C_NO_FACTORY!}');
        this.C_POSITION= ko.observable('${C_POSITION!}');
        this.T_TEST_LAST= ko.observable('${T_TEST_LAST!}').extend({required:true});
        this.T_TEST_NEXT= ko.observable('${T_TEST_NEXT!}').extend({required:true});
        this.C_NO_REP= ko.observable('${C_NO_REP!}');
        this.C_TEST_CON= ko.observable('${C_TEST_CON!}');
        this.C_TEST_UNIT= ko.observable('${C_TEST_UNIT!}');
        this.SID= ${SID!"undefined"};
        this.R_TMPFILE= '${file.id}';
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
    this.testClick = function () {
        var model = {
                nextTime: ko.observable().extend({required: true}),
                lastTime: ko.observable().extend({required: true})
                };
        utils.dialog.open({
            title: "请填写检验信息",
            templateId: '#testTemplate',
            loaded: function (element) {
                ko.applyBindings(model, element[0]);
            },
            ok: function () {
                if (isValid(model)) {
                    utils.messager.showProgress();
                    $.post('${BASE_PATH}/test', {id: ${SID!"undefined"}, next: model.nextTime,last :model.lastTime}, function (result) {
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
</script>