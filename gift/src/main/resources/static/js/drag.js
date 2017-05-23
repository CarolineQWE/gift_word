/**
 * @author Administrator
 */
$(function() {
	$("#add_input").click(function() {
		var obj = document.getElementById("nodesContainer");
		createNode(obj);
	})
})

function createNode(parentNode) {
	var left = document.getElementById("nodesContainer").offsetLeft + 10;
	var top = document.getElementById("nodesContainer").offsetTop + 10;
	var newNode = document.createElement("input");
	newNode.type = "text";
	newNode.className = "new_input";
	parentNode.appendChild(newNode);
	newNode.focus();
	doDrag(newNode);
}

/*
 * @param {Object} obj: If obj is a string,convert it to an obj;
 * @param {number} initWidth: Initial Width of obj;
 * @param {number} initHeight:Initial Height of obj;
 * @param {number} initLeft:Initial Left of obj;
 * @param {number} initTop:Initial Top of obj;
 */
function doDrag(obj, initWidth, initHeight, initLeft, initTop) {
	var posX;
	var posY;
	var dragable;

	if(typeof obj == "string")
		obj = document.getElementById(obj);
	if(initWidth) obj.style.width = initWidth + "px";
	if(initHeight) obj.style.height = initHeight + "px";
	if(initLeft) obj.style.left = initLeft + "px";
	if(initTop) obj.style.top = initTop + "px";
	obj.onmousedown = function(event) {
		down(event);
	}

	obj.onmouseup = function() {
		up();
	}

	function down(e) {
		e = e || window.event;
		posX = e.clientX - obj.offsetLeft; //offsetLeft is a readonly property
		posY = e.clientY - obj.offsetTop;
		dragable = true;
		document.onmousemove = move;
		//$(obj).wrap("<div style = 'position:relative;border:1px solid red;width:300px;height:50px'></div>")
	}

	function move(ev) {
		if(dragable) {
			ev = ev || window.event; //如果是IE   
			obj.style.left = (ev.clientX - posX) + "px";
			obj.style.top = (ev.clientY - posY) + "px";
		}
	}

	function up() {
		//$(obj).unwrap();
		dragable = false;
	};
}