<#import "/layout/_list.ftl" as layout>
<#assign script>
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
            url: "save.json",
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
            url: "del.json",
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
            url: 'save.json',
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
        $.post('del.json', {sid: node.SID}, function () {
            $.messager.alert('提示信息', '删除成功!');
            $('#nav').tree('reload');
        });
    }

    $(function () {
        var panel = $('#navlyt').layout('panel', 'center').panel();

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

        $('#dg').datagrid({
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            striped: true,
            toolbar: '#toolbar',
            columns: [
                [
                    {field: 'C_NAME', title: '导航名称', width: 100},
                    {field: 'C_URL', title: 'URL', width: 100},
                    {field: 'C_DESC', title: '描述', width: 200}
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
        <div class="easyui-panel" title="概要">
            <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-save'" onclick="doSave()">保存</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-save'" onclick="doDelete()">删除</a>

            <form id="details" method="post">
                <table>
                    <tr>
                        <td><label for="C_NAME">导航名称</label></td>
                        <td>
                            <input id="C_NAME" name="C_NAME" type="text" class="easyui-validatebox" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="C_URL">URL</label></td>
                        <td><textarea id="C_URL" name="C_URL"></textarea></td>
                    </tr>
                    <tr>
                        <td><label for="C_DESC">描述</label></td>
                        <td><textarea id="C_DESC" name="C_DESC"></textarea></td>
                    </tr>
                </table>
                <input type="hidden" name="SID"/>
            </form>
        </div>
        <div class="easyui-panel" title="子节点列表">
            <table id="dg"></table>
            <div id="toolbar">
                <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-add', plain: true" onclick="doNew()">新增</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-edit', plain: true" onclick="doEdit()">编辑</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-remove', plain: true" onclick="doDgDelete()">删除</a>
            </div>
        </div>

        <div id="dlg" class="easyui-dialog" data-options="closed:true, modal:true, buttons:'#dlg-buttons'">
            <form id="dlg_fm" method="post">
                <fieldset>
                    <legend>基本信息</legend>
                    <table>
                        <tr>
                            <td><label for="C_NAME">导航名称</label></td>
                            <td><input id="C_NAME" name="C_NAME" type="text" class="easyui-validatebox"
                                       data-options="required:true"/></td>
                        </tr>
                        <tr>
                            <td><label for="C_URL">URL</label></td>
                            <td><textarea id="C_URL" name="C_URL"></textarea></td>
                        </tr>
                        <tr>
                            <td><label for="C_DESC">描述</label></td>
                            <td><textarea id="C_DESC" name="C_DESC"></textarea></td>
                        </tr>
                    </table>
                </fieldset>
                <input type="hidden" name="R_SID"/>
                <input type="hidden" name="SID"/>
            </form>
        </div>
        <div id="dlg-buttons">
            <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-ok'" onclick="doSaveDlg()">保存</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-cancel'" onclick="$('#dlg').dialog('close')">取消</a>
        </div>
    </div>
</div>
</@>