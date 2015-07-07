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
        <a class="easyui-linkbutton" plain="true" iconCls="icon-back" data-bind="click: function(){panelLoad('${BASE_PATH}/view?sid=${R_TMPFILE!pid}')}">返回列表</a>
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
                <td>通知标题:</td>
                <td colspan="3">
                    <input style="width:100%" type="text" class="readonly" value="${C_NAME!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>通知编号:</td>
                <td colspan="3">
                    <input style="width:100%" type="text" class="readonly" value="${C_NUMBER!}" readonly/>
                </td>
            </tr>

            <tr>
                <td colspan="4">
                    <div style="width: 100%; min-height: 400px;border: 1px dashed  #ccc;" >${C_CONTENT!}</div>
                </td>
            </tr>

            <tr>
                <td>发布部门:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_DEPT_01!}" readonly/>
                </td>
                <td>发布日期:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${T_DATE_01!}" readonly/>
                </td>
            </tr>

            <tr>
                <td>主送部门:</td>
                <td>
                    <input style="width:90%" type="text" class="readonly" value="${C_DEPT_02!}" readonly/>
                </td>

                <td>抄送部门:</td>
                <td>
                    <input style="width:100%" type="text" class="readonly" value="${C_DEPT_03!}" readonly/>
                </td>
            </tr>
            <tr>
                <td colspan="4" data-bind="visible: '${SID!}'">
                    <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'STDTMP_FILE_02', sid: '${SID!}'}">[选择文件]</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "SSM_STDTMP_FILE_02",
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