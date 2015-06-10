<div id="kocontainer" class="easyui-panel" border="false" fit="true">
    <div id="z-toolbar" data-bind="visible:!readonly">
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
    </div>
    <table data-bind="datagridValue:selectItem,easyuiOptions: viewSettings"></table>
</div>
<script type="text/javascript">
    function ViewModel(catalogId) {
        var SEX = [{ "value": "1", "text": "男" }, { "value": "2", "text": "女" }];
        var model = {
            editItem: ko.observable(),
            selectItem: ko.observable(),
            selectIndex: ko.pureComputed(function () {
                var row = model.selectItem();
                if (row) return settings.gridSettings.datagrid('getRowIndex', row);
            })
            ,readonly: ${@READONLY!'false'}
        };
        var settings = {
            viewSettings: {
                url: "${BASE_PATH}/list",
                queryParams: {
                    R_TMPFILE: catalogId
                },
                idField: 'SID',
                rownumbers: true,
                pagination: true,
                fit: true,
                pageSize: 50,
                toolbar: '#toolbar',
                columns: [
                    [
                        {field: 'C_NAME', title: '姓名', width: 120,
                            editor: {type: 'textbox', options: {validType: ['length[0, 300]']}}},
                        {field: 'P_SEX', title: '性别', width: 60,
                            editor: {type: 'combobox', options: {data: SEX, valueField: "value", textField: "text"}},
                            formatter:function(value,row){
                                console.log(value);
                                return value=='1'?'男':'女';
                            }},
                        {field: 'C_CARD', title: '身份证号码', width: 140,
                            editor: {type: 'textbox', options: {validType: ['length[0, 18]']}}},
                        {field: 'C_DEPT', title: '所在部门', width: 300,
                            editor: {type: 'textbox', options: {}}},
                        {field: 'C_WORKTYPE', title: '工种',align:'center',width: 60,
                            editor: {type: 'textbox', options: {}}},
                        {field: 'C_AUTH', title: '发证机关', width: 200,
                            editor: {type: 'textbox', options: {validType: ['length[0, 200]']}}},
                        {field: 'T_CERT_GET', title: '取证时间', width: 100,
                            editor: {type: 'datebox', options: {}}},
                        {field: 'C_CERT', title: '证书编号', width: 140,
                            editor: {type: 'textbox', options: {}}},
                        {field: 'T_CERT_REVIEW', title: '复审时间', width: 100,
                            editor: {type: 'datebox', options: {}}}
                        ]
                ]
                <#if @READONLY== 'false' >,
                onDblClickRow: function (index, row) {
                    events.editClick();
                }
                </#if>
            }
        };
        var events = {
            refreshClick: function () {
                settings.viewSettings.datagrid('reload');
            },
            addClick: function () {
                panelLoad('${BASE_PATH}/rec?pid=' + catalogId);
            },
            editClick: function () {
                var value = model.selectItem();
                if (value == null) {
                    $.messager.alert("警告", "请先选择一行数据！", "warning");
                    return;
                }
                panelLoad('${BASE_PATH}/rec?sid=' + value.SID);
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
    var onPanelLoad = function () {
        ko.applyBindings(new ViewModel(${sid}), document.getElementById('kocontainer'));
    };
</script>