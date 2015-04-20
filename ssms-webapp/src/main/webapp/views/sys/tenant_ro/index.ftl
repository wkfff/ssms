<#import "/layout/_list.ftl" as layout/> <#assign script>
<script type="text/javascript">
    var setting = {
        gridId : "dg",
        dataUrl : "list.json",
        delUrl : "dels.json",
        addUrl : "rec.html",
        openUrl : "rec.html"
    };

    $(document).ready(function() {
        var dg = new datagrid(setting);
        dg.doQuery();
    });
</script>
</#assign> <@layout.doLayout script>
<!--导航栏-->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="">
            <!--container-->
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">管理中心 ></a></li>
                    <li class="active"><a href="#">评审机构管理 ></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="">
    <div class="datagrid" id="dg">
        <div class="navbar navbar-default navbar-fixed-top">
            <br> <br>
            <div class="navbar-inner">
                <form class="navbar-form pull-left form-search">
                    &nbsp;&nbsp; <label class="checkbox">单位名称</label>
                    <input type="text" name="C_NAME"
                        style="width: 120px;" query="C_NAME like ?" />
                    <label class="checkbox">地址</label> <input
                        type="text" name="C_ADDR" style="width: 120px;"
                        query="C_ADDR = ?" /> <input type="button"
                        name="btn_query" class="btn" value="查询">
                    <input type="button" name="btn_add" class="btn"
                        value="新增"> <input type="button"
                        name="btn_open" class="btn" value="打开">
                    <input type="button" name="btn_del" class="btn"
                        value="删除"></label>
                </form>
            </div>
        </div>

        <table>
            <thead>
                <tr>
                    <td id="SID" style="display: none;"></td>
                    <td id="C_NAME">单位名称</td>
                    <td id="S_LEVEL">评审专业级别</td>
                    <td id="C_NUMBER">营业执照注册号</td>
                    <td id="C_ADDR">地址</td>
                <tr>
            </thead>
            <tbody />
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
                    class="input-mini navbar-text" value="1" /> <input
                    type="button" name="btn_next" value="下页" class="btn">
                    <input type="button" name="btn_last" value="末页"
                    class="btn">
                </label>
            </form>
        </div>
    </div>
</div>

</@layout.doLayout>
