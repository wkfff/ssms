<#import "../../layout/_rec.ftl" as layout/>
<#assign script>
<style type="text/css">
    .kowrap {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: #FAFAFA;
        overflow: auto;
    }

    .kocontainer, .tbwarp {
        width: 800px;
        margin: 0 auto;
    }

    .lager .item {
        margin: 10px;
        border: 1px solid #CCCCCC;
        background: #EED;
    }

    .lager .item table {
        margin: 5px auto;
    }

    .lager .item table tr {
        height: 40px;
    }

    .lager .item table td.label {
        padding-left: 10px;
    }
</style>
<script type="text/javascript" src="rec.js"></script>
<script type="text/javascript">
    var items = <#if list??>${json(list)}<#else>[]</#if>;
    $(function () {
        ko.applyBindings(new LedgerModel(items));
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="kowrap">
    <h1 style="font-size: xx-large;text-align: center;">${template.C_NAME}</h1>

    <div class="z-toolbar">
        <div class="tbwarp">
            <a class="easyui-linkbutton" plain="true" iconCls="icon-add" data-bind="click: addItem">添加隐患项目</a>
            <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="window.location.href = '${backURL!referer!}'">返回</a>
        </div>
    </div>
    <div class="kocontainer">
        <div class="lager" data-bind="foreach: items">
            <div class="item">
                <div class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: $root.save">保存</a>
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" data-bind="click: $root.remove">删除</a>
                </div>
                <table>
                    <tr>
                        <td class="label">存在问题</td>
                        <td colspan="5"><input data-bind="textboxValue: C_NAME" required/></td>
                    </tr>
                    <tr>
                        <td class="label">检查人</td>
                        <td><input data-bind="textboxValue: C_EXAMINER" required/></td>
                        <td class="label">检查时间</td>
                        <td><input data-bind="dateboxValue: T_EXAMINE" required/></td>
                        <td class="label">隐患等级</td>
                        <td><input data-bind="comboboxSource: $root.levelSource, comboboxValue: P_LEVEL, easyuiOptions: $root.levelSettings" required/></td>
                    </tr>
                    <tr>
                        <td class="label">整改措施</td>
                        <td colspan="5"><input data-bind="textboxValue: C_MEASURE" required/></td>
                    </tr>
                    <tr>
                        <td class="label">整改负责人</td>
                        <td><input data-bind="textboxValue: C_RESPONSIBLE" required/></td>
                        <td class="label">要求整改日期</td>
                        <td><input data-bind="dateboxValue: T_RECTIFICATION" required/></td>
                        <td class="label">验收人</td>
                        <td class="label"><input data-bind="textboxValue: C_ACCEPTANCE" required/></td>
                    </tr>
                    <tr data-bind="visible: SID">
                        <td colspan="6">
                            <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'STDTMP_FILE_06', sid: $data.SID}">[选择文件]</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</@>