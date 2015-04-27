<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#dg').datagrid({
            url: 'list.json',
            idField: 'SID',
            iconCls: 'icon-star',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            striped: true,
            toolbar: '#toolbar',
            border: false,
            columns: [
                [
                    {field: 'C_CODE', title: '专业代码', width: 100},
                    {field: 'C_VALUE', title: '专业名称', width: 100}
                ]
            ]
        });
    });

    function doSearch() {
        $('#dg').datagrid('load', {
            C_CODE: $('#C_CODE').val(),
            C_VALUE: $('#C_VALUE').val()
        });
    }

    function doNew() {
        $('#dlg').dialog('setTitle', '新增').dialog('open');
        $('#fm').form('clear');
    }

    function doEdit() {
        $('#dlg').dialog('setTitle', '编辑').dialog('open');
        var node = $('#dg').datagrid("getSelected");
        $('#fm').form('clear').form('load', node);
    }

    function doDelete() {
        var item = $('#dg').datagrid("getSelected");
        $.post('del.do', {sid: item.SID, C_CODE: item.C_CODE}, function () {
            $.messager.alert('提示信息', '删除成功!');
            $('#dg').datagrid('reload');
        });
    }

    function doSave() {
        // TODO: 用jquery.form进行处理，easyui的form没办法处理error。
        $('#fm').form('submit', {
            url: 'save.do',
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function () {
                $.messager.alert('提示信息', '保存成功!');
                $('#dlg').dialog('close');
                $('#dg').datagrid('reload');
            }
        });
    }
</script>
</#assign>
<@layout.doLayout script>
<table id="dg"></table>
    <@layout.toolbar id="toolbar">
        <@layout.textbox name='C_CODE' title="专业代码" />
        <@layout.textbox name='C_VALUE' title="专业名称" />
        <@layout.button title="查询" icon="search" click="doSearch()"/>
        <@layout.button title="新增" icon="add" click="doNew()"/>
        <@layout.button title="编辑" icon="edit" click="doEdit()"/>
        <@layout.button title="删除" icon="remove" click="doDelete()"/>
    </@>

    <@layout.dialog id="dlg" okClick="doSave()">
        <@layout.form id="fm">
        <table>
            <tr><@layout.td_textbox name="C_CODE" title="专业代码" must=true/></tr>
            <tr><@layout.td_textbox name="C_VALUE" title="专业名称" must=true/></tr>
        </table>
            <@layout.hidden name="SID"/>
        </@>
    </@>
</@layout.doLayout>