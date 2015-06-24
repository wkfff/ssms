<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>专业管理</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <style type="text/css">
        .editor, .readonly {
            width: 140px;
            display: inline-block;
            line-height: 22px;
            height: 22px;
            border: 1px solid #c1d3de;
        }

        select.editor {
            line-height: 28px;
            height: 28px;
        }

        .readonly {
            border: 0;
            border-bottom: 1px solid #CCCCCC;
            line-height: 24px;
            height: 24px;
        }

        ul.profession, ul.profession li {
            margin: 10px;
        }

        .validationMessage {
            color: #f00;
        }

        a {
            line-height: 24px;
            padding: 4px 4px 4px 4px;
        }

        a:hover {
            background-color: #ADBDFF;
        }

        .icon-add {
            background: url("/resource/images/addico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-edit {
            background: url("/resource/images/edtico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-remove {
            background: url("/resource/images/delico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-save {
            background: url("/resource/images/saveicon.png") no-repeat 2px center;
            padding-left: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <header class="titlebar"><img src="/resource/images/bookico.png" /> 专业管理</header>
    <div class="content">
        <div class="z-toolbar">
            <a href="javascript:void(0);" class="icon-add" data-bind="click: addIndustry">添加行业</a>
        </div>
        <div style="padding: 21px;">
            <ul data-bind="template: { name: 'industry-template', foreach: industries }"></ul>
        </div>
    </div>
</div>

<script type="text/html" id="industry-template">
    <li>
        <div>
            <label>行业名称
                <span class="readonly" data-bind="text: name, visible: editable() == false"></span>
                <input class="editor" type="text" data-bind="textInput: name, visible: editable"/>
            </label>

            <a href="javascript:void(0);" class="icon-save" data-bind="click: $root.saveIndustry, visible: editable">保存</a>
            <a href="javascript:void(0);" class="icon-edit" data-bind="click: $root.editIndustry, visible: editable() == false">编辑</a>
            <a href="javascript:void(0);" class="icon-remove" data-bind="visible: professions().length==0, click: $root.removeIndustry">删除</a>
            <a href="javascript:void(0);" class="icon-add" data-bind="click: $root.addProfession, visible: id">添加专业</a>
        </div>
        <ul class="profession" data-bind="template: { name: 'profession-template', foreach: professions }"></ul>
    </li>
</script>
<script type="text/html" id="profession-template">
    <li>
        <label>专业名称
            <span class="readonly" data-bind="text: name, visible: editable() == false"></span>
            <input class="editor" type="text" data-bind="textInput: name, visible: editable"/>
        </label>
        <label>模板
            <span class="readonly" data-bind="text: selectedTemplate()? selectedTemplate().NAME: '', visible: editable() == false"></span>
            <select class="editor" data-bind="options:$root.templates,
                               optionsText: 'NAME',
                               optionsCaption: '请选择模板...',
                               value: selectedTemplate,
                               visible: editable()"></select>
        </label>
        <a href="javascript:void(0);" class="icon-save" data-bind="click: $root.saveProfession, visible: editable">保存</a>
        <a href="javascript:void(0);" class="icon-edit" data-bind="click: $root.editProfession, visible: editable() == false">编辑</a>
        <a href="javascript:void(0);" class="icon-remove" data-bind="click: function(model, bindContext) { $root.removeProfession($parent, model, bindContext) }">删除</a>
    </li>
</script>
</body>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
<script type="text/javascript">
    function ViewModel() {
        var self = this;

        function industryModel(id, name, professions) {
            var self = this;
            this.id = ko.observable(id);
            this.name = ko.observable(name).extend({required: true});

            this.professions = ko.observableArray(ko.utils.arrayMap(professions, function (item) {
                return new professionModel(self, item.id, item.name, item.selectedTemplate);
            }));
            this.editable = ko.observable(id == null);
        }

        function professionModel(industry, id, name, selectedTemplate) {
            this.id = ko.observable(id);
            this.name = ko.observable(name).extend({required: true});
            this.selectedTemplate = ko.observable(ko.utils.arrayFirst(self.templates, function (item) {
                return item.CODE == selectedTemplate;
            })).extend({required: true});
            this.industry = industry;
            this.editable = ko.observable(id == null);
        }

        self.templates = ${json(templates)};
        self.industries = ko.observableArray(ko.utils.arrayMap(${json(industries)}, function (item) {
            return new industryModel(item.id, item.name, item.professions);
        }));

        self.addIndustry = function () {
            self.industries.push(new industryModel());
        };

        self.saveIndustry = function (model) {
            if (isValid(model) == false) return;
            var para = {name: model.name};
            if (model.id() != null) para.id = model.id();
            $.post('${BASE_PATH}/saveIndustry', para, function (result) {
                if (result) {
                    model.id(result);
                    model.editable(false);
                    alert("保存成功");
                }
                else alert("保存的时候出现了问题, 请联系管理员！");
            }, "json");
        };

        self.editIndustry = function (model) {
            model.editable(true);
        };

        self.removeIndustry = function (model, bindContext) {
            if (ko.unwrap(model.id) != null) {
                if (confirm("确定删除行业？") == true)
                    $.post('${BASE_PATH}/removeIndustry', {id: model.id}, function (result) {
                        if (result) {
                            self.industries.remove(model);
                            alert("删除成功");
                        }
                        else alert("删除失败")
                    }, "json")
            }
            else {
                self.industries.remove(model);
            }
        };

        self.addProfession = function (model) {
            model.professions.push(new professionModel(model));
        };

        self.saveProfession = function (model) {
            if (isValid(model) == false) return;
            var para = {
                industryId: model.industry.id(),
                name: model.name,
                template: model.selectedTemplate().CODE
            };
            if (model.id() != null) para.id = model.id();
            $.post('${BASE_PATH}/saveProfession', para, function (result) {
                if (result) {
                    model.id(result);
                    model.editable(false);
                    alert("保存成功");
                }
                else alert("保存的时候出现了问题, 请联系管理员！");
            }, "json");
        };

        self.editProfession = function (model) {
            model.editable(true);
        };

        self.removeProfession = function (data, model, bindContext) {
            if (ko.unwrap(model.id) != null) {
                if (confirm("确定删除专业？") == true)
                    $.post('${BASE_PATH}/delProfession', {sid: model.id()}, function (result) {
                        data.professions.remove(model);
                    }, "json")
            }
            else {
                data.professions.remove(model);
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
</html>