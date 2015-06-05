<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<style>
    .datagrid-cell{
        white-space:normal !important;
    }
    .datagrid-editable-input{
        height:120px !important;
    }
    .panel-header{
        padding:5px !important;
    }
</style>

<script type="text/javascript">
    function doBack(){
        window.location.href='${referer!}';
    }
    
    $(function () {
        $('#formMain').form('load','rec.json?sid=${sid!}');
        $form.disableForm('formMain',true);
        
        $('#dg').datagrid({
            title:'自评内容',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:true,
            border:false,
            striped: true,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 65},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250},
                {field: 'B_BLANK', title: '是否缺项', width: 65,align:'center',
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 65}
            ]],
            onLoadSuccess: function(data){
                $(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
            }
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true,collapsible:true" >
    <div title="自评结果" data-options="region:'north'" style="height:230px;overflow:hidden;">
          <div class="toolbar ue-clear">
              <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post">
                    <table cellspacing="6">
                        <tr>
                            <td class="span2">自评单位:</td>
                            <td class="span10" colspan="3">
                                <input class="easyui-textbox" type="text" name="S_TENANT" data-options="required:true" 
                                disabled=true value="${S_TENANT!}"/>
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

    <div data-options="region:'center'" >
        <table id="dg" class="easyui-datagrid" title="自评内容" />
    </div>
</div>

</@layout.doLayout>