<@layout.extends name="../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/home.css"/>
    </@>

    <@layout.put block="contents">
    <div class="article toolbar">
        <div class="content">
            <ul class="toollist ue-clear">
                <li>
                    <a href="/e/stdtmp/" target="_top" class="img">
                        <img src="/resource/images/icon01.png"/>

                        <p>体系创建</p>
                    </a>
                </li>
                <li style="padding-top:6px;width:100px;margin-left:5px;margin-right:0;">
                    <a href="/e/stdtmp/query">
                        <p>未创建项/合计项</p>
                        <span style="color:red;font-size:20px;">${FILE_NO_CREATE}/${FILE_COUNT}</span>
                    </a>
                </li>
                <li style="padding-top:5px;margin-left:0;"><img src="/resource/images/arrow_right.png"/></li>
                <li>
                    <a href="/e/grade_m/" class="img">
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
                    <a href="/e/grade_m/review_result" class="img">
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
                        <tr class="ue-clear">
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
                    <ul class="tabs">
                        <li id="t1" target="#tc1" class="current">未完成要素</li>
                        <li id="tabz1" target="#tc2">隐患排查</li>
                        <li id="tabz2" target="#tc3">特种设备</li>
                        <li id="tabz3" target="#tc4">特种人员</li>
                    </ul>
                </div>
            <#-- <a href="/e/grade_m/index" class="more">更多</a> -->
            </div>
            <div id="tbc" class="content" style="height:280px">
                <div id="tc1">
                    <table class="matter-list">
                        <#if rs_todo?? && rs_todo?size!=0>
                            <#list rs_todo as rs>
                                <tr>
                                    <td class="more">
                                        <a href="${rs.C_URL!"/e/stdtmp/tree?selected=${rs.R_SID}"}" target="_top" class="matter-title">${rs.C_TITLE}</a>
                                    </td>
                                    <td class="matter-time">${rs.T_CREATE?date}</td>
                                </tr>
                            </#list>
                            <tr>
                                <td colspan="2" style="text-align: right; padding-right: 10px"><a href="/e/stdtmp/tree" target="_top" style="color: #ff0000;">>>更多</a></td>
                            </tr>
                        <#else>
                            <tr class="ue-clear">
                                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
                            </tr>
                        </#if>
                    </table>
                </div>
                <div id="tc2" style="display: none">
                    <table class="matter-list">
                        <colgroup>
                            <col>
                            <col width="80px">
                        </colgroup>
                        <#if rs_yh?? && rs_yh?size!=0>
                            <#list rs_yh as rs>
                                <tr>
                                    <td class="more">
                                        <a href="${rs.C_URL!"/e/stdtmp/tree"}" target="_top" class="matter-title">“${rs.C_NAME}”（要求整改时间：${rs.T_RECTIFICATION}）</a>
                                    </td>
                                    <td class="matter-time">${rs.T_CREATE?date}</td>
                                </tr>
                            </#list>
                            <tr>
                                <td colspan="2" style="text-align: right; padding-right: 10px"><a href="/e/stdtmp/tree" target="_top" style="color: #ff0000;">>>更多</a></td>
                            </tr>
                        <#else>
                            <tr class="ue-clear">
                                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
                            </tr>
                        </#if>
                    </table>
                </div>
                <div id="tc3" style="display: none">
                    <table class="matter-list">
                        <colgroup>
                            <col>
                            <col width="80px">
                        </colgroup>
                        <#if rs_dev?? && rs_dev?size!=0>
                            <#list rs_dev as rs>
                                <tr>
                                    <td class="more">
                                        <a href="${rs.C_URL!"/e/stdtmp/tree"}" target="_top" class="matter-title">“${rs.C_NAME}(${rs.C_SPEC!})”临近下次检验(${rs.T_TEST_NEXT!}）</a>
                                    </td>
                                    <td class="matter-time">${rs.T_CREATE?date}</td>
                                </tr>
                            </#list>
                            <tr>
                                <td colspan="2" style="text-align: right; padding-right: 10px"><a href="/e/stdtmp/tree" target="_top" style="color: #ff0000;">>>更多</a></td>
                            </tr>
                        <#else>
                            <tr class="ue-clear">
                                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
                            </tr>
                        </#if>
                    </table>
                </div>
                <div id="tc4" style="display: none">
                    <table class="matter-list">
                        <colgroup>
                            <col>
                            <col width="80px">
                        </colgroup>
                        <#if rs_ry?? && rs_ry?size!=0>
                            <#list rs_ry as rs>
                                <tr>
                                    <td class="more">
                                        <a href="${rs.C_URL!"/e/stdtmp/tree"}" target="_top" class="matter-title">“${rs.C_DEPT}-${rs.C_NAME}(${rs.C_WORKTYPE})”特种作业人员证书即将到期（复审时间${rs.T_CERT_REVIEW}）</a>
                                    </td>
                                    <td class="matter-time">${rs.T_CREATE?date}</td>
                                </tr>
                            </#list>
                            <tr>
                                <td colspan="2" style="text-align: right; padding-right: 10px"><a href="/e/stdtmp/tree" target="_top" style="color: #ff0000;">>>更多</a></td>
                            </tr>
                        <#else>
                            <tr class="ue-clear">
                                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
                            </tr>
                        </#if>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="article hright matter">
        <div class="wrap-r">
            <div class="title ue-clear">
                <h2>统计分析</h2>
            </div>
            <div class="content" style="height:200px;text-align:center;">
                <#--<img src="/resource/images/chart_e01.png"/>-->
                <img src="/e/charts/chart01" width="100%"/>
            </div>
            <div class="content" style="height:200px;text-align:center;">
                <#--<img src="/resource/images/chart_e02.png"/>-->
                <img src="/e/charts/chart02" width="100%"/>
            </div>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>

    <script type="text/javascript">
        $(".duty").find("tbody").find("tr:even").css("backgroundColor", "#eff6fa");

        $("#more").on("click", function () {
            window.location.href = "/e/" + (aIndex == 0 ? "todo" : "done") + "/index";
        });

        $(function () {
            $('.title .tabs>li').click(function () {
                $('#tbc>div').hide();
                $('.title .tabs>li').removeClass('current');
                $($(this).addClass('current').attr('target')).show();
            });
        });

        function nav(url) {
            window.location.href = url;
        }

        function nav2(url) {
            window.top.location.href = url;
        }

        function doCreate() {
            window.location.href = '/e/grade_m/rec_new';
        }

        function doChangePwd() {

        }

        function doConfig() {

        }
    </script>
    </@>

</@>
