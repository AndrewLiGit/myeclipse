$("#search_button").click(function() {
	var $keyword = $("#search").val();
	
	if ($keyword.length > 256) {
        $("#errorFlashMessage").text("搜索的关键字太长。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
    }
	window.location.href = "search?keyword=" + urlReplaceString($keyword);
});

$("#search").keydown(function(event) {
    if (event.keyCode == 13) {
        $("#search_button").click();
    }
});

$("li a[class=category]").click(function() {
	var $category = $(this).html();
	window.location.href = "searchCategory?category=" + urlReplaceString($category);
});

/*$(".book_detail").click(function() {
	var $id = $("this .book_id").val();
	$(this).attr(href, "bookDetail?id=" + $id);
});*/
$(".go_button").on("click", function () {
    var $pageNum = $(".go_input").val();
    var $totalPages = $("#total_pages").text();
    var $keyword = $("#search").val();
    if (!isTnteger($pageNum)) {
        $("#errorFlashMessage").text("请输入正确的页面。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
    }
    if (eval($pageNum) > eval($totalPages)) {
        $pageNum = $totalPages;
    }
    window.location.href = "searchCategory?currentPage=" + $pageNum + "&category=" + urlReplaceString($keyword);
});

$("#search").keydown(function(event) {
    if (event.keyCode == 13) {
        $("#search_button").click();
    }
});