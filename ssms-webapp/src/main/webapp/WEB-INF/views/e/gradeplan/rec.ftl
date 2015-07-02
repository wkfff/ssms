<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<style>
    .table td{
        padding:5px;
    }
</style>

<script type="text/javascript">
    var model = {
        S_TENANT: ko.observable('${S_TENANT!LANSTAR_IDENTITY.tenantName}'),
        T_START: ko.observable('${T_START!}'),
        T_END: ko.observable('${T_END!}'),
        C_LEADER: ko.observable('${C_LEADER!}'),
        C_MEMBERS: ko.observable('${C_MEMBERS!}'),
        SID: '${SID!}'
    };
   
    var events = {
        saveClick: function () {
            if ($form.validate($('.form'))) {
                utils.messager.showProgress();
                $.post('save', model, function (result) {
                    utils.messager.closeProgress();
                    if (result.SID)
                        utils.messager.alert("保存成功");
                    else 
                        utils.messager.alert("保存失败");
                }, "json");
            }
        }
    };

    $(function () {
        ko.applyBindings($.extend({}, model, events));
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div data-options="region:'center',collapsible:false" style="overflow:hidden;">
          <div class="toolbar">
                <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
          </div>

         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post" class="form">
                    <table class="table">
                        <tr>
                            <td class="span2">自评单位:</td>
                            <td class="span10" colspan="3">
                                <input data-bind="textboxValue: S_TENANT" data-options="disabled:true" />
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">自评日期:</td>
                            <td class="span10">
                            <input data-bind="dateboxValue: T_START" data-options="required:true,width:90"/>
                                                        至
                            <input data-bind="dateboxValue: T_END" data-options="required:true,width:90"/>
                            </td>
                        </tr>
                         <tr>
                            <td class="span2">自评组组长:</td>
                            <td class="span10">
                            <input data-bind="textboxValue: C_LEADER" data-options="required:true"/>
                        </tr>
                        <tr>
                            <td class="span2">自评组主要成员:</td>
                            <td class="span10" colspan="3">
                            <input data-bind="textboxValue: C_MEMBERS" data-options="required:true"/>
                            </td>
                        </tr>
                    </table>
            </form>
        </div>
    </div>
</div>

</@layout.doLayout>