<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.md5.js"></script>
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    var model = {
        C_USER : ko.observable()
        
    };
    var events = {
        };
    
    $(function () {
         ko.applyBindings($.extend({}, model,null, events));
        });
</script>
<style type="text/css">
#user_page {
    background: #fff none repeat scroll 0 0;
    border: 1px solid #ddd;
    font-size: 10pt;
    overflow: hidden;
    padding: 20px;
}
#user_page #form_user h2 {
    border-bottom: 2px solid #ddd;
    color: #333;
    font-size: 16pt;
    font-weight: normal;
    margin: 0 50px 0 0;
    padding-bottom: 10px;
}
#user_page form th {
    font-size: 10.5pt;
    padding: 10px;
    text-align: right;
}
#user_page form input.TEXT {
    border: 1px solid #ccc;
    font-size: 16px;
    padding: 3px;
}
#user_page form input.SUBMIT {
    font-weight: bold;
}
#user_page form input.BUTTON{
    font-size: 14px;
    height: 30px;
    padding: 0 5px;
}
</style>
<div id="user_page">
<form id="form_user">
    <h2>重置登录密码 </h2>
    <table>
        <tbody>
            <tr>
                <td style="padding:20px 0;color:#40AA53;" colspan="2">请输入您注册的邮箱地址，系统将发送重置密码的链接到邮箱中</td>
            </tr>
            <tr>
                <th>电子邮箱 / 账号</th>
                <td><input  class="TEXT" type="text" data-bind="textboxValue : C_USER" required/></td>
            </tr>
            <tr>
                <th>验证码</th>
                <td ><input id="yzm" class="TEXT"
                    type="text" size="6" /> <span>
                                  此处输入下图中的字符 <a href="javascript:_rvi()">换另外一个图</a>
                </span></td>
            </tr>
            <tr>
                <th></th>
                <td><img id="vcodeImg" align="absmiddle"
                    style="border: 2px solid #ccc;"
                    src="/vcode"  alt="..."> 
                    <script language="javascript">
                       function _rvi() {  
                                           $('#vcodeImg').attr("src", '');
			                               $('#vcodeImg').attr("src", '/vcode?_t='+new Date().valueOf());
                                          
                                        }
                    </script></td>
            </tr>
            <tr>
                <th></th>
                <td><input class="BUTTON SUBMIT" type="submit"
                    value="重置密码"></td>
            </tr>
            <tr>
                <th></th>
                <td><a href="/login">转到登录页面？</a></td>
            </tr>
        </tbody>
    </table>
</form>
</div>