<@layout.extends name="../../_layouts/nav.ftl">
    <@layout.put block="head">
    <style type="text/css">
       .icon-add {
            background: url("/resource/images/addico.png") no-repeat 2px center;
            padding-left: 20px;
        }
        
       .icon-edit {
            background: url("/resource/images/edtico.png") no-repeat 2px center;
            padding-left: 20px;
        }

        .icon-cancel {
            background: url("/resource/images/cancel.png") no-repeat 2px center;
            padding-left: 20px;
        }
        
       a:HOVER{
            background-color: #ADBDFF;
        }
    </style>
    </@>
    <@layout.put block="panel_title">
            【导航树】
    </@>
    <@layout.put block="panel_content">
    <table class="table">
        <thead>
        <tr>
            <th class="th_left">功能导航菜单</th>
            <th class="th_left">排序</th>
        </tr>
        </thead>
        <tbody data-bind="template: {name: 'treeNodeTemplate', foreach: treesNode}"></tbody>
    </table>
    
    <script type="text/html" id="treeNodeTemplate">
        <tr>
            <td data-bind="style: {'padding-left': 8+30*level()+'px'}">
                <span data-bind="visible: children().length!=0 || level()==1"><img src="/resource/images/foot02.png"/></span>
                <span data-bind="visible: children().length==0 && level()!=1"><img src="/resource/images/foot01.png"/></span>
                <a data-bind="text: name,click :$root.editNav"></a>
                <a href="javascript:void(0);" class="icon-edit" data-bind="click: $root.editNav" title="编辑"></a>
                <a href="javascript:void(0);" class="icon-cancel" data-bind="click: $root.removeNav,visible: children().length==0" title="删除"></a>
                <a href="javascript:void(0);" class="icon-add" data-bind="click: $root.addNav" title="添加子功能"></a>
            </td>
            <td data-bind="text: _index"></td>
        </tr>
        <!-- ko template: {name: 'treeNodeTemplate', foreach: children} --><!-- /ko -->
    </script>
    <script type="text/html" id="dlgNav">
        <div style="width:570px;">
            <p>
                 名称:
                <input type="text" data-bind="value: name" style="margin-left:28px;width: 400px;"/>
            </p>
            <p>
                 图标名称:
                <input type="text" data-bind="value: icon" style="width: 400px;"/>
            </p>
         URL:
                <input type="text" data-bind="value: url" style="margin-left:30px;width: 400px;"/>
            </p>
            <p>
                排序: <input type="number" data-bind="value: index" style="margin-left:28px;width: 50px"/>
            </p>
            <p>描述:</p>
            <textarea data-bind="value: desc" style="width:100%;height:50px"></textarea>
        </div>
    </script>
    </@>
    <@layout.put block="footer">
    <script type="text/javascript">
        function ViewModel(item) {
            function treeModel(parent,item) {
                var self = this;
                this.parent = parent ;
                item = item || {};
                this.id = ko.observable(item.id);
                this.name = ko.observable(item.name);
                this.icon = ko.observable(item.icon);
                this.url = ko.observable(item.url);
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
                    return new treeModel(self, item);
                }));
            };

            this.treesNode = ko.observableArray(ko.utils.arrayMap(item, function (item) {
                return new treeModel(null,item);
            }));
            
            this.addNav = function (model) {
                var tmpNav = new treeModel(model);

                utils.dialog.open({
                    template: $('#dlgNav').html(),
                    loaded: function (element) {
                        ko.applyBindings(tmpNav, element[0]);
                    },
                    ok: function () {
                        if (isValid(tmpNav) == false) return false;
                        saveNav(tmpNav, function () {
                            model.children.push(tmpNav);
                        });
                    }
                });
            };


            this.editNav = function (model) {
                var tmpModel = new treeModel();
                ko.utils.copyToModel(model, tmpModel);
                
                utils.dialog.open({
                    template: $('#dlgNav').html(),
                    loaded: function (element) {
                        ko.applyBindings(tmpModel, element[0]);
                        
                    },
                    ok: function () {
                        if (isValid(tmpModel) == false) return false;
                        saveNav(tmpModel, function () {
                            ko.utils.copyToModel(tmpModel, model);
                        })
                    }
                });
            };
            
            function saveNav(model, callback) {
                var params = {
                        name: model.name(),
                        icon: model.icon(),
                        url: model.icon(),
                        desc: model.desc(),
                        index: model.index(),
                        parentId: model.parent.id()
                    };
                    var id = ko.unwrap(model.id);
                    if (id != null) params.id = id;
                    $.post('${BASE_PATH}/save', params, function (result) {
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
            };

            this.removeNav = function (model) {
                 utils.messager.confirm("确定要删除该记录吗？",function(deleteActon){
                     if(deleteActon){
                         $.post("${BASE_PATH}/remove", {id: model.id()}, function (result) {
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
                 });
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

            var vm = new ViewModel(items);
            ko.applyBindings(vm);
        });

    </script>
    </@>
</@>

