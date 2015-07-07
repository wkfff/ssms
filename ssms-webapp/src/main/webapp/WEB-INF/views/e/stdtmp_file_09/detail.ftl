<style type="text/css">
    .form table {
        width: 100%;
        margin: 5px auto;
        table-layout: fixed;
    }

    .form table tr {
        height: 40px;
    }
    
    .form table td {
        padding-right:10px;
    }
    

    .form table .label {
        width: 90px;
    }
</style>
<div id="kocontainer">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-back" onclick="panelLoad('${BASE_PATH}/view?sid=${R_TMPFILE!pid}');">返回列表</a>
    </div>

    <form class="form" method="post" style="padding:10px 31px;">
        <table cellspadding="10" cellspading="10" border="1">
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>

            <tr>
                <td>安全附件名称:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_NAME!}" readonly/>
                </td>
                 <td>型号:</td>
                <td>
                    <input style="width:100%" class="readonly" value="${C_SPEC!}" type="text" readonly/>
                </td>
            </tr>

            <tr>
                <td>出厂编号:</td>
                <td>
                    <input style="width:90%" class="readonly" value="${C_NO_FACTORY!}" type="text" readonly/>
                </td>
                <td>所属特种设备安装位置:</td>
                <td>
                    <input style="width:100%" class="readonly" value="${C_POSITION!}" type="text" readonly/>
                </td>
            </tr>
           
            <tr>
                <td>检验日期:</td>
                <td>
                    <input style="width:90%" class="readonly" value="${T_TEST_LAST!}" type="text" readonly/>
                </td>
                <td>下次检验日期:</td>
                <td>
                    <input style="width:100%" class="readonly" value="${T_TEST_NEXT!}" type="text" readonly/>
                </td>
            </tr>
            
            <tr>
                <td>检验报告编号:</td>
                <td>
                    <input style="width:90%" class="readonly" value="${C_NO_REP!}" type="text" readonly/>
                </td>
                <td>检验单位:</td>
                <td>
                    <input style="width:100%" class="readonly" value="${C_TEST_UNIT!}" type="text" readonly/>
                </td>
            </tr>
             <tr>
                <td>检验结论:</td>
                <td colspan=3><input style="width:100%" class="readonly" value="${C_TEST_CON!}" type="text" readonly/></td>
            </tr>
            <#if file.explain?? && file.explain?length!=0>
            <tr>
                <td colspan="4">
                    <div style="border: 1px dashed  #ccc; margin-bottom: 5px;position: relative;">
                    <div style="background-color: #CCCCCC;">政策解读:</div>
                    <table style="table-layout: auto;"></table>
                    ${file.explain}
                   </div>
               </td>
           </tr>
           </#if>
            <tr>
        </table>
    </form>
</div>
