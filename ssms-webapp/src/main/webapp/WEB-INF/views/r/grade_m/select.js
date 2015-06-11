function ViewModel(p_city,p_county,c_name) {
    var self = this;

    var model = {
        //comboPro: ko.observable(),
        comboCity: ko.observable(),
        comboCounty: ko.observable(),
        //comboCountyText: ko.observable(),
        txtName: ko.observable(c_name),
        chkNoComplete: ko.observable(1),
        selectItem: ko.observable(),
        sid: ko.observable()
    };
    /*
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
    });*/
    model.comboCity.subscribe(function (newValue) {
        if (!newValue) return;
        if (settings.comboCountySettings.combobox)
           // model.comboCounty(null)
            settings.comboCountySettings.combobox({
                url: "/sys/para_area/list",
                queryParams: {R_CODE: newValue},
                valueField:'C_CODE',
                textField:'C_VALUE',
                onLoadSuccess: function(){
                    //var b = false;
                    var data = settings.comboCountySettings.combobox('getData');
                    for(var i=0;i<data.length;i++){
                        //alert(data[i].C_CODE);
                        if (data[i].C_CODE==p_county){
                            model.comboCounty(p_county);
                            break;
                        }
                    };
                    //if (b)
                        //model.comboCounty(p_county);
                }
            })
    });
    model.comboCounty.subscribe(function (newValue) {
        if (!newValue) return;
        //if (settings.gridSettings.datagrid)
        //    events.gridEvents.refreshClick();
    });
    
    var settings = {
            /*comboProSettings:{
            url:'/sys/para_area/list?N_LEVEL=1',
            valueField:'C_CODE',
            textField:'C_VALUE'
        },*/
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
        },
        gridSettings: {
            idField: 'SID',
            title:'第一步，请选择待评审的企业',
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
                        field: 'S_REVIEW',
                        title: '评审机构名称',
                        width: 200
                    },
                    {
                        field: 'SID',
                        title: '评审',
                        width: 60,
                        align:'center',
                        formatter:function(value,row){
                                return "<a href='rec_new?R_EID="+value+"'>评审</a>";
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
                    url: "/r/grade_m/list_e"+ (model.chkNoComplete()==1?"?N_STATE=0":""),
                    queryParams: {P_CITY:model.comboCity(),P_COUNTY: model.comboCounty(),C_NAME:model.txtName()}
                });
            }, editClick: function () {
                var sid = self.sid();//企业自评主表SID
                var url = 'rec_new?R_EID={0}'.format(sid);
               window.location.href = url;
            }
        }
    };

    $.extend(self, model, settings, events);
}