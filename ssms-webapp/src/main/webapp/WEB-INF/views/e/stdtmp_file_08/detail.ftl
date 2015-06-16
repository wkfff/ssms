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
        padding-right: 10px;
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
                <td>设备名称:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_NAME!}" readonly/>
                </td>
                <td>登记证号:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${C_NO_REG!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>规格/型号:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_SPEC!}" readonly/>
                </td>
                <td>制造单位:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${C_MAKE_UNIT!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>出厂编号:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_NO_FACTORY!}" readonly/>
                </td>
                <td>使用部门:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${C_DEPT!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>检验报告编号:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_NO_REP!}" readonly/>
                </td>
                <td>检验单位:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${C_TEST_UNIT!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>最新检验日期:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${T_TEST_LAST!}" readonly/>
                </td>
                <td>下次检验日期:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${T_TEST_NEXT!}" readonly/>
                </td>
            </tr>
            <tr>
                <td>检验结论:</td>
                <td colspan=3><input style="width:100%" type="text" class="readonly" value="${C_TEST_CON!}" readonly/></td>
            </tr>
        </table>
    </form>
</div>
