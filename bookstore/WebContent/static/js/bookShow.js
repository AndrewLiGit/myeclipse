$("#modify").click(function() {
	
	var id = $("#book_id").val();
	
	window.location.href = "modifyById?id=" + id;
});

$("#delete").click(function() {
	$("#deleteMark").css("display", "block");
    $("#deleteWin").css("display", "block");
});

$(".action_item, .close").click(function () {
    $("#deleteMark").css("display", "none");
    $("#deleteWin").css("display", "none");
});

$(".yes").click(function () {
	var id = $("#book_id").val();
    window.location.href = "deleteById?id=" + id;
});