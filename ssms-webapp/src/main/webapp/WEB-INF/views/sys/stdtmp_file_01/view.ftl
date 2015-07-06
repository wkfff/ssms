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
        <table>
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>文件名称:</td>
                <td colspan="3">
                    <input type="text" class="readonly" value="${C_NAME!}" style="width:1286px;" readonly/>
                </td>
            </tr>

            <tr>
                <td>文件编号:</td>
                <td>
                    <input type="text" class="readonly" value="${C_NUMBER!}"  readonly/>
                </td>

                <td>是否受控:</td>
                <td>
                    <input type="text" class="readonly" <#if B_CONTROL??&&B_CONTROL==1>value="受控"<#elseif B_CONTROL??&&B_CONTROL==0>value="非受控"<#else>value=""</#if> readonly/>
                </td>
            </tr>

            <tr>
                <td>执行部门:</td>
                <td>
                    <input type="text" class="readonly" value="${C_DEPT_01!}"  readonly/>
                </td>
                <td>监督部门:</td>
                <td>
                    <input type="text" class="readonly" value="${C_DEPT_02!}"  readonly/>
                </td>
            </tr>

            <tr>
                <td colspan="4">
                <div style="width: 100%; min-height: 400px;border: 1px dashed  #ccc;">${C_CONTENT!}</div>
                </td>
            </tr>

            <tr>
                <td>编制日期:</td>
                <td>
                    <input type="text" class="readonly" value="${T_DATE_01!}"  readonly/>
                </td>
                <td>审核日期:</td>
                <td>
                    <input type="text" class="readonly" value="${T_DATE_02!}"  readonly/>
                </td>
            </tr>

            <tr>
                <td>批准日期:</td>
                <td>
                    <input type="text" class="readonly" value="${T_DATE_03!}"  readonly/>
                </td>
                <td>生效日期:</td>
                <td>
                    <input type="text" class="readonly" value="${T_DATE_04!}"  readonly/>
                </td>
            </tr>
        </table>
    </form>
    </@>
</@layout.extends>