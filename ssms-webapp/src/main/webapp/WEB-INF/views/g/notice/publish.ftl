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
    <div class="pannel">
        <div class="titlebar_noborder"><img src="/resource/images/titleico.png" />发布通知公告</div>
        <div class="toolbar">
            <a href="javascript:void(0)" class="icon-publish"  data-bind="click: publishClick">发布</a>
            <a href="javascript:void(0)" class="icon-save"  data-bind="click: saveClick">存为草稿</a>
            <a href="javascript:void(0)" class="icon-delete"  data-bind="click: delClick">删除</a>
        </div>

        <div class="content">
            <div class="row">
                <label style="padding-right:20px;">标题:</label><input style="width:920px;" type="text" data-bind="textbox:C_TITLE" required/>
            </div>
            <div class="row">
                <label style="padding-right:20px;">请选择接收者:</label>
                <ul class="receiver">
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
                <label>已选择的接收者:</label>
                <ul class="received">
                    <li data-bind="visible : at_government" >
                         <span>政府机关:</span>
                         <span data-bind="text : checked_government"></span>
                    </li>
                    <li data-bind="visible : at_review">
                         <span>评审机构:</span>
                         <span data-bind="text : checked_review"></span>
                    </li>
                    <li data-bind="visible : at_enterprise">
                         <span>企业单位:</span>
                         <span data-bind="text : checked_enterprise"></span>
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
                <a href="javascript:void(0);" data-bind="uploadOptions: {module: '', sid: ''}">[选择文件]</a>
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
        <th><input type="checkbox" data-bind="checked : choose" ></th>
        <th></th>
        <td ><span data-bind="text : name" ></span></td>
        <td colspan="3" ><span data-bind="text : addr" ></span></td>
</script>

<script type="text/html" id="reviewTemplate">
        <th><input type="checkbox" data-bind="checked : choose"></th>
        <th></th>
        <td ><span data-bind="text : name" ></span></td>
        <td colspan="3"><span data-bind="text : addr" ></span></td>
</script>

<script type="text/html" id="enterpriseTemplate">
        <th><input type="checkbox" data-bind="checked : choose"></th>
        <th></th>
        <td  ><span data-bind="text : name" ></span></td>
        <td colspan="3"><span data-bind="text : addr" ></span></td>
</script>

<script type="text/javascript" src="/resource/js/layui/layer.js"></script>
<script type="text/javascript" src="/resource/js/core.js"></script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
<script type="text/javascript">
function viewModel(){
    var self = this;
    var model = {
        C_TITLE:ko.observable('test');
    };
    //self.C_TITLE=ko.observable();
    //富文本
    self.htmlContent=ko.observable();
    self.htmleditSettings={
        };
    //所属复选框
    self.at_government=ko.observable(false);
    self.at_review=ko.observable(true);
    self.at_enterprise=ko.observable(true);
    //list选中单位名称
    self.checked_government=ko.observable("");
    self.checked_review=ko.observable("");
    self.checked_enterprise=ko.observable("");
    //list所有接受单位信息
    self.receivegovernment=ko.observableArray([{"choose":false,"name":"sindy","addr":"china"},{"choose":false,"name":"maven","addr":"japanese"},
                                                {"choose":false,"name":"cookey","addr":"english"}]);
    
    self.receivereview=ko.observableArray([{"choose":false,"name":"药监局","addr":"福建福州"},{"choose":false,"name":"烟草局","addr":"安徽合肥"},
                                                {"choose":false,"name":"海边防","addr":"山西煤矿山"}]);
    
    self.receiveenterprise=ko.observableArray([{"choose":false,"name":"阿里巴巴","addr":"美国上市"},{"choose":false,"name":"百度","addr":"福建厦门"},
                                                {"choose":false,"name":"百合集团有限公司","addr":"德国中部"}]);
    
    //确定选中单位的回调function
    self.confirmGovrnment=function(index){
        var values="";
        self.checked_government("");
        for(var i=0;i<self.receivegovernment._latestValue.length;i++){
            if(self.receivegovernment._latestValue[i].choose){
                values+=self.receivegovernment._latestValue[i].name+",";
            }
             layer.close(index);
        };
        values=values.substring(0, values.length-1);
        self.checked_government(values);
    };
    
    self.confirmReview=function(index){
        var values="";
        self.checked_review("");
        for(var i=0;i<self.receivereview._latestValue.length;i++){
            if(self.receivereview._latestValue[i].choose){
                values+=self.receivereview._latestValue[i].name+",";
            }
             layer.close(index);
        };
        values=values.substring(0, values.length-1);
        self.checked_review(values);
    };
    
    self.confirmEnterprise=function(index){
        var values="";
        self.checked_enterprise("");
        for(var i=0;i<self.receiveenterprise._latestValue.length;i++){
            if(self.receiveenterprise._latestValue[i].choose){
                values+=self.receiveenterprise._latestValue[i].name+",";
            }
             layer.close(index);
        };
        values=values.substring(0, values.length-1);
        self.checked_enterprise(values);
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
    
    self.saveClick = function () {
            //if ($form.validate($('.form'))) {
                utils.messager.showProgress();
                $.post('save', model, function (result) {
                    if (result.SID) {
                        utils.messager.alert("提示", "保存成功", "info", function () {
                                utils.messager.closeProgress();
                        });
                    } else {
                        utils.messager.closeProgress();
                        utils.messager.alert("提示", "保存失败", "warning");
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
})
</script>
</html>