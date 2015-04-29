<#import "/layout/_list.ftl" as layout>
<#assign script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.edatagrid.js"></script>
<script type="text/javascript">
    function doLoad(sid) {
        $.post('rec.json', {sid: sid}, function (result) {
            $('#details').form("load", result);
        });
        $('#dg').datagrid({
            url: 'list.json',
            queryParams: {R_SID: sid}
        });
    }

    function doSave() {
        var sid;
        $('#details').form('submit', {
            url: "save.do",
            onSubmit: function (param) {
                sid = param.SID;
                return $(this).form('validate');
            },
            success: function () {
                $.messager.alert('提示信息', '保存成功!');
                doLoad(sid)
            }
        });
    }

    function doDelete() {
        $('#details').form('submit', {
            url: "del.do",
            success: function () {
                $.messager.alert('提示信息', '删除成功!');
                $('#nav').tree('reload');
            }
        });
    }

    function doNew() {
        $('#dlg').dialog('open').dialog('setTitle', '添加');
        var node = $('#nav').tree("getSelected");
        $('#dlg_fm').form('clear').form('load', {R_SID: node.id});
    }

    function doSaveDlg() {
        $('#dlg_fm').form('submit', {
            url: 'save.do',
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function () {
                $('#dlg').dialog('close');
                $('#dg').datagrid('reload');
                $('#nav').tree('reload');
            }
        });
    }

    function doEdit() {
        $('#dlg').dialog('open').dialog('setTitle', '编辑');
        var node = $('#dg').datagrid("getSelected");
        $('#dlg_fm').form('clear').form('load', node);
    }

    function doDgDelete() {
        var node = $('#dg').datagrid("getSelected");
        $.post('del.do', {sid: node.SID}, function () {
            $.messager.alert('提示信息', '删除成功!');
            $('#nav').tree('reload');
        });
    }

    $(function () {
        $('#nav').tree({
            url: 'tree.json',
            onSelect: function (node) {
                doLoad(node.id);
            },
            onLoadSuccess: function () {
                var $this = $(this);
                $this.tree('select', $this.tree('getRoot').target)
            }
        });

        $('#dg').edatagrid({
            idField: 'SID',
            iconCls: 'icon-star',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            striped: true,
            toolbar: '#toolbar',
            border: false,
            autoSave: true,
            updateUrl:'save.do',
            columns: [
                [
                    {field: 'C_NAME', title: '导航名称', width: 100},
                    {field: 'C_URL', title: 'URL', width: 100},
                    {field: 'C_DESC', title: '描述', width: 200},
                    {field: 'C_ICON', title: '图标', width: 200, align: 'center', editor: 'textbox'},
                    {field: 'N_INDEX', title: '排序号', width: 200, align: 'center', editor: 'numberbox'}
                ]
            ]
        });
    });
</script>
</#assign>
<@layout.doLayout script=script>
<div id="navlyt" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west'" style="width: 200px">
        <ul id="nav"></ul>
    </div>
    <div data-options="region:'center'">
        <@layout.group title="概要" icon="star">
            <@layout.toolbar>
                <@layout.button id="" title="保存" icon="save" click="doSave()"/>
                <@layout.button id="" title="删除" icon="remove" click="doDelete()"/>
            </@>
            <@layout.form id="details">
                <table>
                    <tr><@layout.td_textbox name="C_NAME" title="导航名称" must=true/></tr>
                    <tr><@layout.td_textbox name="C_ICON" title="图标"/></tr>
                    <tr><@layout.td_textarea name="C_URL" title="URL"/></tr>
                    <tr><@layout.td_textarea name="C_DESC" title="描述"/></tr>
                </table>
                <@layout.hidden name="SID"/>
            </@>
        </@>
        <table title="子节点列表" id="dg"></table>
        <@layout.toolbar id="toolbar">
            <@layout.button id="" title="新增" icon="add" click="doNew()"/>
            <@layout.button id="" title="编辑" icon="edit" click="doEdit()"/>
            <@layout.button id="" title="删除" icon="remove" click="doDgDelete()"/>
        </@>


        <@layout.dialog id="dlg" okClick="doSaveDlg()">
            <@layout.form id="dlg_fm">
                <table>
                    <tr><@layout.td_textbox name="C_NAME" title="导航名称" must=true/></tr>
                    <tr><@layout.td_textbox name="C_ICON" title="图标"/></tr>
                    <tr><@layout.td_textarea name="C_URL" title="URL"/></tr>
                    <tr><@layout.td_textarea name="C_DESC" title="描述"/></tr>
                </table>
                <@layout.hidden name="R_SID"/>
                <@layout.hidden name="SID"/>
            </@>
        </@>
    </div>
</div>
</@>