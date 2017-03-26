
$(".mess").click(function() {
	$("#deleteMark").css("display", "block");
    $("#deleteWin").css("display", "block");
});

$(".action_item, .close").click(function () {
    $("#deleteMark").css("display", "none");
    $("#deleteWin").css("display", "none");
});

$(".yes").click(function () {
	var $content = $(".i_message").val();
    window.location.href = "save?content=" + $content;
});


















$(".go_button").on("click", function () {
    var $pageNum = $(".go_input").val();
    var $totalPages = $("#total_pages").text();
    if (!isTnteger($pageNum)) {
        $("#errorFlashMessage").text("请输入正确的页面。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
    }
    if (eval($pageNum) > eval($totalPages)) {
        $pageNum = $totalPages;
    }
    window.location.href = "list?currentPage=" + $pageNum;
});