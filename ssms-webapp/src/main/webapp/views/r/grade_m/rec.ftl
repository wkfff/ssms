<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<style>
    .table td{
        padding:5px;
    }
    .datagrid-cell{
        white-space:normal !important;
    }
    .datagrid-cell-c2-B_BLANK{
        text-align:center !important;
    }
    .datagrid-cell-c2-B_BLANK input{
        width:100%;
    }
    .datagrid-editable-input{
        height:120px !important;
    }
    .panel-header{
        padding:5px !important;
    }
    .db_tb{
        height:15px;
        line-height:15px;
        font-size:12;
        vertial-align:top;
        margin:0 0 0 2px;
    }
    .db_tb input{
        margin-top:1px;
    }
    .db_tb a{
        width:90px;
    }
</style>

<script type="text/javascript">
    function doSave(){
        $.messager.progress();
        endEditing();
        $('#formMain').form('submit', {
            url:'save.do?sid=${sid!}',
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');
                }
                return isValid;
            },
            success: function(data){
                $.messager.alert('保存','保存成功！');
            }
        });
    }
    
    function doComplete(){
        $.get("check.json", {sid:'${sid!}',eid:'${R_EID!}'}, function (data) {
            if (data && data>0) {
                $.messager.alert("提示", "评审还有未填写的项，请填写后再完成评审！");
            }
            else {
                $.messager.confirm("完成确认", "您确认完成当前的评审吗？", function (action) {
                    if (action) {
                        $.get("complete.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "评审完成");
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
                });
            }
        });
    }
    
    function doBack(){
        window.location.href='index.html';//${referer!}
    }
    
    function doShow(v){
        var chk = $('.db_tb input')[0].checked;
        if (typeof(v)=='undefined'){
            chk = !chk;
            $('.db_tb input')[0].checked = chk;
        }
        var url = '/r/grade_d/list_detail.json?R_SID=${sid!-1}'+(chk?'&score=0':'');
        $('#dg').datagrid({url:url});
    }

    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#dg').datagrid('validateRow', editIndex)){
            var row = $('#dg').datagrid('getRows')[editIndex];
            var v1 = row['N_SCORE'];
            var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'N_SCORE_REAL'});
            var v2 = ed?ed.target.val():0;
            if (v1<v2){
                alert('实际得分不能大于标准分值！');
                return false;
            }
            
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            
            var opts = $('#dg').datagrid('options');
            var url = opts.updateUrl;
            if (url){
                $.post(url, {R_SID:row.R_SID,R_DID:row.R_DID,C_DESC:row.C_DESC,N_SCORE_REAL:row.N_SCORE_REAL}, function(data){});
            }
            return true;
        } else {
            return false;
        }
    }
    function onClickRow(index){
        var v = $('#dg').datagrid('getRows')[index]['S_PROJECT'];
        if (v=='小计' || v=='总计'){
            return;
        }
        if (editIndex != index){
            if (endEditing()){
                $('#dg').datagrid('selectRow', index)
                        .datagrid('beginEdit', index);
                editIndex = index;
            } else {
                $('#dg').datagrid('selectRow', editIndex);
            }
        }
    }
    $(function () {
        $('#formMain').form('load','rec.json?sid=${sid!}');
        
        $('#dg').datagrid({
            title:'评审内容',
            url: '/r/grade_d/list_detail.json?R_SID=${sid!-1}',
            updateUrl:'/r/grade_d/save.do',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            autoSave:true,
            fit:true,
            border:false,
            striped: false,
            columns: [[
                {field: 'C_CATEGORY', title: '一级要素', width: 100},
                {field: 'C_PROJECT', title: '二级要素', width: 100},
                {field: 'C_CONTENT', title: '基本规范要求', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 65},
                {field: 'C_METHOD', title: '评分方式', width: 350},
                {field: 'N_SCORE_SELF', title: '企业自评分', align:'center',width: 80},
                {field: 'C_DESC', title: '评审描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'N_SCORE_REAL', title: '评审得分', align:'center',width: 65,editor:'numberbox'}
            ]],
            onLoadSuccess: function(data){
                
            },
            onClickRow:onClickRow,
            rowStyler: function(index,row){
                if (row.S_PROJECT == '小计' || row.S_PROJECT == '总计'){
                    return 'background-color:#FAFAFA;color:#000;font-weight:bold;';
                }
            }
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div title="评审" data-options="region:'north',collapsible:true" style="height:230px;overflow:hidden;">
          <div class="toolbar ue-clear">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-ok" onclick="doComplete()">完成评审</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post">
                    <table class="table">
                        <tr>
                            <td class="span2">评审单位:</td>
                            <td class="span10" colspan="3">
                                <input class="easyui-textbox" type="text" name="S_TENANT" data-options="required:true" 
                                disabled=true value="${S_TENANT!}"/>
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">评审日期:</td>
                            <td class="span4"><input class="easyui-datebox" type="text" name="T_START" data-options="required:true,width:100"></input>
                                                        至<input class="easyui-datebox" type="text" name="T_END" data-options="required:true,width:100" ></input>
                            </td>
                            <td class="span2">评审组组长:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_LEADER" data-options="required:true"></input></td>
                        </tr>
                        <tr>
                            <td class="span2">评审组主要成员:
                            <td class="span10" colspan="3"><input class="easyui-textbox" type="text" name="C_MEMBERS" data-options="required:true"></input></td>
                        </tr>
                    </table>
            </form>
        </div>
    </div>
    
    <div data-options="region:'center'" >
        <table id="dg" class="easyui-datagrid" title="评审内容" data-options="tools:'#db_tb'"/>
        <div id="db_tb">
            <div class="db_tb">
                <input type="checkbox" onclick="doShow(this.checked)"></input>
                <a href="javascript:void(0)" onclick="javascript:doShow()">只显示未评审项</a>
            </div>
        </div>
    </div>
</div>

</@layout.doLayout>