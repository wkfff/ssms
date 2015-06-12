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
        //window.location.href = '/e/grade_m/result.html?sid='+sid;
        //'rec_history.html?sid='+sid;
        window.location.href = url+'?sid='+sid;
    }
    function doClear(id) {
        $(".easyui-datebox").datebox("setValue", "");
    }
    $(function () {
        $('#dg_history').datagrid({
            title:'评审历史',
            //iconCls:'icon-star',
            url: '/r/grade_m/list?N_STATE=4',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit:true, 
            //border:false,
            toolbar: '#dg_history_tb',
            columns: [[
                {field: 'C_TITLE', title: '标题', width: 450},
                {field: 'T_START', title: '评审开始日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'T_END', title: '评审结束日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'N_GET', title: '得分项', width: 60},
                {field: 'N_DEDUCT', title: '扣分项', width: 60},
                {field: 'N_LACK', title: '缺项', width: 60},
                {field: 'SID', title: '查看', width: 80,align:'center',
                    formatter:function(value,row){
                            /*return "<a href='#' onclick='doOpen(\"history_rec\","+value+")'>评审结果</a>&nbsp;&nbsp;<a href='#' onclick='doOpen(\"history_rep\","+value+")'>评审报告</a>&nbsp;&nbsp;<a href='#' onclick='doOpen(\"/e/grade_d/sum\","+value+")'>汇总统计</a>";*/
                            return "<a href='#' onclick='doOpen(\"result\","+value+")'>查看</a>";
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
