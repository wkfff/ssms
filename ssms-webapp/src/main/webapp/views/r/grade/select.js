function ViewModel(templateId) {
    var self = this;

    var model = {
        comboPro: ko.observable(),
        comboCity: ko.observable(),
        comboCounty: ko.observable(),
        selectItem: ko.observable(),
        selectIndex: ko.pureComputed(function () {
            var row = model.selectItem();
            if (row) return settings.gridSettings.datagrid('getRowIndex', row);
        }),
        sid: ko.observable()
    };
    model.comboPro.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.comboCitySettings.combobox)
            settings.comboCitySettings.combobox({
                url: "/sys/para_area/list.json",
                queryParams: {R_PARENT: newValue.id}
            })
    });
    model.comboCity.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.comboCountySettings.combobox)
            settings.comboCountySettings.combobox({
                url: "/sys/para_area/list.json",
                queryParams: {R_PARENT: newValue.id}
            })
    });
    model.comboCounty.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.gridSettings.datagrid)
            settings.gridSettings.datagrid({
                url: "list.json",
                queryParams: {R_SID: newValue.id}
            })
    });
    
    var settings = {
        comboProSettings:{
            url:'/sys/para_area/list.json?N_LEVEL=1',
            method:'get',
            valueField:'C_CODE',
            textField:'C_VALUE',
            panelHeight:'auto'
        },
        comboCitySettings:{
        	url:'/sys/para_area/list.json',
            method:'get',
            valueField:'id',
            textField:'text',
            panelHeight:'auto'
        },
        comboCountySettings:{
        	url:'/sys/para_area/list.json',
            method:'get',
            valueField:'id',
            textField:'text',
            panelHeight:'auto'
        },
        gridSettings: {
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {
                        field: 'C_NAME',
                        title: '企业名称',
                        width: 300
                    }
                ]
            ],
            onDblClickRow: function () {
                events.editClick();
            }
        }
    };

    var events = {
        gridEvents: {
            refreshClick: function () {
                settings.gridSettings.datagrid('reload');
            }, queryClick: function () {
                
            }, editClick: function () {
                var sid = model.sid();
                var url = '/r/grade/rec.html?sid={0}'.format(sid);
                alert(url);
            }
        }
    };

    $.extend(self, model, settings, events);
}