<#import "/layout/_list.ftl" as layout/>
<#assign header>
<script type="text/javascript" src="/resource/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="/resource/css/zTreeStyle/zTreeStyle.css"/>
<style type="text/css">
    body {
        overflow: hidden;
        margin-top: 40px;
    }
</style>
</#assign>
<#assign script>
<script type="text/javascript">
    function zTreeOnClick(event, treeId, treeNode) {
        $('#mainFrame').attr("src", 'rec.html?sid='+treeNode.SID);
    }

    var setting = {
        data: {
            key: {
                name: "C_NAME"
            },
            simpleData: {
                enable: true,
                idKey: "SID",
                pIdKey: "R_SID"
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };

    $(document).ready(function () {
        var demoIframe = $("#mainFrame");
        $.post('tree.json', {profession: '${profession}'}, function (result) {
            if ($.isArray(result))
                $.fn.zTree.init($("#treeDemo"), setting, result).expandAll(true);
            demoIframe.bind("load", loadReady);
        });

        function loadReady() {
            var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
                    htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
                    maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
                    h = demoIframe.height() >= maxH ? minH:maxH ;
            if (h < 530) h = 530;
            demoIframe.height(h);
        }
    });
</script>
</#assign>
<@layout.doLayout script=script header=header>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="">
            <!--container-->
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">首页 ></a></li>
                    <li class="active"><a href="#">管理中心 ></a></li>
                    <li class="active"><a href="#">达标体系模板管理 ></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<table border="0" style="height: 600px" align="left">
    <tr>
        <td width="260px" align="left" valign="top" style="border-right: #999999 1px dashed">
            <ul id="treeDemo" class="ztree" style="width: 260px; overflow: auto;"></ul>
        </td>
        <td width="100%" align="left" valign="top">
            <iframe id="mainFrame" name="mainFrame" frameborder="0" scrolling="auto" width="100%"></iframe>
        </td>
    </tr>
</table>

</@layout.doLayout>