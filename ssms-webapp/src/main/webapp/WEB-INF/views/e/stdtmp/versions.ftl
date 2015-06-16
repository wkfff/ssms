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
                &nbsp;13要素版本
            </div>
            <table class="table">
                <tbody>
                    <tr>
                        <td>
                            <a href="view/0">查看当前版本</a>
                        </td>
                    </tr>
                    <#list list as r>
                        <tr <#if (r_index+1)%2==1>class="oddrow"</#if> >
                            <td>
                                <a href="view/${r.N_VERSION!}">查看版本【${r.N_VERSION!}】</a>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
    </body>
</html>
