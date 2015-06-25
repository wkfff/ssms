<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css">
    <link rel="stylesheet" href="/resource/css/common.css">
    <link rel="stylesheet" href="/resource/css/layout.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
</head>
<style type="text/css">
table ,tr{ 
    width:100%;
    vertical-align: middle;
}
td{
    border-color: #ccc;
    border-style: dotted;
    border-width: 0 1px 1px 0;
    vertical-align: middle;
    height:30px;
    margin: 0;
}
table th {
    background-color:#daeef5;
    border:1px solid #c1d3de;
    border-bottom:0px;
    padding-left:25px;
    font-weight:bold;
    font-size:13px;
    padding:7px 25px;
    background:url(/resource/images/righttitlebig.png) repeat-x;
    text-align:left;
}
a {
    display: inline-block;
    font-size: 12px;
    line-height: 24px;
    margin: 0 4px;
    padding: 0;
    vertical-align: top;
    width: auto;
}
a:HOVER{
    background-color: #ADBDFF;
}
.icon-fresh{
    background: url("/resource/css/easyui/themes/icons/reload.png")
    no-repeat 2px center ;
    padding-left: 20px;
}
.icon-add{
    background: url("/resource/images/addico.png")
    no-repeat 2px center ;
    padding-left: 20px;
}
.icon-edit{
    background: url("/resource/images/edtico.png")
    no-repeat 2px center ;
    padding-left: 20px;
}
.icon-delete{
    background: url("/resource/images/delico.png")
    no-repeat 2px center ;
    padding-left: 20px;
}
.icon-save{
    background: url("/resource/images/saveicon.png")
    no-repeat 2px center ;
    padding-left: 20px;
}

.diversification {
            background: #00bfff;
        }
</style>
<body>
    <div class="container" >
    <header class="titlebar" style="padding-left:21px"><img src="/resource/images/titleico.png" />模板管理</header>
     <div class="content">
        <div class="z-toolbar">
     <a href="javascript:void(0)" class="icon-fresh" data-bind="click : refreshClick">刷新</a>
     <a href="javascript:void(0)" class="icon-add"   data-bind="click : addClick">添加</a>
     <a href="javascript:void(0)" class="icon-edit"  data-bind="click : editClick">编辑</a>
     <a href="javascript:void(0)" class="icon-delete" data-bind="click : deleteClick">删除</a>
     <a href="javascript:void(0)" class="icon-save" data-bind="click : saveClick">保存</a> 
         </div>
    </div>
    </div>
    <table><tr><th style="width:0;padding-left:14px;padding-right:14px"></th><th >模板名称</th><th style="width:70%">操作</th></tr></table>
    <table id = "dg" data-bind="template: { name: 'templates', foreach: lists }" >
   </table>
</body>
<script type="text/html" id="templates">
        <tr data-bind="event : {dblclick : dblClick} ,click: Click,css: {diversification: colorable}" >
        <td style="width:0;padding-left:11px;padding-right:11px"><span data-bind="text :$index()+1" ></span></td>
        <td><span data-bind="text :name ,visible: editable() == false" ></span><input size="45" type="text"  data-bind="textInput: name , visible : editable" /></td>
        <td style="width:70%" data-bind="visible : id"> <a href='javascript:void(0)' data-bind="click : editSTDTMP"><img src='/resource/images/edtico.png' />编辑达标体系模板</a>&nbsp;&nbsp;<a href='javascript:void(0)' data-bind="click : editGRADETMP"><img src='/resource/images/edtico.png' />编辑评分标准模板</a>&nbsp;&nbsp;<a href='javascript:void(0)' data-bind="click : editREPTMP"><img src='/resource/images/edtico.png' />编辑自评报告模板</a></td>
        </tr>
</script>
    <script  type="text/javascript">
    function viewModel() {
        var self = this;
        var preModel;//保存前一条model
        var preColor;
        self.nowModel=ko.observable();//当前行
        function templateModel(id, name){
        	this.id = ko.observable(id);
            this.name = ko.observable(name).extend({required: true});
            this.editable = ko.observable(id == null);
            this.colorable = ko.observable(id == null);//点击那行标记
            this.Click = function(model){
            	model.colorable(true);
            	self.nowModel(model);
            	if (preColor!=null) preColor.colorable(false);
            	preColor = model;
            };
            this.dblClick=function(model){
                model.editable(true);
                model.colorable(true);
                if (preModel!=null) {
                	preModel.editable(false);
                	preModel.colorable(false);
                }
                preModel = model;
            };
            this.editSTDTMP = function () {
                window.location.href = '/sys/stdtmp/index?R_SID='+id;
            };
            this.editGRADETMP = function () {
                window.location.href = '/sys/stdtmp_grade/index?R_SID='+id;
            };
            this.editREPTMP = function () {
                window.location.href = '/sys/stdtmp_rep/rec?R_SID='+id;
            };
        }
        self.lists=ko.observableArray(ko.utils.arrayMap(${json(templates)}, function (item) {
            return new templateModel(item.SID, item.C_NAME);
        }));
        
        self.refreshClick =function(){ 
        	window.location.href = 'index';
        	};
        	
        self.addClick =function(){
        	var model=new templateModel();
        	self.nowModel(model);
        	self.lists.push(model);
        };
        self.editClick =function(){
        	if(self.nowModel()==null) {
        		alert("选择一条记录");
        		return;
        	}else{
        		self.nowModel().editable(true);
        		if (preModel!=null) preModel.editable(false);
                preModel = self.nowModel();
        	}
            
        };
        self.deleteClick =function(){
        	if(self.nowModel()==null) {
                alert("选择一条记录");
                return;
            }
            if (confirm("您确认删除选定的记录吗？")){
                    $.get("del", {sid: self.nowModel().id()}, function (data) {
                        if (data == "true" || data == "\"\"") {
                            alert("删除选定的记录成功");
                            refresh();
                        }
                        else {
                            alert("删除失败");
                        }
                    });
             }
        };
        
        self.saveClick =function(){
        	if(self.nowModel()==null) {
                return;
            }
            $.post("save", {name: self.nowModel().name(),id:self.nowModel().id()}, function (flag) {
            	if(flag){
            		 alert('成功保存记录！')
            		 refresh();
            	}else{
            		  alert("保存记录失败");
            	}
               
            }, 'json');
        };
        
        function refresh(){
        	window.location.href = '${BASE_PATH}';
        }
    };
    $(function(){
        var vm=new viewModel();
        ko.applyBindings(vm);
    });
    
    </script>
</html>