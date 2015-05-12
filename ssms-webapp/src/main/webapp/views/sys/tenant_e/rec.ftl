<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    /*$(document).ready(function () {
        $('#formMain').form('load', 'rec.json?sid=${sid!}');
    });

    function doSave() {
        //$.messager.progress();
        $('#formMain').form('submit', {
            url: 'save.do?sid=${sid!}',
            onSubmit: function (data) {
                var isValid = $(this).form('validate');
                if (!isValid) {
                    //$.messager.progress('close');
                }
                data.S_PROFESSION = $('#P_PROFESSION').combobox("getText");
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
    }*/
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" title="企业信息">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-cancel" onclick="doDel()">删除</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '${backURL!referer}'">返回</a>
    </div>
    <form id="formMain" method="post">
        <div class="easyui-panel" border="false" style="padding:10px" title="基本信息">
            <table>
                <tr>
                    <td class="span2">租户编码</td>
                    <td colspan="3">
                        <#if C_CODE??><div class="outline">${C_CODE}</div><#else><input class="easyui-textbox" type="text" name="C_CODE" required="true"/></#if>
                    </td>
                </tr>
                <tr>
                    <td class="span2">企业名称</td>
                    <td colspan="3">
                        <input class="easyui-textbox" type="text" name="C_NAME" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td class="span2">营业执照注册号</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="C_NUMBER"/></td>
                    <td class="span2">营业范围</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="C_SCOPE"/></td>
                </tr>
                <tr>
                    <td class="span2">企业专业</td>
                    <td colspan="3">
                        <#--TODO 现在暂时不用选择-->
                        <select class="easyui-combobox" id="P_PROFESSION" name="P_PROFESSION" data-options="multiple:true, readonly:true">
                            <#list _SYS_PROFESSION_ as item>
                                <option <#if (P_PROFESSION?? && item.key=P_PROFESSION)>selected="selected"</#if> value="${item.key}">${item.value}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="span2">主营业务收入</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="N_INCOME"/></td>
                    <td class="span2">固定资产</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="N_ASSETS"/></td>
                </tr>
                <tr>
                    <td class="span2">特种作业人员</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="N_SPECIAL"/></td>
                    <td class="span2">专职管理人员</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="N_EMPLOYEE"/></td>
                </tr>
            </table>
        </div>
        <div class="easyui-panel" border="false" style="padding:10px;" title="联系方式">
            <table>
                <tr>
                    <td class="span2">固话</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="C_TEL" data-options="validType: 'length[6,8]'"/></td>
                    <td class="span2">电子邮箱</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="C_EMAIL" data-options="validType:['email','length[0,64]']"/></td>
                </tr>
                <tr>
                    <td class="span2">地址</td>
                    <td colspan="3">
                        <table width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <input class="easyui-textbox" type="text" name="S_PROVINCE" style="width: 60px"/>省
                                    <input class="easyui-textbox" type="text" name="S_CITY" style="width: 60px"/>市
                                    <input class="easyui-textbox" type="text" name="S_COUNTY" style="width: 60px"/>区(县)
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="C_ADDR"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="span2">传真</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="C_FAX"/></td>
                    <td class="span2">邮政编码</td>
                    <td class="span4"><input class="easyui-textbox" type="text" name="C_ZIP" data-options="validType:'length[0,6]'"/></td>
                </tr>
                <tr>
                    <td class="span2">网站地址</td>
                    <td colspan="3"><input class="easyui-textbox" type="text" name="C_WEBSITE"/></td>
                </tr>
            </table>
        </div>
        <div class="easyui-panel" border="false" style="padding:10px;" title="企业概述">
            <div class="span12">
                <input class="easyui-textbox" name="C_SUMMARY" type="text" data-options="multiline:true" style="width: 100%; height: 32px"/>
            </div>
        </div>
        <div class="easyui-panel" border="false" style="padding:10px;" title="企业达标情况">
            <table>
                <tr>
                    <td class="">达标等级</td>
                    <td class="span3 outline">${T_EXAMINE!}</td>
                    <td class="">下次复审时间</td>
                    <td class="span3 outline">${T_EXAMINE_NEXT!}</td>
                </tr>
            </table>
        </div>
        <div class="easyui-panel" border="false" style="padding:10px;" title="费用信息">
            <table>
                <tr>
                    <td class="">缴费日期</td>
                    <td class="span3 outline">${T_PAY!}</td>
                    <td class="">下次缴费日期</td>
                    <td class="span3 outline">${T_PAY_NEXT!}</td>
                    <td class="">状态</td>
                    <td class="span3 outline">${S_STATE!}</td>
                </tr>
            </table>
        </div>
    </form>
</div>
</@>