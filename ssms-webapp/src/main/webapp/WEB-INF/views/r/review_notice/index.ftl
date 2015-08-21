<#import "../../layout/_list.ftl" as layout>
<#assign script>
<script type="text/javascript">
    $(function(){
        $('#dg').datagrid({
            url : '${BASE_PATH}/list?state=0&eid=${eid}&pro=${pro}',
            title : '草稿箱',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit: true,
            toolbar : '#toolbar',
            columns:[[
                      {field: 'C_TITLE',title:'通知公告主题',width:300},
                      {field: 'T_CREATE',title:'创建时间',width:200},
                      {field: 'SID', title:'操作', width: 150,align:'center',
                          formatter:function(value,row){
                                  return "<a  href='#' onclick='edit(\""+value+"\")'>编辑</a> <a  href='#' onclick='del(\""+value+"\")'>删除</a>";
                              }}
                  ]]
        });
    });
    
    function edit(sid){
        window.location.href="${BASE_PATH}/draftForm?eid=${eid}&pro=${pro}&sid="+sid;
    };
    
    function del(sid){
        utils.messager.confirm("确定要删除记录吗？",function(deleteAction){
            if(deleteAction){
                $.post('${BASE_PATH}/del',{sid:sid},function(result){
                    utils.messager.closeProgress();
                    if(result){
                        utils.messager.alert("删除成功");
                        window.location.href="${BASE_PATH}/index?eid=${eid}&pro=${pro}";
                    }else{
                        utils.messager.alert("删除失败");
                    }
                },'json');
            }
        });
    };
    function newDrafts(){
        window.location.href="${BASE_PATH}/draftForm?eid=${eid}&pro=${pro}"
    };
    function listPublish(){
        window.location.href="${BASE_PATH}/publishs?eid=${eid}&pro=${pro}"
    }
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar" class="z-toolbar">
    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="newDrafts()">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="listPublish()">查看已发布列表</a>
</div>
<table id="dg" ></table>
</@>
