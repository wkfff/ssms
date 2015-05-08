<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<style>
    .datagrid-cell{
        white-space:normal !important;
    }
    .datagrid-editable-input{
        height:120px !important;
    }
</style>

<script type="text/javascript">
    function doNew(){
        window.location.href='rec.html';
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
                window.location.href='draft_rec.html?sid='+$str.replaceAll(data,'"','');
            }
        });
    }

    function doBack(){
        window.location.href='${referer!}';
    }
    
    $(function () {
        $('#formMain').form('load','rec.json?sid=${sid!}');
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div title="在线自评" data-options="region:'center',collapsible:false" style="overflow:hidden;">
          <div class="toolbar ue-clear">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post">
                    <table cellspacing="6">
                        <tr>
                            <td class="span2">自评单位:</td>
                            <td class="span10" colspan="3">
                                <input class="easyui-textbox" type="text" name="S_TANENT" data-options="required:true" 
                                disabled=true value="${S_TANENT!}"/>
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">自评日期:</td>
                            <td class="span4"><input class="easyui-datebox" type="text" name="T_START" data-options="required:true,width:100"></input>
                                                        至<input class="easyui-datebox" type="text" name="T_END" data-options="required:true,width:100" ></input>
                            </td>
                            <td class="span2">自评组组长:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_LEADER" data-options="required:true"></input></td>
                        </tr>
                        <tr>
                            <td class="span2">自评组主要成员:
                            <td class="span10" colspan="3"><input class="easyui-textbox" type="text" name="C_MEMBERS" data-options="required:true"></input></td>
                        </tr>
                    </table>
            </form>
        </div>
    </div>
</div>

</@layout.doLayout>