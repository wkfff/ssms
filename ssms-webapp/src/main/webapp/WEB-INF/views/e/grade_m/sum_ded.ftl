<!doctype html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="/resource/css/layout.css"/>
    <style>
        .c0{width:32px;text-align:center;}
        .c1{width:120px;}
        .c2{width:160px;}
        .c3{width:450px;}
        .c4{width:320px;}
        .c5{width:60px;text-align:center;}
    </style>
    </head>
    
    <body>
            <div class="titlebar">
                <img src="/resource/images/blue/star.png" />
                &nbsp;自评扣分点及原因说明汇总表
            </div>
                <table class="table">
                <thead>
                    <th class="c0">序号</th>
                    <th class="c1">一级要素</th>
                    <th class="c2">二级要素</th>
                    <th class="c3">企业达标标准</th>
                    <th class="c4">扣分说明</th>
                    <th class="c5">扣分分值</th>
                </thead>
                <tbody>
                <#list list as r>
                <tr>
                    <td class="c0">${r_index+1}</td>
                    <td class="c1">${r.C_CATEGORY!}</td>
                    <td class="c2">${r.C_PROJECT!}</td>
                    <td class="c3">${r.C_CONTENT!}</td>
                    <td class="c4">${r.C_DESC!}</td>
                    <td class="c5">${r.N_DED!}</td>
                </tr> 
                </#list>
                </tbody>
                </table>
    </body>
</html>
