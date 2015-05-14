/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：index.js
 * 创建时间：2015-05-06
 * 创建用户：张铮彬
 */
function ViewModel(templateId) {
    var self = this;

    var model = {
        selectedNode: ko.observable(),
        fileUrl: ko.pureComputed(function () {
            var node = model.selectedNode();
            if (node == null) return;
            if (node.id.startsWith('F')) {
                return '/e/stdtmp_file/index.html?R_SID={0}'.format(node.id);
            }
        })
    };
    model.selectedNode.subscribe(function (newValue) {
        
    });

    var settings = {
        treeSettings: {
            url: 'tree.json',
            queryParams: {template: templateId}
        }
    };

    $.extend(self, model, settings);
}

