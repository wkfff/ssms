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
        select {
            height: 34px;
            border: 1px solid #c3d9e0;
        }

        .table {
            table-layout: fixed;
            width: 100%;
            border: 1px solid #c1d3de;
            border-top: none;
            text-align: center
        }

        .table thead tr th {
            height: 35px;
            line-height: 35px;
            border: 1px solid #c1d3de;
            background: url(/resource/images/tablelistbg.png) repeat-x;
            font-weight: bold;
            padding-left: 2px;
        }

        .table tbody tr td {
            border-left: 1px dotted #222;
            line-height: 30px;
        }

        .table tbody tr:first-child td {
            padding-top: 2px;
        }

        .table tbody tr td:first-child {
            border-left: none;
        }

        .titlebar {
            border: 1px solid #c1d3de;
            padding-left: 20px;
            line-height: 30px;
            font-weight: bold;
            font-size: 13px;
            background: #daeef5 url('/resource/images/blue/star.png') no-repeat 2px center;
            background-size: 16px 16px;
        }

        .diversification {
            background: #eff6fa;
        }
    </style>
</head>
<body>
<div class="titlebar">监督统计</div>

<div style="padding: 10px; border-left: 1px solid #c1d3de; border-right: 1px solid #c1d3de">
    <label>企业名称 <input type="text" data-bind="textboxValue: enterprise"/> </label>
    <label>
        <select data-bind="options: availableCities, optionsText: 'name', value: selectedCity, optionsCaption: '选择地区...'"></select>市
    </label>
    <label>
        <select data-bind="options: availableCountries, optionsText: 'name', value: selectedCountry, optionsCaption: '选择地区...'"></select>区/县城
    </label>
    <label>
        <select data-bind="options: availableProfessions, optionsText: 'name', value: selectedProfession, optionsCaption: '选择行业...'"></select>行业
    </label>
    <label>
        时间范围
        <input type="text" data-bind="dateboxValue: fromDate" style="width: 100px;"/>
        -
        <input type="text" data-bind="dateboxValue: toDate" style="width: 100px;"/>
    </label>
    <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: search">查询</a>
</div>
<table class="table">
    <colgroup>
        <col width="30"/>
        <col>
        <col width="90"/>
        <col width="90"/>
        <col width="90"/>
        <col width="90"/>
        <col width="90"/>
    </colgroup>
    <thead>
    <tr>
        <th>序号</th>
        <th>企业名称</th>
        <th>行业(专业)</th>
        <th>评审性质</th>
        <th>评审状态</th>
        <th>证书有效期至</th>
        <th>证书状态</th>
    </tr>
    </thead>
    <tbody data-bind="foreach: datas">
    <tr data-bind="css: {diversification: $index()%2==1}">
        <td data-bind="text: $index()+1"></td>
        <td style="text-align: left" data-bind="text: C_ENTERPRISE"></td>
        <td data-bind="text: C_PROFESSION"></td>
        <td data-bind="text: C_REVIEW_PROP"></td>
        <td data-bind="text: C_REVIEW_STATUS"></td>
        <td data-bind="text: T_CERT_END"></td>
        <td data-bind="text: S_CERT_STATUS"></td>
    </tr>
    </tbody>
</table>
</body>
<script type="text/javascript" src="/resource/js/costants.js"></script>
<script type="text/javascript">
    function ViewModel() {
        var self = this;

        self.enterprise = ko.observable();
        self.selectedCity = ko.observable();
        self.selectedCountry = ko.observable();

        self.availableCities = ko.observableArray($areas);
        self.availableCountries = ko.observableArray();
        self.selectedCity.subscribe(function (value) {
            self.availableCountries(value ? value.children : []);
        });

        self.availableProfessions = ko.observableArray($professions);
        self.selectedProfession = ko.observable();

        self.fromDate = ko.observable();
        self.toDate = ko.observable();

        self.datas = ko.observableArray([]);

        self.search = function () {
            var params = {
                enterprise: self.enterprise(),
                city: self.selectedCity(),
                country: self.selectedCountry(),
                profession: self.selectedProfession(),
                fromDate: self.fromDate(),
                toDate: self.toDate()
            };
            $.post('${BASE_PATH}/query', params, function (result) {
                self.datas(result);
            }, "json");
        };
    }

    $(function () {
        viewModel = new ViewModel();
        ko.applyBindings(viewModel);
    });
</script>
</html>