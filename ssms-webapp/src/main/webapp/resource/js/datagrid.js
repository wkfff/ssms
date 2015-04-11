
var datagrid = (function() {
    function datagrid(setting){
        this.setting = setting;
        this.gridId = "#"+setting.gridId;
		this.rowTemplate = $("table>thead>tr",this.gridId);
		this.tBody = $("table>tbody",this.gridId);
		this.bindEvent();		
    }
    
    datagrid.prototype.bindEvent = function (){
    	var self = this;
    	$("table>thead>tr .chk",self.gridId).click(function(){self.selectAll(this.checked);});
    	$("input[name='btn_query']",self.gridId).click(function(){self.doQuery();});
    	$("input[name='btn_add']",self.gridId).click(function(){self.doAdd();});
    	$("input[name='btn_open']",self.gridId).click(function(){self.doOpen();});
    	$("input[name='btn_del']",self.gridId).click(function(){self.doDel();});
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
		$.each(data.rows, function(i, n){
			var row = self.rowTemplate.clone();
			var sid = "";
			$.each(n, function(field, value){
				if (field == "SID") sid = value;
				var f = row.find("#"+field);
				if (f!=null) f.text(value);
			});
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
    
    datagrid.prototype.query = function (filter) {
    	var self = this;
    	$.get(this.setting.dataUrl, {_filter:filter},function (data) {      
    		self.bindData(data);
			self.selectRow();
	    },"json");    	    	
    };
    
    datagrid.prototype.doQuery = function () {
    	var filter = $(".filter_field",this.gridId).val()+"="+$(".filter_value",this.gridId).val();
		this.query(filter);		
    };
    
    datagrid.prototype.doAdd = function () {
    	var r = $win.openModelWindow(this.setting.addUrl,800,600);
		this.doQuery();
    };
    
    datagrid.prototype.doOpen = function () {
    	var sid = this.selectID;
		if (sid == null) {alert("请选择要打开的行！");return;}
		var r = $win.openModelWindow(this.setting.openUrl+"?sid="+sid,800,600);
		this.doQuery();
    };
    
    datagrid.prototype.doDel = function () {
    	var self = this;
    	var ids = this.getSelectIds();
		if (ids.length == 0) ids = selectID;
		if (ids == null) {alert("请选择要删除的行！");return;}
		if(!confirm("确认要删除当前选择的行吗？")) return;
		$.post(this.setting.delUrl, {ids:ids},function (data) {      
			self.doQuery();	
	    });				
    };	        

    return datagrid;
})();