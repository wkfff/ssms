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

    <div data-options="region:'west',split:true" title="自评报告" style="width:200px;">
        <div style="padding:5px;">
            <ul>
                <#list rs as r>
                <li style="margin:5px;">
                    <span ><a href="#" onclick="loadData(${r.SID})">${r.C_TITLE}</a></span>
                </li>
                </#list>
            </ul>
        </div>
    </div>

    <div data-options="region:'center'" style="overflow:hidden;border:0;padding:0px;margin:0px;">
        <div class="toolbar ue-clear">
            <#if (_FLAG_!='-1')>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-ok" onclick="doComplete()">完成报告</a>
            </#if>
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()" style="display:none;">返回</a>
         </div>
         
         <form id="formMain" method="post">
                <textarea class="easyui-kindeditor" id="C_CONTENT" name="C_CONTENT" data-options="width:'100%',height:'560px'"></textarea>
                <input type="hidden" id="R_SID" name="R_SID"/>
         </form>
    </div>
</div>

</@layout.doLayout>