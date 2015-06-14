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
</head>
<body>
<div class="easyui-panel" border="false" fit="true">
    <div id="toolbar" class="z-toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" plain="true" data-bind="click: editClick">查看</a>
    </div>
    <table data-bind="datagridValue:selectItem, easyuiOptions: viewSettings"></table>
</div>
</body>
<script type="text/javascript">
    function ViewModel() {
        var self = this;
        self.selectItem = ko.observable();

        self.viewSettings = {
            url: "${BASE_PATH}/publics_list",
            title: "通知公告列表",
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            fit: true,
            pageSize: 50,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'S_PUBLISH', title: '发布单位', width: 150},
                    {field: 'C_TITLE', align: 'center', title: '通知公告主题', width: 500},
                    {field: 'T_PUBLISH', align: 'center', title: '发布时间', width: 160}
                ]
            ],
            onDblClickRow: function (index, row) {
                self.editClick();
            }
        };

        self.refreshClick = function () {
            self.viewSettings.datagrid('reload');
        };

        self.editClick = function () {
            var value = self.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            window.location.href = '/g/notice/view?sid=' + value.SID;
        };
        self.deleteClick = function () {
            var value = self.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            $.messager.confirm('确认', '是否确认要删除选中数据？', function (r) {
                if (r) {
                    $.post('${BASE_PATH}/del', {sid: value.SID}, function () {
                        $.messager.alert('消息', '成功删除记录！', "info", function () {
                            self.refreshClick();
                        });
                    });
                }
            });
        }
    }

    $(function () {
        ko.applyBindings(new ViewModel());
    })
</script>

</html>