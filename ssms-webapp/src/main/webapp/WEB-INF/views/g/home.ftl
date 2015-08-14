<@layout.extends name="../_layouts/base.ftl">
    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/home.css"/>
    <style type="text/css">
        .images img {
            vertical-align: top;
        }
    </style>
    </@>

    <@layout.put block="contents">
    <div class="article half notice">
        <div class="wrap-l">
            <div class="title ue-clear">
                <h2>收到的公告</h2>
                <a href="/sys/notice/receives" class="more">更多</a>
            </div>
            <div class="content">
                <table class="notice-list">
                    <#if rs_notice?? && rs_notice?size!=0>
                        <#list rs_notice as rs>
                            <tr>
                                <td class="more">
                                    <a href="/sys/notice/view?sid=${rs.SID}" class="notice-title" title="${rs.C_TITLE!}">${rs.C_TITLE!}</a>
                                </td>
                                <td class="notice-time">${rs.T_PUBLISH!}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td style="padding-left:5px;">暂时还没有通知公告。</td>
                        </tr>
                    </#if>
                </table>
            </div>
        </div>
    </div>
    <div class="article half notice" style="margin-top: 0;">
        <div class="wrap-r">
            <div class="title ue-clear">
                <h2>发布的公告</h2>
                <a href="/sys/notice/publics" class="more">更多</a>
            </div>
            <div class="content">
                <table class="notice-list">
                    <#if rs_notice?? && rs_notice?size!=0>
                        <#list rs_notice as rs>
                            <tr>
                                <td class="more">
                                    <a href="/sys/notice/view?sid=${rs.SID}" class="notice-title" title="${rs.C_TITLE!}">${rs.C_TITLE!}</a>
                                </td>
                                <td class="notice-time">${rs.T_PUBLISH!}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td style="padding-left:5px;">暂时还没有通知公告。</td>
                        </tr>
                    </#if>
                </table>
            </div>
        </div>
    </div>

    <div class="article toolbar">
        <div class="title ue-clear">
            <h2>统计分析</h2>
            <a href="/r/statistics/index" class="more">更多</a>
        </div>
        <div class="content">
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript" src="/resource/js/costants.js"></script>
    </@>
</@>
