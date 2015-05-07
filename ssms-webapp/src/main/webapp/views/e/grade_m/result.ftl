<#import "/layout/_layout.ftl" as layout/>
<#assign script>
<script type="text/javascript">
function doBack(){
   window.location.href='${referer!}';
}
$(function () {
    $('#tt').tabs({
        border:false,
        onSelect:function(title,index){
            if (index==1){
                $('#tab'+index).html('<iframe frameborder="0" width="100%" height="100%" src="sum_ded.html?sid=${sid!}" ></iframe>');
            }
            if (index==2){
                $('#tab'+index).html('<iframe frameborder="0" width="100%" height="100%" src="report_rec.html?sid=${sid!}" ></iframe>');
            }
        }
    });
})
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'north'" border="false" class="toolbar" style="overflow:hidden;">
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack();">返回</a>
    </div>
    <div data-options="region:'center'" style="overflow:hidden;padding:0px;">
    <!--暂时先用框架页-->
    <div id="tt" class="easyui-tabs" style="width:100%;height:auto" data-options="fit:true,border:false">
        <div id="tab0" title="评分汇总" style="overflow:hidden;">
            <iframe id="frm1" frameborder="0" width="100%" height="100%" src="sum.html?sid=${sid!}" ></iframe>
        </div>
        <div id="tab1" title="扣分汇总" style="overflow:hidden;" >
            <iframe id="frm2" frameborder="0" width="100%" height="100%" src="" ></iframe>
        </div>
        <div id="tab2" title="自评报告" style="overflow:hidden;">
            <iframe id="frm3" frameborder="0" width="100%" height="100%" src="" ></iframe>
        </div>
    </div>
    </div>
</div>
</@layout.doLayout>
