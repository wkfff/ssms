<style type="text/css">
    .form {
        margin: 0 auto;
        width: 80%;
    }

    .form table {
        width: 100%;
        margin: 5px auto;
        table-layout: fixed;
    }

    .form table tr {
        height: 40px;
    }

    .form table .label {
        width: 90px;
    }
</style>
<div id="kocontainer">
    <div class="z-toolbar" data-bind="visible:!readonly">
        <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <#if SID??>
        <a class="easyui-linkbutton"  plain="true" iconCls="icon-word" data-bind="click: function(){window.location.href='${BASE_PATH}/export/${SID}'}">导出</a>
        </#if>
        <#if file.templateModel??>
        <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: function(){window.open('/sys/stdtmp/file/view/${file.sourceFile.id}')}">查看模板</a>
        </#if> 
        
        <span style="position: absolute; right: 30px;">
            编辑状态：<span style="font-size: 12px; font-weight: bold; color: red"><#if SID??>编辑<#else>新增</#if></span>
        </span>
    </div>
    <form class="form" method="post" style="padding:10px 31px;">
        <table>
            <colgroup>
                <col class="label"/>
                <col/>
                <col class="label"/>
                <col/>
            </colgroup>
            <tr>
                <td>文件名称</td>
            <td colspan="3">
                <input data-bind="disable: readonly,textboxValue: C_NAME"/>
            </td>
            </tr>
        
        <tr>
            <td colspan="4">
            <textarea data-bind="htmleditValue: htmlContent" style="width: 100%; height: 500px"></textarea>
            </td>
        </tr>
        <#if file.explain?? && file.explain?length!=0>
        <tr>
          <td colspan="4">
              <div style="border: 1px dashed  #ccc; margin-bottom: 5px;position: relative;">
              <div style="background-color: #CCCCCC;">政策解读:</div>
              <table style="table-layout: auto;"></table>
              ${file.explain}
              </div>
          </td>
        </tr>
        </#if>
        <tr>
            <td colspan="4" data-bind="visible: SID">
            <a href="javascript:void(0);" data-bind="disable: readonly,uploadOptions: {module: 'STDTMP_FILE_03', sid: '${SID!}'}">[选择文件]</a>
            </td>
        </tr>
            <tr>
                <td colspan="4">
                    <div>
                        <div style="background-color: #CCCCCC; margin-top: 5px">操作指南：</div>
                        <span style="color: #ff0000; font-weight: bold">请根据要求将本要素相关记录完成并上传.(可将参考模板在线修改下载执行)</span>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
function ViewModel(fileId){
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        htmlContent: ko.observable(${json(C_CONTENT)}),
        R_TMPFILE: '${file.id!}',
        SID: '${SID!}'
    };
    var extModel = {
        readonly: ${@READONLY!'false'}
    };
    var events = {
        saveClick: function () {
            utils.messager.showProgress();
            $.post('${BASE_PATH}/save', model, function (result) {
                if (result.SID) {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存成功", "info");
                    panelLoad('${BASE_PATH}/${file.id}');
                }else {
                    $.messager.alert("提示", "保存失败", "warning", function () {
                        utils.messager.closeProgress();
                    }); 
                }
            }, "json");
        }
    };
        $.extend(this, model,extModel,events);
}
var onPanelLoad = function () {
    ko.applyBindings(new ViewModel(${fileid}), document.getElementById('kocontainer'));
};
</script>
