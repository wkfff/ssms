<@layout.extends name="../_layouts/base.ftl">

    <@layout.put block="head">
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/home.css"/>
    <script type="text/javascript" src="home.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    </@>

    <@layout.put block="contents">
    <div class="search" align="right" style="background:url(/resource/images/tu15.jpg);padding-right:60px;">
        <div style="background-color:rgb(104,183,209);padding:3px 3px 2px 5px;width:700px;" align="center">
            <input id="cb_city" style="width:80px;" data-bind="comboboxValue:comboCity,easyuiOptions:comboCitySettings">&nbsp;市&nbsp;&nbsp;
            <input class="easyui-combobox" id="cb_county" style="width:80px;" data-bind="comboboxValue:comboCounty,easyuiOptions:comboCountySettings">&nbsp;区/县&nbsp;&nbsp;
            <input id="txt_name" style="width:380px;height:24px;line-height:24px;" data-bind="textboxValue:txtName" placeholder="请输入要查找的企业">
            <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-bind="click:gridEvents.refreshClick">搜索企业</a>
        </div>


    </div>
    <div class="article half notice">
        <div class="wrap-l">
            <div class="title ue-clear">
                <h2>接收公告</h2>
                <a href="/sys/notice/publics" class="more">更多</a>
            </div>
            <div class="content">
                <table class="notice-list">
                    <#if rs_notice?? && rs_notice?size!=0>
                        <#list rs_notice as rs>
                            <tr>
                                <td class="more">
                                    <a href="/sys/notice/view?sid=${rs.SID}" class="notice-title" title="${rs.C_TITLE}">${rs.C_TITLE}</a>
                                </td>
                                <td class="notice-time">${rs.T_PUBLISH}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="2" style="padding-left:5px;">没有接收公告。</td>
                        </tr>
                    </#if>
                </table>
            </div>
        </div>
    </div>

    <div class="article half notice">
        <div class="wrap-r">
            <div class="title ue-clear">
                <h2>已发公告</h2>
                <a href="/r/notice/publics" class="more">更多</a>
            </div>
            <div class="content">
                <table class="notice-list">
                    <#if rs_notice2?? && rs_notice2?size!=0>
                        <#list rs_notice2 as rs>
                            <tr>
                                <td class="more">
                                    <a href="/r/notice/view?sid=${rs.SID}" class="notice-title" title="${rs.C_TITLE}">${rs.C_TITLE}</a>
                                </td>
                                <td class="notice-time">${rs.T_PUBLISH}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="2" style="padding-left:5px;">没有已发公告。</td>
                        </tr>
                    </#if>
                </table>
            </div>
        </div>
    </div>

    <div class="article half matter">
        <div class="wrap-l">
            <div class="title ue-clear">
                <h2>正在评审</h2>
                <a href="/r/grade_m/" class="more">更多</a>
            </div>
            <div class="content">
                <table class="matter-list">
                    <#if rs_todo?? && rs_todo?size!=0>
                        <#list rs_todo as rs>
                            <tr>
                                <td class="more">
                                    <a href="${rs.C_URL!}" class="matter-title" title="${rs.C_TITLE}">${rs.C_TITLE}</a>
                                </td>
                                <td class="matter-time">${rs.T_BEGIN!?date}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="2" style="padding-left:5px;">没有正在评审的企业。</td>
                        </tr>
                    </#if>
                </table>
            </div>
        </div>
    </div>

    <div class="article half matter">
        <div class="wrap-r">
            <div class="title ue-clear">
                <h2>评审完成</h2>
                <a href="/r/grade_m/" class="more">更多</a>
            </div>
            <div class="content">
                <table class="matter-list">
                    <#if rs_done?? && rs_done?size!=0>
                        <#list rs_todo as rs>
                            <tr>
                                <td class="more">
                                    <a href="${rs.C_URL!}" class="matter-title" title="${rs.C_TITLE}">${rs.C_TITLE}</a>
                                </td>
                                <td class="matter-time">${rs.T_END!?date}</td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="2" style="padding-left:5px;">没有评审完成的企业。</td>
                        </tr>
                    </#if>
                </table>
            </div>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript">
        $(function () {
            ko.applyBindings(new ViewModel());
        })
    </script>
    </@>

</@>