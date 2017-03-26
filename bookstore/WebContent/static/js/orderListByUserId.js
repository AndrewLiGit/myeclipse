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
    window.location.href = "listByUserId?currentPage=" + $pageNum;
});

$(".go_input").keydown(function(event) {
    if (event.keyCode == 13) {
        $(".go_button").click();
    }
});