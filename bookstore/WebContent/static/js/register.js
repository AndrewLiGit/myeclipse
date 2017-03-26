var $path = $("#path").val();

var $isSubmitForm = true;
$("#user_name").blur(function() {
	var userName = $("#user_name").val();
	$.ajax({
		data:"userName=" + userName,
		type:"GET",
		url:'/bookstore/page/user/checkUserName',
		dataType:"json",
		success:function(data) {
			if(data.msg == "此用户名已经存在。") {
				$("#errorFlashMessage").text(data.msg);
		        $("#errorFlashMessage").css("display", "block");
		        $("#errorFlashMessage").fadeOut(3000);
		        $isSubmitForm = false;
			} else {
				//$("#errorFlashMessage").css("display", "none");
				$isSubmitForm = true;
			}
		}
	});
});


$("#register").click(function() {
	
	var userName = $("#user_name").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	var email = $("#email").val();
	var birthday = $("#birthday").val();
	
	if (!$.trim(userName)) {
		$("#errorFlashMessage").text("用户名不能为空。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
	}
	
	if (!$.trim(password) || !$.trim(password2)) {
		$("#errorFlashMessage").text("密码不能为空。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
	}
	
	if (!$.trim(email)) {
		$("#errorFlashMessage").text("邮箱不能为空。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
	}
	
	if (password != password2) {
		$("#errorFlashMessage").text("两个密码不一致。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
	}
	
	if (userName.length > 12 || userName.length < 6) {
		$("#errorFlashMessage").text("用户名不合法。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
	}
	
	if (password.length > 12 || password.length < 6) {
		$("#errorFlashMessage").text("密码不合法。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
	}
	
	if ($.trim(email)) {
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!reg.test(email)){
			$("#errorFlashMessage").text("邮箱格式不正确。");
	        $("#errorFlashMessage").css("display", "block");
	        $("#errorFlashMessage").fadeOut(3000);
	        return;
		}
	}
	
	if (!$.trim(birthday)) {
		$("#birthday").val("1970-01-01");
	}
	
	if ($isSubmitForm) {
		
		var country = $("select[name = country]").val();
		var province = $("select[name = province]").val();
		var city = $("input[name = city]").val();
		var street = $("input[name = street]").val();
		$("input[name = address]").val(country + province + city + street);
		
        $("#register_form").submit();
    }
});



$("#search_button").click(function() {
	var $keyword = $("#search").val();
	
	if ($keyword.length > 256) {
        $("#errorFlashMessage").text("搜索的关键字太长。");
        $("#errorFlashMessage").css("display", "block");
        $("#errorFlashMessage").fadeOut(3000);
        return;
    }
	window.location.href = $path + "/search?keyword=" + urlReplaceString($keyword);
});

$("#search").keydown(function(event) {
    if (event.keyCode == 13) {
        $("#search_button").click();
    }
});

$("li a[class=category]").click(function() {
	var $category = $(this).html();
	window.location.href = $path + "/searchCategory?category=" + urlReplaceString($category);
});