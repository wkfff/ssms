<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<style>
    .datagrid-cell-c2-C_CONTENT,.datagrid-cell-c2-C_METHOD,.datagrid-cell-c2-C_DESC{
        white-space:normal !important;
    }
    .datagrid-editable-input{
        height:120px !important;
    }
    .datagrid-row{
        border-bottom:1px solid #000 !important;
    }
</style>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.edatagrid.js"></script>

<script type="text/javascript">
    function doNew(){
        window.location.href='rec.html';
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
                window.location.href='rec_draft.html?sid='+$str.replaceAll(data,'"','');
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
        window.location.href='${referer!}';
    }
    
    $(function () {
        $('#formMain').form('load','rec.json?sid=${sid!}');
        <#if (_FLAG_=='-1' || _FLAG_=='-2')>$form.disableForm('formMain',true);</#if>
        
        $('#dg').<#if (_FLAG_!='-1')>e</#if>datagrid({
            title:'自评内容',border:0,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            updateUrl:'/e/grade_d/save.do',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:true,
            //border:false,
            autoSave:true,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 60},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 60,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 60,editor:'numberbox'}
            ]]
            <#if (_FLAG_=='-1')>,
            onLoadSuccess: function(data){
                $(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
            }
            </#if>
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div title="在线自评" data-options="region:'north',collapsible:false" style="height:230px;overflow:hidden;">
          <#if (_FLAG_!='-2')>
          <div class="toolbar ue-clear">
            <#if (_FLAG_=='0')>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
            </#if>
            
            <#if (_FLAG_=='-1')>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
            </#if>
            
            <#if (_FLAG_=='1')>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-save" onclick="doSave()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-ok" onclick="doComplete()">完成自评</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-cancel" onclick="doDel()">删除</a> 
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack()">返回</a>
            </#if>
         </div>
         </#if>
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
                                                        至<input class="easyui-datebox" type="text" name="T_END" data-options="required:true,width:100" ></input>
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
    
    <#if (_FLAG_!='0')>
    <div data-options="region:'center'" >
        <table id="dg" class="easyui-datagrid" title="自评内容" />
    </div>
    </#if>
</div>

</@layout.doLayout>