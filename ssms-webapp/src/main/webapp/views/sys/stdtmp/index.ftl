<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var page = {
        sid: null,
        init: function () {
            tree.init();
            listChildren.init();
            listFile.init();
        }
    };
    var tree = {
        el: $('#nav'),
        selectNode: null,
        config: {
            url: 'tree.json',
            queryParams: {
                profession: '${profession}'
            },
            onSelect: function (node) {
                page.sid = node.id;
                tree.selectNode = node;
                recForm.load();
                listChildren.load();
                listFile.load();
            },
            onLoadSuccess: function () {
                var $this = $(this);
                var node = tree.selectNode;
                if (node) node = $this.tree('find', node.id);
                if (!node) node = $this.tree('getRoot');

                $this.tree('select', node.target)
            }
        },
        init: function () {
            this.el.tree(tree.config);
        },
        reload: function () {
            this.el.tree('reload');
        }
    };
    var recForm = {
        el: $('#rec_fm'),
        config: {
            recUrl: 'rec.json',
            saveUrl: 'save.do'
        },
        load: function () {
            var $this = this;
            $.post(this.config.recUrl, {sid: page.sid}, function (result) {
                $this.loadData(result);
            });
        },
        loadData: function (data) {
            this.el.form('load', data);
        },
        save: function () {
            var $el = this.el;
            var url = this.config.saveUrl;
            $el.form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function () {
                    $.messager.alert('提示信息', '保存成功!');
                    tree.reload();
                }
            });
        },
        del: function () {
            $.post('del.do', {sid: page.sid}, function () {
                tree.reload();
            })
        }
    };
    var listChildren = {
        el: $('#list_children'),
        config: {
            idField: 'SID',
            iconCls: 'icon-star',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            striped: true,
            toolbar: '#list_children_tb',
            border: false,
            autoSave: true,
            updateUrl: 'save.do',
            onBeforeSave: function (index) {
                listChildren.el.edatagrid('endEdit', index);
                var data = listChildren.getSelect();
                data.R_SID = page.sid;
                data.P_PROFESSION = tree.selectNode.attributes.P_PROFESSION;
                data.S_PROFESSION = tree.selectNode.attributes.S_PROFESSION;
                // 删除isNewRecord
                data.isNewRecord = undefined;
                $.post('save.do', data, function () {
                    tree.reload();
                });
                return false;
            },
            columns: [
                [
                    {field: 'C_NAME', title: '名称', width: '30%', editor: 'textbox'},
                    {field: 'C_DESC', title: '描述', width: '50%', editor: 'textarea'},
                    {field: 'N_INDEX', title: '排序号', width: '10%', align: 'center', editor: 'numberbox'},
                    {
                        field: '_action',
                        title: '操作',
                        width: '60',
                        align: 'center',
                        formatter: function (value, row) {
                            if (row.SID) return '<a href="javascript:;" onclick="listChildren.delRow({0})">删除</a>'.format(row.SID);
                        }
                    }
                ]
            ]
        },
        init: function () {
            this.el.edatagrid(this.config);
        },
        load: function () {
            this.el.edatagrid({
                url: 'list.json',
                queryParams: {R_SID: page.sid}
            });
        },
        addRow: function () {
            this.el.edatagrid('addRow');
        },
        delRow: function (id) {
            $.post('del.do', {sid: id}, function () {
                tree.reload();
            })
        },
        getSelect: function () {
            return this.el.edatagrid('getSelected');
        }
    };
    var listFile = {
        el: $('#list_file'),
        config: {
            idField: 'SID',
            iconCls: 'icon-star',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            striped: true,
            toolbar: '#list_file_tb',
            border: false,
            columns: [
                [
                    {field: 'C_NAME', title: '名称', width: '30%', editor: 'textbox'},
                    {field: 'C_DESC', title: '描述', width: '50%', editor: 'textarea'},
                    {field: 'N_INDEX', title: '排序号', width: '10%', align: 'center', editor: 'numberbox'},
                    {
                        field: '_action',
                        title: '操作',
                        width: '60',
                        align: 'center',
                        formatter: function (value, row) {
                            return '<a href="javascript:;" onclick="listFile.editRow({0})">编辑</a> <a href="javascript:;" onclick="listFile.delRow({0})">删除</a>'.format(row.SID);
                        }
                    }
                ]
            ]
        },
        init: function () {
            this.el.edatagrid(this.config);
        },
        load: function () {
            this.el.edatagrid({
                url: '/sys/stdtmp_file/list.json',
                queryParams: {R_SID: page.sid}
            });
        },
        reload: function () {
            this.el.datagrid('reload');
        },
        addRow: function () {
            window.location.href = '/sys/stdtmp_file/rec.html?pid=' + page.sid;
        },
        editRow: function (sid) {
            window.location.href = '/sys/stdtmp_file/rec.html?sid=' + sid;
        },
        delRow: function (id) {
            $.post('/sys/stdtmp_file/del.do', {sid: id}, function () {
                listFile.reload();
            })
        },
        getSelect: function () {
            return this.el.edatagrid('getSelected');
        }
    };
    $(page.init);
</script>
</#assign>
<@layout.doLayout script=script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'功能导航', split:true" style="width: 200px">
        <ul id="nav"></ul>
    </div>
    <div data-options="region:'center', header: '#bread'" style="overflow: hidden">
        <div class="easyui-panel" title="概要">
            <@layout.toolbar>
                <@layout.button title="保存" icon="save" click="recForm.save()"/>
                <@layout.button title="删除" icon="cancel" click="recForm.delRow()"/>
            </@>
            <@layout.form id="rec_fm">
                <table>
                    <tr><@layout.td_textbox title="名称" name="C_NAME" must=true/></tr>
                    <tr><@layout.td_textarea title="描述" name="C_DESC"/></tr>
                </table>
                <@layout.hidden name='SID'/>
            </@>
        </div>
        <div class="easyui-panel" title="子分类列表">
            <@layout.toolbar id="list_children_tb">
                <@layout.button title="新增" icon="new" click="listChildren.addRow()" />
            </@layout.toolbar>
            <table id="list_children"></table>
        </div>
        <div class="easyui-panel" title="文件列表">
            <@layout.toolbar id="list_file_tb">
                <@layout.button title="新增" icon="add" click="listFile.addRow()" />
                <@layout.button title="编辑" icon="edit" click="listFile.editRow()" />
            </@>
            <table id="list_file"></table>
        </div>
    </div>
</div>
</@>