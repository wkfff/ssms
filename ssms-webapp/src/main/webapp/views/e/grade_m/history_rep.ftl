<#import "/layout/_rec.ftl" as layout/>
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
                window.editor = K.create('#CONTENT', {
                    width:'100%',height:h,border:0,
                    themeType : 'simple',resizeType:0,
                    items : [],
                    afterCreate : function(id) {
                        editor.readonly();
                        editor.toolbar.hide();
                        K.ajax('/sys/attachtext/get.json?table=SSM_GRADE_REPORT&field=CONTENT&sid=${sid!}', function(data) {
                             window.editor.html(data);
                        });
                    }
                 });
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div class="easyui-panel" data-options="region:'center'" style="overflow:hidden;border:1;border-left:0;" title="自评报告">
         <div class="toolbar ue-clear" style="border:0px;border-left:1px solid #c1d3de;">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-download" onclick="doDownload();">下载</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-print" onclick="doPrint()">打印</a>
         </div>
         <form id="formMain" method="post">
         <textarea id="CONTENT" name="CONTENT" style="display:none;"></textarea>
         </form>
    </div>
</div>

</@layout.doLayout>