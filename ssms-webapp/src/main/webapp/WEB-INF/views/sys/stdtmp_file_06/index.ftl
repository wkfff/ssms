<@layout.extends name="../../_layouts/stdtmpfile.ftl">
<@layout.put block="head">
</@>
<@layout.put block="panel_content">
<div style="width:100%;height:685px;">
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-back" plain="true" data-bind="click:function(){window.location.href='${referer!}';}">返回</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table> 
</div>
</@>
<@layout.put block="footer">
<script type="text/javascript">
    function ViewModel(catalogId) {
        var model = {
            editItem: ko.observable(),
            selectItem: ko.observable(),
            selectIndex: ko.pureComputed(function () {
                var row = model.selectItem();
                if (row) return settings.gridSettings.datagrid('getRowIndex', row);
            })
        };
        var settings = {
            viewSettings: {
                url: "${BASE_PATH}/list",
                queryParams: {
                    R_SID: catalogId
                },
                idField: 'SID',
                rownumbers: true,
                pagination: true,
                fit: true,
                pageSize: 50,
                toolbar: '#toolbar',
                columns: [
                    [
                        {field: 'C_NAME', title: '存在问题', width: 150},
                        {field: 'P_LEVEL', title: '隐患等级', width: 150, formatter:function(value,row,index){
                            if (value == '01') return '一般隐患';
                            if (value == '02') return '重大隐患';
                        }},
                        {field: 'C_RESPONSIBLE', title: '整改部门', width: 80},
                        {field: 'T_RECTIFICATION', title: '要求整改日期', width: 80},
                        {field: 'B_FINISH', title: '隐患闭环情况', width: 80, formatter: function (value) {
                            if (value == '1') return '已闭环';
                            else return '未闭环';
                        }}
                    ]
                ],
                onDblClickRow: function (index, row) {
                    events.editClick();
                }
            }
        };
        var events = {
            refreshClick: function () {
                settings.viewSettings.datagrid('reload');
            },
            addClick: function () {
                window.location.href = '${BASE_PATH}/rec?pid=' + catalogId;
            },
            editClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                window.location.href = '${BASE_PATH}/rec?sid=' + value.SID+'&pid='+catalogId;
            },
            deleteClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                $.post('${BASE_PATH}/del', {sid: value.SID}, function () {
                    $.messager.alert('消息', '成功删除记录！', "info", function () {
                        events.refreshClick();
                    });
                });
            }
        };

        $.extend(this, model, settings, events);
    }

    $(function () {
        ko.applyBindings(new ViewModel(${file.id!}));
    });
</script>
</@>
</@layout.extends>