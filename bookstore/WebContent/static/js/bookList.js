
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
    window.location.href = "list?currentPage=" + $pageNum;
});

$(".go_input").keydown(function(event) {
    if (event.keyCode == 13) {
        $(".go_button").click();
    }
});

$(function message() {
    $("#msg").fadeOut(3000);
});

$(".delete_book").click(function() {
	$("#deleteMark").css("display", "block");
    $("#deleteWin").css("display", "block");
    var id = $(this).next(".book_id").val();
    $("#book_id").val(id);
});

$(".action_item, .close").click(function () {
    $("#deleteMark").css("display", "none");
    $("#deleteWin").css("display", "none");
});

$(".yes").click(function () {
	var id = $("#book_id").val();
    window.location.href = "deleteById?id=" + id;
});