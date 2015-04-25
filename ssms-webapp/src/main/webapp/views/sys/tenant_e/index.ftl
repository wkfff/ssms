<#--<#import "/layout/_list.ftl" as layout/>-->
<#import '/s/home/settings.ftl' as layout/>

<#assign script>
<script type="text/javascript">
    var url = "";
    function doSearch() {
        $('#dg').datagrid('load', {
            C_NAME: $('#C_NAME').val()
        });
    }

    function doNew() {
        $('#dlg').dialog('open').dialog('setTitle', '添加');
        $('#fm').form('clear');
    }

    function doSave() {
        $('#fm').form('submit', {
            url: 'save.json',
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.errorMsg) {

                } else {
                    $('#dlg').dialog('close');
                    $('#dg').datagrid('reload');
                }
            }
        });
    }

    $(function () {
        $('#dg').datagrid({
            url: 'list.json',
            idField: 'SID',
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            fitColumns: true,
            striped: true,
            toolbar: '#toolbar',
            columns: [[
                {field: 'C_NAME', title: '企业名称', width: 100},
                {field: 'S_PROVINCE', title: '省份', width: 100},
                {field: 'S_CITY', title: '地市', width: 100},
                {field: 'S_COUNTY', title: '县市', width: 100},
                {field: 'S_NATURE', title: '企业性质', width: 100},
                {field: 'T_PAY', title: '缴费日期', width: 100},
                {field: 'T_PAY_NEXT', title: '下次缴费日期', width: 100}
            ]]
        });
    });
</script>
</#assign>

<@layout.listIndex script=script>
<table id="dg"></table>
<div id="toolbar">
    <label for="C_NAME">企业名称:</label>
    <input id="C_NAME" name="C_NAME" style="line-height:26px;border:1px solid #ccc">
    <span>地区:</span>
    <input id="productid" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', plain: true" onclick="doSearch()">查询</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-add', plain: true" onclick="doNew()">新增</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-edit', plain: true" onclick="editUser()">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-remove', plain: true"
       onclick="destroyUser()">删除</a>
</div>

<div id="dlg" class="easyui-dialog" data-options="closed:true, modal:true, buttons:'#dlg-buttons'">
    <@layout.form id="fm">
        <div id="tt" class="easyui-tabs" style="width:500px;height:250px;">
            <div title="基本信息">
                <table>
                    <tr>
                        <td>企业名称</td>
                        <td>
                            <input type="text"/>
                        </td>
                        <td>企业名称</td>
                        <td>
                            <input type="text"/>
                        </td>
                    </tr>
                </table>
                <@layout.row>
                    <@layout.textbox name="C_NAME" title="企业名称" desc="企业的详细名称" />
                </@layout.row>
                <@layout.row>
                    <@layout.textbox name="S_STATE" title="企业状态" desc="企业状态" readonly=true span=6 />
                    <@layout.textbox name="S_PROVINCE" title="省份" desc="省份" span=6 />
                </@layout.row>
                <@layout.row>
                    <@layout.textbox name="S_CITY" title="地市" desc="地市" span=6 />
                    <@layout.textbox name="S_COUNTY" title="县市" desc="县市" span=6 />
                </@layout.row>
                <@layout.row>
                    <@layout.textbox name="C_NUMBER" title="注册号" desc="注册号" span=6 />
                    <@layout.textbox name="S_NATURE" title="企业性质" desc="企业性质" span=6 />
                </@layout.row>
                <@layout.row>
                    <@layout.textbox name="N_SAFETY" title="专职安全管理员" desc="专职安全管理员" span=6 />
                    <@layout.textbox name="N_EMPLOYEE" title="企业员工总数" desc="企业员工总数" span=6 />
                </@layout.row>
                <@layout.row>
                    <@layout.textbox name="N_INCOME" title="主营业务收入" desc="主营业务收入" span=6 />
                    <@layout.textbox name="N_ASSETS" title="固定资产" desc="固定资产" span=6 />
                </@layout.row>
                <@layout.row>
                    <@layout.textbox name="N_SPECIAL" title="特种作业人员数" desc="特种作业人员数" span=6 />
                    <@layout.textbox name="C_SCOPE" title="营业范围" desc="营业范围" span=6 />
                </@layout.row>
            </div>
            <div title="联系方式">
                tab2
            </div>
            <div title="企业概述">
                tab3
            </div>
            <div title="企业达标情况">
                tab3
            </div>
        </div>
    </@layout.form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-ok'" onclick="doSave()">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-cancel'"
       onclick="$('#dlg').dialog('close')">取消</a>
</div>
</@>

<#macro xx>
<!--导航栏-->
<div class="navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="">
            <!--container-->
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">管理中心 ></a></li>
                    <li class="active"><a href="#">企业信息管理 ></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="">
    <div class="datagrid" id="dg">
        <div class="navbar navbar-default">
            <div class="navbar-inner">
                <form class="navbar-form pull-left form-search">
                    &nbsp;&nbsp; <label class="checkbox">企业名称</label> <input
                        type="text" name="C_NAME" style="width: 60px;"
                        query="C_NAME like ?"/> <label
                        class="checkbox">省份</label> <input type="text"
                                                           name="S_PROVINCE" style="width: 60px;"
                                                           query="S_PROVINCE = ?"/> <label
                        class="checkbox">地市</label> <input type="text"
                                                           name="S_CITY" style="width: 60px;"
                                                           query="S_CITY = ?"/> <label class="checkbox">
                    县市</label> <input type="text" name="S_COUNTY"
                                      style="width: 60px;" query="S_COUNTY = ?"/> <input
                        type="button" name="btn_query" class="btn"
                        value="查询"> <input type="button"
                                           name="btn_add" class="btn" value="新增"> <input
                        type="button" name="btn_open" class="btn"
                        value="打开"> <input type="button"
                                           name="btn_del" class="btn" value="删除"></label>
                </form>
            </div>
        </div>

        <table>
            <thead>
            <tr>
                <td id="SID" style="display: none;"></td>
                <td id="C_NAME">企业名称</td>
                <td id="S_PROVINCE">省份</td>
                <td id="S_CITY">地市</td>
                <td id="S_COUNTY">县市</td>
                <td id="S_NATURE">企业性质</td>
                <td id="T_PAY">缴费日期</td>
                <td id="T_PAY_NEXT">下次缴费日期</td>
            <tr>
            </thead>
            <tbody/>
        </table>

        <div class="paging nav-bar">
            <form class="form-search">
                <label class="checkbox"> 每页显示 <select
                        name="_pageSize" class="input-mini">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="50">50</option>
                </select> 共<span name="_recCount" class="navbar-text"></span>条,<span
                        name="_pageCount" class="navbar-text"></span>页 <input
                        type="button" name="btn_first" value="首页"
                        class="btn navbar-btn"> <input type="button"
                                                       name="btn_prev" value="上页" class="btn"> <input
                        type="text" name="_pageIndex"
                        class="input-mini navbar-text" value="1"/> <input
                        type="button" name="btn_next" value="下页" class="btn">
                    <input type="button" name="btn_last" value="末页"
                           class="btn">
                </label>
            </form>
        </div>
    </div>
</div>
</#macro>