function ViewModel(templateId) {
    var self = this;

    var model = {
        comboPro: ko.observable(),
        comboCity: ko.observable(),
        comboCounty: ko.observable(),
        txtName: ko.observable(),
        selectItem: ko.observable(),
        /*
        selectIndex: ko.pureComputed(function () {
            var row = model.selectItem();
            if (row) return settings.gridSettings.datagrid('getRowIndex', row);
        }),*/
        sid: ko.observable()
    };
    model.comboPro.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.comboCountySettings.combobox){
            settings.comboCountySettings.combobox("clear");
            settings.comboCountySettings.combobox("loadData",[]);
        }
        if (settings.comboCitySettings.combobox)
            settings.comboCitySettings.combobox({
                url: "/sys/para_area/list",
                queryParams: {R_CODE: newValue}
            })
    });
    model.comboCity.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.comboCountySettings.combobox)
            settings.comboCountySettings.combobox({
                url: "/sys/para_area/list",
                queryParams: {R_CODE: newValue}
            })
    });
    model.comboCounty.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.gridSettings.datagrid)
            events.gridEvents.refreshClick();
    });
    
    var settings = {
        comboProSettings:{
            url:'/sys/para_area/list?N_LEVEL=1',
            valueField:'C_CODE',
            textField:'C_VALUE'
        },
        comboCitySettings:{
            valueField:'C_CODE',
            textField:'C_VALUE'
        },
        comboCountySettings:{
            valueField:'C_CODE',
            textField:'C_VALUE'
        },
        gridSettings: {
            idField: 'SID',
            title:'评审',
            url:'/r/grade_m/list_r',
            rownumbers: true,
            pagination: true,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {
                        field: 'C_NAME',
                        title: '企业名称',
                        width: 200
                    },
                    {
                        field: 'C_TITLE',
                        title: '标题',
                        width: 300
                    },
                    {
                        field: 'SID',
                        title: '评审',
                        width: 120,
                        align:'center',
                        formatter:function(value,row){
                            var html = "<a href='/r/grade_m/rec?sid="+value+"'>评审</a>&nbsp;&nbsp;<a href='/r/grade_m/report_rec?sid="+value+"'>编辑评审报告</a>";
                            if (row.N_STATE && row.N_STATE==0)
                            html +="&nbsp;&nbsp;<a href='javascript:undo("+value+");'>撤销误评审</a>";
                            return  html;
                        }
                    }
                ]
            ],
            onDblClickRow: function () {
                events.gridEvents.editClick();
            }
        }
    };

    var events = {
        gridEvents: {
            refreshClick: function () {
                settings.gridSettings.datagrid({
                    url: "/r/grade_m/list_r",
                    queryParams: {P_COUNTY: model.comboCounty(),C_NAME:model.txtName()}
                });
            }, editClick: function () {
                var sid = self.sid();
                var url = '/r/grade_m/rec?sid={0}'.format(sid);
               window.location.href = url;
            }
        }
    };

    $.extend(self, model, settings, events);
}

function undo(sid){
    $.messager.confirm("撤销评审确认", "您确认要撤销对当前企业正在进行的评审吗？", function (deleteAction) {
        if (deleteAction) {
            $.get("/r/grade_m/undo", {sid:sid}, function (data) {
                if (data.result == "OK") {
                    $.messager.alert("提示", "撤销评审成功！");
                    vm.gridEvents.refreshClick();
                }
                else {
                    $.messager.alert("提示", data);
                }
            });
        }
    });
}