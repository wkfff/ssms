<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
function doSearch(id) {
    $("#" + id).datagrid('load', {
        C_NAME : $("input[name='C_NAME']", "#" + id + "_tb").val()
    });
}
function doDel(sid) {
    $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
                if (deleteAction) {
                    $.get("del", {sid:sid}, function (data) {
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
    window.location.href = 'rec?refer=edit&sid='+sid;
}
function doClear(id) {
    $(".easyui-textbox").textbox("setValue", "");
}
function doNew(){
    window.location.href = 'rec?refer=new';
}
$(document).ready(function () {
        $('#dg_index').datagrid({
            url: 'list',
            idField: 'SID',
            iconCls: 'icon-star',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            striped: true,
            toolbar: '#dg_index_tb',
            border: false,
            fit: true,
            columns: [
                [
                    {field: 'C_NAME', title: '专业名称', width: 150},
                    {field: 'SID', title: '操作', width: 150,align:'center',
                        formatter:function(value,row){
                                return "<a href='#' onclick='doEdit("+value+")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'>删除</a>";
                            }}
                ]
            ]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
    <@layout.group title="专业管理" border=true fit=true>
    <table id="dg_index"></table>
        <@layout.toolbar id="dg_index_tb">
        <div>
            <table>
                <tr>
                    <td>专业名称：</td>
                    <td><input class="easyui-textbox"  name="C_NAME"  type="text"/></td>
                    <td>
                        <@layout.button title="查询" icon="search" click="doSearch('dg_index')"/>
                        <@layout.button title="重置" desc="清空查询条件" icon="clear" click="doClear('dg_index')"/>
                        <@layout.button title="新增" desc="新增" icon="new" click="doNew()"/>
                    </td>
                </tr>
            </table>
        </div>
        </@>
       </@>
</@layout.doLayout>