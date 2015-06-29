<!doctype html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="/resource/css/layout.css"/>
    </head>
    
    <body>
            <div class="titlebar">
                <img src="/resource/images/blue/star.png" />
                &nbsp;${S_TENANT!""}评分汇总表
            </div>
            <table class="table">
            <thead>
                <th class="th_left">元素名称</th>
                <th>标准分值</th>
                <th>空项分</th>
                <th>应得分</th>
                <th>实际得分</th>
                <th>百分制得分</th>
            </thead>
            <tbody>
            <#list list as r>
            <tr>
                <td>${r.C_PROJECT}</td>
                <td class="td_center">${r.N_SUBTOTAL}</td>
                <td class="td_center">${r.N_BLANK}</td>
                <td class="td_center">${r.N_GET}</td>
                <td class="td_center">${r.N_REAL}</td>
                <td class="td_center">${r.N_P}</td>
            </tr> 
            </#list>
            </tbody>
            </table>
    </body>
</html>
