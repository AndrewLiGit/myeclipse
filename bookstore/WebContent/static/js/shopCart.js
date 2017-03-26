$("li a[class=category]").click(function() {
	var $category = $(this).html();
	window.location.href = "searchCategory?category=" + urlReplaceString($category);
});

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

/*var $allTotal = $(".all_total").text();*/
$(".decrease").click(function(){
	/*var $allTotal = $(".all_total").text();*/
	var $number = $(this).next(".number").val();
	/*var $price = $(this).parent().prev(".price").text();
	var $total = $(this).parent().next(".total").text();*/
	if($number > 1){
		$number = $number - 1;
		/*$(this).next(".number").val($number);*/
		/*$(this).parent().next(".total").text(parseFloat($total) - parseFloat($price));*/
		/*$(".all_total").text(parseFloat($allTotal) - parseFloat($price));*/
	} 
	if($(this).next(".number").val() == 1) {
		$(this).css('visibility', 'hidden');
	}
	
	var $bookId = $(this).siblings(".book_id").val();
	window.location.href = "updateBookOrder?id=" + parseInt($bookId) + "&number=" + parseInt($number);
});

$(".add").click(function(){
	/*var $allTotal = $(".all_total").text();*/
	var $number = $(this).prev(".number").val();
	/*var $price = $(this).parent().prev(".price").text();
	var $total = $(this).parent().next(".total").text();*/
	$number = parseInt($number) + 1;
	/*$(this).prev(".number").val($number);*/
	/*$(this).parent().next(".total").text(parseFloat($total) + parseFloat($price));
	$(".all_total").text(parseFloat($allTotal) + parseFloat($price));*/
	$(this).siblings(".decrease").css('visibility', 'visible');
	
	var $bookId = $(this).next(".book_id").val();
	window.location.href = "updateBookOrder?id=" + parseInt($bookId) + "&number=" + parseInt($number);
});

$(".number").change(function(){
	var $number = $(this).val();
	/*var $price = $(this).parent().prev(".price").text();*/
	if(!isTnteger($number) || $number < 1 || ($number + "").length > 11) {
		$(this).val(1);
		$number = $(this).val();
		/*$("#errorFlashMessage").text("请输入正确的图书数量。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);*/
       /* return;*/
	}
	
	if($number == 1){
		$(this).prev(".decrease").css('visibility', 'hidden');
	} else {
		$(this).prev(".decrease").css('visibility', 'visible');
	}
	
	/*$(this).parent().next(".total").text($number * parseFloat($price));*/
	/*var $allTotal = 0.0;
	$(".book_order .total").each(function(){
		$allTotal = parseFloat($allTotal) + parseFloat($(this).text());
	});
	$(".all_total").text(parseFloat($allTotal));*/
	
	var $bookId = $(this).siblings(".book_id").val();
	window.location.href = "updateBookOrder?id=" + parseInt($bookId) + "&number=" + parseInt($number);
});

$("#clear").click(function(){
	window.location.href = "removeAllBookOrder";
	
});

$("#go_shop").click(function(){
	window.location.href = "index";
});

$("#go_submit_order").click(function(){
	window.location.href = "confirmOrder";
});

$(".delete").click(function() {
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
    window.location.href = "deleteBookOrder?id=" + parseInt(id);
});

$(function message() {
    $("#msg").fadeOut(3000);
});
















