<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doNew(){
        window.location.href='rec.html';
    }
    function doBack(){
       window.location.href='${referer!}';
    }
    function doSearch(id) {
        $("#"+id).datagrid('load', {
            T_START: $("input[name='T_START']","#"+id+"_tb").val(),
            T_END: $("input[name='T_END']","#"+id+"_tb").val()
        });
    }

    function doOpen(url,sid) {
        window.location.href = url+'.html?sid='+sid;
    }
    function doClear(id) {
        $(".easyui-datebox").datebox("setValue", "");
    }
    function doDel(sid) {
        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
                    if (deleteAction) {
                        $.get("del.do", {sid:sid}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "删除选定的记录成功");
                                $("#dg_draft").datagrid("reload");
                                $("#dg_draft").datagrid("clearSelections");
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
        });
        
    }
    $(function () {
        $('#dg_draft').datagrid({
            title:'自评草稿',
            //iconCls:'icon-star',
            url: 'list.json?N_STATE=0',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            striped: true,
            //fit:true, 
            collapsible:true,
            border:false,
            pageSize:20,
            height:300,
            toolbar: '#dg_draft_tb',
            columns: [[
                {field: 'C_TITLE', title: '标题', width: 500},
                {field: 'T_START', title: '自评开始日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'T_END', title: '自评结束日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'N_GET', title: '得分项', width: 60},
                {field: 'N_DEDUCT', title: '扣分项', width: 60},
                {field: 'N_LACK', title: '缺项', width: 60},
                {field: 'SID', title: '操作',width:80,align:'center',
                    formatter:function(value,row){
                            return "<a href='#' onclick='doOpen(\"draft_rec\","+value+")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'>删除</a>";
                    }}
            ]]
        });
        
        $('#dg_history').datagrid({
            title:'自评历史',
            //iconCls:'icon-star',
            url: 'list.json?N_STATE=1',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            //fit:true, 
            collapsible:true,
            border:false,
            height:300,
            toolbar: '#dg_history_tb',
            columns: [[
                {field: 'C_TITLE', title: '标题', width: 450},
                {field: 'T_START', title: '自评开始日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'T_END', title: '自评结束日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'N_GET', title: '得分项', width: 60},
                {field: 'N_DEDUCT', title: '扣分项', width: 60},
                {field: 'N_LACK', title: '缺项', width: 60},
                {field: 'SID', title: '查看', width: 80,align:'center',
                    formatter:function(value,row){
                            /*return "<a href='#' onclick='doOpen(\"history_rec\","+value+")'>自评结果</a>&nbsp;&nbsp;<a href='#' onclick='doOpen(\"history_rep\","+value+")'>自评报告</a>&nbsp;&nbsp;<a href='#' onclick='doOpen(\"/e/grade_d/sum\","+value+")'>汇总统计</a>";*/
                            return "<a href='#' onclick='doOpen(\"result\","+value+")'>查看</a>";
                        }}
            ]]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div>
    <div class="toolbar">
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-new" onclick="doNew();">开始新的自评</a>
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack();">返回</a>
    </div>

    <table id="dg_draft"></table>
        <div id="dg_draft_tb" style="padding:5px;height:auto">
                自评日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
                至: <input class="easyui-datebox" style="width:90px" name="T_END">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_draft')">查询</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_draft')" title="清空查询条件">重置</a>
        </div>
    <br>
    <table id="dg_history"></table>
        <div id="dg_history_tb" class="toolbar ue-clear">
                自评日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
                至: <input class="easyui-datebox" style="width:90px" name="T_END">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_history')">查询</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_history')" title="清空查询条件">重置</a>
        </div>
</div>
</@layout.doLayout>
