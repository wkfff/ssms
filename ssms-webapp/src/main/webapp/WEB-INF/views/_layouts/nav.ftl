<@layout.extends name="base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/layout.css"/>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    </@>
    <@layout.put block="contents">
       <header class="titlebar">
           <@layout.block name="panel_title"/>
           <span style="position: absolute; right: 15px;">
                <@layout.block name="panel_tools"><img src="/resource/images/undo.png"><a href="/sys/nav/">返回导航端</a></@>
           </span>
       </header>
       
       <div class="content">
           <@layout.block name="panel_content"/>
       </div>
    </@>
    <@layout.put block="footer">
         <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
         <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
         <script type="text/javascript" src="/resource/js/My97DatePicker/WdatePicker.js"></script>
         <script type="text/javascript" src="/resource/js/knockout/knockout.validation.js"></script>
         <script type="text/javascript" src="/resource/js/knockout/knockout.validation.zh-CN.js"></script>
         
         <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
         <script type="text/javascript" src="/resource/js/core.js"></script>
    </@>
</@layout.extends>