<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var SID = ${sid!};
    function doDownload(){
        alert('该功能暂未实现！');
    }
    
    function doPrint(){
        alert('该功能暂未实现！');
    }

    function doBack(){
        window.location.href='${referer!}';
    }

    $(function () {
         
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div class="easyui-panel" data-options="region:'center'" style="overflow:hidden;border:1;border-left:0;" title="上传评审结果">
         <div class="toolbar ue-clear" style="border:0px;border-left:1px solid #c1d3de;">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
         <@layout.com.upload module="SSM_GRADE_R_UPLOAD" sid=SID />
    </div>
</div>

</@layout.doLayout>