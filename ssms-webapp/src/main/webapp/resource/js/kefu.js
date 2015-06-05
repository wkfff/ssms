

$(document).ready(function() {
    var html = '';

    html += '<div id="floatTools" class="rides-cs" style="height:210px;">';
    html += '  <div class="floatL">';
    html += '    <a id="aFloatTools_Show" class="btnOpen" title="查看在线客服" style="top:20px;display:block" href="javascript:void(0);">展开</a>';
    html += '    <a id="aFloatTools_Hide" class="btnCtn" title="关闭在线客服" style="top:20px;display:none" href="javascript:void(0);">收缩</a>';
    html += '  </div>';
    html += '  <div id="divFloatToolsView" class="floatR" style="display: none;height:237px;width: 140px;">';
    html += '    <div class="cn">';
    html += '      <h3 class="titZx">在线客服</h3>';
    html += '      <ul>';
    html += '        <li><span>客服1</span> <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360728464&site=qq&menu=yes"><img border="0" src="/resource/images/online.png" alt="点击这里给我发消息" title="点击这里给我发消息"/></a> </li>';
    html += '        <li><span>客服2</span> <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360728464&site=qq&menu=yes"><img border="0" src="/resource/images/online.png" alt="点击这里给我发消息" title="点击这里给我发消息"/></a> </li>';
    html += '        <li><span>客服3</span> <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360728464&site=qq&menu=yes"><img border="0" src="/resource/images/online.png" alt="点击这里给我发消息" title="点击这里给我发消息"/></a> </li>';
    html += '        <li style="border:none;"><span>电话：400-000-0000</span> </li>';
    html += '      </ul>';
    html += '    </div>';
    html += '  </div>';
    html += '</div>';


    $(document.body).append(html);
    
    $("#aFloatTools_Show").click(function(){
        $('#divFloatToolsView').animate({width:'show',opacity:'show'},100,function(){$('#divFloatToolsView').show();});
        $('#aFloatTools_Show').hide();
        $('#aFloatTools_Hide').show();
    });
    $("#aFloatTools_Hide").click(function(){
        $('#divFloatToolsView').animate({width:'hide', opacity:'hide'},100,function(){$('#divFloatToolsView').hide();});
        $('#aFloatTools_Show').show();
        $('#aFloatTools_Hide').hide();  
    });
});
