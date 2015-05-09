<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSearch() {
        $("#dg").datagrid('load', {
            CB1: $("input[name='cb1']","#dg_tb").val(),
            CB2: $("input[name='cb2']","#dg_tb").val(),
            CB3: $("input[name='cb3']","#dg_tb").val()
        });
    }

    function doCheck(sid) {
        window.location.href = 'rec.html?sid='+sid;
    }
    function doClear(id) {
        $(".easyui-datebox").combbox("setValue", "");
    }
    $(function () {
        $('#dg').datagrid({
            title:'待评审企业',
            url: '/sys/tenant_e/list.json',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit:true, 
            //border:false,
            toolbar: '#dg_tb',
            columns: [[
                {field: 'C_NAME', title: '企业名称', width: 300},
                {field: 'SID', title: '操作', width: 80,align:'center',
                    formatter:function(value,row){
                            return "<a href='#' onclick='doCheck("+value+")'>评审</a>";
                    }}
            ]]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
    <table id="dg"></table>
    <div id="dg_tb" class="toolbar ue-clear">
        省：<input class="easyui-combobox" id="cb1" name="language" data-options="valueField:'id',textField:'text',width:100">
        市：<input class="easyui-combobox" id="cb2" name="language" data-options="valueField:'id',textField:'text',width:100">
        县：<input class="easyui-combobox" id="cb3" name="language" data-options="valueField:'id',textField:'text',width:100">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear()" title="清空查询条件">重置</a>
    </div>
</@layout.doLayout>
