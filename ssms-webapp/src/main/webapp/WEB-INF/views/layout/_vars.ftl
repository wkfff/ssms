<#--组件初始化脚本全局变量-->
<#assign COMPONENT_INIT_SCRIPTS=""/>
<#assign SELECTED_MENU=""/>

<#--组件基础结构-->
<#macro component_base component_script>
<#assign COMPONENT_INIT_SCRIPTS>${COMPONENT_INIT_SCRIPTS}${component_script}</#assign>
<#nested>
</#macro>