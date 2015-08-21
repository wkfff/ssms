<@layout.extends name="../../_layouts/base.ftl">
<@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/fix.css"/>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
    <script type="text/javascript" src="/resource/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.validation.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.validation.zh-CN.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/date.js"></script>
    <style type="text/css">
        select {
            height: 34px;
            border: 1px solid #c3d9e0;
        }
        span.validationMessage {
            position:absolute;
            font-weight:bold;
            color: red;
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
        .icon-save {
            background: url("/resource/images/search.png") no-repeat 2px center;
            padding-left: 20px;
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
</@>
<@layout.put block="contents">
<div class="titlebar">隐患监管</div>
<div style="padding: 10px; border-left: 1px solid #c1d3de; border-right: 1px solid #c1d3de">
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
        整改范围从:
        <input type="text" data-bind="dateValue: from" style="width: 100px;"/>-
        <input type="text" data-bind="dateValue: to" style="width: 100px;"/>
    </label>
    <a class="icon-save" data-bind="click : search">查询</a>
</div>
<table class="table">
    <colgroup>
        <col width="30"/>
        <col width="130">
        <col width="150"/>
        <col width="110"/>
        <col width="110"/>
    </colgroup>
    <thead>
    <tr>
        <th>序号</th>
        <th>企业名称</th>
        <th>地址</th>
        <th>专业</th>
        <th>隐患整改率</th>
    </tr>
    </thead>
    <tbody data-bind="foreach: datas">
    <tr data-bind="template: { name: 'row-template', data: $data },css: {diversification: $index()%2==1}">

    </tr>
    </tbody>
</table>
<script type="text/html" id="row-template">
    <td data-bind="text: $index()+1"></td>
    <td style="text-align: left" data-bind="text: S_TENANT"></td>
    <td data-bind="text: C_ADDR"></td>
    <td data-bind="text: S_PROFESSION"></td>
    <td data-bind="text: C_RECTIFICATION==null? '未整改':C_RECTIFICATION+'%'">未整改</td>
</script>
</@>
<@layout.put block="footer">
<script type="text/javascript" src="/resource/js/costants.js"></script>
<script type="text/javascript" src="/resource/js/layui/layer.js"></script>
<script type="text/javascript">
    function ViewModel() {
        var self = this;
        self.selectedCity = ko.observable();
        self.selectedCountry = ko.observable();

        self.availableCities = ko.observableArray($areas);
        self.availableCountries = ko.observableArray();
        self.selectedCity.subscribe(function (value) {
            self.availableCountries(value ? value.children : []);
        });

        self.availableProfessions = ko.observableArray($professions);
        self.selectedProfession = ko.observable();
        self.from = ko.observable();
        self.to = ko.observable();
        self.datas = ko.observableArray();
        self.search=function(){
            if(self.from()>self.to()){
                utils.messager.alert('开始时间范围不能大于结束时间');
                return;
            }
            var model={
                    from : self.from(),
                    to : self.to()
            }
            if(self.selectedCity()!=null){
                model.city = self.selectedCity().code;
            }
            if(self.selectedCountry()!=null){
                model.country = self.selectedCountry().code;
            }
            if(self.selectedProfession()!=null){
                model.profession = self.selectedProfession().code;
            }
            $.post('${BASE_PATH}/list',model,function(result){
                self.datas(result);
            },'json');
        }
    }

    $(function () {
         viewModel = new ViewModel();
         ko.applyBindings(viewModel);
    });
</script>
</@>
</@layout.extends>
