<style type="text/css">
    .content{
        width:998px;
    }
    table,.up,.headTitle{
        width :800px;
        margin:0px auto;
        margin-top:10px;
    }
    .headTitle{
        border-bottom: solid #daeef5 1px;
        padding-left: 30px;
        text-align: center;
    }
    
    .headTitle span{
        font-size: 15px;
        font-weight: bold;
        
    }
    td{
        padding: 5px 5px;
        border: dotted 1px;
    }
</style>

<div id="kocontainer" class="content">
       <div class="headTitle"> <span>${title!}</span></div>
    <table>
        <#list files as list>
            <tr>
                <td>${list_index+1}</td>
                <td>${list.C_NAME!}</td>
           </tr>
       </#list>
    </table>
    <div class="up">
            <a href="javascript:void(0);" data-bind="disable: readonly,uploadOptions: {module: 'STDTMP_FILE_03', sid: '${SID!}'}">[选择文件]</a>
    </div>
</div>