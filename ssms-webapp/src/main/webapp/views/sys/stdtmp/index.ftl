<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
    ko.applyBindings(new viewModel({template: ${R_SID}}));
</script>
</#assign>
<@layout.doLayout script=script>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', title:'功能导航', split:true" style="width: 200px">
        <ul data-bind="tree: tree"></ul>
    </div>
    <div data-options="region:'center', border:false">
        <div class="easyui-tabs" fit="true">
            <div title="概要" iconCls="icon-details">
                <div class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: rec.saveClick">保存</a>
                    <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" data-bind="click: rec.removeClick">删除</a>
                </div>
                <form class="form" method="post" data-bind="form: rec, formValue: rec.data">
                    <p class="ue-clear">
                        <label>名称：</label>
                        <span class="control">
                            <input class="easyui-textbox" type="text" name="C_NAME"/>
                        </span>
                    </p>

                    <p class="ue-clear">
                        <label>描述：</label>
                        <span class="control">
                            <input class="easyui-textbox" type="text" name="C_DESC" multiline="true"/>
                        </span>
                    </p>
                    <input type="hidden" name="SID"/>
                    <input type="hidden" name="R_TEMPLATE" value="${R_SID}"/>
                </form>
            </div>
            <div title="子分类" iconCls="icon-table" data-bind="with: items">
                <div id="items_toolbar" class="z-toolbar">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-bind="click:refreshClick">刷新</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-bind="click: addClick">添加</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-bind="click: editClick">编辑</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-bind="click: deleteClick">删除</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-bind="click:saveClick">保存</a>
                </div>
                <table data-bind="datagrid: $data">
                    <thead>
                    <tr>
                        <th field="C_NAME" width="100" editor="{ type:'validatebox', options:{ required:true, validType:['length[0, 300]'] }}">名称</th>
                        <th field="C_DESC" width="200" editor="{ type:'textarea', options:{ validType:['length[0, 1000]'] }}">描述</th>
                        <th field="N_INDEX" width="80" align="center" editor="{ type:'numberbox', options: {required:true} }">排序</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div title="文件" iconCls="icon-table" style="padding: 2px">
                <iframe id="iframe" frameborder="0" width="100%" height="99%" data-bind="attr: {src: fileUrl}"></iframe>
            </div>
        </div>
    </div>
</div>
</@>