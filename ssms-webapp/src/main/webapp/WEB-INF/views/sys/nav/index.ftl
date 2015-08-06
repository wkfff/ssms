<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>${_TITLE_!"安全生产标准化管理系统"}</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <style type="text/css">
         .btn{
             position:relative;
             width: 351px;
             height: 130px; 
         }
         li {
            float: right;
            width: 500px;
            padding: 20px;
         }
         ul {
            margin-top:50px;
         }
    </style>
</head>
<body>
    <div class="contents">
        <ul data-bind="template: {name: 'treeNodeTemplate', foreach: treesNode}"></ul>
        <script type="text/html" id="treeNodeTemplate">
            <li>
                <input type="button" class="btn" data-bind="value: name,click :$root.chosePort"/>
            </li>
        </script>
    </div>
</body>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript">
        function ViewModel(root) {
            function treeModel(root) {
                var self = this;
                this.id = ko.observable(root.id);
                this.name = ko.observable(root.name);
            };
            this.chosePort = function(model){
                window.location.href="${BASE_PATH}/tree?parentId="+model.id;
            };
            this.treesNode = ko.observableArray(root);
        }
        $(function () {
            var root = ${json(root)};
            ko.applyBindings(new ViewModel(root));
        });
    </script>
</html>


