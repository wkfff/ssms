
<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.edatagrid.js"></script>
<script type="text/javascript">
 $(document).ready(function() {
          $('#formMain').form('load','rec.json?sid=${sid!}');
 });
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
        }
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
                $.messager.alert("提示", "保存成功");
                window.location.href='index.html?refer=index&sid='+data.replaceAll('"','');
            }
        });
    }
    function doDel(){
        $.messager.confirm("删除确认", "您确认删除当前的信息吗？", function (deleteAction) {
                    if (deleteAction) {
                        $.get("del.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "删除成功");
                                window.location.href='rec.html';
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
        });
    }
    
    function doBack(){
        window.location.href='${Referer!}';
    }
  
</script>
</#assign>
<@layout.doLayout script>
     <div class="easyui-panel" style="border:0;background-color:#FAFAFA;padding:5px;">
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-cancel" onclick="doDel()">删除</a> 
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
     </div>
     <form id="formMain" method="post">
         <div class="easyui-panel" style="border:0;margin:10px" title="基本信息">
                    <table>
                         <tr>
                           <td class="span2">租户编码:</td>
                           <td colspan="3"><input class="easyui-textbox" type="text" name="C_CODE" style="width: 100%" /></td>
                        </tr>
                        <tr>
                           <td class="span2">企业名称:</td>
                            <td colspan="3"><input class="easyui-textbox" type="text" name="C_NAME" data-options="required:true" style="width: 100%" /></td>
                        </tr>
                        <tr>
                            <td class="span2">注册号:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_NUMBER" style="width: 100%" /></td>
                            <td class="span2">企业状态</td>
                            <td class="span4">
                                <input class="easyui-textbox" type="text" name="S_STATE"  style="width: 100%" />
                             </td>
                        </tr>
                        <tr>
                           <td class="span2">企业性质:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name=" S_NATURE" style="width: 100%" /></td>
                            <td class="span2">专职管理人员:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="N_EMPLOYEE" style="width: 100%" /></td>
                        </tr>
                        <tr>
                            <td class="span2">主营业务收入:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="N_INCOME"  style="width: 100%" /></td>
                            <td class="span2">固定资产:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="N_ASSETS" style="width: 100%" /></td>
                        </tr>
                        <tr>
                            <td class="span2">特种作业人员:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="N_SPECIAL"  style="width: 100%" /></td>
                            <td class="span2">营业范围:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_SCOPE" style="width: 100%" /></td>
                        </tr>
                    </table>
               </div>
                <div class="easyui-panel" style="border:0;margin:10px;" title="联系方式">
                    <table>
                        <tr>
                            <td class="span2">固话:</td>
                            <td class="span4" ><input class="easyui-textbox" type="text" name="C_TEL" style="width: 100%" /></td>
                            <td class="span2">电子邮箱:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_EMAIL" style="width: 100%" /></td>
                        </tr>
                        <tr>
                           <td class="span2">地址:</td>
                           <td colspan="3">
                               <table style="width: 100%" cellpadding="0" cellspacing="0">
                                  <tr>
                                     <td>
                                        <input class="easyui-textbox" type="text" name="S_PROVINCE" style="width: 60px"/>省
                                        <input class="easyui-textbox" type="text" name="S_CITY" style="width: 60px"/>市
                                        <input class="easyui-textbox" type="text" name="S_COUNTY" style="width: 60px"/>区(县)
                                     </td>
                                     <td>
                                         <input class="easyui-textbox" type="text" name="C_ADDR" style="width: 100%"/>
                                     </td>
                                  </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="span2">传真:</td>
                            <td class="span4" ><input class="easyui-textbox" type="text" name="C_FAX" style="width: 100%" /></td>
                            <td class="span2">邮政编码:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_ZIP" style="width: 100%" /></td>
                        </tr>
                        <tr>
                            <td class="span2">网站地址:</td>
                            <td colspan="3" ><input class="easyui-textbox" type="text" name="C_WEBSITE" style="width: 100%" /></td>
                        </tr>
                    </table>
                   </div>
                   <div class="easyui-panel" style="border:0;margin:10px;" title="企业概述">
                      <div class="span12">
                          <input class="easyui-textbox" name="C_SUMMARY" type="text" data-options="multiline:true" style="width: 100%; height: 32px"/>
                      </div>
                   </div>
                   <div class="easyui-panel" style="border:0;margin:10px;" title="企业达标情况">
                    <table>
                        <tr>
                            <td class="span2">达标等级:</td>
                            <td class="span4" ><input class="easyui-datebox" type="text" name="S_LEVEL"  style="width: 100%" /></td>
                            <td class="span2">下次复审时间:</td>
                            <td class="span4"><input class="easyui-datebox" type="text" name="T_EXAMINE_NEXT"  style="width: 100%" /></td>
                        </tr>
                        </table>
                     </div>
                   
            </form>
</@>

