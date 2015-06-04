<#import "../../layout/_layout.ftl" as layout/>
<#assign script>

<script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
<#if DEV_MODE>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="/resource/js/kindeditor/plugins/autoheight/autoheight.js"></script>
<script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
<script type="text/javascript" src="/resource/js/knockout/component.js"></script>
<script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
<script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>
<#else>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor.min.js"></script>
<script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
</#if>
<script type="text/javascript">
    function doSelect(node) {
        if (node.url != null && node.url.length > 0)
            $("#content").panel("setTitle", node.text).panel("refresh", node.url);
    }
    var onPanelLoad;
    function isFunction(obj) {
        return typeof obj === 'function';
    }
    function refreshPanel() {
        $("#content").panel("refresh");
    }
    function onLoad() {
        if (isFunction(onPanelLoad)) onPanelLoad();
    }
</script>
</#assign>
<@layout.indexLayout footer=script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'达标体系', split:true" style="width: 200px">
        <div class="easyui-accordion" fit="true" border="false">
            <#list tree as map>
                <div title="${map.text}" style="overflow:auto;">
                    <@buildTree map.children true/>
                </div>
            </#list>
        </div>
    </div>
    <div data-options="region:'center', border:false">
        <div id="content" class="easyui-panel" data-options="onLoad: onLoad" fit="true" title="${firstRec.text}" href="${firstRec.attributes.C_URL!}"></div>
    </div>
</div>
</@>
<#macro buildTree list root=false>
    <#if (list?size>0)>
    <ul <#if root>class="easyui-tree" data-options='onSelect: doSelect'</#if>>
        <#list list as map>
            <li data-options="id: '${map.id}', url: '${map.attributes.C_URL!}', <#if map.attributes.C_URL?exists==false>iconCls: 'icon-folder'</#if>">
                <span>${map.text}</span><@buildTree map.children/></li>
        </#list>
    </ul>
    </#if>
</#macro>