<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/resource/css/base.css" />
    <link rel="stylesheet" href="/resource/css/home.css" />
    <title>安全生产标准化管理系统</title>
    <style type="text/css">
        a:visited{
            color: black;
        }
        ul.matter-list {
            padding: 0 11px;
        }

        ul.matter-list li {
            padding: 1px 0;
        }

        ul.matter-list li span.matter-time {
            float: right;
        }

        ul.matter-list li a.matter-title {
            background: url('/resource/images/leftjt04.png') no-repeat 0 center;
            padding-left: 10px;
        }
    </style>
</head>

<body>
<div class="article toolbar">
    <div class="content" style="border: 1px solid #c1d3de;">
        <ul class="toollist ue-clear" >
            <li style="width:;">
                <a href="/e/stdtmp/" target="_top" class="img"><img src="/resource/images/icon01.png" />
                <p>体系创建</p></a>
            </li>
            <li style="padding-top:6px;width:100px;margin-left:5px;margin-right:0;">
                <a href="/e/stdtmp/query"><p>未创建项/合计项</p><span style="color:red;font-size:20px;">${FILE_NO_CREATE}/${FILE_NO_CREATE+FILE_COUNT}</span></a>
            </li>
            <li style="padding-top:5px;margin-left:0;"><img src="/resource/images/arrow_right.png" /></li>
            <li>
                <a href="/e/grade_m/" class="img"><img src="/resource/images/icon04.png" />
                <p>在线自评</p></a>
            </li>
            <li style="padding-top:5px;"><img src="/resource/images/arrow_right.png" /></li>
            <li>
                <a href="http://aqbzh.chinasafety.gov.cn/sps/loginaction!initPage.action" target="_blank" class="img"><img src="/resource/images/icon02.png" />
                <p>申报评审</p></a>
            </li>
            <li style="padding-top:5px;"><img src="/resource/images/arrow_right.png" /></li>
            <li>
                <a href="javascript:void;" class="img"><img src="/resource/images/icon09.png" /></a>
                <p><a href="javascript:void;">评审管理</a></p>
            </li>
        </ul>
    </div>
</div>
<div class="article hleft notice">
    <div class="wrap-l">
        <div class="title ue-clear">
            <h2>通知公告</h2>
            <a href="/e/notice/publics" class="more">更多</a>
        </div>
        <div class="content" >
            <ul class="notice-list">
                  <#if rs_notice?exists && rs_notice?size!=0>
                    <#list rs_notice as rs>
                     <li class="ue-clear">
                        <a href="javascript:nav('/e/notice/view?sid=${rs.SID}');" class="notice-title">${rs.C_TITLE}</a>
                        <div class="notice-time">${rs.T_PUBLISH}</div>
                    </li>
                    </#list>
                    <#else>
                        <li class="ue-clear">
                        <span style="padding-left:5px;">暂时还没有通知公告。</span>
                        </li>
                  </#if>
            </ul>
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
        <div id="tbc" class="content" style="height:330px">
            <div id="tc1">
                <ul class="matter-list">
                <#if rs_todo?? && rs_todo?size!=0>
                    <#list rs_todo as rs>
                        <li class="ue-clear">
                            <span class="matter-time">${rs.T_CREATE?date}</span>
                            <a href="javascript:nav2('${rs.C_URL!"/e/stdtmp/tree?selected=${rs.SID}"}');" class="matter-title">${rs.C_NAME}</a>
                        </li>
                    </#list>
                    <li class="ue-clear"><a href="/e/stdtmp/" style="float: right; color: #ff0000">>>更多</a></li>
                <#else>
                    <li class="ue-clear">
                        <span style="padding-left:5px;">暂时还没有待办。</span>
                    </li>
                </#if>
                </ul>
            </div>
            <div id="tc2" style="display: none">
                <ul class="matter-list">
                <#if rs_yh?? && rs_yh?size!=0>
                    <#list rs_yh as rs>
                        <li class="ue-clear">
                            <span class="matter-time">${rs.T_CREATE?date}</span>
                            <a href="javascript:nav2('${rs.C_URL!"/e/stdtmp/"}');" class="matter-title">“${rs.C_NAME}”</a>（要求整改时间：${rs.T_RECTIFICATION}）
                        </li>
                    </#list>
                    <li class="ue-clear"><a href="/e/stdtmp/" style="float: right; color: #ff0000">>>更多</a></li>
                <#else>
                    <li class="ue-clear">
                        <span style="padding-left:5px;">暂时还没有待办。</span>
                    </li>
                </#if>
                </ul>
            </div>
            <div id="tc3" style="display: none">
                <ul class="matter-list">
            <#if rs_dev?? && rs_dev?size!=0>
                <#list rs_dev as rs>
                    <li class="ue-clear">
                        <span class="matter-time">${rs.T_CREATE?date}</span>
                        <a href="javascript:nav2('${rs.C_URL!"/e/stdtmp/"}');" class="matter-title">“${rs.C_NAME}(${rs.C_SPEC!})”</a>临近下次检验(${rs.T_TEST_NEXT!}）
                    </li>
                </#list>
                <li class="ue-clear"><a href="/e/stdtmp/" style="float: right; color: #ff0000">>>更多</a></li>
            <#else>
                <li class="ue-clear">
                    <span style="padding-left:5px;">暂时还没有待办。</span>
                </li>
            </#if>
                </ul>
            </div>
            <div id="tc4" style="display: none">
                <ul class="matter-list">
                <#if rs_ry?? && rs_ry?size!=0>
                    <#list rs_ry as rs>
                        <li class="ue-clear">
                            <span class="matter-time">${rs.T_CREATE?date}</span>
                            <a href="javascript:nav2('${rs.C_URL!"/e/stdtmp/"}');" class="matter-title">“${rs.C_DEPT}-${rs.C_NAME}(${rs.C_WORKTYPE})”</a>特种作业人员证书即将到期（复审时间${rs.T_CERT_REVIEW}）
                        </li>
                    </#list>
                    <li class="ue-clear"><a href="/e/stdtmp/" style="float: right; color: #ff0000">>>更多</a></li>
                <#else>
                    <li class="ue-clear">
                        <span style="padding-left:5px;">暂时还没有待办。</span>
                    </li>
                </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="article hright matter">
    <div class="wrap-r">
	    <div class="article toolbar" >
		    <div class="title ue-clear">
		        <h2>统计分析</h2>
		        <#--<a href="/e/statistics/index" class="more">更多</a>-->
		    </div>
		    <div class="content" style="height:200px;text-align:center;">
		        <img src="/resource/images/chart_e01.png" />
		    </div>
		    <div class="content" style="height:200px;text-align:center;">
		        <img src="/resource/images/chart_e02.png" />
		    </div>
		    <#--
		    <div class="content" style="height:500px;">
		        <iframe src="/charts.html" frameborder="0" width="100%" height="100%"></iframe>
		    </div>
		    -->
    	</div>
	</div>
</div>

</body>

<script type="text/javascript" src="/resource/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
<script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
    $(".duty").find("tbody").find("tr:even").css("backgroundColor","#eff6fa");

    $("#more").on("click",function(){
        window.location.href = "/e/"+(aIndex==0?"todo":"done")+"/index";
    });

    $(function () {
        $('.title .tabs>li').click(function(){
            $('#tbc>div').hide();
            $('.title .tabs>li').removeClass('current');
            $($(this).addClass('current').attr('target')).show();
        });
    });

    function nav(url){
        window.location.href = url;
    }

    function nav2(url){
        window.top.location.href = url;
    }

    function doCreate(){
        window.location.href = '/e/grade_m/rec_new';
    }

    function doChangePwd(){

    }

    function doConfig(){

    }
</script>
</html>
