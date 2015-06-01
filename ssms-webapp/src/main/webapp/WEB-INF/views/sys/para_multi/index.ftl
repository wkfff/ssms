<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
var sid;
function doSearch(id) {
    $("#" + id).datagrid('load', {
        C_NAME : $("input[name='C_NAME']", "#" + id + "_tb").val()
    });
}
function doClear(id) {
    $(".easyui-textbox").textbox("setValue", "");
}
function doNew(){
    window.location.href = 'reg';
}
function doList(name){
	window.location.href = 'valueList?name='+name;
}
$(function () {
    $('#dg_index').datagrid({
        title:'参数列表',
        url: 'list',
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
            {field: 'SID', title: '参数值', width: 120,align:'center',
                formatter:function(value,row){
                        return "<a href='#' onclick='doList(\""+row.C_NAME+"\")'>查看</a>";
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
</div>

</@>
