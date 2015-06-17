<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
<script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var model = {
		oldPwd : ko.observable(),
		newPwd : ko.observable(),
		confirmPwd : ko.observable()
	};
	var events = {
	        updateClick : function() {
	            if (model.newPwd() != model.confirmPwd()) {
	                $.messager.alert("提示", "两次输入密码不一致");
	                return false;
	            } else {
	                model.oldPwd($.md5(model.oldPwd()));
	                model.newPwd($.md5(model.newPwd()));
	                $.post('${BASE_PATH}/changePassword', {
	                    oldPwd : model.oldPwd(),
	                    newPwd : model.newPwd()
	                }, function(flag) {
	                    if (flag) {
	                        $.messager.alert("提示", "密码修改成功", 'info', function() {
	                            window.location.href = window.location.href;
	                        })
	                    } else {
	                        $.messager.alert("提示", "密码修改失败")
	                    }
	                }, 'json');
	            }
	        }
	    };
	
	$(function () {
		 ko.applyBindings($.extend({}, model,null, events));
		});
</script>
<style type="text/css">
.MainForm {
    font-size: 10pt;
}
 table, input, textarea, select {
    font-family: Microsoft YaHei,Verdana,sans-serif,宋体;
}
table th{
    text-align:right;
    padding-right:10px;
}
#AdminBody {
    color: #333333;
    min-height: 600px;
}
.SpaceList {
    background: #fff none repeat scroll 0 0;
    border: 1px solid #eee;
    margin: 0;
    padding: 10px 15px;
    word-wrap: break-word;
}
.AdminHead h1 {
    color: #666666;
    margin-bottom: 15px;
}
input.TEXT {
    padding: 3px;
}
.MainForm form input.SUBMIT {
    font-weight: bold;
    padding: 2px 10px;
}
.MainForm form input.BUTTON {
    font-size: 12pt;
    padding: 2px 5px;
}
.hr{
    height: 0;
    border-top: 1px solid #CCCCCC;
    margin-top: 5px;
    margin-bottom: 5px
}
</style>

<div id="AdminBody" class="SpaceList">
<div class="AdminHead">
<h1>修改登录密码</h1>
</div> 
       <div class="hr"></div>
    <div class="MainForm">
        <form class="form">
            <table style="width:100%">
                <tr>
                    <th width="150">旧的登录密码</th>
                    <td ><input class="TEXT"
                        data-bind="textboxValue: oldPwd" type="password" tabindex="2" size="35"
                        required />
                        </td>
                </tr>

                <tr>
                    <th>新密码</th>
                    <td ><input width="100px" class="TEXT"
                        data-bind="textboxValue: newPwd" type="password" tabindex="2" size="35"
                        required /></td>
                </tr>
    
                <tr>
                    <th>再次输入新密码</th>
                    <td ><input  class="TEXT" 
                        data-bind="textboxValue: confirmPwd" type="password"  tabindex="3" size="35"
                        required /></td>
                </tr>
                <tr><th colspan="2"></th></tr>
                <tr>
                    <th></th>
                    <td >
                        <input class="BUTTON SUBMIT" type="submit" tabindex="4" value="修改密码" data-bind="click :updateClick"/>
                    </td>
                </tr>
            </table>
        </form>
      </div>
    </div>

