<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doNew(){
        window.location.href='/e/gradeplan/create';
    }
    function doBack(){
       window.location.href='${referer!}';
    }
    function doSearch(id) {
        $("#"+id).datagrid('load', {
            T_START: $("input[name='T_START']","#"+id+"_tb").val(),
            T_END: $("input[name='T_END']","#"+id+"_tb").val()
        });
    }

    function doOpen(url,sid) {
        window.location.href = url+'?sid='+sid;
    }
    function doClear(id) {
        $(".easyui-datebox",$("#"+id+"_tb")).datebox("setValue", "");
    }
    function doDel(sid) {
        $.messager.confirm("删除确认", "您确认删除选定的记录吗？", function (deleteAction) {
                    if (deleteAction) {
                        $.get("del", {sid:sid}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "记录删除成功!");
                                $("#dg_draft").datagrid("reload");
                                $("#dg_draft").datagrid("clearSelections");
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
        });
        
    }
    $(function () {
        $('#dg_draft').datagrid({
            title:'自评草稿',
            //iconCls:'icon-star',
            url: '/e/gradeplan/list?N_STATE=0',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            striped: true,
            fit:true, 
            //collapsible:true,
            //border:false,
            pageSize:20,
            toolbar: '#dg_draft_tb',
            columns: [[
                {field: 'S_PROFESSION', title: '专业', width: 100},
                {field: 'T_START', title: '自评开始日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'T_END', title: '自评结束日期', width: 100,formatter:function(value,row){return (value)?value.substring(0,10):'';}},
                {field: 'N_GET', title: '得分项', width: 60},
                {field: 'N_DEDUCT', title: '扣分项', width: 60},
                {field: 'N_LACK', title: '缺项', width: 60},
                {field: 'SID', title: '操作',width:180,align:'center',
                    formatter:function(value,row){
                    //&nbsp;&nbsp;<a href='#' onclick='doOpen(\"rep\","+value+")'>编辑自评报告</a>
                            return "<a href='#' onclick='doOpen(\"tabs\","+value+")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'>删除</a>";
                    }}
            ]],
            onLoadSuccess: function(data){
                $(this).datagrid("getPanel").panel("setTitle","自评草稿["+data.total+"]");
            }
        });
    });
</script>
</#assign>
<@layout.doLayout script>
        <table id="dg_draft"></table>
        <div id="dg_draft_tb" style="padding:5px;height:auto">
                自评日期从: <input class="easyui-datebox" style="width:90px" name="T_START">
                至: <input class="easyui-datebox" style="width:90px" name="T_END">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch('dg_draft')">查询</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="doClear('dg_draft')" title="清空查询条件">重置</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-new" onclick="doNew();">开始新的自评</a>
        </div>
</@layout.doLayout>
