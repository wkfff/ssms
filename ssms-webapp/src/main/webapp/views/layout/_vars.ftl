<#--组件初始化脚本全局变量-->
<#assign COMPONENT_INIT_SCRIPTS=""/>
<#--组件基础结构-->
<#macro component_base component_script>
<#if (component_script?length>0)>
<#assign COMPONENT_INIT_SCRIPTS>${COMPONENT_INIT_SCRIPTS}${component_script}</#assign>
</#if>
<#nested>
</#macro>