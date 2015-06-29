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
    <div data-options="region:'center'" >
        <table data-bind="datagridValue:selectItem,datagridEditValue:editItem,easyuiOptions:gridSettings"></table>
    </div>
</div>

</@layout.doLayout>