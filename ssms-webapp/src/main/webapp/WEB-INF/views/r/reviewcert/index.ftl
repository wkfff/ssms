<#import "../../layout/_list.ftl" as layout>
<#assign script>
<script type="text/javascript">
    function ViewModel() {
        var self = this;
        self.selectItem = ko.observable();

        self.viewSettings = {
            title: "证书管理",
            url: "${BASE_PATH}/list",
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            fit: true,
            pageSize: 50,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_ENTERPRISE', title: '单位名称', width: 300},
                    {field: 'S_INSDUTRY', align: 'center', title: '行业', width: 60},
                    {field: 'S_INSDUTRY', align: 'center', title: '专业', width: 60},
                    {field: 'C_CERT_NUMBER', align: 'center', title: '证书编号', width: 80},
                    {
                        field: 'N_STATE', align: 'center', title: '证书状态', width: 70, formatter: function (value) {
                        if (value == '0') return '有效期内';
                        if (value == '1') return '撤销';
                        if (value == '2') return '吊销';
                        if (value == '3') return '已过期';
                    }
                    },
                    {field: 'T_ISSUING_DATE', align: 'center', title: '发证日期', width: 80},
                    {field: 'T_VALIDITY', align: 'center', title: '有效期至', width: 80}
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
            location.href = "${BASE_PATH}/rec?sid="+value.SID;
        };

    }

    $(function () {
        ko.applyBindings(new ViewModel());
    })
</script>
</#assign>
<@layout.doLayout script=script>
<div class="easyui-panel" border="false" fit="true">
    <div id="toolbar" class="z-toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
    </div>
    <table data-bind="datagridValue:selectItem, easyuiOptions: viewSettings"></table>
</div>
</@>