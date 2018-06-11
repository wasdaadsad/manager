//modal模板
var templates = {
	dialog: 
		'<div class="modal fade" tabindex="-1" role="dialog">' + 
			'<div class="modal-dialog" role="document">' + 
		    	'<div class="modal-content">' +
		    		'<div class="modal-header">' +
		    			'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
	    				
    				'</div>' +
    				'<div class="modal-body">' +
	        		'</div>' + 
	        		'<div class="modal-footer">' + 
    				'</div>' +
				'</div>' +
		  	'</div>' +
		'</div>',
	confirm:
		'<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="false">'+
			'<div class="modal-dialog" role="document">'+
				'<div class="modal-content">'+
					'<div class="modal-body">'+
						'<button type="button" class="bootbox-close-button close" data-dismiss="modal" aria-hidden="true">×</button>'+
						'<div class="modal-title"></div>'+
					'</div>'+
					'<div class="modal-footer">'+
						'<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>'+
						'<button type="button" class="btn btn-primary">OK</button>'+
					'</div>'+
				'</div>'+
			'</div>'+
		'</div>',
	alert:
		'<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">'+
			'<div class="modal-dialog modal-sm">'+
				'<div class="modal-content">'+
					'<div class="modal-body">'+
						'<div class="modal-title"></div>'+
					'</div>'+
					'<div class="modal-footer">'+
						'<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>'+
					'</div>'+
				'</div>'+
			'</div>'+
		'</div>'
}

var vivoDialog = {
	init: function(options){
		
		var dialogToggle = options.toggle;

		//按钮与模态框绑定
		$("#"+dialogToggle).attr({"data-toggle": "modal", "data-target": "#"+options.targetId});
		
		var dialogObj = dialogBuilder[options.type](options);
		
		return dialogObj;
	}
}

var dialogBuilder = {
	"dialog": function(options){
		var dialog = $(templates[options.type]);
		dialog.attr("id", options.targetId);
		
		//header
		var header = $('<h4 class="modal-title" id="myModalLabel">'+ options.title +'</h4>');
		dialog.find('.modal-header').append(header);
		
		//footer
		var buttons = options.buttons;
		for(var p in buttons){
			var btnObj = $('<button type="button" class="'+buttons[p].className+'">'+buttons[p].label+'</button>').on("click", {index: p}, clickHandler);
			dialog.find(".modal-footer").append(btnObj);
		}
		
		function clickHandler(event){
			var p = event.data.index;
			buttons[p].callback(dialog);
		}
		
		//body
		$.ajax({
			url: options.url,
			type:'get',
			dataType: 'html',
			success: function(data){
				dialog.find(".modal-body").html(data);
				
				$('body').append(dialog);
			}
		});

		return dialog;
	},
	"confirm": function(options){
		var dialog = $(templates[options.type]);
		dialog.modal({
			backdrop: 'static',
			keyboard: false,
			show: false
		});
		dialog.attr("id", options.targetId);
		
		dialog.find(".modal-title").text(options.message);
		
		$('body').append(dialog);
		return dialog;
	},
	"alert": function(options){
		var dialog = $(templates[options.type]);
		dialog.attr("id", options.targetId);
		
		dialog.find(".modal-title").text(options.message);
		
		$('body').append(dialog);
		return dialog;
	}
}
