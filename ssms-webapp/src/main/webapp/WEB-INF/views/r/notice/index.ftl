<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new ViewModel());
</script>
</#assign>
<@layout.doLayout script=script>
<table data-bind="datagridValue:selectItem,easyuiOptions: gridSettings" ></table>
</@>
