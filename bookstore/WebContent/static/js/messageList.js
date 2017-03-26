
$(".go_button").click(function () {
    var $pageNum = $(".go_input").val();
    var $totalPages = $("#total_pages").text();
    if (!isTnteger($pageNum)) {
        $("#errorFlashMessage").text("请输入正确的页码。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
    }
    if (eval($pageNum) > eval($totalPages)) {
        $pageNum = $totalPages;
    }
    window.location.href = "listMess?currentPage=" + $pageNum;
});

$(".go_input").keydown(function(event) {
    if (event.keyCode == 13) {
        $(".go_button").click();
    }
});

$(".reply").click(function() {
	var userName = $(this).parent().prev().prev().text();
	$(".i_message").text("回复 " + userName + " 的留言：");
	$("#deleteMark").css("display", "block");
    $("#deleteWin").css("display", "block");
});

$(".action_item, .close").click(function () {
    $("#deleteMark").css("display", "none");
    $("#deleteWin").css("display", "none");
    $("#deleteMessWin").css("display", "none");
});

$("#reply_message").click(function () {
	var $content = $(".i_message").val();
    window.location.href = "saveMess?content=" + $content;
});

$(".delete_mess").click(function() {
	$("#deleteMark").css("display", "block");
    $("#deleteMessWin").css("display", "block");
    var id = $(this).next(".mess_id").val();
    $("#message_id").val(id);
});

$("#delete_message").click(function () {
	var id = $("#message_id").val();
    window.location.href = "delete?id=" + id;
});

$(function message() {
    $("#msg").fadeOut(3000);
});