/* */
!function ($) {
    $url = {
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null)
                return unescape(r[2]);
            return null;
        },
        addPara: function (url, paras) {
            if (!url || !paras)
                return;
            var qry = $url.getPara(url);
            for (var o in paras)
                qry[o] = encodeURIComponent(encodeURIComponent(paras[o]));
            var pos = url.indexOf("?");
            return (pos >= 0 ? url.slice(0, pos) : url) + "?"
                + $url.toPara(qry);
        },
        // 获得所有的参数
        getPara: function (url) {
            if (!url)
                return {};
            var i = url.indexOf("?");
            if (i < 0)
                return {};
            var qry = url.substr(i + 1).split("&");
            var r = {}, tmp;
            for (i = 0; i < qry.length; i++) {
                tmp = qry[i].split("=");
                if (!tmp[0] || tmp[0] == '')
                    continue;
                r[tmp[0]] = tmp.length > 1 ? tmp[1] || '' : "";
            }
            return r;
        },
        // 将O转为URL参数串
        toPara: function (o) {
            var r = [];
            for (var n in o) {
                r.push(n + "=" + o[n]);
            }
            return r.join("&");
        },
        paraEncoding: function (o) { // 转换为URL的参数串，并对值进行UTF-8的转义处理
            var r = [];
            for (key in o)
                r.push(key
                + "="
                + encodeURIComponent(encodeURIComponent(o[key]
                || '')));
            return r.join("&");
        }
    }, $win = {
        navigate: function (url) {
            window.location.href = url;
        },
        openModelWindow: function (url, wid, hei) {
            if (!wid)
                wid = screen.availWidth - 10;
            if (!hei)
                hei = screen.availHeight - 30;
            var sf = "resizable:yes;status:no;dialogWidth:" + wid
                + "px;dialogHeight:" + hei + "px;";
            return window.showModalDialog(url, window, sf);
        }
    }, $str = {
        replaceAll: function (s, s1, s2) {
            return s.replace(new RegExp(s1, "gm"), s2);
        }
    }, $form = {
        disableForm: function (formId, isDisabled) {
            var attr = isDisabled ? "disable" : "enable";
            $("form[id='" + formId + "'] :text").attr("disabled",
                isDisabled);
            $("form[id='" + formId + "'] textarea").attr("disabled",
                isDisabled);
            $("form[id='" + formId + "'] select").attr("disabled",
                isDisabled);
            $("form[id='" + formId + "'] :radio").attr("disabled",
                isDisabled);
            $("form[id='" + formId + "'] :checkbox").attr("disabled",
                isDisabled);

            //禁用jquery easyui中的下拉选
            $(".easyui-combobox", "#" + formId).each(function (i, e) {
                $(e).combobox(attr);
            });

            //禁用jquery easyui中的日期组件dataBox
            $(".easyui-datebox", "#" + formId).each(function (i, e) {
                $(e).datebox(attr);
            });

            //禁用jquery easyui中的日期时间组件datatimeBox
            $(".easyui-datetimebox", "#" + formId).each(function (i, e) {
                $(e).datebox(attr);
            });
        }
    }
}(jQuery);

var plugins = ['textbox','filebox','combo','combobox','combotree','combogrid','numberbox','validatebox','searchbox','spinner','numberspinner','timespinner','datetimespinner','calendar', 'datebox', 'datetimebox', 'slider'];
$.each(plugins, function (i, e) {
    var plugin = $.fn[e];
    if (typeof(plugin)=='undefined') return;
    var pluginDefaults = plugin.defaults;
    if (pluginDefaults.width && pluginDefaults.width == 'auto') pluginDefaults.width = '100%';
    pluginDefaults.height = 33;
});

$form.validate = function (element) {
    if ($.fn.validatebox) {
        var t = $(element);
        t.find(".validatebox-text:not(:disabled)").validatebox("validate");
        var items = t.find(".validatebox-invalid");
        items.filter(":not(:disabled):first").focus();
        return items.length == 0;
    }
    return true;
};