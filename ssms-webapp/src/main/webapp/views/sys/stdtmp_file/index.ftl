<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var model = {
        selectItem: ko.observable()
    };
    var settings = {
        viewSettings: {
            url: "list.json",
            queryParams: {
                R_SID: ${R_SID}
            },
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_NAME', title: '文件名', width: 100},
                    {field: 'C_DESC', title: '描述', width: 200},
                    {
                        field: 'N_CYCLE', title: '更新周期', align: 'center', width: 80,
                        formatter: function (value, row) {
                            if (value) return '{0}/{1}'.format(value, row.S_CYCLE);
                        }
                    },
                    {
                        field: 'B_REMIND', title: '是否提醒', align: 'center', width: 80,
                        formatter: function (value, row) {
                            if (value) return value === 1 ? "是" : "否"
                        }
                    }
                ]
            ]
        }
    };
    var events = {
        refreshClick: function () {
            settings.viewSettings.datagrid('reload');
        },
        addClick: function () {
            window.location.href = 'rec.html?pid=${R_SID}';
        },
        editClick: function () {
            var value = model.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            window.location.href = 'rec.html?sid={0}'.format(value.SID);
        },
        deleteClick: function () {
            var value = model.selectItem();
            if (value == null) {
                $.messager.alert("警告", "请先选择一行数据！", "warning");
                return;
            }
            $.post('del.do', {sid: value.SID}, function () {
                $.messager.alert('消息', '成功删除记录！', "info", function () {
                    events.refreshClick();
                });
            });
        }
    };
    $(function () {
        ko.applyBindings($.extend(model, settings, events));
    })
</script>
</#assign>
<@layout.doLayout script=script>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
</div>
<table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table>
</@>