/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：pagelinks.js
 * 创建时间：2015-06-04
 * 创建用户：张铮彬
 */

(function () {
    ko.dataPager = {
        // Defines a view model class
        viewModel: function (configuration) {
            var self = this;
            this.data = ko.mapping.fromJS(configuration.data);
            this.total = ko.observable(configuration.total);
            this.currentPageIndex = ko.observable(1);
            this.pageSize = configuration.pageSize || 5;

            this.currentPageIndex.subscribe(function (newValue) {
                utils.messager.showProgress();
                var params = ko.utils.extend({page: newValue, rows: self.pageSize}, configuration.params);
                $.post(configuration.src, params, function (result) {
                    self.total(result.total);
                    self.data.removeAll();
                    for (var i = 0; i < result.rows.length; i++) {
                        self.data.push(ko.mapping.fromJS(result.rows[i]));
                    }
                    utils.messager.closeProgress();
                });
            });

            this.maxPageIndex = ko.computed(function () {
                return Math.ceil(this.total() / this.pageSize);
            }, this);
        }
    };

    // Templates used to render the grid
    var templateEngine = new ko.nativeTemplateEngine();

    templateEngine.addTemplate = function (templateName, templateMarkup) {
        document.write("<script type='text/html' id='" + templateName + "'>" + templateMarkup + "<" + "/script>");
    };

    templateEngine.addTemplate("ko_dataPager_pageLinks", "\
                    <div class=\"ko-dataPage-pageLinks\">\
                        <span>分页:</span>\
                        <!-- ko foreach: ko.utils.range(1, maxPageIndex) -->\
                               <a href=\"#\" data-bind=\"text: $data, click: function() { $root.currentPageIndex($data) }, css: { selected: $data == $root.currentPageIndex() }\">\
                            </a>\
                        <!-- /ko -->\
                    </div>");

    // The "dataPager" binding
    ko.bindingHandlers.dataPager = {
        init: function () {
            return {'controlsDescendantBindings': true};
        },
        // This method is called to initialize the node, and will also be called again if you change what the grid is bound to
        update: function (element, viewModelAccessor, allBindings) {
            var viewModel = viewModelAccessor();

            // Empty the element
            while (element.firstChild)
                ko.removeNode(element.firstChild);

            // Allow the default templates to be overridden
            var pageLinksTemplateName = allBindings.get('dataPagerTemplate') || "ko_dataPager_pageLinks";

            // Render the page links
            var pageLinksContainer = element.appendChild(document.createElement("DIV"));
            ko.renderTemplate(pageLinksTemplateName, viewModel, {templateEngine: templateEngine}, pageLinksContainer, "replaceNode");
        }
    };
})();