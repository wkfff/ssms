<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>安全生产标准化管理系统</title>
    <link rel="stylesheet" href="/resource/css/base.css"/>
    <link rel="stylesheet" href="/resource/css/common.css"/>
    <link rel="stylesheet" href="/resource/css/fix.css"/>
    <script type="text/javascript" src="/resource/js/knockout/knockout.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.json.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/easyui/themes/icon.css">
    <script type="text/javascript" src="/resource/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resource/js/layui/layer.js"></script>
    <script type="text/javascript" src="/resource/js/core.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <script type="text/javascript" src="/resource/js/plupload/plupload.min.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/knockout.mapping.debug.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/component.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/upload.js"></script>
    <script type="text/javascript" src="/resource/js/knockout/dataPager.js"></script>
    <style type="text/css">
        *, .caption, input, td {
            font-family: "微软雅黑", 宋体, cursive;
        }

        .container {
            width: 800px;
            margin: 0 auto;
        }

        h1.caption {
            font-size: 150%;
            font-weight: bold;
            color: #58544e;
        }

        .group table.content {
            width: 100%;
            font-size: 110%;
            margin: 10px 30px;
            table-layout: fixed;
        }

        span.must {
            color: #ff0000;
            font-weight: bold;
        }

        .group table.content tr {
            height: 40px;
        }

        .group table.content tr td {
            padding: 3px;
        }

        .group table.content input {
            width: 100%;
            line-height: 120%;
            color: #383fa2;
            font-size: 110%;
        }

        input[readonly="readonly"], input[readonly] {
            border: 0;
            border-bottom: 1px solid #CCCCCC;
        }
    </style>
</head>
<body>
<div class="toolbar">
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="location.reload()">刷新</a>
        <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" data-bind="click: events.saveClick">保存</a>
</div>
<div class="container">
 <form class="form">
    <div class="group">
        <h1 class="caption">申请企业信息</h1>
        <table class="content">
            <colgroup>
                <col width="60px"/>
            </colgroup>
            <tr>
                <td>申请企业</td>
                <td><input type="text" data-bind="value: S_ENTERPRISE" readonly/></td>
            </tr>
            <tr>
                <td>单位地址</td>
                <td><input type="text" data-bind="value: C_ENTERPRISE_ADDR" readonly/></td>
            </tr>
        </table>
    </div>

    <div class="group">
        <h1 class="caption">评审企业信息</h1>
        <table class="content">
            <colgroup>
                <col width="70px"/>
                <col/>
                <col width="70px"/>
                <col/>
                <col width="70px"/>
                <col/>
            </colgroup>
            <tr>
                <td>评审单位</td>
                <td colspan="5"><input type="text" data-bind="value: C_REVIEW" readonly/></td>
            </tr>
            <tr>
                <td>单位地址</td>
                <td colspan="5"><input type="text" data-bind="value: C_REVIEW_ADDR" readonly/></td>
            </tr>
            <tr>
                <td>评审日期</td>
                <td colspan="5">
                    <input type="text" data-bind="dateboxValue: T_START" required style="width: 200px"/>
                    至
                    <input type="text" data-bind="dateboxValue: T_END" required style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td><span class="must">*</span>电话</td>
                <td><input type="text" data-bind="textboxValue: C_PHONE" required/></td>
                <td><span class="must">*</span>传真</td>
                <td><input type="text" data-bind="textboxValue: C_FAX" required/></td>
                 <td><span class="must">*</span>电子邮箱</td>
                <td><input type="text" data-bind="textboxValue: C_EMAIL" required/></td>
            </tr>
        </table>
    </div>
    <div class="group">
        <h1 class="caption">评审小组组长</h1>

        <table class="content">
            <colgroup>
                <col width="70px"/>
                <col/>
                <col width="70px"/>
                <col/>
                <col width="70px"/>
                <col/>
            </colgroup>
            <tr>
                <td><span class="must">*</span>组长姓名</td>
                <td>
                    <input data-bind="comboboxValue:R_LEADER,comboboxText:S_LEADER,easyuiOptions:$root.comboConfig" required/>
                </td>
                <td>职称</td>
                <td><input type="text" data-bind="textboxValue: C_JOB"/></td>
                <td>电话</td>
                <td><input type="text" data-bind="textboxValue: C_TEL"/></td>
            </tr>
        </table>
    </div>
    
    <div class="group">
        <h1 class="caption">评审小组成员
            <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" data-bind="click: addMember">添加</a>
        </h1>
        <table class="content">
            <colgroup>
                <col width="70px"/>
                <col/>
                <col width="70px"/>
                <col/>
                <col width="70px"/>
                <col/>
            </colgroup>
            <tbody data-bind="foreach: members">
            <tr>
                <td><span class="must">*</span>成员姓名</td>
                <td>
                    <input data-bind="comboboxValue:R_MEMBER,comboboxText:S_MEMBER,easyuiOptions:$root.comboConfig" required/>
                </td>
                <td>职称</td>
                <td><input type="text" data-bind="textboxValue: C_JOB"/></td>
                <td>电话</td>
                <td><input type="text" data-bind="textboxValue: C_TEL"/></td>
                <td><a href="#" data-bind="click: delMember"><img src="/resource/images/delico.png"/>删除</a></td>
            </tr>
            </tbody>
        </table>
    </div>
    </form>
</div>
</body>
<script type="text/javascript">
    var model = {
            S_ENTERPRISE : ko.observable('${S_ENTERPRISE!}'),
            C_ENTERPRISE_ADDR : ko.observable('${C_ENTERPRISE_ADDR!}'),
            C_REVIEW : ko.observable('${S_TENANT!}'),
            C_REVIEW_ADDR : ko.observable('${C_REVIEW_ADDR!}'),
            T_START : ko.observable('${T_START!}'),
            T_END : ko.observable('${T_END!}'),
            C_CONTACT : ko.observable('${C_CONTACT!}'),
            C_PHONE : ko.observable('${C_PHONE!}'),
            C_FAX : ko.observable('${C_FAX!}'),
            C_TEL : ko.observable('${C_TEL!}'),
            C_EMAIL : ko.observable('${C_EMAIL!}'),
            R_LEADER: ko.observable('${R_LEADER!}'),
            S_LEADER: ko.observable('${S_LEADER!}'),
            C_JOB: ko.observable('${C_JOB!}'),
            SID: '${sid!}'
    };

    var extModel = {
            members:ko.observableArray([])
    };
    
    var settings = {
        comboConfig:{
            url: '/static/reviewers.json',
            valueField: 'SID',
            textField: 'C_NAME'
        }
    };

    var events = {
        saveClick: function () {
                if ($form.validate($('.form'))) {
                    $.post('save', $.extend({}, model), function (result) {
                        if (result.SID){
                            $.post('/r/reviewmember/batchSave', {"R_SID":result.SID,"data":ko.toJSON(extModel.members())}, function (result) {
                                utils.messager.alert("保存成功");
                            }, 'json');
                        }
                        else {
                            utils.messager.alert("保存失败");
                        }
                    }, 'json');
                }
        },addMember:function () {
             extModel.members.push({
                R_MEMBER: ko.observable(),
                S_MEMBER: ko.observable(),
                C_JOB: ko.observable(),
                C_TEL: ko.observable()
            });
        }
    };

    ko.applyBindings($.extend({}, model, extModel, settings, events));
    function delMember(m){
       extModel.members.remove(m);
    }
    $.getJSON("/r/reviewmember/list?R_SID=${sid!}", function (data) {
            for(var i=0;i<data.length;i++){
                extModel.members.push({
                    R_MEMBER: ko.observable(data[i].R_MEMBER),
                    S_MEMBER: ko.observable(data[i].S_MEMBER),
                    C_JOB: ko.observable(data[i].C_JOB),
                    C_TEL: ko.observable(data[i].C_TEL)
                });
            }
    });

</script>
</html>