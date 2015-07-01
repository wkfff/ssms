<@layout.extends name="../_layouts/base.ftl">
    <@layout.put block="head">
    <script type="text/javascript">
        setInterval(function () {
            window.location.href = "${BASE_PATH}/status?_"+new Date().valueOf()+"#this";
        }, 3000);
    </script>
    </@>
    <@layout.put block="contents">
    <div style="  width: 800px; margin: 0 auto;">
        <div style="overflow: auto; max-height: 400px;">
            <#list LOGS as log>
                <p>${log}</p>
            </#list>
            <div id="this"></div>
        </div>
        <div>
            <#if STATUS=='FINISH'>
                <a href="${BASE_PATH}/finish">
                    <button>完成初始化</button>
                </a>
            </#if>
        </div>

    </div>
    </@>
</@>