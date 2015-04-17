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
    通知公告编辑
      <div class="navbar-inner">
        <div class="container">
	        <div class="nav pull-right">
		      <input type="button" name="btn_save" value="保存">	
              <input type="button" name="btn_del" value="删除">
              <input type="button" name="btn_publice" value="发布">
		      <input type="button" onclick="javascript:window.location.href='publics.html'" value="返回">	
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
		</fieldset>		
	</form>
</div>

</@layout.doLayout>