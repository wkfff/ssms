<@layout.extends name="../../_layouts/stdtmp.ftl">
    <@layout.put block="head">
    <style type="text/css">
        .icon-folder {
            background: url("/resource/images/foot02.png") no-repeat 0 center;
            padding-left: 20px;
        }

        .icon-file {
            background: url("/resource/images/rmail.png") no-repeat 0 center;
            padding-left: 20px;
        }

    </style>
    </@>

    <@layout.put block="panel_title"> 【${template.name}】达标体系
    <span style="color: #ff0000">(当前版本:${template.version})</span>
    </@>
    <@layout.put block="panel_tools" type="PREPEND">
    <a data-bind="click: publish">发布模板</a>
    </@>
    <@layout.put block="panel_content">
    <table class="table">
        <thead>
        <tr>
            <th class="th_left">体系目录名称</th>
            <th class="th_left">排序</th>
            <th style="width: 200px">操作</th>
        </tr>
        </thead>
        <tbody data-bind="template: {name: 'folderTemplate', foreach: folders}"></tbody>
    </table>

    <script type="text/html" id="folderTemplate">
        <tr>
            <td data-bind="style: {'padding-left': 8+30*level()+'px'}">
                <span class="icon-folder" data-bind="text:name"></span>
            </td>
            <td data-bind="text: _index"></td>
            <td>
                <a href="javascript:void(0);" class="icon-edit" data-bind="click: $root.addFolder" title="添加子目录">添加目录</a>
                <a href="javascript:void(0);" class="icon-edit" data-bind="click: $root.addFile" title="添加文件">添加文件</a>
                <a href="javascript:void(0);" class="icon-edit" data-bind="click: $root.editFolder">编辑</a>
                <a href="javascript:void(0);" class="icon-remove" data-bind="click: $root.removeFolder, visible: children().length == 0 && files().length == 0">删除</a>
            </td>
        </tr>
        <!-- ko template: {name: 'folderTemplate', foreach: children} --><!-- /ko -->
        <!-- ko template: {name: 'fileTemplate', foreach: files} --><!-- /ko -->
    </script>
    <script type="text/html" id="fileTemplate">
        <tr>
            <td data-bind="style: {'padding-left': 8+30*level()+'px'}">
                <a class="icon-file" data-bind="text: name, attr: {href: templateUrl}"></a>
            </td>
            <td data-bind="text: _index"></td>
            <td>
                <a href="javascript:void(0);" class="icon-edit" data-bind="click: $root.editFile">编辑</a>
                <a href="javascript:void(0);" class="icon-remove" data-bind="click: $root.removeFile">删除</a>
                <a data-bind="attr: {href: templateUrl}">编辑模板</a>
            </td>
        </tr>
    </script>
    <script type="text/html" id="dlgFolder">
        <div style="width:570px;">
            <p>
                目录名称: <input style="width:400px" type="text" data-bind="value: name"/>
            </p>
            <p>
                目录排序: <span data-bind="text: parent._index()+'-'"></span><input type="number" data-bind="value: index" style="width: 50px"/>
            </p>
            <p>描述: </p>
            <textarea data-bind="value: desc" style="width:100%;height:150px;"></textarea>
        </div>
    </script>
    <script type="text/html" id="dlgFile">
        <div style="width:570px;" data-bind="with:model">
            <p>
                文件名称:
                <input type="text" data-bind="value: name" style="width: 400px;"/>
            </p>

            <p>
                更新周期:
                <input data-bind="value: (cycleUnit()==null ? null : cycleValue),disable:cycleUnit()==null" style="width: 50px;"/>
                <select data-bind="options: $root.cycleSource,
                                   optionsText:'name',
                                   optionsValue: 'code',
                                   optionsCaption: '请选择周期...',
                                   value: cycleUnit">
                </select>
                <label><input type="checkbox" data-bind="checked : remind"/>是否提醒</label>
            </p>

            <p>
                模板文件:
                <select data-bind="options: $root.tmpfilesSource,
                                   optionsText: 'name',
                                   optionsValue: 'code',
                                   optionsCaption: '请选择模板...',
                                   value: templateFileCode"
                        style="width: 400px;">
                </select>
            </p>
            <p>
                文件排序: <span data-bind="text: parent._index()+'-'"></span><input type="number" data-bind="value: index" style="width: 50px"/>
            </p>
            <p>政测解读:</p>
            <textarea data-bind="value: explain" style="width:100%;height:50px"> </textarea>
            <p>描述:</p>
            <textarea data-bind="value: desc" style="width:100%;height:50px"></textarea>
        </div>
    </script>

    <script type="text/html" id="dlgStatus">
        <div style="height: 300px; width: 450px; overflow: auto; white-space:nowrap;">
            <!-- ko foreach: logs -->
            <p data-bind="text: $data"></p>
            <!-- /ko -->
        </div>
        <div class="layui-layer-btn" data-bind="visible: status">
            <a class="layui-layer-btn0" data-bind="click: finish">确定</a></div>
    </script>
    </@>
    <@layout.put block="footer">
    <script type="text/javascript">
        function reload() {
            window.location.href = "${BASE_PATH}/manager?template=${template.id}";
        }
        function ViewModel(template, folders) {
            this.publish = function () {
                utils.messager.confirm("确定要发布模板吗？", function (_index) {
                    layer.close(_index);
                    var publishResult = {
                        status: ko.observable(),
                        logs: ko.observableArray(),
                        finish: function () {
                            layer.close(index);
                            $.post("${BASE_PATH}/finish/${template.id}", reload);
                        }
                    };

                    var index = layer.open({
                        type: 1,
                        title: "发布状态",
                        closeBtn: false, //不显示关闭按钮
                        content: $('#dlgStatus').html(),
                        maxWidth: 500,
                        scrollbar: false,
                        success: function (layero) {
                            ko.applyBindings(publishResult, layero[0]);
                        }
                    });

                    var callback = function (result) {
                        publishResult.status(result.status == "FINISH");
                        publishResult.logs(result.logs);
                        if (publishResult.status()) return;
                        setTimeout(function () {
                            $.post('${BASE_PATH}/status/${template.id}', callback, 'json');
                        }, 3000);
                    };
                    $.post('${BASE_PATH}/publish/${template.id}', callback, 'json');
                });
            };

            var fileViewModel = {
                cycleSource: ${json(SYS_CYCLE)},
                tmpfilesSource: ${json(tmpfiles)},
                getCycle: function (code) {
                    for (var i = 0; i < this.cycleSource.length; i++) {
                        if (this.cycleSource[i].code == code) return this.cycleSource[i];
                    }
                },
                model: null
            };

            function FolderModel(parent, item) {
                var self = this;
                this.parent = parent;
                item = item || {};
                this.id = ko.observable(item.id);

                this.name = ko.observable(item.name).extend({required: true});
                this.desc = ko.observable(item.desc);
                this.index = ko.observable(item.index || 0);
                this._index = ko.computed(function () {
                    var parent = this.parent;
                    if (parent == null) return this.index();
                    return this.parent._index() + '-' + this.index();
                }, this);

                this.level = ko.computed(function () {
                    var level = 0;
                    var parent = this.parent;
                    while (parent != null) {
                        level++;
                        parent = parent.parent;
                    }
                    return level;
                }, this);
                this.children = ko.observableArray(ko.utils.arrayMap(item.children, function (item) {
                    return new FolderModel(self, item);
                }));

                this.files = ko.observableArray(ko.utils.arrayMap(item.files, function (item) {
                    return new FileModel(self, item);
                }));
            }

            function FileModel(parent, item) {
                item = item || {};

                this.parent = parent;
                this.id = ko.observable(item.id);
                this.name = ko.observable(item.name).extend({required: true});
                this.desc = ko.observable(item.desc);
                this.explain = ko.observable(item.explain);
                this.templateFileCode = ko.observable(item.templateFileCode).extend({required: true});
                this.remind = ko.observable(item.remind == '1');
                this.cycleUnit = ko.observable(item.cycleUnitCode);
                this.cycleValue = ko.observable(item.cycleValue);
                this.index = ko.observable(item.index || 0);
                this._index = ko.computed(function () {
                    var parent = this.parent;
                    if (parent == null) return this.index();
                    return this.parent._index() + '-' + this.index();
                }, this);

                this.templateUrl = "${BASE_PATH}/file/" + item.id;
                this.level = ko.computed(function () {
                    var level = 0;
                    var parent = this.parent;
                    while (parent != null) {
                        level++;
                        parent = parent.parent;
                    }
                    return level;
                }, this);
            }


            this.folders = ko.observableArray(ko.utils.arrayMap(folders, function (item) {
                return new FolderModel(null, item);
            }));

            this.addFolder = function (model) {
                // 弹出对话框
                var tmpFolder = new FolderModel(model);

                utils.dialog.open({
                    template: $('#dlgFolder').html(),
                    loaded: function (element) {
                        ko.applyBindings(tmpFolder, element[0]);
                    },
                    ok: function () {
                        if (isValid(tmpFolder) == false) return false;
                        saveFolder(tmpFolder, function () {
                            model.children.push(tmpFolder);
                        });
                    }
                });
            };

            this.editFolder = function (model) {
                var tmpModel = new FolderModel();
                ko.utils.copyToModel(model, tmpModel);

                utils.dialog.open({
                    template: $('#dlgFolder').html(),
                    loaded: function (element) {
                        ko.applyBindings(tmpModel, element[0]);
                    },
                    ok: function () {
                        if (isValid(tmpModel) == false) return false;
                        saveFolder(tmpModel, function () {
                            ko.utils.copyToModel(tmpModel, model);
                        })
                    }
                });
            };

            this.removeFolder = function (model) {
                if (ko.unwrap(model.id) == null) model.parent.files.remove(model);
                else {
                    $.post("${BASE_PATH}/removeFolder", {id: model.id()}, function (result) {
                        if (result != false) {
                            utils.messager.alert('删除成功!', function () {
                                model.parent.children.remove(model);
                            })
                        }
                        else {
                            utils.messager.alert('删除失败!');
                        }
                    }, 'json');
                }
            };

            this.addFile = function (model) {
                // 弹出对话框
                fileViewModel.model = new FileModel(model);

                utils.dialog.open({
                    template: $('#dlgFile').html(),
                    loaded: function (element) {
                        ko.applyBindings(fileViewModel, element[0]);
                    },
                    ok: function () {
                        if (isValid(fileViewModel.model) == false) return false;
                        saveFile(fileViewModel.model, function () {
                            model.files.push(fileViewModel.model);
                        });
                    }
                });
            };

            this.editFile = function (model) {
                fileViewModel.model = new FileModel();
                ko.utils.copyToModel(model, fileViewModel.model);
                utils.dialog.open({
                    template: $('#dlgFile').html(),
                    loaded: function (element) {
                        ko.applyBindings(fileViewModel, element[0]);
                    },
                    ok: function () {
                        if (isValid(fileViewModel.model) == false) return false;
                        saveFile(fileViewModel.model, function () {
                            ko.utils.copyToModel(fileViewModel.model, model);
                        })
                    }
                });
            };
            function saveFolder(model, callback) {
                var params = {
                    name: model.name(),
                    desc: model.desc(),
                    index: model.index(),
                    template: template,
                    parent: model.parent.id()
                };
                var id = ko.unwrap(model.id);
                if (id != null) params.id = id;
                $.post('${BASE_PATH}/saveFolder', params, function (result) {
                    if (result != null) {
                        utils.messager.alert("保存成功!", function () {
                            model.id(result);
                            callback(model);
                        });
                    }
                    else {
                        utils.messager.alert("保存失败!");
                    }
                }, 'json');
            }

            function saveFile(model, callback) {
                var params = {
                    name: model.name(),
                    desc: model.desc(),
                    index: model.index(),
                    template: template,
                    parentId: model.parent.id(),
                    parentName: model.parent.name(),
                    templateFileCode: model.templateFileCode(),
                    cycleValue: model.cycleValue(),
                    explain: model.explain(),
                    remind: (model.remind() == true ? '1' : '0')
                };
                if (model.cycleUnit() != null) {
                    params.cycleUnitCode = model.cycleUnit();
                    params.cycleUnitName =fileViewModel.getCycle(model.cycleUnit()).name;
                }
                var id = ko.unwrap(model.id);
                if (id != null) params.id = id;
                $.post('${BASE_PATH}/saveFile', params, function (result) {
                    if (result != null) {
                        utils.messager.alert("保存成功!", function () {
                            model.id(result);
                            callback(model);
                        });
                    }
                    else {
                        utils.messager.alert("保存失败!");
                    }
                }, 'json');
            }

            this.removeFile = function (model) {
                var id = ko.unwrap(model.id);
                if (id == null) model.parent.files.remove(model);
                else {
                    $.post("${BASE_PATH}/removeFile", {id: id}, function (result) {
                        if (result != false) {
                            utils.messager.alert('删除成功!', function () {
                                model.parent.files.remove(model)
                            })
                        }
                        else {
                            utils.messager.alert('删除失败!');
                        }
                    }, 'json');
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
            var items = ${json(items)};

            var vm = new ViewModel(${template.id}, items);
            ko.applyBindings(vm)
        });

    </script>
    </@>
</@>

