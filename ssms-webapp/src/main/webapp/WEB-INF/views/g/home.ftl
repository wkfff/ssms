<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/home.css"/>
    <title>安全生产标准化管理系统</title>
    <style type="text/css">
        .images img{
            vertical-align: top;
        }
    </style>
</head>

<body>
<div class="article toolbar">
<#--<div class="title ue-clear">
        <h2>快捷入口</h2>
    </div>
    <div class="content">
        <ul class="toollist ue-clear">
            <li>
                <a href="javascript:doCreate();" class="img"><img src="/resource/images/icon01.png" /></a>
                <p><a href="javascript:doCreate();" title="选择企业后开始评审">企业评审</a></p>
            </li>
            <li>
                <a href="javascript:doChangePwd();" class="img"><img src="/resource/images/icon03.png" /></a>
                <p><a href="javascript:doChangePwd();">密码修改</a></p>
            </li>
            <li>
                <a href="javascript:doConfig();" class="img"><img src="/resource/images/icon09.png" /></a>
                <p><a href="javascript:doConfig();">系统配置</a></p>
            </li>
        </ul>
    </div>-->
</div>
<div class="article half notice">
    <div class="wrap-l">
        <div class="title ue-clear">
            <h2>收到的公告</h2>
            <a href="/r/notice/publics" class="more">更多</a>
        </div>
        <div class="content">
            <ul class="notice-list">
            <#if rs_notice?exists && rs_notice?size!=0>
                <#list rs_notice as rs>
                    <li class="ue-clear">
                        <a href="javascript:nav('/g/notice/view?sid=${rs.SID}');" class="notice-title">${rs.C_TITLE}</a>

                        <div class="notice-time">${rs.T_PUBLISH}</div>
                    </li>
                </#list>
            <#else>
                <li class="ue-clear">
                    <span style="padding-left:5px;">暂时还没有通知公告。</span>
                </li>
            </#if>
            </ul>
        </div>
    </div>
</div>
<div class="article half notice">
    <div class="wrap-r">
        <div class="title ue-clear">
            <h2>发布的公告</h2>
            <a href="/r/notice/publics" class="more">更多</a>
        </div>
        <div class="content">
            <ul class="notice-list">
            <#if rs_notice?? && rs_notice?size!=0>
                <#list rs_notice as rs>
                    <li class="ue-clear">
                        <a href="javascript:nav('/g/notice/view?sid=${rs.SID}');" class="notice-title">${rs.C_TITLE}</a>

                        <div class="notice-time">${rs.T_PUBLISH}</div>
                    </li>
                </#list>
            <#else>
                <li class="ue-clear">
                    <span style="padding-left:5px;">暂时还没有通知公告。</span>
                </li>
            </#if>
            </ul>
        </div>
    </div>
</div>

<div class="article toolbar">
    <div class="title ue-clear">
        <h2>统计分析</h2>
        <a href="/r/statistics/index" class="more">更多</a>
    </div>
    <div class="content">
        <div id="statistics" style="padding-top: 10px; padding-left:20px; padding-bottom: 10px; border-bottom: 1px solid #CCCCCC">
            查询统计区域:
            <label>
                <select data-bind="options: availableCities, optionsText: 'name', value: selectedCity, optionsCaption: '选择地区...'"></select>市
            </label>
            <label>
                <select data-bind="options: availableCountries, optionsText: 'name', value: selectedCountry, optionsCaption: '选择地区...'"></select>区/县城
            </label>
            <a class="easyui-linkbutton" plain="true" iconCls="icon-search" data-bind="click: search">查询</a>
        </div>
        <div class="images">
            <img src="/resource/1.png" alt=""/>
            <img src="/resource/2.png" alt=""/>
            <img src="/resource/3.png" alt=""/>
        </div>
    </div>
</div>

<!-- <div id="win_select" class="easyui-window" title="企业评审" data-options="onLoad:winSelectOnLoad,iconCls:'icon-add',modal:true,minimizable:false,closed:true,href:'/r/grade_m/select'" style="width:900px;height:500px;padding:6px;" >
    
</div> -->

</body>

<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
<script type="text/javascript" src="/resource/js/costants.js"></script>
<script type="text/javascript">
    var aIndex = 0;
    $(".title-list ul").on("click", "li", function () {
        aIndex = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $(".matter-content").removeClass("current").eq(aIndex).addClass("current");
    });

    $(".duty").find("tbody").find("tr:even").css("backgroundColor", "#eff6fa");

    $("#more").on("click", function () {
        window.location.href = "/r/" + (aIndex == 0 ? "todo" : "done") + "/index";
    });

    function nav(url) {
        window.location.href = url;
    }

    function doCreate() {
        window.location.href = '/r/grade_m/select2';
    }

    function doChangePwd() {

    }

    function doConfig() {

    }

    function ViewModel() {
        var self = this;

        self.selectedCity = ko.observable();
        self.selectedCountry = ko.observable();

        self.availableCities = ko.observableArray($areas);
        self.availableCountries = ko.observableArray();
        self.selectedCity.subscribe(function (value) {
            self.availableCountries(value ? value.children : []);
        });

        self.search = function () {

        };
    }

    $(function () {
        viewModel = new ViewModel();
        ko.applyBindings(viewModel, document.getElementById('statistics'));
    });
</script>
</html>
