<#import "../../layout/_rec.ftl" as layout/>
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

<script type="text/javascript" src="rec.js"></script>
<script type="text/javascript">
    var vm = new ViewModel(${sid});
    ko.applyBindings(vm);
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div title="在线自评" data-options="region:'north',collapsible:true" style="height:230px;overflow:hidden;">
          <div id="toolbar" class="toolbar">
                <!-- <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:gridEvents.refreshClick">刷新</a> -->
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: recEvents.completeClick">完成自评</a>
                <!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: recEvents.reportClick">自评报告</a> -->
                <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click: recEvents.saveClick">保存</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: recEvents.removeClick">删除</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-back" plain="true" data-bind="click: recEvents.backClick">返回</a>
          </div>

         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post" data-bind="with:rec">
                    <table class="table">
                        <tr>
                            <td class="span2">自评单位:</td>
                            <td class="span10" colspan="3">
                                <input data-bind="textboxValue: S_TENANT" data-options="disabled:true" />
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">自评日期:</td>
                            <td class="span4">
                            <input data-bind="dateboxValue: T_START" data-options="required:true,width:90"/>
                                                        至
                            <input data-bind="dateboxValue: T_END" data-options="required:true,width:90"/>
                            </td>
                            <td class="span2">自评组组长:</td>
                            <td class="span4">
                            <input data-bind="textboxValue: C_LEADER" data-options="required:true"/>
                        </tr>
                        <tr>
                            <td class="span2">自评组主要成员:
                            <td class="span10" colspan="3">
                            <input data-bind="textboxValue: C_MEMBERS" data-options="required:true"/>
                            </td>
                        </tr>
                    </table>
            </form>
        </div>
    </div>
    
    <div data-options="region:'center'" >
        <div id="db_tb">
            <div class="db_tb">
                <input type="checkbox" data-bind="booleanValue:noComplete"/>
                <a href="javascript:void(0)" data-bind="click:gridEvents.noCompleteClick">只显示未完成项</a>
            </div>
        </div>
        <table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
    </div>
</div>

</@layout.doLayout>