
var datagrid = (function() {
    function datagrid(setting){
        this.setting = setting;
        this.gridId = "#"+setting.gridId;
		this.rowTemplate = $("table>thead>tr",this.gridId);
		this.tBody = $("table>tbody",this.gridId);
		this.pageIndex = $("input[name='_pageIndex']",self.gridId);
		this.pageSize = $("select[name='_pageSize']",self.gridId);
		this.pageCount = $("label[name='_pageCount']",self.gridId);
		this.recCount = $("label[name='_recCount']",self.gridId);
		this.bindEvent();		
    }
    
    datagrid.prototype.bindEvent = function (){
    	var self = this;
    	$("table>thead>tr .chk",self.gridId).click(function(){self.selectAll(this.checked);});
    	$("input[name='btn_query']",self.gridId).click(function(){self.doQuery();});
    	$("input[name='btn_add']",self.gridId).click(function(){self.doAdd();});
    	$("input[name='btn_open']",self.gridId).click(function(){self.doOpen();});
    	$("input[name='btn_del']",self.gridId).click(function(){self.doDel();}); 
    	
    	$("input[name='btn_first']",self.gridId).click(function(){
    		self.pageIndex.val(1);
    		self.doQuery();
    	});
    	$("input[name='btn_prev']",self.gridId).click(function(){
    		var i = self.pageIndex.val();
    		if (i>1) {
    			i--;
    			self.pageIndex.val(i);
    			self.doQuery();    			
    		}
    	});
    	$("input[name='btn_next']",self.gridId).click(function(){
    		var i = self.pageIndex.val();
    		if (i<self.pageCount.text()){
    			i++;
    			self.pageIndex.val(i);
    			self.doQuery();
    		}
    		
    	});
    	$("input[name='btn_last']",self.gridId).click(function(){
    		self.pageIndex.val(self.pageCount.text());
    		self.doQuery();
    	});
    	$("input[name='_pageIndex']",self.gridId).click(function(){
    		self.doQuery();
    	});
    	$("select[name='_pageSize']",self.gridId).change(function(){
    		self.doQuery();
    	});
    }
    
    datagrid.prototype.selectRow = function () {
    	this.activeRow = $("#"+this.selectID,this.tBody).addClass('rowSelected');    	
    };
    
    datagrid.prototype.selectAll = function (checked) {
    	$.each(this.checkBoxs, function(i, chk){
			chk.checked = checked;
		});
    };
    
    datagrid.prototype.getSelectIds = function () {
    	var ids = [];
		this.checkBoxs.each(function(){
	    	if (true == $(this).attr("checked")) {		          
	          ids.push($(this).attr("id"));
	    	}
	    });
	    return ids.join(",");
    };
    
    datagrid.prototype.bindData = function (data) {
    	var self = this;
    	this.tBody.html("");    	
    	this.recCount.text(data.total);
    	this.pageCount.text(data.pageCount);
    	
		$.each(data.data, function(i, n){
			var row = self.rowTemplate.clone();
			var sid = "";
			$.each($("td[id]",row),function(i,cell){				
				var value = n[cell.id];
				if (cell.id == "SID") sid = value;
				cell.innerHTML = value==null?"":value;					
			});
			/*
			$.each(n, function(field, value){
				if (field == "SID") sid = value;
				var f = row.find("#"+field);
				if (f!=null) f.text(value);
			});
			*/
			row.find(".chk").attr("id",sid);
			row.attr("id",sid);
			row.click(function(){
				self.selectID = this.id;
				if (self.activeRow!=null) 
					self.activeRow.removeClass('rowSelected');
				self.activeRow = $(this);
				self.activeRow.addClass('rowSelected'); 
			});
			row.dblclick(function(){self.doOpen()});
			row.appendTo(self.tBody);					
        });
		this.checkBoxs = $(self.gridId+">table>tbody .chk");
    };
    
//    datagrid.prototype.query = function (filter) {
//    	var self = this;
//    	$.get(this.setting.dataUrl, {_filter:filter},function (data) {      
//    		self.bindData(data);
//			self.selectRow();
//	    },"json");
//    };
    datagrid.prototype.query = function (para) {
    	var self = this;
    	$.get(this.setting.dataUrl,para,function (data) {      
    		self.bindData(data);
			self.selectRow();
	    },"json");
    };
    
    datagrid.prototype.doQuery = function (para) {
    	if (!para) para = {};
//    	var filter = $(".filter_field",this.gridId).val()+"="+$(".filter_value",this.gridId).val();
//    	this.query(filter);
    	
    	var filter = {},paging = {};
    	filter[$(".filter_field",this.gridId).val()] = $(".filter_value",this.gridId).val();
    	para["_filter"] = filter;
    	
    	paging["pageIndex"]=this.pageIndex.val();
    	paging["pageSize"]=$("select[name='_pageSize']",self.gridId).val();
    	para["_paging"] = paging;
    	this.query(para);		
    };
    
    datagrid.prototype.doAdd = function () {
    	$win.navigate(this.setting.addUrl);
    };
    
    datagrid.prototype.doOpen = function () {
    	var sid = this.selectID;
		if (sid == null) {alert("请选择要打开的记录！");return;}
		$win.navigate(this.setting.openUrl+"?sid="+sid);
    };
    
    datagrid.prototype.doDel = function () {
    	var self = this;
    	var ids = this.getSelectIds();
		if (ids.length == 0) ids = this.selectID;
		if (ids == null) {alert("请选择要删除的行！");return;}
		if(!confirm("确认要删除当前选择的行吗？")) return;
		$.post(this.setting.delUrl, {ids:ids},function (data) {      
			self.doQuery();	
	    });				
    };	        

    return datagrid;
})();