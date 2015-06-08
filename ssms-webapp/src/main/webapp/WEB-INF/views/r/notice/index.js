function ViewModel(templateId) {
    var self = this;

    var model = {
        selectItem: ko.observable(),
        sid: ko.observable()
    };
    
    
    var settings = {
        gridSettings: {
            idField: 'SID',
            title:'通知公告',
            rownumbers: true,
            pagination: true,
            fit: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {
                        field: 'C_TITLE',
                        title: '标题',
                        width: 400
                    },
                    {
                        field: 'T_PUBLISH',
                        title: '发布时间',
                        width: 200
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
                settings.gridSettings.datagrid('reload');
            }, editClick: function () {
                
            }
        }
    };

    $.extend(self, model, settings, events);
}