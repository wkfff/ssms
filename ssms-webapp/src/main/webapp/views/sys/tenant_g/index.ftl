<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
function doSearch(id) {
    $("#" + id).datagrid('load', {
        C_NAME : $("input[name='C_NAME']", "#" + id + "_tb").val(),
        S_PROVINCE : $("input[name='S_PROVINCE']", "#" + id + "_tb").val(),
        S_CITY : $("input[name='S_CITY']", "#" + id + "_tb").val(),
        S_COUNTY : $("input[name='S_COUNTY']", "#" + id + "_tb").val()
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
    window.location.href = 'rec.html?refer=edit&sid='+sid;
}
function doListUser(sid){
     window.location.href = '/sys/tenant_gu/index.html?pid='+sid;
}
function doClear(id) {
    $(".easyui-textbox").textbox("setValue", "");
}
function doNew(){
    window.location.href = 'rec.html?refer=new';
}

$(function () {
    $('#dg_index').datagrid({
        title:'政府机构列表',
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
            {field: 'C_NAME', title: '单位名称', width: 400},
            {field: 'S_PROVINCE', title: '省', width: 110},
            {field: 'S_CITY', title: '市', width: 110},
            {field: 'S_COUNTY', title: '县', width: 110},
            {field: 'C_ADDR', title: '地址', width: 260},
            {field: 'SID', title: '操作', width: 220,align:'center',
                formatter:function(value,row){
                        return "<a href='#' onclick='doEdit("+value+")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'>删除</a>&nbsp;&nbsp;<a href='#' onclick='doListUser("+value+")'>用户列表</a>";
                    }}
        ]]
    });
});
</script>
</#assign> 
<@layout.doLayout script>
<table id="dg_index"></table>
<div id="dg_index_tb" style="padding:5px;height:auto">
            单位名称: <input class="easyui-textbox" style="width:90px" name="C_NAME">
            省:<input class="easyui-textbox" style="width:90px" name="S_PROVINCE">
            市:<input class="easyui-textbox" style="width:90px" name="S_CITY">
            县:<input class="easyui-textbox" style="width:90px" name="S_COUNTY">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_index')">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_index')" title="清空查询条件">重置</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-new" plain="true" onclick="doNew()">新增</a>
</div>
</@>
