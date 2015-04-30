/**
 * jQuery EasyUI KindEditor 组件扩展
 * 林峰 2015-04-29
 */
(function ($, K) {
	if (!K)
		throw "KindEditor未定义!";

	function create(target) {
		var opts = $.data(target, 'kindeditor').options;
		var editor = K.create(target, opts);
		$.data(target, 'kindeditor').options.editor = editor;
	}

	$.fn.kindeditor = function (options, param) {
		if (typeof options == 'string') {
			var method = $.fn.kindeditor.methods[options];
			if (method) {
				return method(this, param);
			}
		}
		options = options || {};
		return this.each(function () {
			var state = $.data(this, 'kindeditor');
			if (state) {
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'kindeditor', {
						options : $.extend({}, $.fn.kindeditor.defaults, $.fn.kindeditor.parseOptions(this), options)
					});
			}
			create(this);
		});
	}

	$.fn.kindeditor.parseOptions = function (target) {
		return $.extend({}, $.parser.parseOptions(target, []));
	};

	$.fn.kindeditor.methods = {
		editor : function (jq) {
			return $.data(jq[0], 'kindeditor').options.editor;
		}
	};

	$.fn.kindeditor.defaults = {
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
                 'fontname', 'fontsize',
                 '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline','removeformat',
                 '|', 'justifyleft', 'justifycenter', 'justifyright',
                 'justifyfull', 'insertorderedlist','insertunorderedlist',
                 '|', 'table','|','fullscreen'
                 ],
		afterChange:function(){
			this.sync();
		}
	};
	$.parser.plugins.push("kindeditor");
})(jQuery, KindEditor);