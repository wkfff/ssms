<@layout.extends name="../../_layouts/base.ftl">
    <@layout.put block="head">
    <style type="text/css">
         .content{
             width: 998px;
             border: solid #daeef5 1px;
         }
         .readOptioner{
             background: url("${RES!}/images/cancel.png") no-repeat ;
             padding-left: 16px
         }
        .bot{
             background: url("/resource/images/bot.png") no-repeat 2px center;
             padding-left: 13px;
             background-size: 7px 7px;
            }
         table{
             margin-top : 10px;
             margin-left: 20px;
             margin-bottom: 10px;
         }
         table tr td{
             padding: 5px 5px;
         }
         table tr{
             border-bottom: solid black 1px;
         }
         .title{
             height:20px;
             background: #daeef5 ;
         }
         .title span{ 
             padding-left:20px;
             font-weight: bold;
         }
         a, a:visited {
             text-decoration: none;
             color: #0000EE;
         }
         a {
             text-decoration: none;
             cursor: pointer;
            }
    </style>
    </@>
    
    <@layout.put block="contents">
    <div class="content">
        <div class="title"><span>隐患排查提醒</span></div>
        <table>
        <#list list as list>
            <tr>
                <td class="bot">${list.T_CREATE!}未创建隐患排查</td>
                <td><a class="readOptioner" onclick="readHiddenDanger('${list.SID}')" >[未阅]</a></td>
            </tr>
        </#list>
        </table>
    </div>
    </@>
    <@layout.put block="footer">
        <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
        <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
        <script type="text/javascript" src="/resource/js/core.js"></script>
        <script type="text/javascript">
            function readHiddenDanger(sid){
                $.post('${BASE_PATH}/readHidden',{sid:sid},function(result){
                    if(result){
                        window.location.href="/e/hidden_danger/listEnterprise"
                    }
                },'json');
            }
        </script>
    </@>
</@layout.extends>