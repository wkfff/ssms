<#import "/layout/_rec.ftl" as layout/>

<#assign script>
<script type="text/javascript">
    var form = {
        el: $('#fm'),
        save: function () {
            this.el.form('submit', {
                url: 'save.do',
                onSubmit: function (data) {
                    data.S_CYCLE = $('#P_CYCLE').combobox("getText");
                    data.S_TMPFILE = $('#P_TMPFILE').combobox("getText");
                    return $(this).form('validate');
                },
                success: function (result) {
                    result = $.parseJSON(result);
                    $.messager.alert('提示信息', '保存成功!', "info", function(){
                        window.location.href = 'rec.html?sid='+result.SID+"&backURL=${backURL!referer}";
                    });
                }
            });
        },
        back: function () {
            window.location.href = '${backURL!referer}'
        }
    }
</script>
<style type="text/css">
    td {
        vertical-align: top;
        line-height: 30px;
    }
</style>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" title="<#if sid??>编辑文件<#else>添加文件</#if>" fit="true" style="padding: 10px">
    <@layout.toolbar>
        <@layout.button title="保存" icon="save" click="form.save()"/>
        <@layout.button title="返回" icon="undo" click="form.back()"/>
    </@>
    <div class="hr"></div>
    <form id="fm" method="post">
        <table>
            <tr>
                <td class="span1"><label for="C_NAME">名称</label></td>
                <td class="span6">
                    <input type="text" id="C_NAME" name="C_NAME" class="easyui-textbox" required="true" style="width: 100%" value="${C_NAME!}"/>
                </td>
            </tr>
            <tr>
                <td class="span1">描述</td>
                <td class="span6">
                    <input type="text" id="C_DESC" name="C_DESC" class="easyui-textbox" multiline="true" style="width: 100%; height: 100px" value="${C_DESC!}"/>
                </td>
            </tr>
            <tr>
                <td class="span1">更新周期</td>
                <td class="span6">
                    <input type="text" class="easyui-numberbox" id="N_CYCLE" name="N_CYCLE" value="${N_CYCLE!}"/>
                    <select class="easyui-combobox" id="P_CYCLE" name="P_CYCLE" style="width: 80px">
                        <#list _SYS_CYCLE_ as item>
                            <option <#if (P_CYCLE?? && item.key=P_CYCLE)>selected="selected"</#if> value="${item.key}">${item.value}</option>
                        </#list>
                    </select>
                    <label><input type="checkbox" name="" id=""/>是否提醒</label>
                </td>
            </tr>
            <tr>
                <td class="span1"><label for="P_TMPFILE">模板文件</label></td>
                <td class="span6">
                    <select class="easyui-combobox" id="P_TMPFILE" name="P_TMPFILE">
                        <#list tmpfiles as item>
                            <option <#if (P_TMPFILE?? && item.key=P_TMPFILE)>selected="selected"</#if> value="${item.key}">${item.value}</option>
                        </#list>
                    </select>
                    <#if P_TMPFILE??><a href="/sys/stdtmp_file_${P_TMPFILE}/rec.html?pid=${SID}&backURL=${backURL!referer}">[配置模板]</a></#if>
                </td>
            </tr>
            <tr>
                <td class="span1"><label for="C_EXPLAIN">政策解读</label></td>
                <td class="span6">
                    <input id="C_EXPLAIN" name="C_EXPLAIN" value="${C_EXPLAIN!}" type="text" class="easyui-textbox" required="true" multiline="true" style="width: 100%; height: 100px"/>
                </td>
            </tr>
        </table>
        <input type="hidden" name="SID" value="${SID!}"/>
        <input type="hidden" name="R_SID" value="${R_SID!folder.SID}"/>
        <input type="hidden" name="S_NAME" value="${S_NAME!folder.C_NAME}"/>
    </form>
</div>
</@>