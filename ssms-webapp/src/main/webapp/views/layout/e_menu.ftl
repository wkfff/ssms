<#import "_component.ftl" as component/>
<#assign menus = [
    {"title": "工作中心", "url": "/index"},
    {"title": "达标创建", "url": ""},
    {"title": "在线自评", "url": "/e/grade_m/index.html"},
    {"title": "外部评审", "url": ""},
    {"title": "知识库", "url": ""},
    {"title": "设置", "url": ""}
]/>
<@component.menu menus />