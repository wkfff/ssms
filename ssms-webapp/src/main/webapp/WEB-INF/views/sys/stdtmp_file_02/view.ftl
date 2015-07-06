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
    <form class="form" method="post" style="padding: 10px 31px;">
        <table>
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>通知标题:</td>
                <td colspan="3"><input class="readonly" value="${C_NAME!}" style="width:1286px;"  readonly/></td>
            </tr>

            <tr>
                <td>通知编号:</td>
                <td colspan="3"><input class="readonly" value="${C_NUMBER!}" style="width:1286px;" readonly/></td>
            </tr>

            <tr>
                <td colspan="4">
                    <div style="width: 100%; min-height: 400px;border: 1px dashed  #ccc;" >${C_CONTENT!}</div>
                </td>
            </tr>

            <tr>
                <td>发布部门:</td>
                <td><input class="readonly" value="${C_DEPT_01!}"  readonly/></td>
                <td>发布日期:</td>
                <td><input class="readonly" value="${T_DATE_01!}"  readonly/></td>
            </tr>

            <tr>
                <td>主送部门:</td>
                <td><input class="readonly" value="${C_DEPT_02!}"  readonly/>
                </td>

                <td>抄送部门:</td>
                <td><input class="readonly" value="${ C_DEPT_03!}"  readonly/>
                </td>
            </tr>
        </table>
    </form>
    </@>
</@layout.extends>