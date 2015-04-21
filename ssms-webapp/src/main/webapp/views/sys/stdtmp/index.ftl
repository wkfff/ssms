<#import "/layout/_list.ftl" as layout/>
<#assign header>
<script type="text/javascript" src="/resource/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="/resource/css/zTreeStyle/zTreeStyle.css"/>
<style type="text/css">
    .left {
        width: 300px;
        float: left;
        border: 1px solid #000;
    }

    .right {
        margin-left: 310px;
        height: 100%;
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
        $.post('tree.json', {profession: '${profession}'}, function (result) {
            if ($.isArray(result)) $.fn.zTree.init($("#treeDemo"), setting, result).expandAll(true);
        });
//        $.fn.zTree.init($('#treeDemo'), setting, zNodes).expandAll(true);

        $("#mainFrame").load(function () {
            $(this).height(0);
            var height = $(this).contents().height() + 30;
            $(this).height(height < 500 ? 500 : height);
        });
    });
</script>
</#assign>
<@layout.doLayout script=script header=header>
<div>
    <div class="left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
    <div class="right" style="text-align: center">
        <div style="margin: 0 auto; width: 980px">
            <iframe id="mainFrame" width="100%" frameborder="0" scrolling="auto" style="margin: 0 auto"></iframe>
        </div>
    </div>
</div>
</@layout.doLayout>