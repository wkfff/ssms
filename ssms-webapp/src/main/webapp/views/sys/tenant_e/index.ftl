<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
        gridId: "dg",
        dataUrl: "list.json",
        delUrl: "dels.json",
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
<!--导航栏-->
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active">
                <a href="#">首页 ></a>
              </li>
              <li class="active">
                <a href="#">管理中心 ></a>
              </li>
              <li class="active">
                <a href="#">企业信息管理 ></a>
              </li>            
            </ul>
          </div>
        </div>
      </div>
</div>

<div class="container">
<div class="datagrid" id="dg">
    <div class="form-actions">
    	<!--
    	模糊查询
        <select class="filter_field">
            <option value="C_NAME">企业名称</option>
            <option value="C_ADDR">地址</option>
        </select>
        <input type="text" class="filter_value"/>
        -->
       	 企业名称<input type="text" name="C_NAME" style="width:60px;" query="C_NAME like ?" class="search-query"/>
       	 省份<input type="text" name="S_PROVINCE" style="width:60px;" query="S_PROVINCE = ?" class="search-query"/>
       	 地市<input type="text" name="S_CITY" style="width:60px;" query="S_CITY = ?" class="search-query"/>
       	 县市<input type="text" name="S_COUNTY" style="width:60px;" query="S_COUNTY = ?" class="search-query"/>
        <input type="button" name="btn_query" class="btn" value="查询">
        
        <input type="button" name="btn_add" class="btn" value="新增">
        <input type="button" name="btn_open" class="btn" value="打开">
        <input type="button" name="btn_del" class="btn" value="删除">
    </div>
    <table>
        <thead>
        <tr>
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
    <!--系统大文本表--><table><thead><tr><td id="R_TABLE">对应表</td><td id="R_SID">对应记录编号</td><td id="R_FIELD">对应字段</td><td id="C_CONTENT">内容</td><td id="C_TANENT">所属租户</td><tr></thead><tbody/></table>
    <div class="paging">
    	每页显示
    	<select name="_pageSize" class="input-mini">
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
            <option value="50">50</option>
        </select>
                共<span name="_recCount"></span>条,<span name="_pageCount"></span>页
        <input type="button" name="btn_first" value="首页" class="btn">
        <input type="button" name="btn_prev" value="上页" class="btn">
        <input type="text" name="_pageIndex" class="pageIndex input-mini" value="1"/>
        <input type="button" name="btn_next" value="下页" class="btn">
        <input type="button" name="btn_last" value="末页" class="btn">
    </div>
</div>
</div>
</@layout.doLayout>