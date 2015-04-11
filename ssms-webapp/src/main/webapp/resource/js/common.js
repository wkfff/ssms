/* */
!function($) {
	$url = {
		getUrlParam:function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null) return unescape(r[2]); return null;
		},
		addPara				:function( url,paras ){ 
			if(!url || !paras) return ; 
			var qry = $url.getPara(url); 
			for( var o in paras ) qry[o] = encodeURIComponent(encodeURIComponent(paras[o]));
			var pos = url.indexOf("?"); 
			return (pos>=0?url.slice(0,pos):url) + "?" + $url.toPara(qry);
		},
		// 获得所有的参数
		getPara				:function( url ){
			if( !url ) return {}; 
			var i = url.indexOf("?");
			if( i<0 )return {};
			var qry = url.substr( i+1 ).split("&"); 
			var r = {},tmp;
			for( i=0;i<qry.length;i++ ){
				tmp = qry[i].split("="); 
				if( !tmp[0] || tmp[0]=='' ) continue;
				r[tmp[0]] = tmp.length>1 ? tmp[1]||'' : "";
			}
			return r;
		},
		// 将O转为URL参数串
		toPara				:function( o ){
			var r = [];
			for( var n in o ){ r.push( n + "=" + o[n] );}
			return r.join("&");
		},
		paraEncoding		:function( o ){ // 转换为URL的参数串，并对值进行UTF-8的转义处理
			var r = [];
			for( key in o ) 
				r.push( key + "=" + encodeURIComponent( encodeURIComponent(o[key]||'') ) );
			return r.join("&");
		}
	},
	$win = {
			openModelWindow:function( url,wid,hei ){
				if (!wid) wid = screen.availWidth - 10;
				if (!hei) hei = screen.availHeight- 30;
				var sf = "resizable:yes;status:no;dialogWidth:"+wid+"px;dialogHeight:"+hei+"px;";
				return window.showModalDialog(url,window,sf);
			}
	}
} (jQuery);
