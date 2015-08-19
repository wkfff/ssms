<@layout.extends name="../../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <style type="text/css">
         .btn{
             position:relative;
             width: 351px;
             height: 130px; 
         }
         li {
            float: right;
            width: 500px;
            padding: 20px;
         }
         ul {
            margin-top:50px;
         }
    </style>
    </@>
    <@layout.put block="contents">
    <div class="contents">
        <ul >
             <li>
                <input type="button" class="btn" onclick="chosePort(13)" value="企业端导航"/>
            </li>
        </ul>
        <ul >
             <li>
                <input type="button" class="btn" onclick="chosePort(14)" value="评审端导航"/>
            </li>
        </ul>
        <ul >
             <li>
                <input type="button" class="btn" onclick="chosePort(15)" value="政府端导航"/>
            </li>
        </ul>
        <ul >
             <li>
                <input type="button" class="btn" onclick="chosePort(16)" value="系统端导航"/>
            </li>
        </ul>
    </div>
    </@>
    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript">
        function chosePort(id){
            window.location.href="${BASE_PATH}/tree?parentId="+id;
        };
    </script>
    </@>
</@layout.extends>



