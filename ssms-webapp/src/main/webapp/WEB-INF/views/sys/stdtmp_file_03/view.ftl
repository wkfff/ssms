<@layout.extends name="../../_layouts/stdtmpfile.ftl">
    <@layout.put block="head">
    <style type="text/css">
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
    </@>
    <@layout.put block="panel_content">
    <form class="form" method="post" style="padding:10px 31px;">
      <table >
        <colgroup>
            <col class="label"/>
            <col/>
            <col class="label"/>
            <col/>
        </colgroup>
        <tr >
            <td>文件名称:</td>
            <td colspan="3">
                <input type="text" class="readonly" value="${C_NAME!}" style="width:1286px" readonly/>
            </td>
        </tr>
     </table>
     <table >
        <tr >
            <td colspan="4">
                <div style="width: 100%; min-height: 400px;border: 1px dashed  #ccc;" >${C_CONTENT!}</div>
            </td>
        </tr>
     </table>
    
     <div>
          操作指南：
     </div>
    </form>
    </@>
</@layout.extends>
