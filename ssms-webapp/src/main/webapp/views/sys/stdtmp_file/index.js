/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-05-08
 * 创建用户：张铮彬
 */

function ViewModel(catalogId){
    var model = {
        selectItem: ko.observable()
    };
    var settings = {
        viewSettings: {
            url: "list.json",
            queryParams: {
                R_SID: catalogId
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
            ],
            onDblClickRow: function () {
                events.editClick();
            }
        }
    };
    var events = {
        refreshClick: function () {
            settings.viewSettings.datagrid('reload');
        },
        addClick: function () {
            window.location.href = 'rec.html?pid='+catalogId;
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

    $.extend(this, model, settings, events);
}