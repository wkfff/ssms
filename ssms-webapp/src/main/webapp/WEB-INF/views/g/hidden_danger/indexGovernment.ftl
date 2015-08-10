<#import "../../layout/_list.ftl" as layout/>
    <style type="text/css">
        .readOptioner{
             background: url("${RES!}/images/cancel.png") no-repeat ;
             padding-left: 20px;
             padding-bottom: 10px;
         }
    </style>
<#assign script>
    <script type="text/javascript">
        $(function(){
                $('#dg').datagrid({   
                    url:'${BASE_PATH!}/listGovernment',
                    title: '隐患排查提醒',
                    idField: 'SID',
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    fitColumns: false,
                    fit: true,
                    columns:[[
                        {field: 'S_TENANT',title:'未完成创建的企业',width:200},
                        {field:'T_CREATE',title:'未完成创建时间',width:100},
                        {field: 'SID', title:'操作', width: 120,align:'center',
                            formatter:function(value,row){
                                    return "<a class='readOptioner' href='#' onclick='readHiddenDanger(\""+value+"\")'>未阅</a>";
                                }}
                    ]]
                });
            });
            
            function readHiddenDanger(sid){
                $.post('${BASE_PATH}/readHidden',{sid:sid},function(result){
                    if(result){
                        $('#dg').datagrid('reload');
                    }
                },'json');
            }
    </script>
</#assign>
<@layout.doLayout script>
        <table id="dg"></table>
</@>