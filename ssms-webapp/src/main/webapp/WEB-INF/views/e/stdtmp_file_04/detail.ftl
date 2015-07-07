<style type="text/css">
    .form {
        margin: 0 auto;
        width: 80%;
    }

    .form table {
        width: 100%;
        margin: 5px auto;
        table-layout: fixed;
    }

    .form table tr {
        height: 40px;
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
        <table>
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>主题:</td>
                <td colspan="3">
                    <input type="text" style="width:100%"  class="readonly" value="${C_NAME!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>培训日期:</td>
                <td>
                    <input type="text" style="width:90%"  class="readonly" value="${T_TIME!}" readonly/>
                </td>

                <td>培训地址:</td>
                <td>
                    <input type="text" style="width:100%"  class="readonly" value="${C_ADDR!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>讲师:</td>
                <td>
                    <input type="text" style="width:90%"  class="readonly" value="${C_USER_01!}" readonly/>
                </td>

                <td>培训类型:</td>
                <td>
                    <input type="text" style="width:100%"  class="readonly" value="${S_TYPE!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>学时:</td>
                <td>
                    <input type="text" style="width:90%"  class="readonly" value="${N_TIME!}" readonly/>
                </td>

                <td>记录人:</td>
                <td>
                    <input type="text" style="width:100%"  class="readonly" value="${C_USER_02!}" readonly/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div style="width: 100%; min-height: 400px;border: 1px dashed  #ccc;" >${C_CONTENT!}</div>
                </td>
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
                <td colspan="4">
                    操作指南
                </td>
            </tr>
        </table>
    </form>
</div>