<#import "/layout/_list.ftl" as layout>
<#assign script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.edatagrid.js"></script>
<script type="text/javascript">
    $(function () {
        $('#area').tree({
            url: 'tree.json'
        });
    });
</script>
</#assign>
<@layout.doLayout script=script>
<div id="navlyt" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center', split:true" title="地区列表" style="width: 50%">
        <ul id="area"></ul>
    </div>
</div>
</@>