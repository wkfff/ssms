<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta name="viewport" content="width=620"/>
    <title>通知公告</title>
    <style>
        body {
            padding: 0;
            margin: 0;
            border: 0;
            font-size: 14px;
            font-family: "宋体";
        }

        .mainBox {
            width: 1000px;
            height: auto;
            margin: 20px auto;
            background: #F3FDFF;
            border: 1px solid #E2E7EA;
            border-radius: 20px;
            box-shadow: 0 0 1px 1px rgba(0, 0, 0, 0.1);
            padding-bottom: 30px;
        }

        .titile {
            width: 1000px;
            height: auto;
            margin: 20px auto;
            background: #F3FDFF;
            border: 1px solid #E2E7EA;
            border-radius: 20px;
            box-shadow: 0 0 1px 1px rgba(0, 0, 0, 0.1);
            padding-bottom: 10px;
        }

        .titileTop {
            margin: 15px 0px 5px 10px;

        }

        .newsTop {
            margin: 15px 0 0;
            text-align: center;
        }

        .nCont p {
            line-height: 1.5em;
            margin: 15px 35px 0 35px;
            word-wrap: break-word;
            word-break: break-all;
            color: #666;
            font-family: 宋 体, simsun;
            text-indent: 2em;
        }

        .nCont p img {
            width: 600px;
        }

    </style>
</head>
<body>
<!-- 头部开始 -->
<div class="titile">
    <div class="titileTop">
        主&nbsp;&nbsp;题：${C_TITLE!}<br/><br/>
        接收单位：
    </div>
</div>

<!-- 头部结束 -->
<!-- 内容开始 -->

<div class="mainBox">
    <div class="nCont">
    ${C_CONTENT!}
    </div>
    <!-- 内容结束 -->
</div>

<#if noticeFile?? && noticeFile?size gt 0 >
<!-- 头部开始 -->
<div class="titile">
    <div class="titileTop">
        <table id="item">
            <#list noticeFile as list>
                <tr>
                    <td><a href="/sys/attachfile/down?id=${list.SID!}"> ${list.C_OUTER_NAME!}.${list.C_EXT!}</a></td>
                </tr>
            </#list>
        </table>
    </div>
</div>
</#if>
<!-- 尾部开始 -->
<div id="footer">

</div>
<!-- 尾部结束 -->
</body>
</html>