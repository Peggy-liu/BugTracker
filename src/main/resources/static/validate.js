//$(document).ready(function(){
//	$("#password", "#confirmed_psd").on("keyup", function(){
//		if($("#password").val() != $("#confirmed_psd").val() ){
//			$("#psdError").text("Confirmed password does not match!");
//			$("#submit").prop("disabled", true);
//		}
//		else{
//			$("#psdError").text("");
//			$("#submit").prop("disabled", false);
//		}
//	});
//});
    $(document).ready(function() {
        $('#register').validate({
            rules: {
                username: "required",
                password: {
                	minlength:6
                },
                confirmed_password: {
                	minlength:6,
                	equalTo:"#password"
                },
                email: "required"
            },
            messages: {
                username: "Please enter username",
                confirmed_password: "Please enter matching password",
                email: "Please enter email address!"

            },
            submitHandler: function(form) {
                form.submit();
            }

            // any other options and/or rules
        });
    });