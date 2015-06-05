lastScrollY = 0;
var graySrc = false;
var InterTime = 1;
var maxWidth = -1;
var minWidth = -128;
var numInter = 8;
var BigInter;
var SmallInter;
var o = null;
var i = 0;
kf = function(id, _top, _left) {
    var me = id.charAt ? document.getElementById(id) : id, d1 = document.body, d2 = document.documentElement;
    d1.style.height = d2.style.height = '100%'; me.style.top = _top ? _top + 'px' : 0; me.style.left = _left + "px"; 
    me.style.position = 'absolute';
    me.style.display = 'block';
    setInterval(function() { me.style.top = parseInt(me.style.top) + (Math.max(d1.scrollTop, d2.scrollTop) + _top - parseInt(me.style.top)) * 0.1 + 'px'; }, 10 + parseInt(Math.random() * 20));
    return arguments.callee;
};
$(document).ready(function() {
    var html = '';
    html += '<div id="kf" style="z-index:2;display:none;" onmouseover=toBig() onmouseout=toSmall()>';
    html += '    <div class="services">';
    html += '        <div class="con">';
    html += '        	<ul>';
    html += '            	<li class="qq">';
    html += '                	<p><a href="javascript:;" class="qq"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360728464&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:954502368:41" alt="" title=""/></a></p>';
    html += '                   <p><a href="javascript:;" class="qq"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360728464&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:954502368:41" alt="" title=""/></a></p>';
    html += '                   <p><a href="javascript:;" class="qq"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360728464&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:954502368:41" alt="" title=""/></a></p>';
    html += '                   <p><a href="javascript:;" class="qq"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360728464&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:954502368:41" alt="" title=""/></a></p>';
    html += '                 </li>';
    html += '            </ul>';
    html += '        </div>';
    html += '    </div>';
    html += '    ';
    html += '    <div class="Obtn"></div>';
    html += '</div>';

    $(document.body).append(html);

    o = document.getElementById("kf");
    i = parseInt(o.style.left);

    kf('kf', 410, -128);
});

function Big() {
    if (parseInt(o.style.left) < maxWidth) {
        i = parseInt(o.style.left);
        i += numInter;
        o.style.left = i + "px";
        if (i == maxWidth)
            clearInterval(BigInter);
    }
    if (!graySrc) {
        $(o).find("img").each(function() {
            $(this).attr("src", $(this).attr("Original"));
        });
        graySrc = true;
    }
}
function toBig() {
    clearInterval(SmallInter);
    clearInterval(BigInter);
    BigInter = setInterval(Big, InterTime);
}
function Small() {
    if (parseInt(o.style.left) > minWidth) {
        i = parseInt(o.style.left);
        i -= numInter;
        o.style.left = i + "px";

        if (i == minWidth)
            clearInterval(SmallInter);
    }
}
function toSmall() {
    clearInterval(SmallInter);
    clearInterval(BigInter);
    SmallInter = setInterval(Small, InterTime);

}