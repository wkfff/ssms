<#import "_vars.ftl" as vars/>
<#macro doLayout header="" footer="">
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
<#--easyui-->
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <#if (header?length>0)>${header}</#if>
</head>
<body>
    <#nested/>
    <#if (footer?length>0)>${footer}</#if>${vars.COMPONENT_INIT_SCRIPTS}<#--输出组件初始化脚本-->
</body>
</html>
</#macro>

<#macro indexLayout header="" footer="">
    <#local script>
    <script type="text/javascript">
        $(function () {
            function doBreadClick(){
                if (this.href.length <= 0) return false;
                $('#mainFrame').attr('src', this.href);
                return false;
            }

            var nav = $('#nav').tree({
                url: '/sys/nav/navTree.json',
                onSelect: function (node) {
                    if (node.attributes.C_URL) {
                        $('#mainFrame').attr('src', node.attributes.C_URL);

                        // 处理导航
                        var breadItems = [node];
                        var parent = node;
                        while (parent = nav.tree("getParent", parent.target)) {
                            breadItems.push(parent);
                        }
                        var $breadContent = $('#bread_content');
                        $breadContent.html('>');
                        for (var i = breadItems.length - 1; i >= 0; i--) {
                            var item = breadItems[i];
                            var url = item.attributes.C_URL;
                            $('<a></a>').attr('href', url).text(item.text).click(doBreadClick).appendTo($breadContent);
                            $breadContent.append('>');
                        }
                    }
                }
            });
        })
    </script>
        <#if (footer?length>0)>${footer}</#if>
    </#local>
    <@doLayout _header script>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north'">
            <#include 'parts/header.ftl'/>
        </div>
        <div data-options="region:'west', title:'功能导航', split:true" style="width: 200px">
            <ul id="nav"></ul>
        </div>
        <div data-options="region:'center', header: '#bread'" style="overflow: hidden">
            <iframe id="mainFrame" name="mainFrame" frameborder="0" width="100%" height="100%"></iframe>
        </div>
        <div id="bread">
            <a href="#">首页</a><span id="bread_content"></span>
        </div>
        <div data-options="region:'south'" style="height: 50px">
            <#include 'parts/copyright.ftl'/>
        </div>
    </div>
    </@doLayout>
</#macro>