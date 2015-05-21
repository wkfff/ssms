<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSearch(id) {
        $("#"+id).datagrid('load', {
            T_START: $("input[name='T_START']","#"+id+"_tb").val(),
            T_END: $("input[name='T_END']","#"+id+"_tb").val()
        });
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
    function doEdit(sid) {
        window.location.href = 'draft_rec.html?sid='+sid;
    }

    function doClear(id) {
        $(".easyui-datebox").datebox("setValue", "");
        doSearch('#dg_draft');
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
            fit:true, 
            //border:false,
            pageSize:20,
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
                            return "<a href='#' onclick='doEdit("+value+")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'>删除</a>";
                    }}
            ]]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
    <table id="dg_draft"></table>
    <div id="dg_draft_tb" style="padding:5px;height:auto">
            自评日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
            至: <input class="easyui-datebox" style="width:90px" name="T_END">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_draft')">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_draft')" title="清空查询条件">重置</a>
    </div>
</@layout.doLayout>
