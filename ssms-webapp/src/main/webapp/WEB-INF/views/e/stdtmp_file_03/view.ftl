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
            <textarea data-bind="htmleditValue: htmlContent, htmleditOptions:htmleditSettings" style="width: 100%; height: 500px"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="4" data-bind="visible: '${SID!}'">
            <a href="javascript:void(0);" data-bind="disable: 'true',uploadOptions: {module: 'STDTMP_FILE_03', sid: '${SID!}'}">[选择文件]</a>
            </td>
        </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
function ViewModel(catalogId){
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "SSM_STDTMP_FILE_03",
            field: 'C_CONTENT',
            sid: '${SID!}',
            readonly:'true'
        }
    };
        $.extend(this, settings, extModel);
}
var onPanelLoad = function () {
    ko.applyBindings(new ViewModel(${sid}), document.getElementById('kocontainer'));
};
</script>
