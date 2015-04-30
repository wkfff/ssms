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
    });

    function doSearch() {
        $('#dg').datagrid('load', {
            C_CODE: $('#code').val(),
            C_VALUE: $('#value').val()
        });
    }

    function doClear() {
        $('#code').textbox('setValue', null);
        $('#value').textbox('setValue', null);
        doSearch();
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

    function editorSTDTMP(value) {
        window.location.href = "/sys/stdtmp/index.html?profession=" + value;
    }
</script>
</#assign>
<@layout.doLayout script>
    <@layout.group title="专业管理" border=true fit=true>
    <table id="dg"></table>
        <@layout.toolbar id="toolbar">
        <div>
            <table>
                <tr>
                    <td>专业代码</td>
                    <td><input class="easyui-textbox" id="code" type="text"/></td>
                    <td>专业名称</td>
                    <td><input class="easyui-textbox" id="value" type="text"/></td>
                    <td><@layout.button title="查询" icon="search" click="doSearch()"/><@layout.button title="重置" desc="清空查询条件" icon="clear" click="doClear()"/></td>
                </tr>
            </table>
        </div>
        <div class="hr" style="height: 0; border-top: 1px solid #CCCCCC; margin-top: 5px; margin-bottom: 5px"></div>
        <div>
            <@layout.button title="新增" icon="add" click="doNew()"/>
            <@layout.button title="编辑" icon="edit" click="doEdit()"/>
            <@layout.button title="删除" icon="remove" click="doDelete()"/>
        </div>

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
    </@>
</@layout.doLayout>