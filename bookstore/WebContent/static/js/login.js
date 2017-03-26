  $(function () {
      $(document).ready(function() {
          $(".login_form_username_input").focus();
      });
      
      $(".login_form_button").on("click", function() {
          var $isSubmitForm = true;
          if (!$.trim($("#userName").val())) {
              $("#error_div").css("visibility", "visible");
              $("#username_wrong").css("visibility", "visible");
              $isSubmitForm = false;
              if (!$.trim($("#password").val())) {
                  $("#error").text("用户名和密码为空。");
                  $("#login_form_password_wrong").css("visibility", "visible");
                  return;
              } else {
                  $("#error").text("用户名不能为空。");
                  $("#login_form_password_wrong").css("visibility", "hidden");
              }
          } else if (!$.trim($("#password").val())) {
              $("#error").text("密码不能为空。");
              $("#error_div").css("visibility", "visible");
              $("#username_wrong").css("visibility", "hidden");
              $("#login_form_password_wrong").css("visibility", "visible");
              $isSubmitForm = false;
          } else {
              $("error").text("");
              $("#error_div").css("visibility", "hidden");
          }
          if($isSubmitForm){
	          if (($.trim($("#userName").val())).length > 12 || ($.trim($("#userName").val())).length < 6) {
	              $("#error_div").css("visibility", "visible");
	              $("#username_wrong").css("visibility", "visible");
	              $isSubmitForm = false;
	              if (($.trim($("#password").val())).length > 12) {
	                  $("#error").text("用户名和密码不正确。");
	                  $("#login_form_password_wrong").css("visibility", "visible");
	                  return;
	              } else {
	                  $("#error").text("用户名不存在。");
	                  $("#login_form_password_wrong").css("visibility", "hidden");
	              }
	          } else if (($.trim($("#password").val())).length > 12 || ($.trim($("#password").val())).length < 6) {
	              $("#error").text("密码不正确。");
	              $("#error_div").css("visibility", "visible");
	              $("#username_wrong").css("visibility", "hidden");
	              $("#login_form_password_wrong").css("visibility", "visible");
	              $isSubmitForm = false;
	          } else {
	              $("error").text("");
	              $("#error_div").css("visibility", "hidden");
	          }
          }
          if ($isSubmitForm) {
              $("#loginForm").submit();
          }
      });
      
      $("body").keydown(function(event) {
          if (event.keyCode == 13) {
              $(".login_form_button").click();
          }
      });
  });