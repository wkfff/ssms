<@layout.extends name="../../_layouts/nav.ftl">
    <@layout.put block="head">
        <style type="text/css">
            .rootImg{
               background: url("/resource/images/foot02.png") no-repeat 2px center;
               padding-left: 10px;
            }
            .childrenImg{
                background: url("/resource/images/bot.png") no-repeat 2px center;
                padding-left: 13px;
                background-size: 7px 7px;
            }
            .chose{
                background-color: #9cc8f7;
            }
            .up{
                background: url("/resource/images/blue/up.png") no-repeat 2px center;
                padding-left: 20px;
                background-size: 14px 14px;
            }
            .down{
                background: url("/resource/images/blue/down.png") no-repeat 2px center;
                padding-left: 20px;
                background-size: 14px 14px;
            }
            .add{
                background: url("/resource/images/addico.png") no-repeat 2px center;
                padding-left: 20px;
                background-size: 14px 14px;
            }
            .edit{
                background: url("/resource/images/edtico.png") no-repeat 2px center;
                padding-left: 20px;
                background-size: 14px 14px;
            }
            .cancel{
                background: url("/resource/images/cancel.png") no-repeat 2px center;
                padding-left: 20px;
                background-size: 14px 14px;
            }
            
            table a, a:visited {
                text-decoration:none;
                color: #000;
            }
            table a:HOVER {
               text-decoration: underline;
               color :blue; 
            }
            .toolbar a:HOVER{
                background-color: #ADBDFF; 
            }
        </style>
    </@>
    <@layout.put block="panel_title">
            ${title!}
    </@>
    <@layout.put block="panel_tools">
    </@>
    <@layout.put block="panel_content">
    <div class="toolbar">
        <a class="add" data-bind="click : addRoot">添加根目录</a>
        <a class="up" style="margin-left:10px;"  data-bind="click: upMove">上移</a>
        <a class="down" data-bind="click : downMove">下移</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th class="th_left" style="width: 200px">功能导航菜单</th>
            <th  class="th_left" style="width: 200px">URL</th>
            
            <th >操作</th>
        </tr>
        </thead>
        <tbody data-bind="template: {name: 'treeNodeTemplate', foreach: treesNode}" ></tbody>
    </table>
    <script type="text/html" id="treeNodeTemplate">
        <tr data-bind="click:$root.click,css:{chose:chosed}">
            <td data-bind="style: {'padding-left': 8+30*level()+'px'}">
                <span data-bind="css: {rootImg : children().length!=0 || level()==0,childrenImg : children().length==0 && level()!=0}"></span>
                <a  data-bind="text: name,click :$root.editNav"></a>
            </td>
            <td>
                <a data-bind="text: url"></a>
           </td>
           
           <td>
                <a href="javascript:void(0);" class="add" data-bind="click: $root.addNav">添加子功能</a>
                <a href="javascript:void(0);" class="edit" data-bind="click: $root.editNav">编辑</a>
                <a href="javascript:void(0);" class="cancel" data-bind="click: $root.removeNav,visible: children().length==0">删除</a>
           </td>
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
            <p>描述:</p>
            <textarea data-bind="value: desc" style="width:100%;height:50px"></textarea>
        </div>
    </script>
    </@>
    <@layout.put block="footer">
    <script type="text/javascript">
        function ViewModel(item) {
            var self = this;
            function treeModel(parent,item) {
                var self = this;
                this.parent = parent;
                item = item || {};
                this.chosed = ko.observable(false);
                this.id = ko.observable(item.id);
                this.name = ko.observable(item.name).extend({required:true});
                this.icon = ko.observable(item.icon);
                this.url = ko.observable(item.url);
                this.desc = ko.observable(item.desc);
                this.index = ko.observable(item.index);
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
            
            this.currModel = ko.observable();
            this.currModel.subscribe(function(newModel){
                if (newModel) newModel.chosed(true);
            });
            this.currModel.subscribe(function(oldValue){
                if (oldValue) oldValue.chosed(false);
            }, null, "beforeChange");
            this.click=function(model){
                self.currModel(model);
            };
            this.preModel = ko.observable();
            this.upMove = function(){
                if(self.currModel()==null){
                    utils.messager.alert("请选中一条记录");
                    return;
                }
                var items;
                if(self.currModel().parent==null){
                    items = self.treesNode();
                }else{
                    items = self.currModel().parent.children();
                }
               for(var i=0;i<items.length;i++){
                    if((items[i].index())<(self.currModel().index())){
                        self.preModel(items[i]);
                    }
                    if(items[i].id()==self.currModel().id()) break;
                    if(items[i].index()==self.currModel().index()){
                        var k=items[i].index();
                        k++;
                        self.preModel(items[i]);
                        self.currModel().index(k);
                    }
                }
                if(self.preModel()==null) utils.messager.alert("第一条数据无法上移");
                else{
                    $.post("${BASE_PATH}/move",
                            {currId:self.currModel().id(),currIndex:self.currModel().index(),replaceId:self.preModel().id(),replaceIndex:self.preModel().index()},
                            function(result){
                                if(result!=null&&result.length>0){
                                    self.currModel().index(result[0]);
                                    self.preModel().index(result[1]);
                                    sortNav();
                                }else{
                                    utils.messager.alert("上移失败")
                                }
                            },'json');
                }
            };
            this.downMove = function(){
                if(self.currModel()==null){
                    utils.messager.alert("请选中一条记录");
                    return;
                }
                var items;
                if(self.currModel().parent==null){
                    items = self.treesNode();
                }else{
                    items = self.currModel().parent.children();
                }
               for(var i=items.length-1;i>=0;i--){
                    if((items[i].index())>(self.currModel().index())){
                        self.preModel(items[i]);
                    }
                    if(items[i].id()==self.currModel().id()) break;
                    if(items[i].index()==self.currModel().index()){
                        var k=items[i].index();
                        k++;
                        self.preModel(items[i]);
                        self.preModel().index(k);
                    }
                }
                if(self.preModel()==null) utils.messager.alert("最后一条数据无法下移");
                else{
                    $.post("${BASE_PATH}/move",
                            {currId:self.currModel().id(),currIndex:self.currModel().index(),replaceId:self.preModel().id(),replaceIndex:self.preModel().index()},
                            function(result){
                                if(result!=null&&result.length>0){
                                    self.currModel().index(result[0]);
                                    self.preModel().index(result[1]);
                                    sortNav();
                                }else{
                                    utils.messager.alert("下移失败")
                                }
                            },'json');
                }
            }
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
                            self.currModel(tmpNav);
                            sortNav();
                        });
                    }
                });
            };
            this.addRoot = function (){
                var tmpRoot = new treeModel(null,null);
                tmpRoot.parent=new treeModel(null,null);
                tmpRoot.parent.id('${parentId}');
                utils.dialog.open({
                    template: $('#dlgNav').html(),
                    loaded: function(element){
                        ko.applyBindings(tmpRoot,element[0]);
                    },
                    ok : function () {
                        if(isValid(tmpRoot)==false) return false;
                        saveNav(tmpRoot,function(){
                            tmpRoot.parent=null;
                            self.treesNode.push(tmpRoot);
                            self.currModel(tmpRoot);
                            sortNav();
                        });
                    }
                })
            }

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
                            sortNav();
                        })
                    }
                });
            };
            
            function saveNav(model, callback) {
                var params = {
                        name: model.name(),
                        icon: model.icon(),
                        url: model.url(),
                        desc: model.desc()
                    };
                    var id = ko.unwrap(model.id);
                    var index = ko.unwrap(model.index);
                    if (id != null) params.id = id;
                    if (model.parent != null) params.parentId = model.parent.id();
                    if (index==null){
                        if(model.parent.index()!=null) {
                            var listAll = model.parent.children();
                            var i = model.parent.children().length;
                            if (i==0) params.index = 1;
                            else params.index = listAll[i-1].index()+1;
                        }else{
                            var listAll=self.treesNode()
                            var i = self.treesNode().length;
                            if (i==0) params.index = 1;
                            else params.index = listAll[i-1].index()+1;
                        }
                        model.index(params.index);
                    }
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
                                     if(model.parent!=null){
                                         model.parent.children.remove(model);
                                     }else{
                                         self.treesNode.remove(model);
                                     }
                                 })
                             }
                             else {
                                 utils.messager.alert('删除失败!');
                             }
                         }, 'json');
                     }
                 });
            };

            function sortNav(){
                var items;
                var tmp;
                if(self.currModel().parent==null){
                    items=self.treesNode();
                }else{
                    items = self.currModel().parent.children();
                }
                for(var i=0;i<items.length;i++){
                    for(var j=i+1;j<items.length;j++){
                        if(items[i].index()>items[j].index()){
                            tmp=items[j];
                            items[j]=items[i];
                            items[i]=tmp;
                        }
                    }
                }
                if(self.currModel().parent==null){
                    self.treesNode(items);
                }else{
                    self.currModel().parent.children(items);
                }
                self.preModel(null);
            };
            
            function isValid(model) {
                var errors = ko.validation.group(model);
                if (errors().length == 0) return true;
                errors.showAllMessages();
                return false;
            }
            
            
        };
        
        $(function () {
            var item = ${json(item)};
            ko.applyBindings(new ViewModel(item));
        });

    </script>
    </@>
</@>

