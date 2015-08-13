<#import "../../layout/_list.ftl" as layout>
<#assign script>
<script type="text/javascript">
    $(function(){
        $('#dg').datagrid({
            url : '${BASE_PATH}/list?state=1&eid=${eid}&pro=${pro}',
            title : '已发布列表',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: false,
            fit: true,
            toolbar : '#toolbar',
            columns:[[
                      {field: 'C_TITLE',title:'通知公告主题',width:300},
                      {field: 'T_PUBLISH',title:'发布时间',width:200},
                      {field: 'SID', title:'操作', width: 150,align:'center',
                          formatter:function(value,row){
                                  return "<a  href='#' onclick='lookPublish(\""+value+"\")'>查看</a>";
                              }}
                  ]]
        });
    });
    function lookPublish(sid){
        var reader = '${reader!}';
        if(reader!=null){
            window.location.href="${BASE_PATH}/publishForm?eid=${eid}&pro=${pro}&sid="+sid+'&reader='+reader;
        }else{
            window.location.href="${BASE_PATH}/publishForm?eid=${eid}&pro=${pro}&sid="+sid;
        }
    };
    
</script>
</#assign>
<@layout.doLayout script=script>
<#if reader??>
<#else>
<div id="toolbar" class="z-toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-back" plain="true" onclick="window.location.href = '${BASE_PATH}/index?eid=${eid}&pro=${pro}'">返回草稿箱</a>
</div>
</#if>
<table id="dg" ></table>
</@>
