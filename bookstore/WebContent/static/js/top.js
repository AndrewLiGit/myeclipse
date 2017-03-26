
var $path = $("#path").val();
$("#search_button").click(function() {
	var $keyword = $("#search").val();
	
	if ($keyword.length > 256) {
        $("#errorFlashMessage").text("搜索的关键字太长。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
    }
	window.location.href = $path + "search?keyword=" + urlReplaceString($keyword);
});

$("#search").keydown(function(event) {
    if (event.keyCode == 13) {
        $("#search_button").click();
    }
});

$("li a[class=category]").click(function() {
	var $category = $(this).html();
	window.location.href = $path + "searchCategory?category=" + urlReplaceString($category);
});