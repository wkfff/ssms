<#import "/layout/_mix.ftl" as layout/> 
<#assign script>
<script type="text/javascript">
    $(document).ready(function() {
        var dg_complete = new datagrid({
            gridId : "dg_complete",
            dataUrl : "list.json",
            delUrl : "dels.json",
            addUrl : "rec.html",
            openUrl : "rec.html"
        });
        dg_complete.doQuery();
        
        var dg_draft = new datagrid({
            gridId : "dg_draft",
            dataUrl : "list.json",
            delUrl : "dels.json",
            addUrl : "rec.html",
            openUrl : "rec.html"
        });
        dg_draft.doQuery();
        
        $form.init({
            dataUrl: "rec.json"
        });
        
        $("input[name='btn_create']").click(function() {
            $win.navigate('rec.html');
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<!--导航栏-->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="">
            <!--container-->
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">在线自评 ></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@layout.toolbar class="navbar-fixed-top" outer="<br><br>">
    <input type="button" name="btn_create" class="btn" value="开始新的自评">
</@layout.toolbar>

<div class="container">
    <!--已完成-->
    <@layout.group title="自评历史">
    <div class="datagrid" id="dg_complete">
        <div class="navbar navbar-default">
            <div class="navbar-inner">
            <form class="navbar-form pull-left form-search">
                    &nbsp;&nbsp; <label class="checkbox">自评日期从</label>
                    <input type="text" class="ui-date input-small" name="T_START" op="d" />
                    <label class="checkbox">至</label>
                    <input type="text" class="ui-date input-small" name="T_END" op="d" />
                    <input type="button" name="btn_query" class="btn"value="查询">
                    </label>
                </form>
            </div>
        </div>

        <table><thead><tr>
            <td id="SID" style="display:none;"></td>
            <td id="C_NAME">标题</td>
            <td id="T_START">自评开始时间</td>
            <td id="T_END">自评结束时间</td>
            <td id="N_GET">得分项</td>
            <td id="N_DEDUCT">扣分项</td>
            <td id="N_LACK">缺项</td>
            <td class="tdOperator">
                <a href="rec.html" class="rowOperator">查看</a>
            </td>
        <tr></thead><tbody/></table>
        <@layout.pagingbar />
    </div>
    </@layout.group>
    
    <!--草稿-->
    <@layout.group title="草稿">
    <div class="datagrid" id="dg_draft">
        <div class="navbar navbar-default">
            <div class="navbar-inner">
            <form class="navbar-form pull-left form-search">
                    &nbsp;&nbsp; <label class="checkbox">自评日期从</label>
                    <input type="text" class="ui-date input-small" name="T_START" op="d" />
                    <label class="checkbox">至</label>
                    <input type="text" class="ui-date input-small" name="T_END" op="d" />
                    <input type="button" name="btn_query" class="btn"value="查询">
                    </label>
                </form>
            </div>
        </div>

        <table><thead><tr>
            <td id="SID" style="display:none;"></td>
            <td id="C_NAME">标题</td>
            <td id="T_START">自评开始时间</td>
            <td id="T_END">自评结束时间</td>
            <td id="N_GET">得分项</td>
            <td id="N_DEDUCT">扣分项</td>
            <td id="N_LACK">缺项</td>
            <td class="tdOperator">
                <a href="rec.html" class="rowOperator">编辑</a>
                <a href="dels.json" class="rowOperator">删除</a>
            </td>
        <tr></thead><tbody/></table>
        <@layout.pagingbar />
    </div>
    </@layout.group>
</div>

</@layout.doLayout>
