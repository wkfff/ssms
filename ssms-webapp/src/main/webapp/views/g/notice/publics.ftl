
<#import "/layout/_list.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
        gridId: "dg",
        dataUrl: "list_publics.json",
        openUrl: "look.html"
    };
    
    $(document).ready(function () {
        var dg = new datagrid(setting);
        dg.doQuery();       
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="datagrid" id="dg"> 
    已发布列表
     <div class="form-actions">
     <input type="button" name="btn_open" class="btn" value="查看">
     <input type="button" class="btn" onclick="javascript:window.location.href='rec.html'" value="新增">	
     </div>
    <table>
        <thead>
        <tr>
            <td class="cell-chk"><input class="chk" type="checkbox"/></td>
            <td id="SID" style="display:none;"></td>
            <td id="C_TITLE">标题</td>   
            <td id="S_PUBLIC">发布人</td> 
            <td id="T_PUBLIC">发布时间</td>                         
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