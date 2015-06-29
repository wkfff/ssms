<!doctype html>
<html lang="zh-CN">
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
    <script type="text/javascript" src="index.js"></script>
    
    
    <style>
         li{ float:left; margin:10px;}
        .card{width:300px;border:1px solid #EEE;background-color:#FAFAFA;}
        .cardbar{background-color:#F6F6F6;padding:5px 0;text-align:center;}
        .cardcontent{padding:5px 0;}
        .nofound{padding:10px;}
        .t_title {font-weight: bold; color: #c9302c; text-align: right}
        .table { table-layout: fixed;width: 100%; }
        .table td { white-space: nowrap;overflow: hidden; }
        a.searchIcon{ background: url('/resource/css/easyui/themes/icons/search.png') no-repeat 0 center; padding-left: 20px; color: #0066cc}
        a.searchIcon:visited {color: #0066cc}
    </style>
</head>
<body>

<div id="toolbar" class="z-toolbar">
     请选择企业范围:
    <input class="easyui-combobox" id="cb_city" style="width:140px;" data-bind="comboboxValue:comboCity,easyuiOptions:comboCitySettings">&nbsp;市&nbsp;&nbsp;
    <input class="easyui-combobox" id="cb_county" style="width:160px;" data-bind="comboboxValue:comboCounty,easyuiOptions:comboCountySettings">&nbsp;区/县&nbsp;&nbsp;
         企业名称：<input class="easyui-textbox" id="txt_name" style="width:80px;" data-bind="textboxValue:txtName">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click:bindData">查询</a>
</div>

<div>
    <ul data-bind="foreach: items">
        <li data-bind="template: { name: 'card-template', data: $data }"></li>
    </ul>
    <div class="nofound" data-bind="visible:items().length==0">没有查询到符合条件的企业！</div>
</div>

<script type="text/html" id="card-template">
    <div class="card">
        <div class="cardcontent">
            <table class="table">
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
            <a data-bind="attr: {'href': viewUrl}" class="searchIcon">查看体系</a>&nbsp;&nbsp;
            <a data-bind="attr: {'href': reviewUrl}" class="searchIcon">进入评审</a>
        </div>
    </div>
</script>


<script type="text/javascript">
        var vm = new ViewModel('${P_CITY!}','${P_COUNTY!}','${C_NAME!}');
        ko.applyBindings(vm);
</script> 
</body>
</html>
