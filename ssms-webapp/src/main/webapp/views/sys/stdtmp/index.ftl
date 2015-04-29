<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var page = {
        sid: null,
        init: function () {
            tree.init();
            listChildren.init();
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
            fit: true,
            updateUrl: 'save.do',
            onBeforeSave: function (index) {
                listChildren.el.edatagrid('endEdit', index);
                var data = listChildren.getSelect();
                $.post('save.do', data, function(){
                    // TODO: 添加处理
                });
                return false;
            },
            columns: [
                [
                    {field: 'C_NAME', title: '名称', width: '30%', editor: 'textbox'},
                    {field: 'C_DESC', title: '描述', width: '30%', editor: 'textarea'},
                    {field: 'N_INDEX', title: '排序号', width: '10%', align: 'center', editor: 'numberbox'}
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
        getSelect: function(){
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
        <div class="easyui-tabs" data-options="fit:true">
            <div title="概要">
                <@layout.toolbar>
                    <@layout.button title="保存" icon="save" click="recForm.save()"/>
                    <@layout.button title="删除" icon="cancel"/>
                </@>
                <@layout.form id="rec_fm">
                    <table>
                        <tr><@layout.td_textbox title="名称" name="C_NAME" must=true/></tr>
                        <tr><@layout.td_textarea title="描述" name="C_DESC"/></tr>
                    </table>
                    <@layout.hidden 'SID'/>
                </@>
            </div>
            <div title="子分类">
                <@layout.toolbar id="list_children_tb">
                    <@layout.button title="新增" icon="new" click="listChildren.addRow()" />
                </@layout.toolbar>
                <table id="list_children"></table>
            </div>
        </div>
    </div>
</div>
</@>