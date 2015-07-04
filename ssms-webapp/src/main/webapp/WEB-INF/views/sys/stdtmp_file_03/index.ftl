<@layout.extends name="../../_layouts/stdtmpfile.ftl">
    <@layout.put block="head">
    <style type="text/css">
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
    </@>
    <@layout.put block="panel_content">
    <div class="z-toolbar" data-bind="visible: !readonly">
       <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
    </div>
    <form class="form" method="post" style="padding:10px 31px;">
      <table >
        <colgroup>
            <col class="label"/>
            <col/>
            <col class="label"/>
            <col/>
        </colgroup>
        <tr >
            <td>文件名称:</td>
            <td colspan="3">
                <input data-bind="disable: readonly, textboxValue: C_NAME" required/>
            </td>
        </tr>
     </table>
     <table >
        <tr >
            <td colspan="4">
                <textarea data-bind=" htmleditValue: htmlContent" style="width: 100%; height: 500px"></textarea>
            </td>
        </tr>
     </table>
    
    <div>
          操作指南：
    </div>
   </form>
    </@>
    <@layout.put block="footer">
    <script type="text/javascript">
    var model = {
        C_NAME: ko.observable('${C_NAME!}'),
        R_TEMPLATE: '${file.templateId}',
        htmlContent: ko.observable(${json(C_CONTENT)}),
        R_TMPFILE: '${R_TMPFILE!file.id}',
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
                        $.messager.alert("提示", "保存成功", "info", function () {
                            window.location.href = "sys/stdtmp/file"+file.id;
                        });
                    }else{
                        $.messager.alert("提示", "保存失败", "warning", function () {
                            utils.messager.closeProgress();
                        });
                    }
                }, "json");
        }
    };
    $(function () {
        ko.applyBindings($.extend({}, model, extModel, events));
    });
    </script>
    </@>
</@layout.extends>
