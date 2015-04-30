<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.edatagrid.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#formMain').form('load', 'rec.json?sid=${sid!}');
    });
    String.prototype.replaceAll = function (s1, s2) {
        return this.replace(new RegExp(s1, "gm"), s2);
    }
    function doPublic() {
        //$.messager.progress();
        $('#formMain').form('submit', {
            url: 'save.do?sid=${sid!}&N_STATE=1',
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (!isValid) {
                    //$.messager.progress('close');
                }
                return isValid;
            },
            success: function (data) {
                $.messager.alert("提示", "保存成功");
                window.location.href = 'index.html?refer=index&sid=' + data.replaceAll('"', '');
            }
        });
    }
    function doDraft() {
        //$.messager.progress();
        $('#formMain').form('submit', {
            url: 'save.do?sid=${sid!}&N_STATE=0',
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (!isValid) {
                    //$.messager.progress('close');
                }
                return isValid;
            },
            success: function (data) {
                $.messager.alert("提示", "保存成功");
                window.location.href = 'index.html?refer=index&sid=' + data.replaceAll('"', '');
            }
        });
    }
    function doDel() {
        $.messager.confirm("删除确认", "您确认删除当前的信息吗？", function (deleteAction) {
            if (deleteAction) {
                $.get("del.do", {sid: '${sid!}'}, function (data) {
                    if (data == "true" || data == "\"\"") {
                        $.messager.alert("提示", "删除成功");
                        window.location.href = 'rec.html';
                    }
                    else {
                        $.messager.alert("提示", data);
                    }
                });
            }
        });
    }

    function doBack() {
        window.location.href = '${Referer!}';
    }

</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" style="border:0;background-color:#FAFAFA;padding:5px;">
    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doPublic()">发布</a>
    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doDraft()">草稿</a>
    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-cancel" onclick="doDel()">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
</div>
<form id="formMain" method="post">
    <div class="easyui-panel" style="border:0;margin:10px" title="基本信息">
        <table>
            <tr>
                <td class="span2">标题:</td>
                <td colspan="3"><input class="easyui-textbox" type="text" name="C_TITLE" data-options="required:true"
                                       style="width: 100%"/></td>
            </tr>

            <tr>
                <td class="span2">发布人:</td>
                <td class="span4">
                    <input class="easyui-textbox" type="text" name="S_PUBLIC" style="width: 100%"/>
                </td>
                <td class="span2">发布时间:</td>
                <td class="span4">
                    <input class="easyui-datebox" type="text" name="T_PUBLIC" data-options="required:true"
                           style="width: 100%"/>
                </td>
            </tr>

            <tr>
                <td class="span2">生效时间起:</td>
                <td class="span4">
                    <input class="easyui-datebox" type="text" name="T_BEGIN" data-options="required:true"
                           style="width: 100%"/>
                </td>
                <td class="span2">生效时间止:</td>
                <td class="span4">
                    <input class="easyui-datebox" type="text" name="T_END" data-options="required:true"
                           style="width: 100%"/>
                </td>
            </tr>


        </table>
    </div>

    <div class="easyui-panel" style="border:0;margin:10px;" title="正文">
        <div class="span12">
            <input class="easyui-textbox" name="C_CONTENT" type="text" data-options="multiline:true"
                   style="width: 100%; height: 128px"/>
        </div>
    </div>


</form>
</@>

