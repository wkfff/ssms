<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doSelect(node) {
        if (node.id.startsWith('F')) {
            $('#iframe').attr('src', '/e/stdtmp_file?R_SID={0}'.format(node.id));
        }
    }
</script>
</#assign>
<@layout.doLayout script=script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'达标体系', split:true" style="width: 200px">
        <div class="easyui-accordion" fit="true">
            <#list tree as map>
                <div title="${map.text}" style="overflow:auto;padding:10px;width: 100%;">
                    <@buildTree map.children true/>
                </div>
            </#list>
        </div>
    </div>
    <div data-options="region:'center', border:false">
        <iframe id="iframe" frameborder="0" width="100%" height="99%" src="/e/stdtmp_file?R_SID=${firstRec.id}"></iframe>
    </div>
</@>
<#macro buildTree list root=false>
    <#if (list?size>0)>
        <ul <#if root>class="easyui-tree" data-options='onSelect: doSelect'</#if>>
            <#list list as map>
                <li data-options="id: '${map.id}'"><span>${map.text}</span><@buildTree map.children/></li>
            </#list>
        </ul>
    </#if>
</#macro>