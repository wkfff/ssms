<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/costants.js"></script>
    <script type="text/javascript" src="index.js"></script>
    
    <style>
         li{ float:left; margin:10px;}
        .card{width:300px;border:1px solid #EEE;background-color:#FAFAFA;}
        .cardbar{background-color:#F6F6F6;padding:5px 0;text-align:center;}
        .cardcontent{padding:5px 0;}
        .nofound{padding:10px;}
        .t_title {font-weight: bold; color: #c9302c; text-align: right}
        .cardtable { table-layout: fixed;width: 100%; }
        .cardtable td { white-space: nowrap;overflow: hidden; }
        a.searchIcon{ background: url('/resource/css/easyui/themes/icons/search.png') no-repeat 0 center; padding-left: 20px; color: #0066cc}
        a.searchIcon:visited {color: #0066cc}
    </style>
</head>
<body>
<div class="container">
        <header class="titlebar">
            <label>企业监管</label>
        </header>
       
        <div class="content ue-clear">
             <div class="toolbar">
                        请选择企业范围:
                   <select data-bind="options: availableCities, optionsText: 'name', value: selectedCity, optionsCaption: '选择地区...'"></select>市&nbsp;&nbsp;
                   <select data-bind="options: availableCountries, optionsText: 'name', value: selectedCountry, optionsCaption: '选择地区...'"></select>区/县城&nbsp;&nbsp;
                         企业名称：<input type="text" data-bind="value:txtName"/>
                    <a href="#" class="searchIcon" data-bind="click:bindData">查询</a>
             </div>

             <ul data-bind="foreach: items" class="ue-clear">
                    <li data-bind="template: { name: 'card-template', data: $data }"></li>
             </ul>
            <div class="nofound" data-bind="visible:items().length==0">没有查询到符合条件的企业！</div>
        </div>
</div>

<script type="text/html" id="card-template">
    <div class="card">
        <div class="cardcontent">
            <table class="cardtable">
                <tr>
                    <td class="t_title">企业名称：</td>
                    <td colspan="3" data-bind="text: C_NAME"></td>
                </tr>
                <tr>
                    <td class="t_title">地址：</td>
                    <td colspan="3" data-bind="text: C_ADDR"></td>
                </tr>
                <tr>
                    <td class="t_title">联系人：</td>
                    <td data-bind="text: C_CONTACT"></td>
                    <td class="t_title">联系电话：</td>
                    <td data-bind="text: C_TEL"></td>
                </tr>
                <tr>
                    <td class="t_title">行业(专业)：</td>
                    <td><span data-bind="text: C_NAME_IND"></span>(<span data-bind="text: C_NAME_PRO"></span>)</td>
                    <td class="t_title">评审状态：</td>
                    <td data-bind="text:N_STATE"></td>
                </tr>
            </table>
        </div>
        <div class="cardbar">
            <a data-bind="attr: {'href': viewUrl}" class="searchIcon">详细信息</a>&nbsp;&nbsp;
        </div>
    </div>
</script>


<script type="text/javascript">
        var vm = new ViewModel('${P_CITY!}','${P_COUNTY!}','${C_NAME!}');
        ko.applyBindings(vm);
</script> 
</body>
</html>
