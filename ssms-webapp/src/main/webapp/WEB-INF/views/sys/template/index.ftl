<@layout.extends name="../../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css">
    <link rel="stylesheet" href="/resource/css/common.css">
    <link rel="stylesheet" href="/resource/css/layout.css">
    <style type="text/css">
        table {
            width: 100%;
            vertical-align: middle;
        }

        a {
            line-height: 24px;
            padding: 4px 4px 4px 4px;
        }

        a:hover {
            background-color: #ADBDFF;
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
        li .editarea {
            padding-left: 5px;
            padding-right: 5px;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        li .toolbar {
            border-top: 1px solid #EEE;
            padding: 5px 10px;
            background-color: #F6F6F6;
            text-align: center;
        }

        .icon-fresh {
            background: url("/resource/css/easyui/themes/icons/reload.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-add {
            background: url("/resource/images/addico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-edit {
            background: url("/resource/images/edtico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-delete {
            background: url("/resource/images/delico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-save {
            background: url("/resource/images/saveicon.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .diversification {
            background: #00bfff;
        }
    </style>
    </@>

    <@layout.put block="contents">
    <div class="container">
        <header class="titlebar" style="padding-left:21px"><img src="/resource/images/titleico.png"/>模板管理</header>
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
            <div class="editarea">
                模板名称：<span class="readonly" data-bind="text :name ,visible: editable() == false"></span>
                <input type="text" data-bind="textInput: name , visible : editable"/>
                <a href="javascript:void(0)" class="icon-edit" data-bind="click: $root.editClick, visible: editable()==false">编辑</a>
                <a href="javascript:void(0)" class="icon-edit" data-bind="click: $root.cancelEditClick, visible: editable()==true && id()!=null">取消编辑</a>
                <a href="javascript:void(0)" class="icon-save" data-bind="click: $root.saveClick, visible: editable">保存</a>
                <a href="javascript:void(0)" class="icon-delete" data-bind="click: $root.deleteClick">删除</a>
            </div>
            <div class="toolbar">
                <a href='javascript:void(0)' class="icon-edit" data-bind="click : editSTDTMP">编辑达标体系模板</a>
                <a href='javascript:void(0)' class="icon-edit" data-bind="click : editGRADETMP">编辑评分标准模板</a>
                <a href='javascript:void(0)' class="icon-edit" data-bind="click : editREPTMP">编辑自评报告模板</a>
            </div>
        </li>
    </script>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript">
        function ViewModel() {
            var self = this;

            function TemplateModel(id, name) {
                this.id = ko.observable(id);
                this.name = ko.observable(name).extend({required: true});
                this.editable = ko.observable(id == null);

                this.editSTDTMP = function () {
                    window.location.href = '/sys/stdtmp/index?R_SID=' + id;
                };
                this.editGRADETMP = function () {
                    window.location.href = '/sys/stdtmp_grade/index?R_SID=' + id;
                };
                this.editREPTMP = function () {
                    window.location.href = '/sys/stdtmp_rep/rec?R_SID=' + id;
                };
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
                var para = { name: model.name };
                if (model.id() != null) para.id = model.id();

                $.post('${BASE_PATH}/save', para, function (result) {
                    if (result) {
                        model.id(result);
                        model.editable(false);
                        alert("保存成功");
                    }
                    else alert("保存的时候出现了问题, 请联系管理员！");
                }, "json");
            };

            self.deleteClick = function (model) {
                if (ko.unwrap(model.id) != null) {
                    if (confirm("确定删除行业？") == true)
                        $.post('${BASE_PATH}/del', {id: model.id}, function (result) {
                            if (result) {
                                self.lists.remove(model);
                                alert("删除成功");
                            }
                            else alert("删除失败")
                        }, "json")
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