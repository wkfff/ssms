<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSearch(id) {
        $("#"+id).datagrid('load', {
            S_PROFESSION: $("#S_PROFESSION").val()
        });
    }
    function doDel(sid) {
        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
                    if (deleteAction) {
                        $.get("del", {sid:sid}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "删除选定的记录成功");
                                $("#dg").datagrid("reload");
                                $("#dg").datagrid("clearSelections");
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
        });
        
    }
    function doEdit(sid) {
        window.location.href = 'rec?sid='+sid;
    }

    function doClear(id) {
        //$(".easyui-datebox").datebox("setValue", "");
        $("#S_PROFESSION").val("");
        doSearch('#dg');
    }
    $(function () {
        $('#dg').datagrid({
            title:'自评报告模板',
            url: 'list',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            striped: true,
            fit:true, 
            border:false,
            pageSize:20,
            toolbar: '#dg_tb',
            columns: [[
                {field: 'S_PROFESSION', title: '专业', width: 200},
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
    <table id="dg"></table>
    <div id="dg_tb" style="padding:5px;height:auto">
            专业名称: <input class="easyui-textbox" style="width:120px" id="S_PROFESSION" name="S_PROFESSION">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg')">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg')" title="清空查询条件">重置</a>
    </div>
</@layout.doLayout>
