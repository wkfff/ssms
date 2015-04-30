<#import "/layout/_layout.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/resource/js/easyui/plugins/jquery.kindeditor.js"></script>
<script type="text/javascript">
    $(function () {
        //评分结果
        $('#form_result').form('load','/e/grade_m/rec.json?sid=${sid!}');
        $form.disableForm('formMain',true);
        
        $('#dg').datagrid({
            //title:'自评内容',border:0,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:false,
            height:500,
            //border:false,
            autoSave:true,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 60},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 60,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 60,editor:'numberbox'}
            ]]
           ,
            onLoadSuccess: function(data){
                $(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
            }
        });
        
        //扣分项
        $('#dg1').datagrid({
            title:'扣分项',border:0,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:false,
            height:500,
            //border:false,
            autoSave:true,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 60},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 60,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 60,editor:'numberbox'}
            ]]
           ,
            onLoadSuccess: function(data){
                //$(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
            }
        });
        //得分项
        $('#dg2').datagrid({
            title:'得分项',border:0,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:false,
            height:500,
            //border:false,
            autoSave:true,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 60},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 60,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 60,editor:'numberbox'}
            ]]
           ,
            onLoadSuccess: function(data){
                //$(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
            }
        });
        //缺项
        $('#dg3').datagrid({
            title:'缺项',border:0,
            //iconCls: 'icon-star',
            url: '/e/grade_d/list.json?R_SID=${sid!-1}',
            idField: 'SID',
            rownumbers: false,
            pagination: false,
            singleSelect: true,
            fitColumns: false,
            autoRowHeight:true,
            fit:false,
            height:500,
            //border:false,
            autoSave:true,
            striped: false,
            columns: [[
                {field: 'S_CATEGORY', title: '类目', width: 100},
                {field: 'S_PROJECT', title: '项目', width: 100},
                {field: 'C_CONTENT', title: '内容', width: 256},
                {field: 'N_SCORE', title: '标准分值',align:'center',width: 60},
                {field: 'C_METHOD', title: '考评办法', width: 350},
                {field: 'C_DESC', title: '自评描述', width: 250,editor:{type:'textarea',options:{},height:'100%'}},
                {field: 'B_BLANK', title: '是否缺项', width: 60,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
                        formatter:function(value,row){
                            return value=='1'?'是':'否';
                        }},
                {field: 'N_SCORE_REAL', title: '实际得分', align:'center',width: 60,editor:'numberbox'}
            ]]
           ,
            onLoadSuccess: function(data){
                //$(this).datagrid("autoMergeCells",["S_CATEGORY","S_PROJECT"]);
            }
        });
    });
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-layout" data-options="fit:true" >
    <div class="easyui-tabs" style="width:100%;height:0px;">
        <div title="评分结果" style="padding:0px">
            <form id="form_result" method="post">
                        <table style="width:900px;" cellspacing="6">
                            <tr>
                                <td class="span2">自评单位:</td>
                                <td class="span10" colspan="3">
                                    <input class="easyui-textbox" type="text" name="S_TANENT" data-options="required:true,width:602" 
                                    disabled=true value="${S_TANENT!}"/>
                                 </td>
                            </tr>
                            <tr>
                                <td class="span2">自评日期:</td>
                                <td class="span4"><input class="easyui-datebox" type="text" name="T_START" data-options="required:true,width:100"></input>
                                                            至<input class="easyui-datebox" type="text" name="T_END" data-options="required:true,width:100" ></input>
                                </td>
                                <td class="span2">自评组组长:</td>
                                <td class="span4"><input class="easyui-textbox" type="text" name="C_LEADER" data-options="required:true"></input></td>
                            </tr>
                            <tr>
                                <td class="span2">自评组主要成员:
                                <td class="span10" colspan="3"><input class="easyui-textbox" type="text" name="C_MEMBERS" data-options="required:true,width:602"></input></td>
                            </tr>
                        </table>
                </form>
                
                <table id="dg" class="easyui-datagrid"></table>
        </div>
        <div title="自评报告" style="padding:0px">
            <form id="form_rep" method="post">
                <textarea class="easyui-kindeditor" id="C_CONTENT" name="C_CONTENT" data-options="width:'100%',height:'560px'"></textarea>
                <input type="hidden" id="R_SID" name="R_SID"/>
            </form>
        </div>
        <div title="评分汇总" style="padding:0px">
            <div style="width:100%;height:100%;overflow:auto;">
            <table id="dg1" class="easyui-datagrid"></table>
            <table id="dg2" class="easyui-datagrid"></table>
            <table id="dg3" class="easyui-datagrid"></table>
            </div>
        </div>
    </div>
</div>
</@layout.doLayout>
