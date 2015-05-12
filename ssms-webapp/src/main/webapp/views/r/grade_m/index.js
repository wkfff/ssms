function ViewModel(templateId) {
    var self = this;

    var model = {
        comboPro: ko.observable(),
        comboCity: ko.observable(),
        comboCounty: ko.observable(),
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
                url: "/sys/para_area/list.json",
                queryParams: {R_PARENT: newValue}
            })
    });
    model.comboCity.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.comboCountySettings.combobox)
            settings.comboCountySettings.combobox({
                url: "/sys/para_area/list.json",
                queryParams: {R_PARENT: newValue}
            })
    });
    model.comboCounty.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.gridSettings.datagrid)
            settings.gridSettings.datagrid({
                url: "/sys/tenant_e/list.json",
                queryParams: {P_COUNTY: newValue}
            })
    });
    
    var settings = {
        comboProSettings:{
            url:'/sys/para_area/list.json?N_LEVEL=1',
            valueField:'SID',
            textField:'C_VALUE'
        },
        comboCitySettings:{
            url:'/sys/para_area/list.json',
            valueField:'SID',
            textField:'C_VALUE'
        },
        comboCountySettings:{
            url:'/sys/para_area/list.json',
            valueField:'SID',
            textField:'C_VALUE'
        },
        gridSettings: {
            idField: 'SID',
            url: "/r/grade/list_e.json",//测试用
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
                        width: 60,
                        align:'center',
                        formatter:function(value,row){
                                return "<a href='#'>评审</a>";
                        }
                    }
                ]
            ],
            onDblClickRow: function () {
                events.gridEvents.editClick();
            },
            onClickCell:function(index, field, value){
                if (field=='SID'){
                    self.sid(value);
                    events.gridEvents.editClick();
                }
            }
        }
    };

    var events = {
        gridEvents: {
            refreshClick: function () {
                settings.gridSettings.datagrid('reload');
            }, editClick: function () {
                var sid = self.sid();
                var url = '/r/grade/rec_new.html?R_EID={0}'.format(sid);
               window.location.href = url;
            }
        }
    };

    $.extend(self, model, settings, events);
}