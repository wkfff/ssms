<#--未创建要素-->
<#if todo.createTodo?size!=0>
<@layout.put block="tabs_title">
<li id="t1" target="#tc1" class="current">未创建要素</li>
</@>
<@layout.put block="tabs_content">
<div id="tc1">
    <table class="matter-list">
        <#if todo.createTodo?? && todo.createTodo?size!=0>
            <#list todo.createTodo as rs>
                <tr>
                    <td class="more">
                        <a href="${rs.C_URL!"/e/stdtmp/tree?selected=${rs.SID!}"}" target="_top" class="matter-title">${rs.C_NAME!}</a>
                    </td>
                    <td class="matter-time">${(rs.T_CREATE?date)!}</td>
                </tr>
            </#list>
            <tr>
                <td colspan="2" style="text-align: right; padding-right: 10px">
                    <a href="/e/stdtmp/tree" target="_top" style="color: #ff0000;">>>更多</a></td>
            </tr>
        <#else>
            <tr>
                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
            </tr>
        </#if>
    </table>
</div>
</@>
</#if>

<#--未完成要素-->

<#--隐患排查-->
<@layout.put block="tabs_title">
<li id="tabz1" target="#tc2">隐患排查</li>
</@>
<@layout.put block="tabs_content">
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
                        <a href="/e/todo/stdtmp06/rec?sid=${rs.srcId!}" target="_top" class="matter-title">${rs.title!}</a>
                    </td>
                    <td class="matter-time">${(rs.notifyTime?date)!}</td>
                </tr>
            </#list>
            <tr>
                <td colspan="2" style="text-align: right; padding-right: 10px">
                    <a href="/e/todo/stdtmp06/" target="_top" style="color: #ff0000;">>>更多</a></td>
            </tr>
        <#else>
            <tr>
                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
            </tr>
        </#if>
    </table>
</div>
</@>


<#--特种设备-->
<@layout.put block="tabs_title">
<li id="tabz2" target="#tc3">特种设备</li>
</@>
<@layout.put block="tabs_content">
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
                        <a href="/e/todo/stdtmp08/rec?sid=${rs.srcId!}" target="_top" class="matter-title">${rs.title!}</a>
                    </td>
                    <td class="matter-time">${(rs.notifyTime?date)!}</td>
                </tr>
            </#list>
            <tr>
                <td colspan="2" style="text-align: right; padding-right: 10px">
                    <a href="/e/todo/stdtmp08/" target="_top" style="color: #ff0000;">>>更多</a></td>
            </tr>
        <#else>
            <tr>
                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
            </tr>
        </#if>
    </table>
</div>
</@>

<#--特种人员-->
<@layout.put block="tabs_title">
<li id="tabz3" target="#tc4">特种人员</li>
</@>
<@layout.put block="tabs_content">
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
                        <a href="/e/todo/stdtmp07/rec?sid=${rs.srcId!}" target="_top" class="matter-title">${rs.title!}</a>
                    </td>
                    <td class="matter-time">${(rs.notifyTime?date)!}</td>
                </tr>
            </#list>
            <tr>
                <td colspan="2" style="text-align: right; padding-right: 10px">
                    <a href="/e/todo/stdtmp07/" target="_top" style="color: #ff0000;">>>更多</a></td>
            </tr>
        <#else>
            <tr>
                <td colspan="2" style="padding-left:5px;">暂时还没有待办。</td>
            </tr>
        </#if>
    </table>
</div>
</@>