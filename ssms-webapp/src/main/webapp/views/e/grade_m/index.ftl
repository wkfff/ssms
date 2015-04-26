<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >

    <div data-options="region:'north',iconCls:'icon-star'"  style="padding:5px;">
        <img src="/resource/images/blue/home.png" width="16" height="16"/>
        <a href="/index.html" class="easyui-linkbutton" data-options="plain: true">首页</a> >
        <a href="#" class="easyui-linkbutton" data-options="plain: true">在线自评</a>
    </div>

    <div data-options="region:'west',split:true" title="在线自评" style="width:200px;">
        <div style="padding:6px;">
            <ul class="easyui-tree">
                <li>
                            <span><a href="rec.html?refer=add" target="mainFrame">开始自评</a></span>
                        </li>
                        <li>
                            <span><a href="history.html" target="mainFrame">自评历史</a></span>
                        </li>
                        <li>
                            <span><a href="draft.html" target="mainFrame">自评草稿</a></span>
                        </li>
            </ul>
        </div>
    </div>

    <div data-options="region:'center'" style="overflow:hidden;border:0;">
        <iframe id="mainFrame" name="mainFrame" frameborder="0" width="100%" height="100%" src="" />
    </div>
</div>
</@layout.doLayout>
