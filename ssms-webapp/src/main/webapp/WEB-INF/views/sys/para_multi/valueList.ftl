<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
function doClear(id) {
    $(".easyui-textbox").textbox("setValue", "");
}
function doBack(){
      window.location.href="index"
}
function doEdit(sid) {
    window.location.href = 'rec?sid=${sid!}';
}
function doAdd() {
    window.location.href = 'rec?pid=${sid!}';
}
function doDel(sid) {
    $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
                if (deleteAction) {
                    $.get("del", {sid:sid}, function (data) {
                        if (data == "true" || data== "\"\"") {
                            $.messager.alert("提示", "删除选定的记录成功");
                            window.location.href='index';
                        }
                        else {
                            $.messager.alert("提示", data);
                            window.location.href=window.location.href;
                        }
                    });
                }
    });
}
$(function () {
    $('#dg_index').datagrid({
        title:'参数值列表',
        url: 'listV?sid=${sid!}',
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
    })
});
</script>
</#assign>
<@layout.doLayout script>
<table id="dg_index"></table>
<div id="dg_index_tb" style="padding:5px;height:auto">
        <a href="#" class="easyui-linkbutton" id="add" iconCls="icon-new" plain="true" onclick="doAdd()">新增</a>
        <a href="#" class="easyui-linkbutton" id="back" iconCls="icon-undo" plain="true" onclick="doBack()" title="返回">返回</a>
</div>

</@>
