$("#modify").click(function() {
	
	var id = $("#news_id").val();
	
	window.location.href = "modifyById?id=" + id;
});

$("#delete").click(function() {
	$("#deleteMark").css("display", "block");
    $("#deleteWin").css("display", "block");
    var id = $("#news_id").val();
    $(".news_id").val(id);
});

$(".action_item, .close").click(function () {
    $("#deleteMark").css("display", "none");
    $("#deleteWin").css("display", "none");
});

$(".yes").click(function () {
	var id = $(".news_id").val();
    window.location.href = "delete?id=" + id;
});