<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.kindeditor.js"></script>
<script type="text/javascript">
    
    function doSave(){
        //$.messager.progress();
        $('#formMain').form('submit', {
            url:'save.do?sid=${sid!}',
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    //$.messager.progress('close');
                }
                return isValid;
            },
            success: function(data){
                $.messager.alert('保存','保存成功！');
            }
        });
    }

    function doBack(){
        window.location.href='${referer!}';
    }
    
    function loadData(sid){
        $('#formMain').form('load','rec.json?r_sid='+sid);
        if ($('#R_SID').val()=='') $('#R_SID').val(sid);
    }
    
    $(function () {
        $('#formMain').form('load','rec.json?sid=${sid!}');
        /*
        var editor = KindEditor.create('#C_CONTENT', {
                width:'100%',height:'600',
                allowFileManager :false,
                allowUpload:false,
                items : [
                    'fontname', 'fontsize',
                    '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline','removeformat',
                    '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist','insertunorderedlist',
                    '|', 'table','|','fullscreen'
                    ]
        });*/
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >

    <div title="自评报告模板" data-options="region:'center',iconCls:'icon-star'" style="overflow:hidden;border:0;padding:0px;margin:0px;">
        <div class="easyui-panel" style="border:0;background-color:#FAFAFA;padding:5px;">
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
         </div>
         
         <form id="formMain" method="post">
                <textarea class="easyui-kindeditor" id="C_CONTENT" name="C_CONTENT" data-options="width:'100%',height:'600px'"></textarea>
                <input type="hidden" id="P_PROFESSION" name="P_PROFESSION"/>
                <input type="hidden" id="S_PROFESSION" name="S_PROFESSION"/>
         </form>
    </div>
</div>

</@layout.doLayout>