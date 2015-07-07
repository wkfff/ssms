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
    <form class="form" method="post" style="padding:10px 31px;">
        <table>
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>文件名称</td>
            <td colspan="3">
                <input type="text" style="width:100%" class="readonly" readonly value="${C_NAME!}"/>
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
       </table>
    </form>
</div>

