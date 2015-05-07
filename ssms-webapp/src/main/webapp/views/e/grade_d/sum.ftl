<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<style>
    .datagrid-cell{
        white-space:normal !important;
    }
    .datagrid-editable-input{
        height:120px !important;
    }
</style>

<script type="text/javascript">
    function doBack(){
        window.location.href='${referer!}';
    }
    function initGrid1(){
        $('#dg1').datagrid({
            title:'扣分项',border:0,height:200,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}&type=0',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:false,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 65},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 65,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 65,editor:'numberbox'}
            ]]
            ,
            onLoadSuccess: function(data){
                //$(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
                initGrid2();
            }
        });
    }
    function initGrid2(){
        $('#dg2').datagrid({
            title:'得分项',border:0,height:200,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}&type=1',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:false,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 65},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 65,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 65,editor:'numberbox'}
            ]]
            ,
            onLoadSuccess: function(data){
                //$(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
                initGrid3();
            }
        });
    }
    
    function initGrid3(){
        $('#dg3').datagrid({
            title:'缺项',border:0,height:200,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}&type=2',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:false,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 65},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 65,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 65,editor:'numberbox'}
            ]]
            ,
            onLoadSuccess: function(data){
                //$(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
            }
        });
    }
    $(function () {
        initGrid1();
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div data-options="region:'north',collapsible:false" style="overflow:hidden;">
          <div class="toolbar ue-clear">
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-download" onclick="alert('暂未完成');">下载</a>
                <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="doBack()">返回</a>
         </div>
    </div>
    <div data-options="region:'center'" >
        <table id="dg1" class="easyui-datagrid"/>
        <table id="dg2" class="easyui-datagrid"/>
        <table id="dg3" class="easyui-datagrid"/>
    </div>
</div>

</@layout.doLayout>