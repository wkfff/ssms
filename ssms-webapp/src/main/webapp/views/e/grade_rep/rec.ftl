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

    function doComplete(){
        $.messager.confirm("完成确认", "您确认完成当前的自评报告吗？", function (action) {
                    if (action) {
                        $.get("complete.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "自评报告完成");
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
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
        <#if (_FLAG_=='-1')>$form.disableForm('formMain',true);</#if>
        /*
        var editor = KindEditor.create('#C_CONTENT', {
                width:'100%',
                allowFileManager :false,
                allowUpload:false,
                autoHeightMode : true,
                items : [
                    'fontname', 'fontsize',
                    '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline','removeformat',
                    '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist','insertunorderedlist',
                    '|', 'table','|','fullscreen'
                    ]
        });*/


         KindEditor.ready(function(K) {
                var h = $('.layout-body')[0].scrollHeight-35;
                editor = K.create('#C_CONTENT', {
                    width:'100%',height:h,border:0,
                    //allowFileManager :false,
                    //allowUpload:false,
                    //autoHeightMode : true,
                    themeType : 'simple',resizeType:0,
                    items : [],
                    afterCreate : function(id) {
                        //editor.readonly();
                        editor.toolbar.hide();
                        K.ajax('/sys/attachtext/get.json?table=SSM_GRADE_E_M&field=C_CONTENT&sid=${sid!}', function(data) {
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
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-download" onclick="alert('暂未完成');">下载</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
         <form id="formMain" method="post">
                <textarea  id="C_CONTENT" name="C_CONTENT" width=0 height=0></textarea>
                <input type="text" name="table" value="SSM_GRADE_E_M" />
                <input type="text" name="field" value="C_REPORT" />
                <input type="text" name="sid" value="1" />
         </form>
    </div>
</div>

</@layout.doLayout>