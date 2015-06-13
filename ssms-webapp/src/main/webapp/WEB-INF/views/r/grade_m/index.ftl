<#import "../../layout/_list.ftl" as layout/>
<#assign script>
<style>
     li{ float:left; margin:10px;}
     .card{width:300px;border:1px solid #EEE;background-color:#FAFAFA;}
     .cardbar{background-color:#F6F6F6;padding:5px;text-align:center;}
     .cardcontent{padding:5px;}
     .nofound{padding:10px;}
</style>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    var vm = new ViewModel('${P_CITY!}','${P_COUNTY!}','${C_NAME!}');
    ko.applyBindings(vm);
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar" class="z-toolbar">
     请选择企业范围:
    <!-- <input class="easyui-combobox" id="cb_pro" style="width:120px;" data-bind="comboboxValue:comboPro,easyuiOptions:comboProSettings">&nbsp;省&nbsp;&nbsp; -->
    <input class="easyui-combobox" id="cb_city" style="width:140px;" data-bind="comboboxValue:comboCity,easyuiOptions:comboCitySettings">&nbsp;市&nbsp;&nbsp;
    <input class="easyui-combobox" id="cb_county" style="width:160px;" data-bind="comboboxValue:comboCounty,easyuiOptions:comboCountySettings">&nbsp;区/县&nbsp;&nbsp;
         企业名称：<input class="easyui-textbox" id="txt_name" style="width:80px;" data-bind="textboxValue:txtName">
    <!-- <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click:gridEvents.refreshClick">查询</a> -->
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click:bindData">查询</a>
</div>
<!-- <table data-bind="datagridValue:selectItem,easyuiOptions: gridSettings"></table> -->
<div>
    <ul data-bind="foreach: items">
        <li>
            <div class="card">
                <div class="cardcontent">
                                    企业名称:<span data-bind="text:C_NAME"></span><br>
                                    地址:<span data-bind="text:C_ADDR"></span><br>
                                    联系人:<span data-bind="text:C_CONTACT"></span>
                                    <div style="float:right;">联系电话:<span data-bind="text:C_TEL"></span></div><br>
                                    行业:<span data-bind="text:C_NAME_IND"></span>
                                    专业:<span data-bind="text:C_NAME_PRO"></span>
                                    评审状态:<span data-bind="text:N_STATE"></span>
                </div>
                <div class="cardbar">
                    <a href="/r/stdtmp/query2?showback=1&sid=&pro=" class="easyui-linkbutton" iconCls="icon-search" plain="true">查看体系</a>&nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true">进入评审</a>
                </div>
            </div>
        </li>
    </ul>
    <div class="nofound" data-bind="visible:items().length==0">没有查询到符合条件的企业！</div>
</div>
</@>
