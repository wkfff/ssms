<#import "/layout/_list.ftl" as layout/>
<#assign header>
<script type="text/javascript" src="/resource/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="/resource/css/zTreeStyle/zTreeStyle.css"/>
<style type="text/css">
    body {
         margin-top: 40px;
    }

    * { margin:0; padding:0; list-style:none;}
    html { height:100%; overflow:hidden; background:#fff;}
    body { height:100%; overflow:hidden; background:#fff;}
    .side { position:absolute; left:0; top:40px; bottom:0; width:260px; overflow:auto; border-right: dashed 1px #cccccc}
    .main { position:absolute; left:270px; top:40px; bottom:0; right:0; overflow:hidden;}
    .main iframe { width:100%; height:100%;}
    /*---ie6---*/
    html { *padding:70px 10px;}
    .side { *height:100%; *float:left; *width:260px; *position:relative; *top:0; *right:0; *bottom:0; *left:0;}
    .main { *height:100%; *margin-left:210px; _margin-left:207px; *position:relative; *top:0; *right:0; *bottom:0; *left:0;}
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

    function loadTree(){
        $.post('tree.json', {profession: '${profession}'}, function (result) {
            if ($.isArray(result)) {
                $.each(result, function (index, item) {
                    item.open = true;
                });
                $.fn.zTree.init($("#treeDemo"), setting, result);
            }
        });
    }

    $(document).ready(function () {
        loadTree();
        $('#btnRefresh').click(function () {
            loadTree();
        });
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

<div class="side" style="padding-left: 5px">
    <input type="button" class="btn" value="刷新" id="btnRefresh"/>
    <ul id="treeDemo" class="ztree"></ul>
</div>

<div class="main" style="padding-right: 5px">
    <iframe id="mainFrame" frameborder="0"></iframe>
</div>

</@layout.doLayout>