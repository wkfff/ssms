<@layout.extends name="stdtmp.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    </@>

    <@layout.put block="panel_title">
    ${file.name}
    </@>
    <@layout.put block="panel_tools" type="REPLACE">
    <a href="/sys/stdtmp/manager?template=${file.templateId}">返回列表</a>
    </@>    
</@>