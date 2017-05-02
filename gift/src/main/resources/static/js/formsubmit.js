/**
 * 表单提交
 */
function formSubmit(url,obj){
	var len = arguments.length;
	var form=document.createElement('form');
    form.setAttribute('method','post');
	form.setAttribute('action',url);
	for(var i= 1; i < len;i ++){
		var input= document.createElement('input');
		console.log(arguments[i]);
        input.setAttribute("name",arguments[i].name);
		input.setAttribute("value",arguments[i].value);
		form.append(input);
	}
    $(document.body).append(form);
	form.submit();
}