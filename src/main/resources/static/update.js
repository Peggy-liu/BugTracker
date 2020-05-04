$(document).ready(function(){
	$("form").submit(function(event){
		event.preventDefault();
		ajaxPost();
	})
});

function ajaxPost(){
	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');
	var id = $("#ID").attr('content');
	var title = $("#title").val();
	var language = [];
	$.each($("input[name='language']:checked"), function(){
		language.push($(this).val());
	})
	language = language.join(",");
	
	var description=$("#des").val();
	
	
	var data = {
			id:id,
			title:title,
			language:language,
			description:description
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url:"/user/update",
		data : JSON.stringify(data),
	    dataType : 'json',
		beforeSend: function(xhr) {
             xhr.setRequestHeader(header, token);
         },
		success:function(result){
			if(result.status == "Done"){
				if(result.data==true){
					$("#msg").text("The ticket has been updated successfully!");
				}
				else{
					$("#msg").html("<strong>Error</strong>");
				}
			}
		},
	error:function(e){
		alert("error!");
		console.log("ERROR:", e);
	}
	});
}