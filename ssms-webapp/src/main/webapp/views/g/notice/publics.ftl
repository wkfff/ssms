<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSearch(id) {
        $("#" + id).datagrid('load', {
            T_START: $("input[name='T_START']", "#" + id + "_tb").val(),
            T_END: $("input[name='T_END']", "#" + id + "_tb").val()
        });
    }
    function doOpen(sid) {
        window.location.href = 'look.html?sid=' + sid;
    }
    function doClear(id) {
        $(".easyui-datebox").datebox("setValue", "");
    }
    function doNew(){
        window.location.href = 'rec.html?refer=new';
    }

    $(function () {
        $('#dg_publics').datagrid({
            title: '已发布的通知公告',
            //iconCls:'icon-star',
            url: 'list.json?N_STATE=1',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit: true,
            //border:false,
            toolbar: '#dg_publics_tb',
            columns: [
                [
                    {field: 'C_TITLE', title: '标题', width: 500},
                    {field: 'S_PUBLIC', title: '发布人', width: 100},
                    {field: 'T_PUBLIC', title: '发布日期', width: 100, formatter: function (value, row) {
                        return (value) ? value.substring(0, 10) : '';
                    }},
                    {field: 'SID', title: '操作', width: 80, align: 'center',
                        formatter: function (value, row) {
                            return "<a href='#' onclick='doOpen(" + value + ")'>查看</a>";
                        }}
                ]
            ]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<table id="dg_publics"></table>
<div id="dg_publics_tb" class="toolbar ue-clear">
    标题: <input class="easyui-textbox" style="width:90px" name="C_TITLE">
    发布日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
    至: <input class="easyui-datebox" style="width:90px" name="T_END">
    发布机构: <input class="easyui-textbox" style="width:90px" name="S_TANENT">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_publics')">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_publics')"
       title="清空查询条件">重置</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-new" plain="true" onclick="doNew()">新增</a>

</div>

</@layout.doLayout>
