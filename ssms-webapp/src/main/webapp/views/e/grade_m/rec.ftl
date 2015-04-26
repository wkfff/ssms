<#import "/layout/_rec.ftl" as layout/>
<#assign script>

<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.edatagrid.js"></script>

<script type="text/javascript">
    String.prototype.replaceAll = function(s1,s2) { 
        return this.replace(new RegExp(s1,"gm"),s2); 
    }

    function doNew(){
        window.location.href='rec.html?refer=add';
    }
    
    function doSave(){
        //$.messager.progress();
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
                window.location.href='rec.html?refer=draft&sid='+data.replaceAll('"','');
            }
        });
    }
    
    function doDel(){
        $.messager.confirm("删除确认", "您确认删除当前的自评吗？", function (deleteAction) {
                    if (deleteAction) {
                        $.get("del.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "删除自评成功");
                                window.location.href='draft.html';
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
        });
    }
    
    function doComplete(){
        $.messager.confirm("完成确认", "您确认完成当前的自评吗？", function (action) {
                    if (action) {
                        $.get("complete.do", {sid:'${sid!}'}, function (data) {
                            if (data == "true" || data== "\"\"") {
                                $.messager.alert("提示", "自评完成");
                            }
                            else {
                                $.messager.alert("提示", data);
                            }
                        });
                    }
        });
    }
    
    function doBack(){
        window.location.href='${refer!}.html';
    }
    
    $.extend($.fn.datagrid.methods, {
            editCell: function(jq,param){
                return jq.each(function(){
                    var opts = $(this).datagrid('options');
                    var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                    for(var i=0; i<fields.length; i++){
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor1 = col.editor;
                        if (fields[i] != param.field){
                            col.editor = null;
                        }
                    }
                    $(this).datagrid('beginEdit', param.index);
                    for(var i=0; i<fields.length; i++){
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor = col.editor1;
                    }
                });
            }
        });
        
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#dg').datagrid('validateRow', editIndex)){
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCell(index, field){
            if (endEditing()){
                $('#dg').datagrid('selectRow', index)
                        .datagrid('editCell', {index:index,field:field});
                editIndex = index;
            }
        }
        function onClickRow(index){
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
        function accept(){
            if (endEditing()){
                $('#dg').datagrid('acceptChanges');
            }
        }
        function getChanges(){
            var rows = $('#dg').datagrid('getChanges');
            alert(rows.length+' rows are changed!');
        }
        
    $(function () {

        $('#formMain').form('load','rec.json?sid=${sid!}');
        
        $('#dg').edatagrid({
            title:'自评内容',border:0,
            iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            updateUrl:'/e/grade_d/save.do',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            fit:true,
            border:false,
            autoSave:false,
            striped: true,
            columns: [[
                {field: 'C_CATEGORY', title: '类目', width: 100},
                {field: 'C_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 250},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 80},
                {field: 'C_METHOD', title: '考评办法', width: 250},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',rows:'5'}},
                {field: 'B_BLANK', title: '是否缺项', width: 80,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='0'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 80,editor:'numberbox'}
            ]]
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    
    <div title="在线自评" data-options="region:'north',iconCls:'icon-star'" style="height:170px;overflow:hidden;border:0;">
          <div class="easyui-panel" style="border:0;background-color:#FAFAFA;padding:5px;">
            <#if (refer=='add')>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
            </#if>
            
            <#if (refer=='history')>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
            </#if>
            
            <#if (refer=='draft')>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-ok" onclick="doComplete()">完成自评</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-cancel" onclick="doDel()">删除</a> 
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
            </#if>
         </div>
         
         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post">
                    <table style="width:900px;" cellspacing="6">
                        <tr>
                            <td class="span2">自评单位:</td>
                            <td class="span10" colspan="3">
                                <input class="easyui-textbox" type="text" name="S_TANENT" data-options="required:true,width:602" 
                                disabled=true value="${S_TANENT!}"/>
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">自评日期:</td>
                            <td class="span4"><input class="easyui-datebox" type="text" name="T_START" data-options="required:true,width:100"></input>
                                                        至<input class="easyui-datebox" type="text" name="T_END" data-options="required:true,width:100"></input>
                            </td>
                            <td class="span2">自评组组长:</td>
                            <td class="span4"><input class="easyui-textbox" type="text" name="C_LEADER" data-options="required:true"></input></td>
                        </tr>
                        <tr>
                            <td class="span2">自评组主要成员:
                            <td class="span10" colspan="3"><input class="easyui-textbox" type="text" name="C_MEMBERS" data-options="required:true,width:602"></input></td>
                        </tr>
                    </table>
            </form>
        </div>
    </div>
    
    <#if (refer!='add')>
    <div data-options="region:'center'" style="border:0;">
        <table id="dg" class="easyui-datagrid" title="自评内容" />
    </div>
    </#if>
</div>

</@layout.doLayout>