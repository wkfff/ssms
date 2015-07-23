<@layout.extends name="../../_layouts/index.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/form.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <style type="text/css">
        .icon-notCreated {
            padding-left: 20px;
            background: url("/resource/images/warning.png") no-repeat 0 center;
        }

        .icon-new {
            padding-left: 20px;
            background: url("/resource/images/new.png") no-repeat 0 center;
        }

        .icon-update {
            padding-left: 20px;
            background: url("/resource/images/update.png") no-repeat 0 center;
        }

        .icon-del {
            padding-left: 20px;
            background: url("/resource/images/delete.png") no-repeat 0 center;
        }

        .fileCount {
            color: blue;
        }

        .leftWrap {
            position: relative;
            width: 250px;
            display: none;
        }

        .leftWrap .treeWrap {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }
    </style>
    </@>

    <@layout.put block="containers">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west', title:'达标体系', split:true" class="leftWrap">
            <div></div>
            <div class="treeWrap">
                <@buildTree tree true/>
            </div>
        </div>
        <div data-options="region:'center', border:false">
            <div id="content" class="easyui-panel" style="position: relative;" data-options="onLoad: onLoad" fit="true">
                <header>
                    <span id="content_title">&nbsp;</span>
                    <a href="/" style="position: absolute; right: 3px">返回首页</a>
                </header>
            </div>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    <link rel="stylesheet" href="/resource/js/kindeditor/themes/default/default.css"/>
        <#if DEV_MODE>
        <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
        <script type="text/javascript" src="/resource/js/kindeditor/plugins/autoheight/autoheight.js"></script>
        <script type="text/javascript" src="/resource/js/kindeditor/lang/zh_CN.js"></script>
        <script type="text/javascript" src="/resource/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/knockout.validation.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/knockout.validation.zh-CN.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/htmleditor.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/date.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>
        <#else>
        <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.min.js"></script>
        <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
        </#if>
    <script type="text/javascript">
        function doSelect(node) {
            if (node.url != null && node.url.length > 0) {

                var $content = $("#content");
                var options = $content.panel('options');
                if (options.href === node.url) return;

                var title = '<span>' + $(node.target).find('.title').text() + '</span>';
                var parent = $(this).tree('getParent', node.target);
                while (parent != null) {
                    title = '<span>' + $(parent.target).find('.title').text() + '</span>' + ' / ' + title;
                    parent = $(this).tree('getParent', parent.target);
                }
                $('#content_title').html(title);
                $content.panel("refresh", node.url);

                if ($(node.target).find(".icon-new").length > 0) {
                    $.messager.confirm("提醒", "达标体系模板新增加了【" + $(node.target).find('.title').text() + "】文件。请确认是否同步添加？");
                }
                else if ($(node.target).find(".icon-del").length > 0) {
                    $.messager.confirm("提醒", "达标体系模板删除了【" + $(node.target).find('.title').text() + "】文件。请确认是否同步删除？");
                }
                else if ($(node.target).find(".icon-update").length > 0) {
                    $.messager.confirm("提醒", "达标体系模板修改了【" + $(node.target).find('.title').text() + "】模板。请确认是否同步修改？");
                }
            }
        }
        var onPanelLoad;
        function isFunction(obj) {
            return typeof obj === 'function';
        }
        function refreshPanel() {
            $("#content").panel("refresh");
        }
        function panelLoad(url) {
            $("#content").panel("refresh", url);
        }
        function onLoad() {
            if (isFunction(onPanelLoad)) onPanelLoad();
        }
        function showDev() {
            //        $.messager.alert('提示', '功能正在开发中...');
        }
        var selected = <#if selected??>'F-${selected}'<#else>null</#if>;
        $(function () {
            $('.treeWrap').parent().show();
            setTimeout(function () {
                var $tree = $('#std_tree');
                if (selected != null) {
                    selectNode(selected);
                }
                else {
                    var children = $tree.tree('getChildren');
                    for (var i = 0; i < children.length; i++) {
                        if ($tree.tree('isLeaf', children[i].target)) {
                            $tree.tree('select', children[i].target);
                            return;
                        }
                    }
                }
            }, 500);
        });

        function selectNode(id) {
            var $tree = $('#std_tree');
            var node = $tree.tree('find', id);
            var parent = $tree.tree('getParent', node.target);
            while (parent != null) {
                $tree.tree('expand', parent.target);
                parent = $tree.tree('getParent', parent.target);
            }
            $tree.tree('select', node.target);
        }

        function doSearch() {
            $('#std_tree').tree('doFilter', $('#searchTree').textbox('getText'));
        }

        function doDblClick(node) {
            $('#std_tree').tree('expand', node.target);
        }
    </script>
    </@>
</@layout.extends>

<#macro buildTree list root=false>
    <#if (list?size>0)>
    <ul <#if root>class="easyui-tree" id="std_tree" data-options='onSelect: doSelect, onDblClick: doDblClick'</#if>>
        <#list list as map>
            <li data-options="id: '${map.id}', iconCls: 'icon-folder', state: 'closed'">
                <span>
                    <span class="title" title="${map.name}">${map.name}</span>
                    <span class="fileCount">(${map.fileCount})</span>
                </span>
                <@buildTree map.children/>
                <#if map.files?size gt 0>
                <ul>
                    <#list map.files as file>
                    <li data-options="id: '${file.id}', url: '${CONTEXT_PATH}/e/stdtmp_file_${file.templateFileCode}/${file.id}'">
                        <span><span class="title" title="${file.name}">${file.name}</span><span class="fileCount">(${file.count})</span></span>
                    </li>
                    </#list>
                </ul>
                </#if>
            </li>
        </#list>
    </ul>
    </#if>
</#macro>