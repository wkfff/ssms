<#import "/layout/_rec.ftl" as layout/>

<#assign script>
<script charset="utf-8" src="/resource/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript">
    var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"rec.json",
			saveUrl:"save.json",
			delUrl: "del.json"
	}
	
	$(document).ready(function(){
		$form.init(setting);

		$('#btn_dialog').click(function () {
		    $('#dialog_simple').dialog('open');
		    return false;
		});
		$('#btn_modaldialog').click(function () {$('body').css('overflow-x','auto');
		    $('#dialog_message').dialog('open');
		    return false;
		});
		
		$('#dialog_simple').dialog({
		    autoOpen: false,
		    width: 600,
		    height:400,
		    buttons: {
		        "确定": function () {
		            $(this).dialog("close");
		        },
		        "关闭": function () {
		            $(this).dialog("close");
		        }
		    }
		});
		$('#dialog_message').dialog({
		    autoOpen: false,
		    modal:true,
		    width: 600,
		    height:400,
		    buttons: {
		        "确定": function () {
		            $(this).dialog("close");
		        },
		        "关闭": function () {
		            $(this).dialog("close");
		        }
		    }
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
              <li class="active">
                <a href="#">首页 ></a>
              </li>
              <li class="active">
                <a href="#">管理中心 ></a>
              </li>
              <li class="active">
                <a href="#">企业信息管理 ></a>
              </li>
              <li class="active">
                <a href="#">企业信息编辑</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
</div>

<div class="navbar navbar-default navbar-fixed-top"><br><br>
      <div class="navbar-inner">
        <div class="container">
	        <div class="nav pull-right">
	           <input type="button" class="btn" name="btn_save" value="保存">
	           <input type="button" class="btn" name="btn_del" value="删除">
	           <input type="button" class="btn" name="btn_back" value="返回">
	        </div>
        </div>
      </div>
</div>

<div class="container">
<!--表单-->
<form class="form-horizontal" id="mainForm">
	    <fieldset>
			<div id="legend">
			<legend>表单</legend>
			</div>
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">文本框:</label>
						<div class="controls">
							<input type="text" placeholder="" class="ui-text input-xlarge" id="C_NAME" name="C_NAME">
							<p class="help-block">文本框</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">数值:</label>
						<div class="controls">
							<input type="text" placeholder="" class="ui-number input-xlarge" id="N_SAFETY" name="N_SAFETY">
							<p class="help-block">数值</p>
						</div>
					</div>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">密码:</label>
						<div class="controls">
							<input type="password" placeholder="" class="ui-text input-xlarge" id="S_CITY" name="S_CITY">
							<p class="help-block">密码</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">参数:</label>
						<div class="controls">
							<select id="S_COUNTY" class="ui-combbox input-xlarge" src="">
								<option>Slower</option>
								<option>Slow</option>
								<option selected="selected">Medium</option>
								<option>Fast</option>
								<option>Faster</option>
							</select>
							<p class="help-block">参数</p>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
			          <label class="control-label">多选框</label>
			          <div class="ui-checkbox controls" id="C_ADDR">
			            <label class="checkbox inline">
						  <input type="checkbox" value="option1" name="C_ADDR"> 1
						</label>
						<label class="checkbox inline">
						  <input type="checkbox" value="option2" name="C_ADDR"> 2
						</label>
						<label class="checkbox inline">
						  <input type="checkbox" value="option3" name="C_ADDR"> 3
						</label>
			            <p class="help-block">多选</p>
			          </div>
			        </div>
				</div>
				
				<div class="span6">
					<div class="control-group">
			          <!-- Text input-->
			          <label class="control-label">单选框</label>
			          <div class="ui-radio controls" id="C_EMAIL">
			            <label class="radio inline">
						  <input type="radio" name="C_EMAIL" value="option1"> 1
						</label>
						<label class="radio inline">
						  <input type="radio" name="C_EMAIL" value="option2"> 2
						</label>
						<label class="radio inline">
						  <input type="radio" name="C_EMAIL" value="option3"> 3
						</label>
			            <p class="help-block">单选</p>
			          </div>
			        </div>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">日期控件:</label>
						<div class="controls">
							 <div class="input-append">
							    <input type="text" placeholder="" class="ui-date input-xlarge" id="T_EXAMINE" name="T_EXAMINE">
							    <!--<button class="btn" type="button">选择</button>-->
							  </div>
							<p class="help-block">测试日期控件</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label">日期时间:</label>
						<div class="controls">
							<input type="text" placeholder="" class="ui-datetime input-xlarge" id="T_CREATE" name="T_CREATE">
							<p class="help-block"></p>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<label class="control-label">富文本:</label>
					<div class="controls">
						<textarea id="C_SUMMARY" name="C_SUMMARY" rows=5 class="ui-editor"> </textarea>
					</div>
				</div>
			</div>
		</div>
		</fieldset>
		
		<fieldset>
		<div id="legend">
		<legend>交互控件</legend>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div id="tabs" class="ui-tabs">
                    <ul>
                        <li><a href="#tabs-a">标签页一</a></li>
                        <li><a href="#tabs-b">标签页二</a></li>
                        <li><a href="#tabs-c">标签页三</a></li>
                    </ul>
                    <div id="tabs-a">1111111</div>
                    <div id="tabs-b">2222222</div>
                    <div id="tabs-c">3333333</div>
                </div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span12">
				<input type="button" class="btn" id="btn_dialog" value="打开对话框"/>
				<input type="button" class="btn" id="btn_modaldialog" value="打开模态对话框" style="display:none;"/>
				<div id="dialog_simple" title="对话框">
                    <p>内容.</p>
                </div>
                <div id="dialog_message" title="对话框">
                    <p>内容.</p>
                </div>
			</div>
		</div>
		</fieldset>
</div>
</form>


</div>
</@layout.doLayout>