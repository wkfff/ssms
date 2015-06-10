<#import "../../layout/_layout.ftl" as layout/>
<#assign script>

<script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    <#if DEV_MODE>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/plugins/autoheight/autoheight.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
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
            var title = '<span>' + $(node.target).find('.title').text() + '</span>';
            var parent = $(this).tree('getParent', node.target);
            while (parent != null) {
                title = '<span>' + $(parent.target).find('.title').text() + '</span>' + ' / ' + title;
                parent = $(this).tree('getParent', parent.target);
            }
            $('#content_title').html($('#accordion').accordion('getSelected').panel('header').find('.title').html() + ' / ' + title);
            $("#content").panel("refresh", node.url);

            if ($(node.target).find(".icon-new").length > 0) {
                $.messager.show({
                    title:'提醒',
                    msg:"达标体系模板新增加了【"+$(node.target).find('.title').text()+"】文件。请确认是否同步添加？<br/><div style='position: absolute; right: 2px;'><button>确定</button><button>取消</button></div><br/>",
                    timeout:5000
                });
            }
            else if ($(node.target).find(".icon-del").length > 0) {
                $.messager.show("提醒", "达标体系模板删除了【"+$(node.target).find('.title').text()+"】文件。请确认是否同步删除？");
            }
            else if ($(node.target).find(".icon-update").length > 0) {
                $.messager.show("提醒", "达标体系模板修改了【"+$(node.target).find('.title').text()+"】模板。请确认是否同步修改？");
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
    $(function () {
        var $tree = $($('#accordion').accordion('getSelected').children().get(0));
        var children = $tree.tree('getChildren');
        for (var i = 0; i < children.length; i++) {
            if ($tree.tree('isLeaf', children[i].target)) {
                $tree.tree('select', children[i].target);
                return;
            }
        }
    })
</script>
</#assign>
<@layout.indexLayout footer=script>
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
</style>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'达标体系', split:true" style="width: 250px">
        <div id="accordion" class="easyui-accordion" fit="true" border="false">
            <#list tree as map>
                <div style="overflow:auto;">
                    <header>
                        <span class="title">${map.text}</span><span class="fileCount">(${map.attributes.N_COUNT})</span>
                    </header>
                    <@buildTree map.children true/>
                </div>
            </#list>
        </div>
    </div>
    <div data-options="region:'center', border:false">
        <div id="content" class="easyui-panel" style="position: relative;" data-options="onLoad: onLoad" fit="true">
            <header>
                <span id="content_title">正在加载中...</span>
                <a href="/" style="position: absolute; right: 3px">返回首页</a>
            </header>
        </div>
    </div>
</div>
</@>
<#macro buildTree list root=false>
    <#if (list?size>0)>
    <ul <#if root>class="easyui-tree" data-options='onSelect: doSelect'</#if>>
        <#list list as map>
            <li data-options="id: '${map.id}', url: '${map.attributes.C_URL!}', <#if map.attributes.C_URL??==false>iconCls: 'icon-folder'</#if>">
                <span>
                    <#--有URL说明是“文件”-->
                        <#if map.attributes.C_URL??>
                        <#--文件如果数量为0则表示'未创建'-->
                            <#if (map.attributes.N_COUNT == 0)>
                                <a href="javascript:;" onclick="showDev()" class="icon-notCreated">[未创建]</a>
                            <#else>
                                <#if (map.attributes.N_STATE == 1)>
                                    <a href="javascript:;" onclick="showDev()" class="icon-new">[新增]</a>
                                <#elseif (map.attributes.N_STATE == 2)>
                                    <a href="javascript:;" onclick="showDev()" class="icon-del">[删除]</a>
                                <#elseif (map.attributes.N_STATE == 3)>
                                    <a href="javascript:;" onclick="showDev()" class="icon-update">[更新]</a>
                                </#if>
                            </#if>
                        </#if>
                        <span class="title">${map.text}</span>
                    <span class="fileCount">(${map.attributes.N_COUNT})</span>
                </span><@buildTree map.children/></li>
        </#list>
    </ul>
    </#if>
</#macro>