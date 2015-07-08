<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css">
    <link rel="stylesheet" href="/resource/css/common.css">
    <link rel="stylesheet" href="/resource/js/layui/skin/layer.css" />
    <link rel="stylesheet" href="/resource/css/layout.css" />
    <link rel="stylesheet" href="/resource/css/icon.css" />
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
   
    <style type="text/css">
        .button{height:30px;border:0;color:darkblue;cursor: pointer;}
        .button:disabled{height:30px;border:0;color:999;cursor:default;}
        .button:active{background:-webkit-linear-gradient(top,#fefefe,#ebeced);background:-moz-linear-gradient(top,#f2f3f7,#ebeced);background:linear-gradient(top,#f2f3f7,#ebeced);}
        a:HOVER{background-color:#ADBDFF;}
        .receiver li{padding:0px;border:1px solid #999;float:left;margin:10px;}
        .received li{padding:5px;border-bottom:1px solid #EEE;}
        .user{border:1px solid blue;}
        .toolbar label{padding:right:5px;margin:5px;}
        table.dialog{width:100%;}
        table.dialog thead{background-color: #E8F1F7;}
        table.dialog th{text-align:left;}
        table.dialog tr{border-bottom:1px dashed gray;height:40px;}
    </style>
</head>
<body>
    <div class="container">
        <#if (tb?? && tb=="1")>
        <header class="titlebar">
            <label>发布通知公告</label>
        </header>
        </#if>
        
        <div class="content ue-clear">
            <div class="toolbar">
                <a href="javascript:void(0)" class="icon-new"  data-bind="click: newClick">新建</a>
                <a href="javascript:void(0)" class="icon-save"  data-bind="click: saveClick">存为草稿</a>
                <a href="javascript:void(0)" class="icon-publish"  data-bind="click: publishClick">发布</a>
                <a href="javascript:void(0)" class="icon-delete"  data-bind="visible:showdel,click: delClick">删除</a>
            </div>
    
            <div class="form">
                <div class="row">
                    <label style="padding-right:20px;">标题:</label><input style="width:920px;" type="text" data-bind="value:C_TITLE" required/>
                </div>
                <div class="row">
                    <label style="padding-right:0px;margin-top:18px;float:left;">接收者:</label>
                    <ul class="receiver" id="receiver">
                        <#if LANSTAR_IDENTITY.tenantType='G'>
                        <li>
                        <input type="checkbox" data-bind="checked : at_government"> <span>辖区内政府机关</span>
                        <input class="button" type="button" value="选择政府单位" data-bind="click : doGovernmentClick, disable: at_government()==false"/>
                        </li>
                        <li>
                        <input type="checkbox" data-bind="checked : at_review"> <span>辖区内评审机构</span>
                        <input class="button" type="button" value="选择评审机构" data-bind="click : doReviewClick, disable: at_review()==false"/>
                        </li>
                        </#if>
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
                         <textarea id="C_CONTENT" style="width: 100%; height: 300px" data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 政府对话框 -->
    <div id="gbg" style="display: none;">
        <div class="toolbar">
            <label>名称</label><input type="text" data-bind="value:name_g"/>
            <a href="javascript:void(0)" class="icon-search" data-bind="click:query_g">搜索</a>
        </div>
        <table class="dialog">
            <thead>
                <tr>
                <th style="width: 30px"><input type="checkbox" data-bind="checked:chkAll"></th>
                <th style="width: 300px">名称</th>
                <th>地址</th>
                </tr>
            </thead>
            <tbody data-bind="foreach : receivegovernment">
                <tr data-bind="template: {name:'governmentTemplate' , data: $data}"></tr>
            </tbody>
        </table>
    </div>
    <!-- 评审对话框 -->
    <div id="rbg" style="display: none;">
        <div class="toolbar">
            <label>名称</label><input type="text" data-bind="value:name_r"/>
            <a href="javascript:void(0)" class="icon-search" data-bind="click:query_r">搜索</a>
        </div>
        <table class="dialog">
            <thead>
                <tr>
                <th style="width: 30px"><input type="checkbox" data-bind="checked:chkAll"></th>
                <th style="width: 300px">名称</th>
                <th>地址</th>
                </tr>
            </thead>
           <tbody  data-bind="foreach : receivereview">
                <tr data-bind="template: {name:'reviewTemplate' , data: $data}"></tr>
            </tbody>
        </table>
    </div>
    <!-- 企业端对话框 -->
    <div id="ebg" style="display: none;">
        <div class="toolbar">
            <label>名称</label><input type="text" data-bind="value:name_e"/>
            <a href="javascript:void(0)" class="icon-search" data-bind="click:query_e">搜索</a>
        </div>
        <table class="dialog">
            <thead>
                <tr>
                <th style="width: 30px"><input type="checkbox" data-bind=""></th>
                <th style="width: 300px">名称</th>
                <th>地址</th>
                </tr>
            </thead>
           <tbody  data-bind="foreach : receiveenterprise">
                <tr data-bind="template: {name:'enterpriseTemplate' , data: $data}"></tr>
            </tbody>
        </table>
    </div>
</body>

<!-- 对话框数据模版 -->
<script type="text/html" id="governmentTemplate">
        <td><input type="checkbox" data-bind="checked : CHOOSE" ></td>
        <td><span data-bind="text : S_RECEIVER" ></span></td>
        <td><span data-bind="text : C_ADDR" ></span></td>
</script>

<script type="text/html" id="reviewTemplate">
        <td><input type="checkbox" data-bind="checked : CHOOSE"></td>
        <td><span data-bind="text : S_RECEIVER" ></span></td>
        <td colspan="3"><span data-bind="text : C_ADDR" ></span></td>
</script>

<script type="text/html" id="enterpriseTemplate">
        <td><input type="checkbox" data-bind="checked : CHOOSE"></td>
        <td><span data-bind="text : S_RECEIVER" ></span></td>
        <td colspan="3"><span data-bind="text : C_ADDR" ></span></td>
</script>

<script type="text/javascript" src="/resource/js/layui/layer.js"></script>
<script type="text/javascript" src="/resource/js/core.js"></script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
<script type="text/javascript">
function viewModel(){
    var self = this;
    self.SID = ko.observable('${SID!}');
    self.showdel = ko.observable(${SID!'-1'}!=-1);
    self.C_TITLE=ko.observable('${C_TITLE!}');
    //富文本
    self.htmlContent = ko.observable('${C_CONTENT!}');
    self.htmleditSettings = {
        table: 'SYS_NOTICE',
        field: 'C_CONTENT',
        sid: '${SID!}',
    };
    //所属复选框
    self.at_government=ko.observable(false);
    self.at_review=ko.observable(false);
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
    //全选
    self.chkAll = ko.observable(false);
    self.chkAll.subscribe(function(value){
        for(var i=0;i<self.receivegovernment().length;i++){
            self.receivegovernment()[i].CHOOSE = value;
        }
    });
    
    //list所有接受单位信息
    self.data_g = ${data_g!"[]"};
    self.data_r = ${data_r!"[]"};
    self.data_e = ${data_e!"[]"};
    self.receivegovernment=ko.observableArray(self.data_g);
    self.receivereview=ko.observableArray(self.data_r); 
    self.receiveenterprise=ko.observableArray(self.data_e);
    
    self.name_g = ko.observable();
    self.query_g = function(){
        if(self.name_g()=='') {
            self.receivegovernment(self.data_g);
            return;
        }
        var gs = self.data_g.filter(function (item) {
            return (item.S_RECEIVER.indexOf(self.name_g())>-1);
        });
        self.receivegovernment(gs);
    };
    
    self.name_r = ko.observable();
    self.query_r = function(){
        if(self.name_r()=='') {
            self.receivereview(self.data_r);
            return;
        }
        var rs = self.data_r.filter(function (item) {
            return (item.S_RECEIVER.indexOf(self.name_r())>-1);
        });
        self.receivereview(rs);
    };
    
    self.name_e = ko.observable();
    self.query_e = function(){
        if(self.name_e()=='') {
            self.receiveenterprise(self.data_e);
            return;
        }
        var es = self.data_e.filter(function (item) {
            return (item.S_RECEIVER.indexOf(self.name_e())>-1);
        });
        self.receiveenterprise(es);
    };
    
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
        utils.messager.showDialog("1","650px","500px","选择接收的政府机关","gbg",self.confirmGovrnment);
    };
    
    self.doReviewClick=function(){
        utils.messager.showDialog("1","650px","500px","选择接收的评审机构","rbg",self.confirmReview);
    };
    
    self.doEnterpriseClick=function(){
        utils.messager.showDialog("1","650px","500px","选择接收的企业单位","ebg",self.confirmEnterprise);
    };

    self.newClick = function(){
        window.location.href = '${BASE_PATH}/rec';
    };
    self.validate = function(){
        if (self.C_TITLE().length<=0) {
            utils.messager.alert("标题不能为空，请选择！");
            return false;
        }
        /* TODO:富文本组件处理
        if (self.htmlContent().length<=0) {
            utils.messager.alert("内容不能为空，请填写！");
            return false;
        }
        */
        if ((self.governments()!='全部' && self.checked_government().length==0) 
            && (self.reviews()!='全部' && self.checked_review().length==0)
            && (self.enterprises()!='全部' &&self.checked_enterprise().length==0)) {
            utils.messager.alert("接收者不能为空，请选择！");
            return false;
        }
        return true;
    };
    self.saveClick = function () {
            if (!self.validate()) return;
                utils.messager.showProgress();
                var model = {
                    SID:self.SID(),
                    C_TITLE:self.C_TITLE(),
                    C_CONTENT:self.htmlContent(),
                    C_RECEIVER_G:ko.toJSON(self.checked_government()),
                    C_RECEIVER_R:ko.toJSON(self.checked_review()),
                    C_RECEIVER_E:ko.toJSON(self.checked_enterprise())
                };
                
                $.post('save', model, function (result) {
                    utils.messager.closeProgress();
                    if (result.SID) {
                        self.SID(result.SID);
                        self.showdel(true);
                        utils.messager.alert("保存成功",function () {
                             
                        },1);
                    } else {
                        utils.messager.alert("保存失败");
                    }
                }, "json");
    };
    
    self.publishClick=function(){
        if (!self.validate()) return;
        
        utils.messager.confirm("是否发布当前的通知公告？",function(){
            utils.messager.showProgress();
            var model = {
                        SID:self.SID,
                        C_TITLE:self.C_TITLE,
                        C_CONTENT:self.htmlContent
                        /*,
                        C_RECEIVER_G:self.governments()=='全部'?'全部':ko.toJSON(self.checked_government()),
                        C_RECEIVER_R:self.reviews()=='全部'?'全部':ko.toJSON(self.checked_review()),
                        C_RECEIVER_E:self.enterprises()=='全部'?'全部':ko.toJSON(self.checked_enterprise())
                        */
             };
             if (self.at_government()){
                model['C_RECEIVER_G'] = self.governments()=='全部'?'全部':ko.toJSON(self.checked_government());
             }
             if (self.at_review()){
                model['C_RECEIVER_R'] = self.reviews()=='全部'?'全部':ko.toJSON(self.checked_review());
             }
             if (self.at_enterprise()){
                model['C_RECEIVER_E'] = self.enterprises()=='全部'?'全部':ko.toJSON(self.checked_enterprise());
             }
             
             $.post('save', model, function (result) {
                    if (result.SID) {
                        self.SID(result.SID);
                        $.post('publish', model, function (result) {
                                utils.messager.closeProgress();
                                if (result.SID)
                                    utils.messager.alert("发布成功");
                                else
                                    utils.messager.alert("发布失败");
                         }, "json");
                    } else {
                        utils.messager.closeProgress();
                        utils.messager.alert("保存失败");
                    }
                }, "json");
         });
    };
    self.delClick=function(){
        utils.messager.showProgress();
        $.post('${BASE_PATH}/del', {"SID":self.SID()}, function (result) {
            utils.messager.closeProgress();
            if (result) 
                utils.messager.alert("删除成功",function(){window.location.href = window.location.href;});
            else 
                utils.messager.alert("删除失败");
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