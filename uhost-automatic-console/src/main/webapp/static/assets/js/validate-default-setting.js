
//配置jQuery.validator默认的处理方法  
jQuery.validator.setDefaults({  
    event:"keyup",//触发校验的方式，可选值有keyup(每次按键时)，blur(当控件失去焦点时)  
    errorElement: 'div',
    errorClass : 'help-block',    
    focusInvalid : true,  
    highlight: function (e) {
		$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
	},

	success: function (e) {
		$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
		$(e).remove();
	}
	
});  

//配置通用的默认提示语  
$.extend($.validator.messages, {  
    required: '必填',
    digits : "必须为整数",
    maxlength: $.validator.format("长度不能大于{0}"),  
    minlength: $.validator.format("长度不能小于{0}"),  
    rangelength: $.validator.format("长度须在 {0} 和 {1} 之间"),  
    range: $.validator.format("须在 {0} 和 {1} 之间"),  
    max: $.validator.format("不能大于 {0}"),  
    min: $.validator.format("不能小于 {0}") 
});  


