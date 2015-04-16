<#import "/layout/_rec.ftl" as layout/>
<#assign script>

<script type="text/javascript">
    var setting = {
			sid:$url.getUrlParam("sid"),
			dataUrl:"rec.json",			
			saveUrl:"save.json",
			delUrl: "del.json"
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

<div class="container">
<!--表单-->
<form class="form-horizontal" id="mainForm">
	<div class="form-actions">
		<input type="button" class="btn" name="btn_save" value="保存">	
		<input type="button" class="btn" name="btn_del" value="删除">
		<input type="button" class="btn" name="btn_back" value="返回">	
	</div>
	
    <div class="container">
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
		          <!-- Text input-->
		          <label class="control-label" for="input01">所属专业:</label>
		          <div class="controls">
		            <input type="text" placeholder="" class="input-xlarge" id="C_NAME" name="C_NAME">
		            <p class="help-block">企业的所属专业</p>
		          </div>
		        </div>    
			</div>
			<div class="span6">				
				<div class="control-group">
		          <!-- Text input-->
		          <label class="control-label" for="input01">企业性质</label>
		          <div class="controls">
		            <input type="text" placeholder="" class="input-xlarge">
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
		            <input type="text" placeholder="" class="input-xlarge" id="C_NAME" name="C_NAME">
		            <p class="help-block">企业的专职安全管理员人数</p>
		          </div>
		        </div>    
			</div>
			<div class="span6">				
				<div class="control-group">
		          <!-- Text input-->
		          <label class="control-label" for="input01">员工总数</label>
		          <div class="controls">
		            <input type="text" placeholder="" class="input-xlarge">
		            <p class="help-block">企业员工总数</p>
		          </div>
		        </div>
			</div>
		</div>
	</div>
</form>
</div>
</@layout.doLayout>