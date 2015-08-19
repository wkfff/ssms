<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/fix.css"/>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript" src="/resource/js/kindeditor/plugins/autoheight/autoheight.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>

    <style type="text/css">
        .container {
            width: 800px;
            margin: 0 auto;
        }

        table.content {
            width: 100%;
            font-size: 110%;
            margin: 10px 30px;
            table-layout: fixed;
        }

        table.content tr {
            height: 40px;
        }

        table.content tr td {
            padding: 3px;
        }

        table.content input {
            width: 100%;
            line-height: 120%;
            color: #383fa2;
            font-size: 110%;
        }

        input[readonly="readonly"], input[readonly] {
            border: 0;
            border-bottom: 1px solid #CCCCCC;
        }
    </style>
</head>
<body>
<div class="toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: saveClick">保存</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-back" onclick="window.location.href = '${referer!}'">返回列表</a>
</div>
<div class="container">
 <form class="form">
    <table class="content">
        <colgroup>
            <col width="60px">
            <col>
            <col width="60px">
            <col>
            <col width="60px">
            <col>
        </colgroup>
        <tr>
            <td>单位名称</td>
            <td colspan="5"><input type="text" data-bind="value: C_ENTERPRISE" readonly/></td>
        </tr>
        <tr>
            <td>等级</td>
            <td><input type="text" data-bind="textboxValue: N_LEVEL"/></td>
            <td>行业</td>
            <td><input type="text" data-bind="value: S_INSDUTRY" readonly/></td>
            <td>专业</td>
            <td><input type="text" data-bind="value: S_PROFESSION" readonly/></td>
        </tr>
        <tr>
            <td>证书编号</td>
            <td colspan="3"><input type="text" data-bind="textboxValue: C_CERT_NUMBER"/></td>
            <td>证书状态</td>
            <td>
                <input data-bind="comboboxSource:comboState,comboboxValue:N_STATE, easyuiOptions: {valueField:'code',textField: 'name'}"/>
            </td>
        </tr>
        <tr>
            <td>发证机关</td>
            <td colspan="5"><input type="text" data-bind="textboxValue: C_ISSUING_AUTHORITY"/></td>
        </tr>
        <tr>
            <td>印制编号</td>
            <td><input type="text" data-bind="textboxValue: C_PRINTED_NUMBER"/></td>
            <td>发证日期</td>
            <td><input type="text" data-bind="dateboxValue: T_ISSUING_DATE"/></td>
            <td>有效期至</td>
            <td><input type="text" data-bind="dateboxValue: T_VALIDITY"/></td>
        </tr>

        <tr>
            <td colspan="6">
                <a href="javascript:void(0);" data-bind="uploadOptions: {module: 'ENTERPRISE_CART', sid: '-1'}">[选择文件]</a>
            </td>
        </tr>
    </table>
     </form>
</div>
</body>
<script type="text/javascript">
    function ViewModel() {
        var self = this;

        self.C_ENTERPRISE = ko.observable('${C_ENTERPRISE!}');
        self.N_LEVEL = ko.observable('${N_LEVEL!}');
        self.S_INSDUTRY = ko.observable('${S_INSDUTRY!}');
        self.S_PROFESSION = ko.observable('${S_PROFESSION!}');
        self.N_STATE = ko.observable(${N_STATE!0});
        self.C_CERT_NUMBER = ko.observable('${C_CERT_NUMBER!}');
        self.C_ISSUING_AUTHORITY = ko.observable('${C_ISSUING_AUTHORITY!}');
        self.C_PRINTED_NUMBER = ko.observable('${C_PRINTED_NUMBER!}');
        self.T_ISSUING_DATE = ko.observable('${T_ISSUING_DATE!}');
        self.T_VALIDITY = ko.observable('${T_VALIDITY!}');

        var model = {
            C_ENTERPRISE: self.C_ENTERPRISE,
            N_LEVEL: self.N_LEVEL,
            S_INSDUTRY: self.S_INSDUTRY,
            S_PROFESSION: self.S_PROFESSION,
            N_STATE: self.N_STATE,
            C_CERT_NUMBER: self.C_CERT_NUMBER,
            C_ISSUING_AUTHORITY: self.C_ISSUING_AUTHORITY,
            C_PRINTED_NUMBER: self.C_PRINTED_NUMBER,
            T_ISSUING_DATE: self.T_ISSUING_DATE,
            T_VALIDITY: self.T_VALIDITY,
            SID: '${SID!}'
        };
        
        self.comboState = ko.observableArray([
            {code: '0', name: '有效期内'},
            {code: '1', name: '撤销'},
            {code: '2', name: '吊销'},
            {code: '3', name: '已过期'}
        ]);

        self.saveClick = function () {
            if ($form.validate($('.form'))) {
                    $.post('save', $.extend({}, model), function (result) {
                        if (result.SID)
                            $.messager.alert("提示", "保存成功", "info", function () {
                            });
                        else {
                            $.messager.alert("提示", "保存失败", "warning");
                        }
                    }, 'json');
            }
        };
    }

    $(function () {
        ko.applyBindings(new ViewModel());
    })
</script>
</html>