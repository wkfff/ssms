<@layout.extends name="../../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="${RES}/css/base.css">
    <link rel="stylesheet" href="${RES}/css/common.css">
    <link rel="stylesheet" href="${RES}/css/layout.css">
    <link rel="stylesheet" href="${RES}/css/validate.css"/>
    <style type="text/css">
        a {
            line-height: 24px;
            padding: 4px 4px 4px 4px;
        }

        a:hover {
            background-color: #ADBDFF;
        }

        a:visited {
            color: #0000ee;
        }

        .readonly, input {
            width: 200px;
            display: inline-block;
            line-height: 22px;
            height: 22px;
        }

        .readonly {
            border: 0;
            border-bottom: 1px solid #CCCCCC;
            line-height: 27px;
            height: 27px;
        }

        .content ul {
            padding: 10px;
        }

        li {
            border: 1px solid #EEE;
            margin: 5px;
            width: 460px;
            float: left;
            background-color: #FAFAFA;
        }

        li .editArea {
            padding: 10px 5px;
            text-align: center;
        }

        li .editArea * {
            text-align: left;
        }

        li .toolbar {
            border-top: 1px solid #EEE;
            padding: 5px 10px;
            background-color: #F6F6F6;
            text-align: center;
        }

        .icon-fresh {
            background: url("${RES}/css/easyui/themes/icons/reload.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-add {
            background: url("${RES}/images/addico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-edit {
            background: url("${RES}/images/edtico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-delete {
            background: url("${RES}/images/delico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-save {
            background: url("${RES}/images/saveicon.png") no-repeat 2px center;
            padding-left: 20px;
        }
    </style>
    </@>

    <@layout.put block="contents">
    <div class="container">
        <header class="titlebar"><img src="${RES}/images/titleico.png"/>模板管理</header>
        <div class="content ue-clear">
            <div class="z-toolbar">
                <a href="javascript:void(0)" class="icon-add" data-bind="click : $root.addClick">添加模板</a>
                <a href="javascript:void(0)" class="icon-fresh" data-bind="click : $root.refreshClick">刷新</a>
            </div>
            <ul class="ue-clear" data-bind="template: { name: 'templates', foreach: lists }"></ul>
        </div>
    </div>

    <script type="text/html" id="templates">
        <li>
            <div class="editArea">
                模板名称：<span class="readonly" data-bind="text :name ,visible: editable() == false"></span>
                <input type="text" data-bind="textInput: name , visible : editable"/>
                <a href="javascript:void(0)" class="icon-edit" data-bind="click: $root.editClick, visible: editable()==false">编辑</a>
                <a href="javascript:void(0)" class="icon-edit" data-bind="click: $root.cancelEditClick, visible: editable()==true && id()!=null">取消编辑</a>
                <a href="javascript:void(0)" class="icon-save" data-bind="click: $root.saveClick, visible: editable">保存</a>
                <a href="javascript:void(0)" class="icon-delete" data-bind="click: $root.deleteClick">删除</a>
            </div>
            <div class="toolbar">
                <a class="icon-edit" data-bind="attr : {href: templateUrl}, visible: id">达标体系模板</a>
                <a class="icon-edit" data-bind="attr : {href: gradeUrl}, visible: id">评分标准模板</a>
                <a class="icon-edit" data-bind="attr : {href: reportUrl}, visible: id">自评报告模板</a>
                <a class="icon-edit" data-bind="attr : {href: reviewReportUrl}, visible: id">评审报告模板</a>&nbsp;
            </div>
        </li>
    </script>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="${RES}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${RES}/js/layui/layer.js"></script>
    <script type="text/javascript" src="${RES}/js/core.js"></script>
    <script type="text/javascript" src="${RES}/js/knockout/knockout.min.js"></script>
    <script type="text/javascript">
        function ViewModel() {
            var self = this;

            function TemplateModel(id, name) {
                this.id = ko.observable(id);
                this.name = ko.observable(name).extend({required: true});
                this.editable = ko.observable(id == null);

                this.templateUrl = ko.computed(function () {
                    return '/sys/stdtmp/manager?template=' + this.id();
                }, this);
                this.gradeUrl = ko.computed(function () {
                    return '/sys/stdtmp_grade/index?R_SID=' + this.id();
                }, this);
                this.reportUrl = ko.computed(function () {
                    return '/sys/stdtmp_rep/rec?type=1&R_SID=' + this.id();
                }, this);
                this.reviewReportUrl = ko.computed(function () {
                    return '/sys/stdtmp_rep/rec?type=2&R_SID=' + this.id();
                }, this);
            }

            self.lists = ko.observableArray(ko.utils.arrayMap(${json(templates)}, function (item) {
                return new TemplateModel(item.SID, item.C_NAME);
            }));

            self.refreshClick = function () {
                window.location.href = '${BASE_PATH}';
            };

            self.addClick = function () {
                var model = new TemplateModel();
                self.lists.push(model);
            };

            self.editClick = function (model) {
                model.editable(true);
            };

            self.cancelEditClick = function (model) {
                model.editable(false);
            };

            self.saveClick = function (model) {
                if (isValid(model) == false) return;
                var para = {name: model.name};
                if (model.id() != null) para.id = model.id();

                $.post('${BASE_PATH}/save', para, function (result) {
                    if (result) {
                        model.id(result);
                        model.editable(false);
                        utils.messager.alert("保存成功");
                    }
                    else utils.messager.alert("保存的时候出现了问题, 请联系管理员！");
                }, "json");
            };

            self.deleteClick = function (model) {
                if (ko.unwrap(model.id) != null) {
                    utils.messager.confirm("确定删除行业？", function () {
                        $.post('${BASE_PATH}/del', {id: model.id}, function (result) {
                            if (result) {
                                self.lists.remove(model);
                                utils.messager.alert("删除成功");
                            }
                            else utils.messager.alert("删除失败")
                        }, "json")
                    })
                }
                else {
                    self.lists.remove(model);
                }
            };

            function isValid(model) {
                var errors = ko.validation.group(model);
                if (errors().length == 0) return true;
                errors.showAllMessages();
                return false;
            }
        }

        $(function () {
            var vm = new ViewModel();
            ko.applyBindings(vm);
        });
    </script>
    </@>
</@>