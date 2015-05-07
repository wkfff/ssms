<#--TODO缺少上传照片这个控件-->
<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    $('#formMain').form('load','rec.json?sid=${sid!}&pid=${pid}');
});
    function svaeRInfo(){
       if(($("#C_PASSWD").val())!=(($("#C_PASSWD1").val()))){
            alert("两次输入密码一致");
             return;
        }
        var  v=$("#C_PASSWD").val($.md5($("#C_PASSWD").val()));
        $("input[name='C_PASSWD']").val(v.val())
        doSave();
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
                $.messager.alert('保存','保存成功！');
                window.location.href='index.html?pid='+${pid!}
            }
        });
    }
    
    function doDel(){
        $.messager.confirm("删除确认", "您确认删除当前信息吗？", function (deleteAction) {
                    if (deleteAction) {
                        $.get("del.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "删除成功");
                                window.location.href='index.html?pid='+${pid!}
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
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" <#if !sid??> onclick="svaeRInfo()" <#else> onclick="doSave()" </#if>>保存</a>
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-cancel" onclick="doDel()">删除</a> 
            <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
     </div>
     <form id="formMain" method="post">
         <div class="easyui-panel" style="border:0;margin:10px" title="基本信息">
                    <table>
                        <tr>
                            <td class="span2">用户名:</td>
                            <td class="span4">
                                <input class="easyui-textbox" type="text" name="C_USER" data-options="required:true"  <#if (refer=="edit")> editable="false" </#if>  style="width: 100%" />
                             </td>
                             <td class="span2">姓名:</td>
                            <td class="span4">
                                <input class="easyui-textbox" type="text" name="C_NAME" data-options="required:true" style="width: 100%" />
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">聘请证书编号:</td>
                            <td class="span4" ><input class="easyui-textbox" type="text" name="C_CERT" style="width: 100%" /></td>
                            <td class="span2">职务:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_POSITION" style="width: 100%"/></td>
                        </tr>
                        <tr>
                            <td class="span2">出生日期:</td>
                            <td class="span4"><input class="easyui-datebox" type="text" name="T_BIRTH" style="width: 100%" /></td>
                            <td class="span2">性别:</td>
                            <td class="span4" ><input  type="radio" name="S_SEX"  value="男"  />男
                                               <input  type="radio" name="S_SEX"  value="女" />女
                            </td>
                        </tr>
                    </table>
               </div>
                 <div class="easyui-panel" style="border:0;margin:10px;" title="联系方式">
                    <table>
                        <tr>
                            <td class="span2">固话:</td>
                            <td class="span4" ><input class="easyui-textbox" type="text" name="C_TEL" style="width: 100%" /></td>
                            <td class="span2">手机:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_MOBILE" style="width: 100%" /></td>
                        </tr>
                        <tr>
                         <td><input type="hidden" name="S_NAME" value="${tenant.S_TENANT!}"><td>
                        <td><input type="hidden" name="R_SID" value="${pid!}"><td>
                        </tr>
                    </table>
                   </div>
                   <div class="easyui-panel" style="border:0;margin:10px;" title="学历">
                     <table>
                        <tr>
                            <td class="span2">毕业学校:</td>
                            <td colspan="3"><input class="easyui-textbox" type="text" name="C_SCHOOL" style="width: 100%"  /></td>
                        </tr>
                        <tr>
                            <td class="span2">学位:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="S_DEGREE" style="width: 100%"/></td>
                            <td class="span2">学历:</td>
                            <td class="span4" ><input class="easyui-textbox" type="text" name="S_EDUCATION" style="width: 100%"/></td>
                        </tr>
                    </table>
                  </div>
                   <div class="easyui-panel" style="border:0;margin:10px;" title="工作简历">
                      <div class="span12">
                          <input class="easyui-textbox" name="C_RESUME" type="text" data-options="multiline:true" style="width: 100%; height: 32px"/>
                      </div>
                   </div>
                   <#if !sid??>
                    <div class="easyui-panel" style="border:0;margin:10px;" title="密码设置">
                     <table>
                        <tr>
                            <td class="span2">密码:</td>
                            <td class="span4" ><input class="easyui-textbox" type="password" name="C_PASSWD" id="C_PASSWD"  data-options="required:true" style="width: 100%" /></td>
                             <td class="span2">重复密码:</td>
                            <td class="span4"><input class="easyui-textbox"  type="password"  id="C_PASSWD1"  data-options="required:true" style="width: 100%" /></td>
                        </tr>
                        <!-- <tr>
                        <td><input type="button" class="btn" name="btn_reset" value="快速重置密码"/></td>
                        </tr> -->
                    </table>
                   </div>
                   </#if>
            </form>
</@>
