function ViewModel(p_city,p_county,c_name) {
    var self = this;
    self.items = ko.observableArray();
    self.selectedCity = ko.observable(p_city);
    self.selectedCountry = ko.observable(p_county);
    self.availableCities = ko.observableArray($areas);
    self.availableCountries = ko.observableArray();
    self.selectedCity.subscribe(function (value) {
        self.availableCountries(value ? value.children : []);
    });
    self.txtName = ko.observable(c_name);
    self.bindData = function(){
        //utils.messager.showProgress('查询数据中，请稍候......');
        layer.load(1, {
            shade: [0.1,'#000'],
            time: 500
        });
        var paras = { C_NAME:self.txtName() };
        if (self.selectedCity() != null) paras.P_CITY = self.selectedCity().code;
        if (self.selectedCountry() != null) paras.P_CONUTY = self.selectedCountry().code;
        $.post('/r/grade/list', paras, function(data){
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
                    P_PROFESSION:product.P_PROFESSION,
                    viewUrl: '/r/stdtmp/view/'+product.SID+'-'+product.P_PROFESSION+'-1'
                });
            });
            self.items(details);
            //utils.messager.closeProgress();
        }, 'json');
    };
}