<#import "/layout/_rec.ftl" as layout/>
<#assign script>
<script type="text/javascript">
    var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"rec.json",			
			saveUrl:"save.json",
			delUrl:"del.json",
			publiceUrl:"publice.json"
	}
	$(document).ready(function(){
		$form.init(setting);			
	});
</script>
</#assign>
<@layout.doLayout script>

<div class="navbar navbar-default navbar-fixed-top"><br><br>
      <div class="navbar-inner">
        <div class="container">
	        <div class="nav pull-right">
		      <input type="button" name="btn_save" value="保存">	
              <input type="button" name="btn_del" value="删除">
              <input type="button" name="btn_publice" value="发布">
		      <input type="button" onclick="window.history.go( -1 );" value="返回">	
	        </div>
        </div>
      </div>
</div>	
<div class="container">
<!--表单-->
	<form class="form-horizontal" id="mainForm">
	  <fieldset>
		<div id="legend">
			<legend>概述</legend>
		</div>
		    <input type="text" id="N_STATE" name="N_STATE"  style="display:none;" value="0"/>
		    <div class="row-fluid">
				<div class="span12">
					<div class="control-group">
			          <!-- Text input-->
			          <label class="control-label" for="input01">标题:</label>
			          <div class="controls">
			            <input type="text" placeholder="" class="input-xxlarge" id="C_TITLE" name="C_TITLE">
			            <p class="help-block">标题</p>
			          </div>
			        </div>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">发布日期:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="T_BEGIN" name="T_BEGIN">
							<p class="help-block">发布日期</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">创建人:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="S_CREATOR" name="R_CREATE">
							<p class="help-block">创建人</p>
						</div>
					</div>
				</div>
		   </div>
		
	       <div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">创建日期:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="T_CREATE" name="T_CREATE">
							<p class="help-block">创建日期</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">修改人:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="S_UPDATE" name="S_UPDATE">
							<p class="help-block">修改人</p>
						</div>
					</div>
			</div>
			
			</div>
				<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
			          <!-- Text input-->
			          <label class="control-label" for="input01">修改日期:</label>
			          <div class="controls">
			            <input type="text" placeholder="" class="input-xxlarge" id="T_UPDATE"  name="T_UPDATE">
			            <p class="help-block">修改日期</p>
			          </div>
			        </div>
				</div>
			</div>
		</fieldset>		
	</form>
</div>

</@layout.doLayout>