<#macro doLayout header="" footer="">
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
    <#if (header?length>0)>${header}</#if>
</head>
<body>
    <#nested/>
    <#if (footer?length>0)>${footer}</#if>
</body>
</html>
</#macro>