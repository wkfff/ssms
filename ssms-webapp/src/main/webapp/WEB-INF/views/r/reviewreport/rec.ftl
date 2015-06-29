<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript">
    var editor;
    function doSave(){
        parent.$.messager.progress({title : '保存',text : '正在保存中，请稍后....'});
        editor.sync();
        if ($form.validate($('#formMain')))
            $.ajax({
                type:'post',
                url:'/sys/attachtext/save',
                data:$("#formMain").serialize(), 
                success:function(data){
                    parent.$.messager.progress('close');
                    $.messager.alert("提示", "保存成功", "info");
                }
            });
        return false;
    }

    function doDownload(){
        alert('该功能暂未实现！');
    }
    
    function doPrint(){
        alert('该功能暂未实现！');
    }

    function doBack(){
        window.location.href='${referer!}';
    }

    $(function () {
         KindEditor.ready(function(K) {
                var h = $('.layout-body')[0].scrollHeight-35;
                editor = K.create('#content', {
                    width:'100%',height:h,border:0,
                    themeType :'simple',resizeType:0,
                    items : [
                    'fontname', 'fontsize',
                    '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline','removeformat',
                    '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist','insertunorderedlist',
                    '|', 'table','|','fullscreen'
                    ],
                    afterCreate : function() {
                        $.get("/sys/attachtext/get?table=SSM_REVIEW_REPORT&field=C_CONTENT&sid=${sid!}",function(data){
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
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave();">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-download" onclick="doDownload();">下载</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-print" onclick="doPrint()">打印</a>
                <#if showback??>
                    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
                </#if>
         </div>
         <form id="formMain" method="post">
                <textarea id="content" name="content" style="display:none;"></textarea>
                <input type="hidden" name="table" value="SSM_REVIEW_REPORT" />
                <input type="hidden" name="field" value="C_CONTENT" />
                <input type="hidden" name="sid" value="${sid!}" />
         </form>
    </div>
</div>

</@layout.doLayout>