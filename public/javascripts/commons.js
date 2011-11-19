function showQueryDialog(options){
	var opts = options || {};
	var dlg = $('#dlg-query');
	if (!dlg.length){
		dlg = $('<div id="dlg-query"></div>').appendTo('body');
		dlg.dialog({
			title:opts.title||'高级查询',
			width:opts.width||400,
			height:opts.height||300,
			closed:true,
			modal:true,
			href:opts.form,
			buttons:[{
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					dlg.dialog('close');
					var param = {};
					dlg.find('.query').each(function(){
						var name = $(this).attr('name');
						var val = $(this).val();
						if ($(this).hasClass('datebox-f')){
							name = $(this).attr('comboname');
							val = $(this).datebox('getValue');
						} else if ($(this).hasClass('combogrid-f')){
							name = $(this).attr('comboname');
							val = $(this).combogrid('getValue');
						} else if ($(this).hasClass('combobox-f')){
							name = $(this).attr('comboname');
							val = $(this).combobox('getValue');
						}
						param[name] = val;
					});
					opts.callback(param);
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){dlg.dialog('close');}
			}]
		});
	}
	dlg.dialog('open');
}

function showDialog(options){
	var opts = options || {};
	var dlg = $('#dlg-win');
	if (!dlg.length){
		dlg = $('<div id="dlg-query"></div>').appendTo('body');
		dlg.dialog({
			title:opts.title||'新建窗口',
			width:opts.width||400,
			height:opts.height||300,
			closed:true,
			modal:true,
			href:opts.href,
			buttons:[{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){dlg.dialog('close');}
			}]
		});
	}
	dlg.dialog('open');
}

function getTreeValue($obj){
	var cnodes='';  
    var pnodes='';
	var nodes = $obj.find(".tree-checkbox2");
	$(nodes).each(function(i){
		var node = $(this).parent().attr("node-id");
		if(node > 0) pnodes+=node+',';
	});
	nodes = $obj.find(".tree-checkbox1");
	$(nodes).each(function(i){
		var node = $(this).parent().attr("node-id");
		if(node > 0) cnodes+=node+',';
	});
    cnodes = cnodes.substring(0,cnodes.length-1);  
    pnodes = pnodes.substring(0,pnodes.length-1);
    nodes = pnodes;
    if(nodes.length > 0) nodes = nodes + "," + cnodes;
    else nodes = cnodes;
    return nodes;
}