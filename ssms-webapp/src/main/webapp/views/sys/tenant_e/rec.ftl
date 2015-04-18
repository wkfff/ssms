<#import "/layout/_rec.ftl" as layout/>

<#assign script>
<script type="text/javascript">
    var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"rec.json",
			saveUrl:"save.json",
			delUrl: "del.json",
			editor:"C_SUMMARY"
	}
	
	$(document).ready(function(){
		$form.init(setting);
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
			<legend>基本信息</legend>
			</div>
	    	<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
			          <!-- Text input-->
			          <label class="control-label" for="input01">企业名称:</label>
			          <div class="controls">
			            <input type="text" placeholder="" class="input-xxlarge" id="C_NAME" name="C_NAME">
			            <p class="help-block">企业的详细名称</p>
			          </div>
			        </div>
				</div>
			</div>
	
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">企业状态:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="S_STATE" name="S_STATE">
							<p class="help-block">企业状态</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">省份:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="S_PROVINCE" name="S_PROVINCE">
							<p class="help-block">省份</p>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">地市:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="S_CITY" name="S_CITY">
							<p class="help-block">地市</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">县市:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="S_COUNTY" name="S_COUNTY">
							<p class="help-block">县市</p>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
					<!-- Text input-->
			          <label class="control-label" for="input01">所属专业:</label>
			          <div class="controls">
			            <input type="text" placeholder="" class="input-xlarge" id="S_PROFESSION" name="S_PROFESSION">
			            <p class="help-block">企业的所属专业</p>
			          </div>
			        </div>
				</div>
				<div class="span6">
					<div class="control-group">
			          <!-- Text input-->
			          <label class="control-label" for="input01">企业性质</label>
			          <div class="controls">
			            <input type="text" placeholder="" class="input-xlarge" id="S_NATURE" name="S_NATURE">
			            <p class="help-block">企业性质</p>
			          </div>
			        </div>
				</div>
			</div>
	
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
			          <!-- Text input-->
			          <label class="control-label" for="input01">专职安全管理员:</label>
			          <div class="controls">
			            <input type="text" placeholder="" class="input-xlarge" id="N_SAFETY" name="N_SAFETY" value="${N_SAFETY}">
			            <p class="help-block">企业的专职安全管理员人数</p>
			          </div>
			        </div>
				</div>
				<div class="span6">
					<div class="control-group">
					<!-- Text input-->
			          <label class="control-label" for="input01">员工总数</label>
			          <div class="controls">
			            <input type="text" placeholder="" class="input-xlarge" id="N_EMPLOYEE" name="N_EMPLOYEE" value="${N_EMPLOYEE}">
			            <p class="help-block">企业员工总数</p>
			          </div>
			        </div>
				</div>
			</div>
	
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">主营业务收入:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="N_INCOME" name="N_INCOME" value="${N_INCOME}">
							<p class="help-block">主营业务收入</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">固定资产:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="N_ASSETS" name="N_ASSETS" value="${N_ASSETS}">
							<p class="help-block">固定资产</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">特种作业人员数:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="N_SPECIAL" name="N_SPECIAL" value="${N_SPECIAL}">
							<p class="help-block">特种作业人员数</p>
						</div>
					</div>
				</div>
				<div class="span6">
					<div class="control-group">
						<label class="control-label" for="input01">营业范围:</label>
						<div class="controls">
							<input type="text" placeholder="" class="input-xlarge" id="C_SCOPE" name="C_SCOPE">
							<p class="help-block">营业范围</p>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<fieldset>
		<div id="legend">
		<legend>企业概述</legend>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<div class="controls">
						<textarea id="C_SUMMARY" name="C_SUMMARY" rows=5 > </textarea>
					</div>
				</div>
			</div>
		</div>
		</fieldset>
</form>
</div>
</@layout.doLayout>