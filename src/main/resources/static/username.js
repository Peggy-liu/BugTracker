$(document).ready(function(){
	$("#username").on("input", function(){
		var data=$("#username").val();
		
		
		$.ajax({
			type:"GET",
			contentType:"application/json",
			url:"/user/existed?username="+data,
			success:function(result){
				if(result.status=="Done"){
					if(result.data == true){
						$("#usernameError").text("username exist!");
						 $("#submit").prop("disabled", true);
					}
					else{
						$("#usernameError").text("");
						 $("#submit").prop("disabled", false);
					}
				}
			}
		});
	});
});
