<#import "/layout/_layout.ftl" as layout/>
<#assign script>
<script type="text/javascript">
function doBack(){
        window.location.href='${referer!}';
    }
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div class="toolbar ue-clear">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="doBack();">返回</a>
         </div>
    <!--暂时先用框架页-->
    <div class="easyui-tabs" style="width:100%;height:0px;" data-options="fit:true">
        <div title="评分结果" style="padding:0px">
            <iframe frameborder="0" width="100%" height="100%" src="rec_result.html?sid=${sid!}" ></iframe>
        </div>
        <div title="自评报告" style="padding:0px" >
            <iframe frameborder="0" width="100%" height="100%" src="/e/grade_rep/rec.html?sid=${sid!}" ></iframe>
        </div>
        <div title="评分汇总" style="padding:0px">
            <iframe frameborder="0" width="100%" height="100%" src="/e/grade_d/sum.html?sid=${sid!}" ></iframe>
        </div>
    </div>
</div>
</@layout.doLayout>
