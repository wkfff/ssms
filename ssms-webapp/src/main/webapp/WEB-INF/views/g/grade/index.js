function ViewModel(p_city,p_county,c_name) {
    var self = this;
    self.items = ko.observableArray();
    self.bindData = function(){
        $.ajax({
            type: 'get',
            url:'/g/grade/list_e',
            dataType: "json",
            data: {P_CITY:model.comboCity,P_CONUTY:model.comboCounty,C_NAME:model.txtName},
            success: function (data) {
                var details = [];
                $.each(data, function (idx, product) {
                    details.push({
                        SID: product.SID,
                        C_NAME: product.C_NAME,
                        C_ADDR: product.C_ADDR,
                        C_CONTACT: product.C_CONTACT,
                        C_TEL: product.C_TEL,
                        C_NAME_IND: product.C_NAME_IND,
                        C_NAME_PRO: product.C_NAME_PRO,
                        N_STATE: product.N_STATE,
                        viewUrl: '/g/stdtmp/query2?showback=1&sid='+product.SID+'&pro='+product.P_PROFESSION,
                        reviewUrl: '/g/grade/tabs?sid='+product.SID+'&pro='+product.P_PROFESSION
                    });
                });
                self.items(details);
            }
        });
    };
    
    var model = {
        comboCity: ko.observable(),
        comboCounty: ko.observable(),
        txtName: ko.observable(decodeURIComponent(c_name)),
        selectItem: ko.observable(),
        sid: ko.observable()
    };
    
    model.comboCity.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.comboCountySettings.combobox)
            settings.comboCountySettings.combobox({
                url: "/sys/para_area/list",
                queryParams: {R_CODE: newValue},
                valueField:'C_CODE',
                textField:'C_VALUE',
                onLoadSuccess: function(){
                    var data = settings.comboCountySettings.combobox('getData');
                    for(var i=0;i<data.length;i++){
                        if (data[i].C_CODE==p_county){
                            model.comboCounty(p_county);
                            break;
                        }
                    };
                }
            })
    });
   
    var settings = {
        comboCitySettings:{
            url:'/sys/para_area/list?R_CODE=350000',
            valueField:'C_CODE',
            textField:'C_VALUE',
            onLoadSuccess: function(){
                model.comboCity(p_city);
            }
        },
        comboCountySettings:{
            valueField:'C_CODE',
            textField:'C_VALUE'
        }
    };
    
    var events = {
        
    };

    $.extend(self, model, settings, events);
}