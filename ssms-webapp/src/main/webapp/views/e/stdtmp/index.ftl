<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel(${R_SID}));
</script>
</#assign>
<@layout.doLayout script=script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'达标体系', split:true" style="width: 200px">
        <ul data-bind="treeValue:selectedNode,easyuiOptions:treeSettings"></ul>
    </div>
    <div data-options="region:'center', border:false">
        <iframe id="iframe" frameborder="0" width="100%" height="99%" data-bind="attr: {src: fileUrl}"></iframe>
    </div>
</@>