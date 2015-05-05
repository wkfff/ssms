<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var list = {
        el: $('#dg'),
        init: function () {
            this.el.datagrid({
                url: 'list.json',
                idField: 'SID',
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                striped: true,
                toolbar: '#toolbar',
                border: false,
                fit: true,
                columns: [
                    [
                        {field: 'C_CODE', title: '专业代码', width: 100},
                        {field: 'C_VALUE', title: '专业名称', width: 100},
                        {
                            field: "_action",
                            title: "操作",
                            width: 80,
                            align: 'center',
                            formatter: function (value, row) {
                                return "<a href='#' onclick='editorSTDTMP(\"" + row.C_CODE + "\")'>编辑模板</a>";
                            }
                        }
                    ]
                ]
            });
        }
    };
    $(list.init);
</script>
</#assign>
<@layout.doLayout script>
<table id="dg"></table>
</@>