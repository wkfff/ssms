<#import "/layout/_list.ftl" as layout/>
<#assign script>
    <script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
/* function doSearch(id) {
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
	 window.location.href = '/sys/tenant_ru/index.html?pid='+sid;
}
function doClear(id) {
    $(".easyui-textbox").textbox("setValue", "");
}
function doNew(){
    window.location.href = 'rec.html?refer=new';
}

$(function () {
    $('#dg_index').datagrid({
        title:'评审机构列表',
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
            {field: 'C_NAME', title: '单位名称', width: 200},
//            {field: 'S_LEVEL', title: '评审专业级别', width: 100},
            {field: 'C_NUMBER', title: '营业执照注册号', width: 200},
            {field: 'C_ADDR', title: '地址', width: 260},
            {field: 'SID', title: '操作', width: 120,align:'center',
                formatter:function(value,row){
                        return "<a href='#' onclick='doEdit("+value+")'>编辑</a> <a href='#' onclick='doDel("+value+")'>删除</a> <a href='#' onclick='doListUser("+value+")'>用户列表</a>";
                    }}
        ]]
    });
}); */
</script>
</#assign> 
<@layout.doLayout script>
<div id="toolbar" class="z-toolbar">
            单位名称: <input style="width: 120px" data-bind="textboxValue: C_NAME"/>
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: searchClick">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">注册评审单位</a>
</div>
<table id="dg_index" data-bind="datagridValue:selectItem,easyuiOptions:gridSettings"></table>
</@>
