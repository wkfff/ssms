<@layout.extends name="../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/home.css"/>
    <style>
        ul.tabn li {
            height: 20px;
            line-height: 20px;
            list-style-type: none;
            display: block;
            float: left;
            padding: 3px 8px 0 8px;
            margin-right: 2px;
            margin-top: 6px;
            -moz-border-radius: 4px 4px 0 0;
            -webkit-border-radius: 4px 4px 0 0;
            cursor: pointer;
        }
        ul.tabn li.current {
             border-bottom: 2px solid #43b0ce;
             background-color: #43b0ce;
       }
    </style>
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
                <div style="float: right">
                    <ul class="tabn"><li id="tabn1" target="#tcn2"  >评审端发布的通知公告</li></ul>
                </div>
                <div style="float: right">
                    <ul class="tabn"><li id="tn1" target="#tcn1" class="current" >接收到的通知公告</li></ul>
                </div>
            </div>
            <div class="content" id="tbn">
              <div id="tcn1">
                <table class="matter-list">
                    <#if rs_notice?? && rs_notice?size!=0>
                        <#list rs_notice as rs>
                            <tr>
                                <td class="more">
                                    <a href="/sys/notice/view?sid=${rs.SID}" title="${rs.C_TITLE!}" class="matter-title">${rs.C_TITLE!}</a>
                                </td>
                                <td class="matter-time">${(rs.T_PUBLISH?date)!}</td>
                            </tr>
                        </#list>
                            <tr>
                                <td colspan="2" style="text-align: right; padding-right: 10px">
                                        <a href="/sys/notice/publics"  target="_top"  style="color: #ff0000;" class="more">>>更多</a>
                                </td>
                           </tr>
                    <#else>
                        <tr>
                            <td colspan="2" style="padding-left:5px;">暂时还没有通知公告。</td>
                        </tr>
                    </#if>
                </table>
              </div>
                    <div id="tcn2" style="display: none">
                        <table class="matter-list">
                            <#if rs_review_notice?? && rs_review_notice?size!=0>
                            <#list rs_review_notice as rs>
                                <tr>
                                    <td class="more">
                                        <a href="/r/review_notice/publishForm?eid=${eid}&pro=${pro}&sid=${rs.SID!}&reader=true"  class="matter-title">${rs.C_TITLE!}</a>
                                    </td>
                                    <td class="matter-time">${(rs.T_PUBLISH?date)!}</td>
                               </tr>
                            </#list>
                               <tr>
                                    <td colspan="2" style="text-align: right; padding-right: 10px">
                                        <a href="/r/review_notice/publishs?eid=${eid}&pro=${pro}&reader=true"   style="color: #ff0000;" class="more">>>更多</a>
                                    </td>
                               </tr>
                           <#else>
                               <tr>
                                  <td colspan="2" style="padding-left:5px;">>暂时还没有通知公告。</td>
                               </tr>
                          </#if>
                  </table>
              </div>
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
            
            $('.title .tabn>li').click(function () {
                $('#tbn>div').hide();
                $('.title .tabn>li').removeClass('current');
                $($(this).addClass('current').attr('target')).show();
            });
        });
    </script>
    </@>

</@>
