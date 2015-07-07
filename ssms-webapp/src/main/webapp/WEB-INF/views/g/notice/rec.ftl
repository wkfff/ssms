<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css">
    <link rel="stylesheet" href="/resource/css/common.css">
    <link rel="stylesheet" href="/resource/js/layui/skin/layer.css" />
    <link rel="stylesheet" href="/resource/css/layout.css" />
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
   
    <style type="text/css">
        .button{height:30px;border:0;color:darkblue;cursor: pointer;}
        .button:disabled{height:30px;border:0;color:999;cursor:default;}
        .button2{height:30px;color:#00000;text-shadow:1px 1px 1px #fff;border:1px solid #dce1e6;box-shadow:0 1px 2px #fff inset,0 -1px 0 #a8abae inset;background:-webkit-linear-gradient(top,#f2f3f7,#e4e8ec);background:-moz-linear-gradient(top,#f2f3f7,#e4e8ec);background:linear-gradient(top,#f2f3f7,#e4e8ec);}
        .button:active{background:-webkit-linear-gradient(top,#fefefe,#ebeced);background:-moz-linear-gradient(top,#f2f3f7,#ebeced);background:linear-gradient(top,#f2f3f7,#ebeced);}
        table tr{height:40px;}
        table td{width:150px;text-align:left;}
        table.dialog tr{border-bottom:1px dashed gray;}
        a:HOVER{background-color:#ADBDFF;}
        .icon-fresh{background:url("/resource/css/easyui/themes/icons/reload.png")no-repeat 2px center;padding:5 5 5 20;}
        .icon-save{background:url("/resource/images/saveicon.png")no-repeat 2px center;padding:5 5 5 20;}
        .icon-delete{background:url("/resource/images/delico.png")no-repeat 2px center;padding:5 5 5 20;}
        .icon-publish{background:url("/resource/images/publish.jpg")no-repeat 2px center;padding:5 5 5 20;}
        .icon-search{background: url("/resource/images/search.png")no-repeat 2px center ;padding-left: 20px;}
        .receiver li{padding:0px;border:1px solid #999;float:left;margin:10px;}
        .received li{padding:5px;border-bottom:1px solid #EEE;}
        .user{border:1px solid blue;}
    </style>
</head>
<body>
    <div class="container">
        <header class="titlebar">
            <label>发布通知公告</label>
        </header>
        
        <div class="content ue-clear">

            <div class="toolbar">
                <a href="javascript:void(0)" class="icon-new"  data-bind="click: newClick">新建</a>
                <a href="javascript:void(0)" class="icon-save"  data-bind="click: saveClick">存为草稿</a>
                <a href="javascript:void(0)" class="icon-publish"  data-bind="click: publishClick">发布</a>
                <a href="javascript:void(0)" class="icon-delete"  data-bind="click: delClick">删除</a>
            </div>
    
            <div class="form">
                <div class="row">
                    <label style="padding-right:20px;">标题:</label><input style="width:920px;" type="text" data-bind="value:C_TITLE" required/>
                </div>
                <div class="row">
                    <label style="padding-right:0px;margin-top:18px;float:left;">接收者:</label>
                    <ul class="receiver" id="receiver">
                        <li>
                        <input type="checkbox" data-bind="checked : at_government"> <span>辖区内政府机关</span>
                        <input class="button" type="button" value="选择政府单位" data-bind="click : doGovernmentClick, disable: at_government()==false"/>
                        </li>
                        <li>
                        <input type="checkbox" data-bind="checked : at_review"> <span>辖区内评审机构</span>
                        <input class="button" type="button" value="选择评审机构" data-bind="click : doReviewClick, disable: at_review()==false"/>
                        </li>
                        <li>
                        <input type="checkbox" data-bind="checked : at_enterprise"> <span>辖区内企业单位</span>
                        <input class="button" type="button" value="选择企业单位" data-bind="click : doEnterpriseClick, disable: at_enterprise()==false"/>
                        </li>
                    </ul>
                </div>
                <div class="row clear" data-bind="visible: (at_government()||at_review()||at_enterprise())">
                    <ul class="received">
                        <li data-bind="visible : at_government" >
                             <span>政府机关:</span>
                             <span data-bind="text : governments"></span>
                        </li>
                        <li data-bind="visible : at_review">
                             <span>评审机构:</span>
                             <span data-bind="text : reviews"></span>
                        </li>
                        <li data-bind="visible : at_enterprise">
                             <span>企业单位:</span>
                             <span data-bind="text : enterprises"></span>
                        </li>
                    </ul>
                </div>
    
                <div class="row clear">
                    <label>通知公告内容：</label>
                    <div style="padding-top: 10px;">
                         <textarea style="width: 100%; height: 300px" data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings"></textarea>
                    </div>
                </div>
                <div class="row">
                    <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'SYS_NOTICE', sid: '${SID!}'}">[选择文件]</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 政府对话框 -->
    <div id="gbg" style="display: none;">
        <table  style="text-align: left;" class="dialog">
            <colgroup>
                <col style="width: 5px">
                <col style="width: 30px">
                <col>
                <col style="width: 30px">
                <col>
                <col>
            </colgroup>
            <tr style="border-bottom: none;">
                <th></th>
                <th>名称</th><td><input type="text" data-bind=""/></td>
                <th>地址</th><td><input type="text" data-bind=""/></td>
                <td><a href="javascript:void(0)" class="icon-search">搜索</a></td>
            </tr>
            <tr style="background-color: #E8F1F7;">
                <th><input type="checkbox" data-bind=""></th>
                <th></th><td>名称</td>
                <th style="text-align: left" colspan="3">地址</th>
            </tr>
            <tbody  data-bind="foreach : receivegovernment">
                <tr  data-bind="template: {name:'governmentTemplate' , data: $data}"></tr>
            </tbody>
        </table>
    </div>
    <!-- 评审对话框 -->
    <div id="rbg" style="display: none">
        <table  style="text-align: left" class="dialog">
            <colgroup>
                <col style="width: 5px">
                <col style="width: 30px">
                <col>
                <col style="width: 30px">
                <col>
                <col>
            </colgroup>
            <tr style="border-bottom: none;">
                <th></th>
                <th>名称</th><td><input type="text" data-bind=""/></td>
                <th>地址</th><td><input type="text" data-bind=""/></td>
                <td><a href="javascript:void(0)" class="icon-search">搜索</a></td>
            </tr>
            <tr style="background-color: #E8F1F7;">
                <th><input type="checkbox" data-bind=""></th>
                <th></th><td >名称</td>
                <th style="text-align: left" colspan="3">地址</th>
            </tr>
            <tbody  data-bind="foreach : receivereview">
                <tr data-bind="template: {name:'reviewTemplate' , data: $data}"></tr>
            </tbody>
        </table>
    </div>
    <!-- 企业端对话框 -->
    <div id="ebg" style="display: none">
        <table  style="text-align: left" class="dialog">
            <colgroup>
                <col style="width: 5px">
                <col style="width: 30px">
                <col>
                <col style="width: 30px">
                <col>
                <col>
            </colgroup>
            <tr style="border-bottom: none;">
                <th></th>
                <th>名称</th><td><input type="text" data-bind=""/></td>
                <th>地址</th><td><input type="text" data-bind=""/></td>
                <td><a href="javascript:void(0)" class="icon-search">搜索</a></td>
            </tr>
            <tr style="background-color: #E8F1F7;">
                <th><input type="checkbox" data-bind=""></th>
                <th></th><td>名称</td>
                <th  style="text-align: left" colspan="3">地址</th>
            </tr>
            <tbody  data-bind="foreach : receiveenterprise">
                <tr data-bind="template: {name:'enterpriseTemplate' , data: $data}"></tr>
            </tbody>
        </table>
    </div>
</body>

<!-- 对话框数据模版 -->
<script type="text/html" id="governmentTemplate">
        <th><input type="checkbox" data-bind="checked : CHOOSE" ></th>
        <th></th>
        <td ><span data-bind="text : S_RECEIVER" ></span></td>
        <td colspan="3" ><span data-bind="text : C_ADDR" ></span></td>
</script>

<script type="text/html" id="reviewTemplate">
        <th><input type="checkbox" data-bind="checked : CHOOSE"></th>
        <th></th>
        <td ><span data-bind="text : S_RECEIVER" ></span></td>
        <td colspan="3"><span data-bind="text : C_ADDR" ></span></td>
</script>

<script type="text/html" id="enterpriseTemplate">
        <th><input type="checkbox" data-bind="checked : CHOOSE"></th>
        <th></th>
        <td  ><span data-bind="text : S_RECEIVER" ></span></td>
        <td colspan="3"><span data-bind="text : C_ADDR" ></span></td>
</script>

<script type="text/javascript" src="/resource/js/layui/layer.js"></script>
<script type="text/javascript" src="/resource/js/core.js"></script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
<script type="text/javascript">
function viewModel(){
    var self = this;
    self.SID = ko.observable('${SID}');
    self.C_TITLE=ko.observable('${C_TITLE}');
    //富文本
    self.htmlContent=ko.observable('${C_CONTENT}');
    self.htmleditSettings={
    };
    //所属复选框
    self.at_government=ko.observable(false);
    self.at_review=ko.observable(true);
    self.at_enterprise=ko.observable(true);
    
    //选中单位显示的名称
    self.governments=ko.observable("全部");
    self.reviews=ko.observable("全部");
    self.enterprises=ko.observable("全部");
    //选中单位
    self.checked_government=ko.observableArray();
    self.checked_review=ko.observableArray();
    self.checked_enterprise=ko.observableArray();
    
    self.checked_government.subscribe(function (value) {
        var names = [];
        for(var i=0;i<value.length;i++){
            names.push(value[i].S_RECEIVER);
        }
        self.governments(names.join(","));
    });
    self.checked_review.subscribe(function (value) {
        var names = [];
        for(var i=0;i<value.length;i++){
            names.push(value[i].S_RECEIVER);
        }
        self.reviews(names.join(","));
    });
    self.checked_enterprise.subscribe(function (value) {
        var names = [];
        for(var i=0;i<value.length;i++){
            names.push(value[i].S_RECEIVER);
        }
        self.enterprises(names.join(","));
    });
        
    //list所有接受单位信息
    self.receivegovernment=ko.observableArray(${data_g!});
    self.receivereview=ko.observableArray(${data_r!}); 
    self.receiveenterprise=ko.observableArray(${data_e!});
    
    //确定选中单位的回调function
    self.confirmGovrnment=function(index){
        var gs = self.receivegovernment.filter(function (item) {
            return (item.CHOOSE==true);
        });
        self.checked_government(gs);
        layer.close(index);
    };
    
    self.confirmReview=function(index){
        var gs = self.receivereview.filter(function (item) {
            return (item.CHOOSE==true);
        });
        self.checked_review(gs);
        layer.close(index);
    };
    
    self.confirmEnterprise=function(index){
        var gs = self.receiveenterprise.filter(function (item) {
            return (item.CHOOSE==true);
        });
        self.checked_enterprise(gs);
        layer.close(index);
    };
    
    /* 点击弹出对话框的函数 */
    self.doGovernmentClick=function(){
        utils.messager.showDialog("1","550px","270px","选择接收的政府单位","gbg",self.confirmGovrnment);
    };
    
    self.doReviewClick=function(){
        utils.messager.showDialog("1","550px","270px","选择接收的评审单位","rbg",self.confirmReview);
    };
    
    self.doEnterpriseClick=function(){
        utils.messager.showDialog("1","550px","270px","选择接收的企业单位","ebg",self.confirmEnterprise);
    };
    self.newClick = function(){
        window.location.href = window.locatino.href;
    };
    self.saveClick = function () {
            //if ($form.validate($('.form'))) {
                utils.messager.showProgress();
                
                var model = {
                    SID:self.SID,
                    C_TITLE:self.C_TITLE,
                    C_CONTENT:self.htmlContent,
                    C_RECEIVER_G:ko.toJSON(self.checked_government()),
                    C_RECEIVER_R:ko.toJSON(self.checked_review()),
                    C_RECEIVER_E:ko.toJSON(self.checked_enterprise())
                };
                $.post('save', model, function (result) {
                    if (result.SID) {
                        self.SID(result.SID);
                        utils.messager.alert("保存成功",function () {
                             utils.messager.closeProgress();
                        });
                    } else {
                        utils.messager.closeProgress();
                        utils.messager.alert("保存失败");
                    }
                }, "json");
            //}
    };
    
    self.publishClick=function(){
        utils.messager.showProgress();
        $.post('publish', model, function (result) {
            if (result.SID) {
                utils.messager.alert("提示", "发布成功", "info", function () {
                        utils.messager.closeProgress();
                });
            } else {
                utils.messager.closeProgress();
                utils.messager.alert("提示", "发布失败", "warning");
            }
        }, "json");
    };
    self.delClick=function(){
        utils.messager.showProgress();
        $.post('del', model, function (result) {
            if (result.SID) {
                utils.messager.alert("提示", "保存成功", "info", function () {
                        utils.messager.closeProgress();
                });
            } else {
                utils.messager.closeProgress();
                utils.messager.alert("提示", "保存失败", "warning");
            }
        }, "json");
    };
};

$(function(){
    var view=new viewModel();
    ko.applyBindings(view);
    
    view.checked_government(${governments!}); 
    view.checked_review(${reviews!});
    view.checked_enterprise(${enterprises!});
})
</script>
</html>