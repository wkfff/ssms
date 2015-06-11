function ViewModel() {
    var self = this;

    var model = {
        comboCity: ko.observable(),
        comboCounty: ko.observable(),
        txtName: ko.observable(''),
        selectItem: ko.observable()
    };
    
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
    });
    
    var settings = {
        comboCitySettings:{
            url:'/sys/para_area/list?R_CODE=350000',
            valueField:'C_CODE',
            textField:'C_VALUE'
        },
        comboCountySettings:{
            valueField:'C_CODE',
            textField:'C_VALUE'
        }
    };

    var events = {
        gridEvents: {
            refreshClick: function () {
                var url = "/r/grade_m/index?";
                url+="P_CITY="+model.comboCity()+"&P_COUNTY="+model.comboCounty()+"&C_NAME="+model.txtName();
                window.location.href = url;
            }
        }
    };

    $.extend(self, model, settings, events);
}