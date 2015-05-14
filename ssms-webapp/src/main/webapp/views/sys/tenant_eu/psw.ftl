<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript">
    var model={
    		C_PASSWD1:ko.observable(),
    		C_PASSWD:ko.observable(),
    		C_PASSWD2:ko.observable(),
    		SID:'${sid!}'
    }
   var events={
    		updateClick: function(){
    			if(($("#C_PASSWD").val())!=($("#C_PASSWD2").val())){
    		         $.messager.alert("提示","两次输入密码不一致")
    				return;
    			}else{
    				var v=$("#C_PASSWD").val($.md5($("#C_PASSWD").val()));
    			    $("input[name='C_PASSWD']").val(v.val())
    				var v1=$("#C_PASSWD1").val($.md5($("#C_PASSWD1").val()));
    			    $("input[name='C_PASSWD1']").val(v1.val())
    			    var v2=$("#C_PASSWD2").val($.md5($("#C_PASSWD2").val()));
                    $("input[name='C_PASSWD2']").val(v2.val())
    				$.post('updtePSW.do',model,function(flag){
    					if(flag){
    						$.messager.alert("提示","密码修改成功")
    					}else{
    						$.messager.alert("提示","密码修改失败")
    					}
    				})
    			}
    		}
    }
    ko.applyBindings($.extend({}, model, events));
</script>
</#assign>
<@layout.doLayout script>
<div class="easyui-panel" title="密码修改">
    <div class="z-toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: updateClick">修改</a>
        <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-undo" onclick="window.location.href='${Referer!}';">返回</a>
    </div>
    <form class="form" style="padding: 20px">
        <div class="easyui-panel form" style="padding:10px" title="基本信息">
            <p class="long-input ue-clear">
                <label>旧密码</label>
                <span class="control">
                    <input data-bind="textboxValue: C_PASSWD1" id="C_PASSWD1" name="C_PASSWD1" type="password" required/>
                </span>
            </p>
            <p class="long-input ue-clear">
                <label>新密码</label>
                <span class="control">
                    <input data-bind="textboxValue: C_PASSWD" id="C_PASSWD" name="C_PASSWD" type="password" required/>
                </span>
            </p>
            <p class="long-input ue-clear">
                <label>确认密码</label>
                <span class="control">
                    <input data-bind="textboxValue: C_PASSWD2" id="C_PASSWD2" name="C_PASSWD2" type="password" required/>
                </span>
            </p>
        </div>
    </form>
</div>

</@>