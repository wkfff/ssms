<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/fix.css"/>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>
</head>
<body>
<div class="easyui-panel" border="false" fit="true">
    <div id="toolbar" class="z-toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-new" plain="true" data-bind="click: newClick">发布新的通知公告</a>
    </div>
    <table data-bind="datagridValue:selectItem, easyuiOptions: viewSettings"></table>
</div>
</body>
<script type="text/javascript">
    function ViewModel() {
        var self = this;
        self.selectItem = ko.observable();

        self.viewSettings = {
            url: "${BASE_PATH}/drafts_list",
            title: "草稿箱",
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            fit: true,
            pageSize: 50,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_TITLE', align: 'left', title: '通知公告主题', width: 560},
                    {field: 'T_CREATE', align: 'center', title: '创建时间', width: 130},
                    {field: 'SID', align: 'center', title: '操作', width: 100,
                        formatter:function(value,row){
                            return "<a href='#' onclick='vm.editClick("+value+")'><img src='/resource/images/edtico.png'/>编辑</a>&nbsp;&nbsp;<a href='#' onclick='vm.delClick("+value+")'><img src='/resource/images/delico.png'/>删除</a>";
                        }},
                ]
            ],
            onDblClickRow: function (index, row) {
                self.editClick(row.SID);
            }
        };
        
        self.refreshClick = function () {
            self.viewSettings.datagrid('reload');
        };

        self.editClick = function (sid) {
            var index = layer.open({
                type: 2,
                area: ['970px', '530px'],
                maxmin: false,
                title:'发布通知公告',
                content: '/sys/notice/rec?tb=0&sid=' + sid
            });
            layer.full(index);
        };
        
        self.newClick = function(){
            self.editClick('');
        };
        
        self.delClick = function (sid) {
            utils.messager.confirm('是否确认要删除选中数据？', function (r) {
                    $.post('${BASE_PATH}/del', {sid: sid}, function () {
                            utils.messager.alert('成功删除记录！',function () {
                                self.refreshClick();
                            });
                    });
            });
        }
    }
    
    var vm = new ViewModel();
    $(function () {
        ko.applyBindings(vm);
    })
</script>

</html>