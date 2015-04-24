<#import "/layout/_mix.ftl" as layout/> 
<#assign script>
<script type="text/javascript">
    var formSetting = {
        sid: $url.getUrlParam("sid"),
        dataUrl: "rec.json",
        saveUrl: "save.json",
        delUrl: "del.json"
    };
    var gridSetting = {
        gridId : "dg",
        dataUrl : "list.json",
        delUrl : "dels.json",
        addUrl : "rec.html",
        openUrl : "rec.html"
    };
    
    $(document).ready(function () {
        //表单
        $form.init(formSetting);
        //表格
        var dg = new datagrid(gridSetting);
        dg.doQuery();
        
        //完成自评处理
        $("input[name='btn_ok']").click(function() {
            
        });
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
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">在线自评></a></li>
                    <li class="active"><a href="#">自评></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@layout.toolbar class="navbar-fixed-top" outer="<br><br>">
<input type="button" class="btn" name="btn_save" value="保存"/>
<input type="button" class="btn" name="btn_del" value="删除"/>
<input type="button" class="btn" name="btn_ok" value="完成自评"/>
<input type="button" class="btn" name="btn_back" value="返回"/>
</@layout.toolbar>

<div class="container">
    <@layout.form id="mainForm">
        <@layout.group title="基本信息">
            <@layout.row>
                <@layout.textbox name="S_TANENT" title="自评单位" desc="" readonly=true/>
            </@layout.row>
            <@layout.row>
                <@layout.date name="T_STATE" title="自评时间从" desc="" span=6 />
                <@layout.date name="T_END" title="至" desc="" span=6 />
            </@layout.row>
            <@layout.row>
                <@layout.textbox name="C_LEADER" title="自评组组长" desc="" span=6 />
                <@layout.textbox name="C_MEMBERS" title="自评组主要成员" desc="" span=6 />
            </@layout.row>
        </@layout.group>

        <@layout.group title="自评内容">
            <@layout.row>
                <div class="datagrid" id="dg">
                    <table><thead><tr>
                        <td id="C_NAME">标题</td>
                        <td id="T_START">自评开始时间</td>
                        <td id="T_END">自评结束时间</td>
                        <td id="N_GET">得分项</td>
                        <td id="N_DEDUCT">扣分项</td>
                        <td id="N_LACK">缺项</td>
                        <td id="P_STATE" style="display:none;">状态</td>
                        <td id="S_STATE">状态</td>
                    <tr></thead><tbody/></table>
                    <@layout.pagingbar />
                </div>
            </@layout.row>
        </@layout.group>
    </@layout.form>
</div>
</@layout.doLayout>