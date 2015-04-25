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
        dataUrl : "/e/grade_std/list.json?P_PROFESSION=${P_PROFESSION}",
        delUrl : "/e/grade_d/dels.json",
        addUrl : "",
        openUrl : "",
        formId:"mainForm",
        saveUrl:"/e/grade_d/gridsave.json"
    };
    
    $(document).ready(function () {
        //表单
        $form.init(formSetting);
        //表格
        //var dg = new datagrid(gridSetting);
        //dg.doQuery();
        
        //完成自评处理
        $("input[name='btn_ok']").click(function() {
            
        });
        
        $("input[name='btn1']").click(function() {
            //新建
            if ($url.getUrlParam("sid")==""){
                alert("new");
                $.get("init.json",,function (data) {
                    $win.navigate('rec.html?sid='+formSetting.sid);
                },"json");
            }
            var data = $("#mainForm").serializeArray();
            $.post(gridSetting.saveUrl, data,function (data) {
                $win.navigate('rec.html?sid=');
            });
        });
        
        $("input[name='btn2']").click(function() {
            var data = $("#mainForm").serializeArray();
            $.post(gridSetting.saveUrl, data,function (data) {
                $win.navigate(window.location.href);
            });
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

<input type="button" class="btn" name="btn_1" value="测试1"/>
<input type="button" class="btn" name="btn_2" value="测试2"/>

</@layout.toolbar>

<div class="container">
    <@layout.form id="mainForm">
        <@layout.group title="基本信息">
            <@layout.row>
                <@layout.textbox name="S_TANENT" title="自评单位" desc="" readonly=true value="${S_TANENT}"/>
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
                    <table class="table-bordered">
                        <thead>
                            <tr>
                                <td id="SID" style="display:none;" editor="textbox">SID</td>
                                <td id="C_CATEGORY">类目</td>
                                <td id="C_PROJECT">项目</td>
                                <td id="C_CONTENT">内容</td>
                                <td id="N_SCORE">标准分值</td>
                                <td id="C_METHOD">考评办法</td>
                                <td id="C_DESC" editor="textbox">自评描述</td>
                                <td id="B_BLANK" editor="textbox">空项</td>
                                <td id="N_SCORE_REAL" editor="textbox">实际得分</td>
                            <tr>
                        </thead>
                        <tbody>
                            <#list _DETAIL_ as r>
                                <tr>
                                    <td id="SID" style="display:none;" editor="textbox">
                                        <input type="hidden" name="${r_index}#SID" value=${r["SID"]}></input>
                                    </td>
                                    <td id="C_CATEGORY">${r["C_CATEGORY"]!}</td>
                                    <td id="C_PROJECT">${r["C_PROJECT"]!}</td>
                                    <td id="C_CONTENT">${r["C_CONTENT"]!}</td>
                                    <td id="N_SCORE">${r["N_SCORE"]!}</td>
                                    <td id="C_METHOD">${r["C_METHOD"]!}</td>
                                    <td id="C_DESC" editor="textbox">
                                        <input type="text" style="width:100%;padding:0px;margin:0px;" name="${r_index}#C_DESC" value=${r["C_DESC"]!}></input>
                                    </td>
                                    <td id="B_BLANK" editor="textbox">
                                        <input type="checkbox" name="${r_index}#B_BLANK" value=${r["B_BLANK"]!}></input>
                                    </td>
                                    <td id="N_SCORE_REAL" editor="textbox">
                                        <input type="text" style="width:100%;padding:0px;margin:0px;" name="${r_index}#N_SCORE_REAL" value=${r["N_SCORE_REAL"]!}></input>
                                    </td>
                                <tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </@layout.row>
        </@layout.group>
    </@layout.form>
</div>
</@layout.doLayout>