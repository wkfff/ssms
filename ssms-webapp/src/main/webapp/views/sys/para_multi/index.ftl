<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
function doSearch(id) {
    $("#" + id).datagrid('load', {
        C_NAME : $("input[name='C_NAME']", "#" + id + "_tb").val()
    });
}
function doClear(id) {
    $(".easyui-textbox").textbox("setValue", "");
}
function doBack(){
      window.location.href="index.html"
}
function doEdit(sid) {
    window.location.href = 'rec.html?sid='+sid;
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
function doNew(){
    window.location.href = 'rec.html';
}
function doList(id){
	$('#value').hide();
	$('#search').hide();
	$('#clear').hide();
	$('#new').hide();
	$('#back').linkbutton('enable');
    $('#dg_index').datagrid({
        title:'参数值列表',
        url: 'listV.json?sid='+id,
        idField: 'SID',
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        fitColumns: false,
        fit:true, 
        border:false,
        toolbar: '#dg_index_tb',
        columns: [[
            {field: 'C_CODE', title: '参数编码', width: 200},
            {field: 'C_VALUE', title: '参数值', width: 200},
            {field: 'SID', title: '操作', width: 120,align:'center',
                formatter:function(value,row){
                        return "<a href='#' onclick='doDel("+value+")'>删除</a> <a href='#' onclick='doEdit("+value+")'>编辑</a>";
                    }}

        ]]
    });
}
$(function () {
	$('#back').linkbutton('disable');
    $('#dg_index').datagrid({
        title:'参数列表',
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
            {field: 'C_NAME', title: '参数名', width: 200},
            {field: 'C_CODE', title: '参数编码', width: 200},
            {field: 'SID', title: '参数值', width: 120,align:'center',
                formatter:function(value,row){
                        return "<a href='#' onclick='doList("+value+")'>查看</a>";
                    }}
        ]]
    });
});
</script>
</#assign> 
<@layout.doLayout script>
<table id="dg_index"></table>
<div id="dg_index_tb" style="padding:5px;height:auto">
        <span id="value"> 参数名: <input class="easyui-textbox"  style="width:90px" name="C_NAME">
        <a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search" plain="true"   onclick="doSearch('dg_index')">查询</a></span>
        <a href="#" class="easyui-linkbutton" id="clear" iconCls="icon-clear" plain="true" onclick="doClear('dg_index')" title="清空查询条件">重置</a>
        <a href="#" class="easyui-linkbutton" id="new" iconCls="icon-new" plain="true" onclick="doNew()">新增</a>
        <a href="#" class="easyui-linkbutton" id="back" iconCls="icon-undo" plain="true" onclick="doBack()" title="返回">返回</a>
</div>

</@>
