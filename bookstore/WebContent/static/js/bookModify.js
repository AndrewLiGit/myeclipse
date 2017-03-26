$(function(){
	var category = $("#category_right").val();
	var isSpecialPrice = $("#is_special_price_right").val();
	var hot = $("#hot_right").val();
	
	$("select[name=bigCategoryId]").val(category);
	
	$(".hot_radio").each(function() {
        if ($(this).val() == hot) {
            $(this).attr("checked", "checked");
            /*$(this).attr("name", "hot");*/
        }
    });
	
	$(".isSpecialPrice_radio").each(function() {
        if ($(this).val() == isSpecialPrice) {
            $(this).attr("checked", "checked");
            /*$(this).attr("name", "isSpecialPrice");*/
        }
    });
	
});

$("#modify").click(function() {
	var $isSubmitForm = true;
	
	if ($isSubmitForm) {
        $("#modify_form").submit();
    }
});

$("#picture_file").change(function() {
	var path = $(this).val();
	var a = new Array();
	a = path.split("\\");
	$("#picture").val(a[a.length - 1]);
});