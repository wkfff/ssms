<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
        gridId: "dg",
        dataUrl: "list.json",
        delUrl: "del.json",
        addUrl: "rec.html",
        openUrl: "rec.html"
    };
    
    $(document).ready(function () {
        var dg = new datagrid(setting);
        dg.doQuery();       
    });
</script>
</#assign>
<@layout.doLayout script>
<#list _ERROR_?keys as map>${map} ${_ERROR_[map]}</#list>
<div class="datagrid" id="dg">
    <div class="toolbar">
    	<!--
    	模糊查询
        <select class="filter_field">
            <option value="C_NAME">企业名称</option>
            <option value="C_ADDR">地址</option>
        </select>
        <input type="text" class="filter_value"/>
        -->
       	 企业名称<input type="text" name="C_NAME" style="width:60px;" query="C_NAME like ?"/>
       	 省份<input type="text" name="S_PROVINCE" style="width:60px;" query="S_PROVINCE = ?"/>
       	 地市<input type="text" name="S_CITY" style="width:60px;" query="S_CITY = ?"/>
       	 县市<input type="text" name="S_COUNTY" style="width:60px;" query="S_COUNTY = ?"/>
        <input type="button" name="btn_query" value="查询">
        
        <input type="button" name="btn_add" value="新增">
        <input type="button" name="btn_open" value="打开">
        <input type="button" name="btn_del" value="删除">
    </div>
    <table>
        <thead>
        <tr>
            <td class="cell-chk"><input class="chk" type="checkbox"/></td>
            <td id="SID" style="display:none;"></td>
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
    <div class="paging">
    	每页显示
    	<select name="_pageSize">
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
            <option value="50">50</option>
        </select>
                共<label name="_recCount"></label>条,
        <label name="_pageCount"></label>页
        <input type="button" name="btn_first" value="首页">
        <input type="button" name="btn_prev" value="上页">
        <input type="text" name="_pageIndex" class="pageIndex" value="1"/>
        <input type="button" name="btn_next" value="下页">
        <input type="button" name="btn_last" value="末页">
    </div>
</div>
</@layout.doLayout>