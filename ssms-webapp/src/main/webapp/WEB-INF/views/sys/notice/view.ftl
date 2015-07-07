<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link rel="stylesheet" href="/resource/css/layout.css"/>
<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<style>
    BODY {
        margin: 0;
        padding: 0;
    }
    BODY {
        font-size: 12px;
    }
    BODY {
        background: #fff;
    }
    HTML BODY {
        height: 100%;
    }
    .layout {
        width: 990px;
        margin: 0 auto;
        overflow: hidden;
        zoom: 1;
    }
    .mt19 {
        margin-top: 11px;
    }
    .layoutleft {
        width: 990px;
        float: left;
    }
    #artcon {
        width: 990px;
        padding-right: 25px;
        border-right: #e6e6e6 solid 1px;
    }
    H3 {
        margin: 0;
        padding: 0;
    }
    #artcon H3.title {
        font: 700 30px/40px "Microsoft Yahei","冬青黑体简体中文w3";
        padding: 2px 0 0;
        text-align:center;
    }
    H5 {
        margin: 0;
        padding: 0;
    }
    #artcon H5.data {
        height: 24px;
        font-size: 12px;
        font-weight: normal;
        line-height: 24px;
        padding: 10px 0;
        color: #999;
        border-bottom: #d2d2d2 solid 2px;
    }
    #arttext {
        font: 16px/30px "Microsoft Yahei","冬青黑体简体中文w3";
        position: relative;
        overflow: hidden;
    }
    .fl {
        float: left;
    }
    #artcon H5.data .fl {
        padding-right: 14px;
    }
    .fr {
        float: right;
    }
    #artcon H5.data SPAN.fontzoom {
        width: 55px;
        padding-left: 10px;
    }
    A {
        text-decoration: none;
    }
    #artcon H5.data SPAN.fontzoom A {
        background: url(../img/bg_fontzoom.gif)no-repeat 0 0;
        display: block;
        float: left;
        height: 24px;
        width: 24px;
        text-indent: -999em;
    }
    #artcon H5.data SPAN.fontzoom A.small {
        margin-right: 7px;
    }
    #artcon H5.data SPAN.fontzoom A.big {
        background-position: 0 -24px;
    }
</style>
</head>

<BODY>
    <DIV class="content" style="padding-bottom:10px;">
        <div id="tb" class="titlebar_noborder">
                <img src="/resource/images/blue/star.png"/>&nbsp;通知公告
                <span class="backing">
                    <a href="#" class="easyui-linkbutton" data-options="plain: true" iconCls="icon-back" onclick="window.location.href='${referer!}';">返回</a>
                </span>
        </div>
        <DIV id=container>
            <DIV id=content>
                <DIV class="layout mt19">
                    <DIV id=artcon class=layout>
                        <H3 class=title>
                            ${C_TITLE!}
                        </H3>
                        <H5 class=data>
                            <SPAN class=fl>
                                <SPAN id=pubtime>
                                    ${T_CREATE!}
                                </SPAN>
                                共计
                                <A id=count_show>
                                    ${N_READER!1}
                                </A>
                                次阅读 发布人：
                                <SPAN id=source>
                                    ${S_CREATE!}
                                </SPAN>
                            </SPAN>
                            <SPAN class="fontzoom fr">
                                <A class=small href="javascript:doZoom(16)">
                                    小
                                </A>
                                <A class=big href="javascript:doZoom(18)">
                                    中
                                </A>
                            </SPAN>
                        </H5>
                        <DIV id=arttext class=bort>
                            ${C_CONTENT!}
                        </DIV>
                    </DIV>
                </DIV>
            </DIV>
        </DIV>
    </DIV>
</BODY>

</html>
