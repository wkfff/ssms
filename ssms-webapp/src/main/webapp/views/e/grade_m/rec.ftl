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
    function doNew(){
        window.location.href='rec.html';
    }
    
    function doSave(){
        //$.messager.progress();
        endEditing();
        $('#formMain').form('submit', {
            url:'save.do?sid=${sid!}',
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (!isValid){
                    //$.messager.progress('close');
                }
                return isValid;
            },
            success: function(data){
                $.messager.alert('保存','保存成功！');
                window.location.href='draft_rec.html?sid='+$str.replaceAll(data,'"','');
            }
        });
    }
    
    function doDel(){
        $.messager.confirm("删除确认", "您确认删除当前的自评吗？", function (deleteAction) {
                    if (deleteAction) {
                        $.get("del.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "删除自评成功");
                                window.location.href='index.html';
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
        });
    }
    
    function doComplete(){
        $.get("check.json", {sid:'${sid!}'}, function (data) {
            if (data && data>0) {
                $.messager.alert("提示", "自评还有未填写的项，请填写后再完成自评！");
            }
            else {
                $.messager.confirm("完成确认", "您确认完成当前的自评吗？", function (action) {
                    if (action) {
                        $.get("complete.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "自评完成");
                                window.location.href='/e/grade_rep/rec.html?sid=${sid!}';
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
        window.location.href='index.html';
    }
    
    function doShow(v){
        var chk = $('.db_tb input')[0].checked;
        if (typeof(v)=='undefined'){
            chk = !chk;
            $('.db_tb input')[0].checked = chk;
        }
        $('#dg').datagrid({url:'/e/grade_d/list.json?R_SID=${sid!-1}'+(chk?'&score=0':'')});
    }
    function doReport(){
        window.location.href='report_rec.html?sid=${sid!}';
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
                    $.post(url, {"SID":row.SID,"R_SID":row.R_SID,"C_DESC":row.C_DESC,"B_BLANK":row.B_BLANK,"N_SCORE_REAL":row.N_SCORE_REAL}, function(data){});
            }
            return true;
        } else {
            return false;
        }
    }
    function onClickRow(index){
        var v = $('#dg').datagrid('getRows')[index]['C_PROJECT'];
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
            title:'自评内容',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            updateUrl:'/e/grade_d/save.do',
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
                {field: 'C_CATEGORY', title: '类目', width: 100},
                {field: 'C_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 65},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 65,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            var s = row['C_PROJECT'];
                            if (s=='小计' || s == '总计') return '';
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 65,editor:'numberbox'}
            ]],
            onLoadSuccess: function(data){
                //$(this).datagrid("autoMergeCells",["C_CATEGORY","C_PROJECT"]);
            },
            onClickRow:onClickRow,
            rowStyler: function(index,row){
                if (row.C_PROJECT == '小计' || row.C_PROJECT == '总计'){
                    return 'background-color:#FAFAFA;color:#000;font-weight:bold;';
                }
            }
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div title="在线自评" data-options="region:'north',collapsible:true" style="height:230px;overflow:hidden;">
          <div class="toolbar ue-clear">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-ok" onclick="doComplete()">完成自评</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-details" onclick="doReport()">自评报告</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-cancel" onclick="doDel()">删除</a> 
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post">
                    <table class="table">
                        <tr>
                            <td class="span2">自评单位:</td>
                            <td class="span10" colspan="3">
                                <input class="easyui-textbox" type="text" name="S_TENANT" data-options="required:true" 
                                disabled=true value="${S_TENANT!}"/>
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">自评日期:</td>
                            <td class="span4"><input class="easyui-datebox" type="text" name="T_START" data-options="required:true,width:100"></input>
                                                        至<input class="easyui-datebox" type="text" name="T_END" data-options="required:true,width:100" ></input>
                            </td>
                            <td class="span2">自评组组长:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_LEADER" data-options="required:true"></input></td>
                        </tr>
                        <tr>
                            <td class="span2">自评组主要成员:
                            <td class="span10" colspan="3"><input class="easyui-textbox" type="text" name="C_MEMBERS" data-options="required:true"></input></td>
                        </tr>
                    </table>
            </form>
        </div>
    </div>
    
    <div data-options="region:'center'" >
        <table id="dg" class="easyui-datagrid" title="自评内容" data-options="tools:'#db_tb'"/>
        <div id="db_tb">
            <div class="db_tb">
                <input type="checkbox" onclick="doShow(this.checked)"></input>
                <a href="javascript:void(0)" onclick="javascript:doShow()">只显示未完成项</a>
            </div>
        </div>
    </div>
</div>

</@layout.doLayout>