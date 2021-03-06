<@layout.extends name="_layouts/index.ftl">
    <@layout.put block="containers">
    <div class="wrap ue-clear">
        <div class="sidebar">
            <h2 class="sidebar-header"><p>功能导航</p></h2>
            <ul class="nav">
                <li class="home current">
                    <div class="nav-header">
                        <a href="javascript:void(0);" date-src="${BASE_PATH}/home" class="ue-clear"><span>首页</span><i class="icon"></i></a>
                    </div>
                </li>
                <#list nav as map>
                    <li class="<#if !map.attributes.C_ICON??||map.attributes.C_ICON?length=0>office<#else>${map.attributes.C_ICON}</#if>">
                        <div class="nav-header">
                            <a href="javascript:void(0);" date-src="${map.attributes.C_URL!""}" class="ue-clear"><span>${map.text}</span><i class="icon"></i></a>
                        </div>
                        <#if (map.children?size>0)>
                            <ul class="subnav">
                                <#list map.children as item>
                                    <li>
                                        <a href="javascript:void(0);" date-src="${item.attributes.C_URL!}">${item.text}</a>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>
            </ul>
        </div>
        <div class="content">
            <iframe src="${HOME_PAGE!"/home.html"}" id="iframe" width="100%" height="100%" frameborder="0"></iframe>
        </div>
    </div>
    </@>

    <@layout.put block="footer">
    <script type="text/javascript">
        $(function () {
            $(window).resize(function (e) {
                $(".wrap").height($("#bd").height() - 6);
                $(".nav").css("minHeight", $(".sidebar").height() - $(".sidebar-header").height() - 1);
                $("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height() - 12);
            }).resize();

            $(".nav>li").css({"borderColor": "#dbe9f1"});
            $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
            $(".nav").on("click", "li", function (e) {
                $(this).siblings().removeClass("current").removeClass("hasChild");
                var hasChild = !!$(this).find(".subnav").length > 0;
                if (hasChild) {
                    $(this).toggleClass("hasChild");
                }
                $(this).addClass("current");

                var aurl = $(this).find("a").attr("date-src");
                if (aurl == "") return;
                if (aurl == "/e/stdtmp/") window.location.href = aurl;
                else if (aurl == "/e/stdtmp/tree") window.location.href = aurl;
                else {
                    $("#iframe").attr("src", aurl);
                    $(".nav>li").css({"borderColor": "#dbe9f1"});
                    $(".nav>.current").prev().css({"borderColor": "#7ac47f"});
                }
                return false;
            });
        });
    </script>
    </@>
</@>