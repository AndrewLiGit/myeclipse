$(function(){

	$(document).ready(function (){
		//window.setInterval();
		setInterval("showTime()",999);
		}
	);
});

function showTime(){
	
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	var time = year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
	$("#now_time").text(time);
}