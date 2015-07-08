<@layout.extends name="../../../_layouts/todo.ftl">

    <@layout.put block="contents">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north'" style="overflow:hidden;">
            <div class="titlebar_noborder">
                <img src="/resource/images/blue/star.png"/>&nbsp;${title!'查看'}
                <span class="backing"><a href="/index"}">返回</a></span>
            </div>
        </div>
        <div data-options="region:'center', border:false">
            <div id="content" class="easyui-panel" style="position: relative;" data-options="onLoad: onLoad" fit="true" title="" href=""></div>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript">
        var onPanelLoad;
        function isFunction(obj) {
            return typeof obj === 'function';
        }
        function refreshPanel() {
            $("#content").panel("refresh");
        }
        function panelLoad(url) {
            $("#content").panel("refresh", url);
        }
        function onLoad() {
            if (isFunction(onPanelLoad)) onPanelLoad();
        }
        $(document).ready(function(){
            var url = '/e/stdtmp_file_08/rec_todo?sid=${sid}';
            $("#content").panel("setTitle", "").panel("refresh",url);
        });
    </script>
    </@>
</@>