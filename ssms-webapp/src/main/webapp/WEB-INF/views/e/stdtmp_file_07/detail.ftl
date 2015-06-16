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
                <td>姓名:</td>
                <td> 
                    <input style="width:90%" type="text" class="readonly" value="${C_NAME!}" readonly />
                </td>
                <td>性别:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" <#if P_SEX?? && P_SEX=="1">value="男" <#elseif P_SEX?? && P_SEX=="2">value="女" <#else> value="女" </#if> readonly />
                </td>
            </tr>

            <tr>
                <td>身份证号码:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_CARD!}" readonly />
                </td>
                <td>所在部门:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${C_DEPT!}" readonly />
                </td>
            </tr>

            <tr>
                <td>工种:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_WORKTYPE!}" readonly />
                </td>
                <td>发证机关:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${C_AUTH!}" readonly />
                </td>
            </tr>

            <tr>
                <td>证书编号:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_CERT!}" readonly/>
                </td>
                <td>取证时间:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${T_CERT_GET!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>复审时间:</td>
                <td>
                   <input style="width:90%" type="text" class="readonly" value="${T_CERT_REVIEW!}" readonly/>
                </td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
