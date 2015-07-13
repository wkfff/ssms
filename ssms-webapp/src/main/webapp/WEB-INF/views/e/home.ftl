<@layout.extends name="../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/home.css"/>
    </@>

    <#include "todo.ftl">

    <@layout.put block="contents">
    <div class="article toolbar">
        <div class="content">
            <ul class="toollist ue-clear">
                <li>
                    <a href="/e/stdtmp/tree" target="_top" class="img">
                        <img src="/resource/images/icon01.png"/>

                        <p>体系创建</p>
                    </a>
                </li>
                <li style="padding-top:6px;width:100px;margin-left:5px;margin-right:0;">
                    <a href="/e/stdtmp/view/0-0">
                        <p>未创建项/合计项</p>
                        <span style="color:red;font-size:20px;">${todo.notCreateFileCount}/${todo.fileCount}</span>
                    </a>
                </li>
                <li style="padding-top:5px;margin-left:0;"><img src="/resource/images/arrow_right.png"/></li>
                <li>
                    <a href="/e/gradeplan/" class="img">
                        <img src="/resource/images/icon04.png"/>

                        <p>在线自评</p>
                    </a>
                </li>
                <li style="padding-top:5px;"><img src="/resource/images/arrow_right.png"/></li>
                <li>
                    <a href="http://aqbzh.chinasafety.gov.cn/sps/loginaction!initPage.action" target="_blank" class="img">
                        <img src="/resource/images/icon02.png"/>

                        <p>申报评审</p>
                    </a>
                </li>
                <li style="padding-top:5px;"><img src="/resource/images/arrow_right.png"/></li>
                <li>
                    <a href="/e/reviewresult/" class="img">
                        <img src="/resource/images/icon09.png"/>

                        <p>评审管理</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="article hleft notice">
        <div class="wrap-l">
            <div class="title ue-clear">
                <h2>通知公告</h2>
                <a href="/sys/notice/publics" class="more">更多</a>
            </div>
            <div class="content">
                <table class="notice-list">
                    <#if rs_notice?? && rs_notice?size!=0>
                        <#list rs_notice as rs>
                            <tr>
                                <td class="more">
                                    <a href="/sys/notice/view?sid=${rs.SID}" title="${rs.C_TITLE!}" class="notice-title">${rs.C_TITLE!}</a>
                                </td>
                                <td class="notice-time">${rs.T_PUBLISH!}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="2"><span style="padding-left:5px;">暂时还没有通知公告。</span></td>
                        </tr>
                    </#if>
                </table>
            </div>
        </div>
        <div class="wrap-l" style="margin-top:6px;">
            <div class="title ue-clear">
                <h2 style="margin-right:10px;width:200px;">工作提醒</h2>

                <div style="float: right">
                    <ul class="tabs"><@layout.block name="tabs_title"/></ul>
                </div>
            </div>
            <div id="tbc" class="content" style="height:280px">
                <@layout.block name="tabs_content"/>
            </div>
        </div>
    </div>
    <div class="article hright">
        <div class="wrap-r">
            <div class="title ue-clear">
                <h2>统计分析</h2>
            </div>
            <div class="content" style="text-align: center">
                <img src="/e/charts/chart01" />
                <img src="/e/charts/chart02" />
            </div>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $('.title .tabs>li').click(function () {
                $('#tbc>div').hide();
                $('.title .tabs>li').removeClass('current');
                $($(this).addClass('current').attr('target')).show();
            });
        });
    </script>
    </@>

</@>
