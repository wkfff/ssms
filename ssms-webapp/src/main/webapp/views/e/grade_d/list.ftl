<#import "/layout/_list.ftl" as layout/> 
<#assign script>
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

<div class="">
    <div class="datagrid" id="dg">
        <div class="navbar navbar-default navbar-fixed-top">
            <br> <br>
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
			<td id="T_START">自评开始时间</td>
			<td id="T_END">自评结束时间</td>
			<td id="N_GET">得分项</td>
			<td id="N_DEDUCT">扣分项</td>
			<td id="N_LACK">缺项</td>
			<td id="P_STATE">状态</td>
			<td id="S_STATE">状态</td>
		<tr></thead><tbody/></table>


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
