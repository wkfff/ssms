<div id="kocontainer">
    <form class="form" method="post" style="padding:10px 31px;">
        <div class="easyui-panel" title="概要" style="padding-bottom: 10px;">
            <p class="long-input ue-clear">
                <label>文件名称</label>
                 <span class="control">
                 <input type="text" style="width:860px" class="readonly" value="${C_NAME}" readonly/>
                </span>
            </p>
        </div>

        <div class="easyui-panel" title="附件" style="padding-bottom: 10px;" data-bind="visible: '${SID!}'">
            <a href="javascript:void(0);" data-bind="disable: 'true',uploadOptions: {module: 'STDTMP_FILE_05', sid: '${SID}'}">[选择文件]</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    var extModel = {
        htmlContent: ko.observable()
    };
    var settings = {
        htmleditSettings: {
            table: "SSM_STDTMP_FILE_05",
            field: 'C_CONTENT',
            sid: '${SID!}',
            readonly: 'true'
        }
    };
    var onPanelLoad = function () {
        ko.applyBindings($.extend({},settings, extModel), 
        		document.getElementById("kocontainer"));
    };
</script>