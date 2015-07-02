<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSearch(id) {
        $("#"+id).datagrid('load', {
            T_START: $("input[name='T_START']","#"+id+"_tb").val(),
            T_END: $("input[name='T_END']","#"+id+"_tb").val()
        });
    }

    function doOpen(url,sid) {
        window.location.href = url+'?sid='+sid;
    }
    function doClear(id) {
        $(".easyui-datebox").datebox("setValue", "");
    }
    $(function () {
        $('#dg_history').datagrid({
            title:'评审结果列表',
            //iconCls:'icon-star',
            url: '/e/reviewresult/list',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit:true, 
            //border:false,
            toolbar: '#dg_history_tb',
            columns: [[
                {field: 'S_TENANT', title: '评审机构', width: 150},
                {field: 'T_START', title: '评审开始日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'T_END', title: '评审结束日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'SID', title: '查看', width: 80,align:'center',
                    formatter:function(value,row){
                            return "<a href='#' onclick='doOpen(\"/e/reviewresult/tabs\","+value+")'>查看</a>";
                        }}
            ]]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
    <table id="dg_history"></table>
    <div id="dg_history_tb" class="toolbar ue-clear">
            评审日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
            至: <input class="easyui-datebox" style="width:90px" name="T_END">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_history')">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_history')" title="清空查询条件">重置</a>
    </div>
</@layout.doLayout>