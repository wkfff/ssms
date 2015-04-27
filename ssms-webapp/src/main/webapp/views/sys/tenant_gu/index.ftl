<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
function doSearch(id) {
    $("#" + id).datagrid('load', {
        C_NAME : $("input[name='C_NAME']", "#" + id + "_tb").val(),
        C_POSITION : $("input[name='C_POSITION']", "#" + id + "_tb").val()
    });
}
function doDel(sid) {
    $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
                if (deleteAction) {
                    $.get("del.do", {sid:sid}, function (data) {
                        if (data == "true" || data== "\"\"") {
                            $.messager.alert("提示", "删除选定的记录成功");
                            $("#dg_index").datagrid("reload");
                            $("#dg_index").datagrid("clearSelections");
                        }
                        else {
                            $.messager.alert("提示", data);
                        }
                    });
                }
    });
    
}
function doEdit(sid) {
    window.location.href = 'rec.html?refer=index&sid='+sid;
}
function doClear(id) {
    $(".easyui-textbox").textbox("setValue", "");
}
$(function () {
    $('#dg_index').datagrid({
        title:'政府用户管理',
        iconCls:'icon-star',
        url: 'list.json',
        idField: 'SID',
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        fitColumns: false,
        fit:true, 
        border:false,
        toolbar: '#dg_index_tb',
        columns: [[
            {field: 'C_NAME', title: '用户名', width: 500},
            {field: 'C_POSITION', title: '职务', width: 210},
            {field: 'T_BIRTH', title: '出生日期', width: 310},
            {field: 'SID', title: '操作', width: 160,align:'center',
                formatter:function(value,row){
                        return "<a href='#' onclick='doEdit("+value+")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'>删除</a>";
                    }}
        ]]
    });
});
</script>
</#assign> 
<@layout.doLayout script>
<table id="dg_index"></table>
<div id="dg_index_tb" style="padding:5px;height:auto">
            用户名: <input class="easyui-textbox" style="width:90px" name="C_NAME">
            职务:   <input class="easyui-textbox" style="width:90px" name="C_POSITION">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_index')">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_index')" title="清空查询条件">重置</a>
</div>
</@>
