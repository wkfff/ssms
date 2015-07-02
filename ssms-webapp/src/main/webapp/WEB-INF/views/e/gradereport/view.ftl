<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    function doDownload(){
        alert('该功能暂未实现！');
    }
    
    function doPrint(){
        alert('该功能暂未实现！');
    }

    function doBack(){
        window.location.href='${referer!}';
    }
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div class="easyui-panel" data-options="region:'center'" style="overflow:hidden;border:1;border-left:0;">
         <div class="toolbar ue-clear" style="border:0px;border-left:1px solid #c1d3de;">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-download" onclick="doDownload();">下载</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-print" onclick="doPrint()">打印</a>
                <#if showback??>
                    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
                </#if>
         </div>
         <div style="width:100%;height:100%;padding:10px;">${C_CONTENT!}</div>
    </div>
</div>

</@layout.doLayout>