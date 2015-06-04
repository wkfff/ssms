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
        SID: '${SID!}',
        R_EID:'${R_EID!}'
    };
   
    var events = {
        saveClick: function () {
            utils.messager.showProgress();
            $.post('save', model, function (result) {
                if (result.SID) {
                    $.messager.alert("提示", "保存成功", "info", function () {
                            utils.messager.closeProgress();
                            window.location.href = 'rec?sid=' + result.SID + "&backURL=${backURL!referer!}";
                    });
                } else {
                    utils.messager.closeProgress();
                    $.messager.alert("提示", "保存失败", "warning");
                }
            }, "json");
        },backClick: function(){
            window.location.href= '${referer!'/r/grade_m/index'}';
        }
    };

    $(function () {
        ko.applyBindings($.extend({}, model, events));
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div title="第二步，请填写评审概要信息" data-options="region:'center',collapsible:false" style="overflow:hidden;">
          <div class="z-toolbar">
                <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
                <a class="easyui-linkbutton" onclick="" plain="true" iconCls="icon-back" data-bind="click: backClick">返回</a>
          </div>

         <div class="easyui-panel" style="border:0;margin:10px;">
             <form id="formMain" method="post">
                    <table class="table">
                        <tr>
                            <td class="span2">评审单位:</td>
                            <td class="span10" colspan="3">
                                <input data-bind="textboxValue: S_TENANT" data-options="disabled:true" />
                             </td>
                        </tr>
                        <tr>
                            <td class="span2">评审日期:</td>
                            <td class="span4">
                            <input data-bind="dateboxValue: T_START" data-options="required:true,width:90"/>
                                                        至
                            <input data-bind="dateboxValue: T_END" data-options="required:true,width:90"/>
                            </td>
                            <td class="span2">评审组组长:</td>
                            <td class="span4">
                            <input data-bind="textboxValue: C_LEADER" data-options="required:true"/>
                        </tr>
                        <tr>
                            <td class="span2">评审组主要成员:
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