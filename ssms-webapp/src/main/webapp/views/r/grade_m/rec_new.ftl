<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<style>
    .table td{
        padding:5px;
    }
    .datagrid-cell{
        white-space:normal !important;
    }
    .datagrid-cell-c2-B_BLANK{
        text-align:center !important;
    }
    .datagrid-cell-c2-B_BLANK input{
        width:100%;
    }
    .datagrid-editable-input{
        height:120px !important;
    }
    .panel-header{
        padding:5px !important;
    }
    .db_tb{
        height:15px;
        line-height:15px;
        font-size:12;
        vertial-align:top;
        margin:0 0 0 2px;
    }
    .db_tb input{
        margin-top:1px;
    }
    .db_tb a{
        width:90px;
    }
</style>

<script type="text/javascript">
    function doSave(){
        $.messager.progress();
        var isValid;
        $('#formMain').form('submit', {
            url:'save.do?sid=${sid!}',
            onSubmit: function(){
                isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');
                }
                return isValid;
            },
            success: function(data){
                $.messager.alert('保存','保存成功！');
                var sid = $.evalJSON(data).SID;
                window.location.href='rec.html?sid='+sid;
            }
        });
    }

    function doBack(){
        window.location.href='select.html';//${referer!}
    }
    
    $(function () {
        $('#formMain').form('load','rec.json?sid=${R_EID!}');
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div title="评审" data-options="region:'center',collapsible:true" style="height:230px;overflow:hidden;">
          <div class="toolbar ue-clear">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post">
                    <table class="table">
                        <tr>
                            <td class="span2">评审单位:</td>
                            <td class="span10" colspan="3">
                                <input class="easyui-textbox" type="text" name="S_TENANT" data-options="required:true" 
                                disabled=true value="${S_TENANT!}"/>
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">评审日期:</td>
                            <td class="span4"><input class="easyui-datebox" type="text" name="T_START" data-options="required:true,width:100"></input>
                                                        至<input class="easyui-datebox" type="text" name="T_END" data-options="required:true,width:100" ></input>
                            </td>
                            <td class="span2">评审组组长:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_LEADER" data-options="required:true"></input></td>
                        </tr>
                        <tr>
                            <td class="span2">评审组主要成员:
                            <td class="span10" colspan="3"><input class="easyui-textbox" type="text" name="C_MEMBERS" data-options="required:true"></input></td>
                        </tr>
                    </table>
                    <input type="hidden" name="R_EID" value="${R_EID!}"/>
            </form>
        </div>
    </div>
</div>

</@layout.doLayout>