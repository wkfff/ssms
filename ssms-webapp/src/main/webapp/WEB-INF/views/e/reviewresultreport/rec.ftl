<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor-min.js"></script>

<script type="text/javascript">
    function doBack(){
        window.location.href='${referer!}';
    }
    
    function doDownload(){
        alert('该功能暂未实现！');
    }
    
    function doPrint(){
        alert('该功能暂未实现！');
    }
    
    $(function () {
         KindEditor.ready(function(K) {
                var h = $('.layout-body')[0].scrollHeight-35;
                window.editor = K.create('#content', {
                    width:'100%',height:h,border:0,
                    themeType : 'simple',resizeType:0,
                    items : [],
                    afterCreate : function(id) {
                        editor.readonly();
                        editor.toolbar.hide();
                        $.get("/sys/attachtext/get?table=SSM_GRADE_REPORT&field=content&sid=${sid!}",function(data){
                             if (data) editor.html(data);
                        });
                    }
                 });
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div class="easyui-panel" data-options="region:'center'" style="overflow:hidden;border:1;border-left:0;">
         <div class="toolbar ue-clear" style="border:0px;border-left:1px solid #c1d3de;">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-download" onclick="doDownload();">下载</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-print" onclick="doPrint()">打印</a>
         </div>
         <form id="formMain" method="post">
         <textarea id="content" name="content" style="display:none;"></textarea>
         </form>
    </div>
</div>

</@layout.doLayout>