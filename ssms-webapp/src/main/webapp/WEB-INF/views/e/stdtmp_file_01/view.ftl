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
                <td>文件名称:</td>
                <td colspan="3">
                     <input style="width:100%" type="text"  class="readonly" value="${C_NAME!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>文件编号:</td>
                <td>
                    <input style="width:90%" type="text"  class="readonly" value="${C_NUMBER!}" readonly/>
                </td>

                <td>是否受控:</td>
                <td>
                    <input style="width:100%" type="text"  class="readonly" <#if B_CONTROL?? && B_CONTROL=="1">value="受控" <#elseif B_CONTROL?? && B_CONTROL=="0"> value="非受控" <#else> value=""</#if> readonly/>
                </td>
            </tr>

            <tr>
                <td>执行部门:</td>
                <td>
                    <input style="width:90%" type="text"  class="readonly" value="${C_DEPT_01!}" readonly/>
                </td>
                <td>监督部门:</td>
                <td>
                    <input style="width:100%" type="text"  class="readonly" value="${C_DEPT_02!}" readonly/>
                </td>
            </tr>

            <tr>
                <td colspan="4">
                    <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings"
                              style="width: 100%; min-height: 400px"></textarea>
                </td>
            </tr>

            <tr>
                <td>编制日期:</td>
                <td>
                    <input style="width:90%" type="text"  class="readonly" value="${T_DATE_01!}" readonly/>
                </td>
                <td>审核日期:</td>
                <td>
                    <input style="width:100%" type="text"  class="readonly" value="${T_DATE_02!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>批准日期:</td>
                <td>
                    <input style="width:90%" type="text"  class="readonly" value="${T_DATE_03!}" readonly/>
                </td>
                <td>生效日期:</td>
                <td>
                    <input style="width:100%" type="text"  class="readonly" value="${T_DATE_04!}" readonly/>
                </td>
            </tr>
            <tr data-bind="visible: '${SID!}'">
                <td colspan="4">
                    <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'STDTMP_FILE_01', sid: '${SID!}'}">[选择文件]</a>
                </td>
            </tr>

        <#if pass?? && pass?size gt 0 >
            <tr>
                <td colspan="4">
                    <table id="item">
                        <tr>
                        <td>序号</td>
                        <td>年审时间</td>
                        <td>操作人</td>
                        </tr>
                        <#list pass as list>
                            <tr>
                            <td>${list_index+1}</td>
                            <td>${list.T_DATE_01!}</td>
                            <td>${list.S_CREATE!}</td>
                            </tr>
                        </#list>
                    </table>
                </td>
            </tr>
        </#if>
        </table>
    </form>
</div>
<script type="text/javascript">
    var extModel = {
        htmlContent: ko.observable(),
    };
    var settings = {
        htmleditSettings: {
            table: "SSM_STDTMP_FILE_01",
            field: 'C_CONTENT',
            sid: '${SID!}',
            readonly: 'true'
        }
    };
    var onPanelLoad = function () {
        var vm = $.extend({}, settings, extModel);
        ko.applyBindings(vm, document.getElementById('kocontainer'));
    }
</script>