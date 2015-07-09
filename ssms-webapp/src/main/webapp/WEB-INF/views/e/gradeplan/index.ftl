<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
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
        utils.messager.confirm("您确认删除选定的自评吗？", function () {
            $.get("del", {sid:sid}, function (data) {
                if (data == "true" || data== "\"\"") {
                    utils.messager.alert("记录删除成功!");
                    $("#dg_draft").datagrid("reload");
                    $("#dg_draft").datagrid("clearSelections");
                }
                else {
                    utils.messager.alert(data);
                }
            });
        });
        
    }
    function doCreate(){
        utils.messager.showProgress('开始新的自评，初始化数据中，请稍候......');
            
        $.post('/e/gradeplan/init', {}, function (result) {
                utils.messager.closeProgress();
                if (result.SID) {
                    utils.messager.alert("初始化成功! 点击确认开始自评。",function () {
                        window.location.href = '/e/gradeplan/tabs/' + result.SID;
                    });
                } else {
                    utils.messager.alert("初始化失败:"+result);
                }
         }, "json");
    }
    
    $(function () {
        <#if autoCreate??>
            doCreate();
        </#if>
        
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
                {field: 'SID', title: '操作',width:140,align:'center',
                    formatter:function(value,row){
                            return "<a href='#' onclick='doOpen(\"tabs\","+value+")'><img src='/resource/images/edtico.png'/>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='doDel("+value+")'><img src='/resource/images/delico.png'/>删除</a>";
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
            <a href="#" class="easyui-linkbutton" iconCls="icon-new" onclick="doCreate();">开始新的自评</a>
        </div>
</@layout.doLayout>
