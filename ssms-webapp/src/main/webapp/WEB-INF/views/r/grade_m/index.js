function ViewModel(p_city,p_county,c_name) {
    var self = this;

    var model = {
        //comboPro: ko.observable(),
        comboCity: ko.observable(),
        comboCounty: ko.observable(),
        txtName: ko.observable(c_name),
        selectItem: ko.observable(),
        /*
        selectIndex: ko.pureComputed(function () {
            var row = model.selectItem();
            if (row) return settings.gridSettings.datagrid('getRowIndex', row);
        }),*/
        sid: ko.observable()
    };
    /*model.comboPro.subscribe(function (newValue) {
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
    */
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
    /*
    model.comboCounty.subscribe(function (newValue) {
        if (!newValue) return;
    });
    */
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
            title:'评审办理',
            url:'/r/grade_m/list_e',
            queryParams:{P_CITY:p_city,P_CONUTY:p_county,C_NAME:c_name},
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
                        field: 'C_ADDR',
                        title: '地址',
                        width: 300
                    },
                    {
                        field: 'C_CONTACT',
                        title: '联系人',
                        width: 300
                    },
                    {
                        field: 'C_TEL',
                        title: '联系电话',
                        width: 300
                    },
                    {
                        field: 'C_NAME_IND',
                        title: '行业',
                        width: 300
                    },
                    {
                        field: 'C_NAME_PRO',
                        title: '专业',
                        width: 300
                    },
                    {
                        field: 'N_STATE',
                        title: '评审状态',
                        width: 300
                    },
                    {
                        field: 'SID',
                        title: '评审',
                        width: 260,
                        align:'center',
                        formatter:function(value,row){
                            var pid = row.P_PROFESSION;
                            var html = "<a href='/e/stdtmp/list_version?R_TENANT="+value+"'>查看体系</a>&nbsp;&nbsp;<a href='/r/grade_m/tabs?sid="+value+"&pro="+pid+"'>进入评审</a>";
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
            },createClick:function(){
                window.location.href = '/r/grade_m/select';
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