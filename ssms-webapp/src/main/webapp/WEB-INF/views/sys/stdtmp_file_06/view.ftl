<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<style type="text/css">
    .kocontainer {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        overflow: auto;
    }

    .form table {
        margin: 5px auto;
        width: 800px;
    }

    .form table tr {
        height: 40px;
    }

    .form table td.label {
        padding-left: 10px;
        text-align: left;
        vertical-align: top;
        line-height: 35px;
        height: 35px;
    }
</style>
</#assign>
<@layout.doLayout script>
    <form class="form">
        <table>
            <tr>
                <td class="label">存在问题</td>
                <td colspan="5"><input type="text" class="readonly" value="${C_NAME!}"  readonly/></td>
            </tr>
            <tr>
                <td class="label">检查人</td>
                <td><input type="text" class="readonly" value="${C_EXAMINER!}"  readonly/></td>
                <td class="label">检查时间</td>
                <td><input type="text" class="readonly" value="${T_EXAMINE!}"  readonly/></td>
                <td class="label">隐患类型</td>
                <td>
                    <input type="text" class="readonly" <#if P_TYPE??&&P_TYPE=="01">value="预防" <#elseif  P_TYPE??&&P_TYPE=="02"> value="纠正" <#else> value=""</#if> readonly/>
                </td>
            </tr>
            <tr>
                <td class="label">隐患所在区域/部门</td>
                <td colspan="3">
                    <input type="text" class="readonly" value="${C_DEPT!}"  readonly/>
                </td>
                <td class="label">隐患等级</td>
                <td>
                    <input type="text" class="readonly" <#if P_TYPE??&&P_TYPE=="01">value="一般隐患" <#elseif  P_TYPE??&&P_TYPE=="02"> value="重大隐患" <#else> value=""</#if> readonly/>
                </td>
            </tr>

            <tr>
                <td class="label">整改措施（包括<br/>工程技术措施、<br/>管理措施、<br/>教育措施、<br/>防护措施、<br/>应急措施）</td>
                <td colspan="5">
                    <textarea   style="height: 300px;" readonly>${C_MEASURE!}</textarea>
                </td>
            </tr>
            <tr>
                <td class="label">整改负责人</td>
                <td><input type="text" class="readonly" value="${C_RESPONSIBLE!}"  readonly/></td>
                <td class="label">要求整改日期</td>
                <td><input type="text" class="readonly" value="${T_RECTIFICATION!}"  readonly/></td>
            </tr>
            <tr>
                <td class="label">治理方案</td>
                <td colspan="5">
                    <textarea   style="height: 300px;" readonly>${C_PLANT!}</textarea>
                </td>
            </tr>
            <tr>
                <td class="label">验收部门</td>
                <td><input type="text" class="readonly" value="${C_ACCEPTANCE_DEPT!}"  readonly/></td>
                <td class="label">验收人</td>
                <td><input type="text" class="readonly" value="${C_ACCEPTANCE!}"  readonly/></td>
                <td class="label">验收时间</td>
                <td><input type="text" class="readonly" value="${T_ACCEPTANCE!}"  readonly/></td>
            </tr>
        <#--<tr>
            <td class="label">隐患闭环情况</td>
            <td>
                 <input type="text" class="readonly" <#if B_FINISH??&&B_FINISH=="1">value="已闭环" <#elseif  B_FINISH??&&B_FINISH=="0"> value="未闭环" <#else> value=""</#if> readonly/>
            </td>
        </tr>-->
        </table>
    </form>
</@>

