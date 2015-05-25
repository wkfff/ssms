<#import "../../layout/_rec.ftl" as layout/> <#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript"
    src="/resource/js/easyui/plugins/jquery.edatagrid.js"></script>
<!--  <script type="text/javascript" src="/resource/js/cascade.js"></script>-->
<script type="text/javascript">
    function doSave() {
        $('#formMain').form(
                'submit',
                {
                    url : 'save',
                    onSubmit : function() {
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            //$.messager.progress('close');
                        }
                        return isValid;
                    },
                    success : function(data) {
                        $.messager.alert("提示", "保存成功");
                        window.location.href = 'index';
                    }
                });
    }
    function doBack() {
        window.location.href = "index";
    }
</script>
</#assign> <@layout.doLayout script>
<div class="easyui-panel"
    style="border: 0; background-color: #FAFAFA; padding: 5px;">
    <a href="#" class="easyui-linkbutton" data-options="plain: true"
        iconCls="icon-save" onclick="doSave()">保存</a> <a href="#"
        class="easyui-linkbutton" data-options="plain: true"
        iconCls="icon-undo" onclick="doBack()">返回</a>
</div>
<form id="formMain" method="post">
    <div class="easyui-panel" style="border: 0; margin: 10px"
        title="参数信息">
        <table>
            <tr>
                <td class="span2">参数名:</td>
                <td colspan="3"><input class="easyui-textbox"
                    type="text" name="C_NAME"
                    data-options="required:true"
                    style="width: 100%" /></td>
            </tr>
            <tr>
                <td class="span2">参数编号:</td>
                <td colspan="3"><input class="easyui-textbox"
                    data-options="required:true"
                    name="C_CODE"
                    style="width: 100%" /></td>
            </tr>
            <tr>
                <td class="span2">参数值:</td>
                <td colspan="3"><input class="easyui-textbox"
                    type="text" name="C_VALUE"
                    data-options="required:true" style="width: 100%" />
                </td>
            </tr>
        </table>
    </div>
</form>
</@>

