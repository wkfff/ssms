<#import "/layout/_component.ftl" as component/>
<#assign menus = [
    {"title": "工作中心", "url": "/index"},
    <#-- TODO: 根据专业进行配置-->
    {"title": "模板管理", "url": "/sys/stdtmp/index.html?profession=01"},
    {"title": "设置", "url": "/sys/tenant_e/index.html"}
]/>
<@component.menu menus />
