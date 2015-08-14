<@layout.extends name="../../_layouts/base.ftl">
<@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css">
    <link rel="stylesheet" href="/resource/css/common.css">
    <link rel="stylesheet" href="/resource/js/layui/skin/layer.css" />
    <link rel="stylesheet" href="/resource/css/layout.css" />
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <style type="text/css">
        .icon-new {
            background: url('/resource/images/blue/new.png') no-repeat 2px center;
            background-size: 15px 15px;
            padding-left: 22px;
        }
        .icon-save {
            background: url('/resource/images/saveicon.png') no-repeat 2px center;
            background-size: 15px 15px;
            padding-left: 22px;
        }
        .icon-delete {
            background: url('/resource/images/delico.png') no-repeat 2px center;
            background-size: 15px 15px;
            padding-left: 22px;
        }
        .icon-publish {
            background: url('/resource/images/blue/publish.png') no-repeat 2px center;
            background-size: 15px 15px;
            padding-left: 22px;
         }
        .icon-back {
           background: url('/resource/images/blue/back.png') no-repeat 2px center;
           background-size: 15px 15px;
           padding-left: 22px;
         }
        a:HOVER{background-color:#ADBDFF;}
    </style>
</@>
<@layout.put block="contents">
    <div class="container">
        <div class="content ue-clear">
            <div class="toolbar">
                <a href="javascript:void(0)" class="icon-new"  data-bind="click: newDraft,visible : SID().length!=0">新建</a>
                <a href="javascript:void(0)" class="icon-save"  data-bind="click: save">存为草稿</a>
                <a href="javascript:void(0)" class="icon-publish"  data-bind="click: publish">发布</a>
                <a href="javascript:void(0)" class="icon-delete"  data-bind="visible:showdel,click: del,visible : SID().length!=0">删除</a>
                <a href="javascript:void(0)" class="icon-back"  onclick="window.location.href = '${BASE_PATH}/index?eid=${eid}&pro=${pro}'">返回草稿箱</a>
            </div>
            <div class="form">
                <div class="row">
                    <label style="padding-right:20px;">标题:</label><input style="width:920px;" type="text" data-bind="value:C_TITLE" required/>
                </div>
                <div class="row clear">
                    <label>通知公告内容：</label>
                    <div style="padding-top: 10px;">
                         <textarea id="C_CONTENT" style="width: 100%; height: 500px" data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings">
                         </textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@>
<@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    <script type="text/javascript">
        function ViewModel(){
           var self=this;
           self.SID=ko.observable('${SID!}');
           self.C_TITLE=ko.observable('${C_TITLE!}');
           self.htmlContent=ko.observable(${json(C_CONTENT)});
           if(typeof self.htmlContent()=='undefined'){
               self.htmlContent=ko.observable('');
           }
           self.htmleditSettings = {
                   table: 'SYS_REVIEW_NOTICE',
                   field: 'C_CONTENT',
                   sid: '${SID!}',
               };
           
           self.newDraft=function(){
               window.location.href="${BASE_PATH}/draftForm?eid=${eid}&pro=${pro}";
           };
           
           self.save = function(){
               if(!self.validate()) return;
               utils.messager.showProgress();
               var model={
                       sid : self.SID,
                       title : self.C_TITLE(),
                       content : self.htmlContent(),
                       eid : '${eid!}',
                       pro : '${pro!}'
               };
               $.post('${BASE_PATH}/save',model,function(result){
                   utils.messager.closeProgress();
                   if(result!=null){
                       window.location.href="${BASE_PATH}/draftForm?eid=${eid}&pro=${pro}&sid="+result;
                       utils.messager.alert("保存成功");
                   }else{
                       utils.messager.alert("保存失败");
                   }
               },"json");
           };
           
           self.publish = function(){
               if(!self.validate()) return;
               utils.messager.showProgress();
               var model={
                       sid : self.SID,
                       title : self.C_TITLE,
                       content : self.htmlContent,
                       eid : '${eid!}',
                       pro : '${pro!}'
               };
               $.post('${BASE_PATH}/publish',model,function(result){
                   utils.messager.closeProgress();
                   if(result!=null){
                       utils.messager.alert("发布成功");
                       window.location.href="${BASE_PATH}/publishs?eid=${eid}&pro=${pro}";
                   }else{
                       utils.messager.alert("发布失败");
                   }
               },'json');
           };
           
           self.del = function(){
               utils.messager.confirm("确定要删除记录吗？",function(deleteAction){
                   if(deleteAction){
                       $.post('${BASE_PATH}/del',{sid:self.SID},function(result){
                           utils.messager.closeProgress();
                           if(result){
                               utils.messager.alert("删除成功");
                               window.location.href="${BASE_PATH}/index?eid=${eid}&pro=${pro}";
                           }else{
                               utils.messager.alert("删除失败");
                           }
                       },'json');
                   }
               });
           }
           
           self.validate = function(){
               if (self.C_TITLE().length<=0) {
                   utils.messager.alert("标题不能为空！");
                   return false;
               }
               return true;
           };
        };
        $(function(){
            ko.applyBindings(new ViewModel());
        })
        
    </script>
</@>
</@layout.extends >