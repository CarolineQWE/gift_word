/**
 * 表单提交
 */
function formSubmit(url,obj){
	var len = arguments.length;
	var form=$("<form method=post></form>");
	form.attr('action',url);
	for(var i= 1; i <= len;i ++){
		var input=$('<input type=text></input>');
		input.attr('name',arguments[i].name);
		input.attr('value',arguments[i].value);
		form.append(input);
	}
	form.submit();
}