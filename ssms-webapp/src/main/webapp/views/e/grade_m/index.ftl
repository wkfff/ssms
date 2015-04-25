<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSearch(id) {
        $("#"+id).datagrid('load', {
            T_START: $("input[name='T_START']","#"+id+"_tb").val(),
            T_END: $("input[name='T_END']","#"+id+"_tb").val()
        });
    }
    function doNew() {
        $('#dlg').dialog('open').dialog('setTitle', '开始新的自评');
    }
    $(function () {
        $('#dg_history').datagrid({
            url: 'list.json',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: true,
            striped: true,
            toolbar: '#dg_history_tb',
            columns: [[
                {field: 'C_NAME', title: '标题', width: 100},
                {field: 'T_START', title: '自评开始时间', width: 100},
                {field: 'T_END', title: '自评结束时间', width: 100},
                {field: 'N_GET', title: '得分项', width: 100},
                {field: 'N_DEDUCT', title: '扣分项', width: 100},
                {field: 'N_LACK', title: '缺项', width: 100},
                {field: '操作', title: '操作', width: 100}
            ]]
        });
        $('#dg_draft').datagrid({
            url: 'list.json',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: true,
            striped: true,
            toolbar: '#dg_draft_tb',
            columns: [[
                {field: 'C_NAME', title: '标题', width: 100},
                {field: 'T_START', title: '自评开始时间', width: 100},
                {field: 'T_END', title: '自评结束时间', width: 100},
                {field: 'N_GET', title: '得分项', width: 100},
                {field: 'N_DEDUCT', title: '扣分项', width: 100},
                {field: 'N_LACK', title: '缺项', width: 100},
                {field: '操作', title: '操作', width: 100}
            ]]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div style="padding:10px;">

    <div class="easyui-panel" style="padding:5px;">
        <a href="#" class="easyui-linkbutton" data-options="plain: true">首页</a>
        >
        <a href="#" class="easyui-linkbutton" data-options="plain: true">在线自评</a>
    </div>
    
    <div class="easyui-panel" style="padding:5px;background:#FAFAFA;border-top:0;">
        <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-add', plain: false" onclick="doNew()">开始新的自评</a>
    </div>
    <br>
    
        历史<hr>
    <table id="dg_history"></table>
    <div id="dg_history_tb" style="padding:5px;height:auto">
                自评日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
                至: <input class="easyui-datebox" style="width:90px" name="T_END">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_history')">查询</a>
    </div>
    <br>
        草稿<hr>
    <table id="dg_draft"></table>
    <div id="dg_draft_tb" style="padding:5px;height:auto">
                自评日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
                至: <input class="easyui-datebox" style="width:90px" name="T_END">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_draft')">查询</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" data-options="closed:true, modal:true, buttons:'#dlg-buttons',fit:true,draggable:false">
        <iframe src="rec.html" frameborder=0 width=100% height=100%></iframe>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-ok'" onclick="doSave()">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-cancel'" onclick="$('#dlg').dialog('close')">取消</a>
</div>

</@layout.doLayout>
