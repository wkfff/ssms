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
    <div data-options="region:'north',collapsible:true" style="overflow:hidden;">
          <div id="toolbar" class="toolbar">
                <!-- <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:gridEvents.refreshClick">刷新</a> -->
                <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click: recEvents.saveClick">保存</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: recEvents.completeClick">完成评审</a>
                <#if showback??>
                <a href="#" class="easyui-linkbutton" iconCls="icon-back" plain="true" data-bind="click: recEvents.backClick">返回</a>
                </#if>
          </div>
    </div>
    
    <div data-options="region:'center'" >
        <div id="db_tb">
            <div class="db_tb">
                <input type="checkbox" data-bind="booleanValue:noComplete"/>
                <a href="javascript:void(0)" data-bind="click:gridEvents.noCompleteClick">只显示未评审项</a>
            </div>
        </div>
        <table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
    </div>
</div>

</@layout.doLayout>