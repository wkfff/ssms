<#--TODO缺少上传照片这个控件-->
<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>

<script type="text/javascript">
    function doSave() {
        $('#formMain').form('submit', {
            url: 'save?sid=${sid!}',
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (data) {
                $.messager.alert('保存', '保存成功！');
                window.location.href = 'index?pid=' +${pid!};
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
    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
</div>
<form id="formMain" method="post">
    <div class="easyui-panel" style="border:0;margin:10px" title="基本信息">
        <table>
            <tr>
                <td class="span2">用户名:</td>
                <td class="span4">
                    <input class="easyui-textbox" type="text" name="C_USER" data-options="required:true" style="width: 100%"/>
                </td>
                <td class="span2">姓名:</td>
                <td class="span4">
                    <input class="easyui-textbox" type="text" name="C_NAME" data-options="required:true" style="width: 100%"/>
                </td>
            </tr>
            <tr>
                <td class="span2">性别:</td>
                <td class="span4"><input type="radio" name="S_SEX" value="男"/>男
                    <input type="radio" name="S_SEX" value="女"/>女
                </td>
                <td class="span2">出生日期:</td>
                <td class="span4"><input class="easyui-datebox" type="text" name="T_BIRTH" style="width: 100%"/></td>
            </tr>
            <tr>
                <td class="span2">职务:</td>
                <td class="span4">
                    <input class="easyui-textbox" type="text" name="C_POSITION" style="width: 100%"/>
                </td>

                <td class="span2">部门:</td>
                <td class="span4"><input class="easyui-textbox" type="text" name="C_DEPT" style="width: 100%"/></td>
            </tr>
        </table>
    </div>
    <div class="easyui-panel" style="border:0;margin:10px;" title="联系方式">
        <table>
            <tr>
                <td class="span2">固话:</td>
                <td class="span4"><input class="easyui-textbox" type="text" name="C_TEL" style="width: 100%"/></td>
                <td class="span2">手机:</td>
                <td class="span4"><input class="easyui-textbox" type="text" name="C_MOBILE" style="width: 100%"/></td>
            </tr>
            <tr>
                <td class="span2">电子邮件:</td>
                <td class="span4"><input class="easyui-textbox" type="text" name="C_EMAIL" style="width: 100%"/></td>
            </tr>
            <tr>
                <td><input type="hidden" name="S_NAME" value="${tenant.S_TENANT!}">
                <td>
                <td><input type="hidden" name="R_SID" value="${pid!}">
                <td>
            </tr>
        </table>
    </div>
    <div class="easyui-panel" style="border:0;margin:10px;" title="学历">
        <table>
            <tr>
                <td class="span2">毕业学校:</td>
                <td colspan="3"><input class="easyui-textbox" type="text" name="C_SCHOOL" style="width: 100%"/></td>
            </tr>
            <tr>
                <td class="span2">学位:</td>
                <td class="span4"><input class="easyui-textbox" type="text" name="S_DEGREE" style="width: 100%"/></td>
                <td class="span2">学历:</td>
                <td class="span4"><input class="easyui-textbox" type="text" name="S_EDUCATION" style="width: 100%"/>
                </td>
            </tr>
        </table>
    </div>
</form>
</@>
