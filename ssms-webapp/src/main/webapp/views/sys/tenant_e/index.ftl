<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSearch(id) {
        $("#" + id).datagrid('load', {
            C_NAME: $("input[name='C_NAME']", "#" + id + "_tb").val(),
            S_PROVINCE: $("input[name='S_PROVINCE']", "#" + id + "_tb").val(),
            S_CITY: $("input[name='S_CITY']", "#" + id + "_tb").val(),
            S_COUNTY: $("input[name='S_COUNTY']", "#" + id + "_tb").val()
        });
    }
    function doDel(sid) {
        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
            if (deleteAction) {
                $.get("del.do", {sid: sid}, function (data) {
                    if (data == "true" || data == "\"\"") {
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
        window.location.href = 'rec.html?refer=edit&sid=' + sid;
    }
    function doListUser(sid) {
        window.location.href = '/sys/tenant_eu/index.html?pid=' + sid;
    }
    function doClear(id) {
        $(".easyui-textbox").textbox("setValue", "");
    }
    function doNew() {
        window.location.href = 'reg.html';
    }

    $(function () {
        $('#dg_index').datagrid({
            title: '企业列表',
            url: 'list.json',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit: true,
            toolbar: '#dg_index_tb',
            columns: [
                [
                    {field: 'C_NAME', title: '企业名称', width: 200},
                    /*{field: 'S_PROVINCE', title: '省', width: 70},
                    {field: 'S_CITY', title: '市', width: 70},
                    {field: 'S_COUNTY', title: '区(县)', width: 70},*/
                    {field: 'S_PROFESSION', title: '专业', width: 70},
                    {field: 'T_PAY', title: '缴费日期', width: 100},
                    {field: 'T_PAY_NEXT', title: '下次缴费日期', width: 100},
                    {
                        field: 'SID', title: '操作', width: 130, align: 'center',
                        formatter: function (value, row) {
                            return "<a href='#' onclick='doEdit(" + value + ")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel(" + value + ")'>删除</a>&nbsp;&nbsp;<a href='#' onclick='doListUser(" + value + ")'>用户列表</a>";
                        }
                    }
                ]
            ]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<table id="dg_index"></table>
<div id="dg_index_tb" style="padding:5px;height:auto">
    企业名称: <input class="easyui-textbox" style="width:90px" name="C_NAME">
    省:<input class="easyui-textbox" style="width:90px" name="S_PROVINCE">
    市:<input class="easyui-textbox" style="width:90px" name="S_CITY">
    县:<input class="easyui-textbox" style="width:90px" name=" S_COUNTY">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_index')">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_index')" title="清空查询条件">重置</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-new" plain="true" onclick="doNew()">注册新企业</a>
</div>
</@>
