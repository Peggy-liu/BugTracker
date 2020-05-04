

$(document).ready(function(){
	$('.status').each(function(i, obj){
		var status = $(obj).text();
		
		if(status == "CLOSE"){
			$(".btn").eq(i).addClass('disabled');
		}
		else{
			$(".btn").eq(i).removeClass('disabled');
			$(".btn").eq(i).attr("data-toggle", "modal");
		}
	});
	
	$(".btn").on('click',function(){
		var id = $(this).attr('content');   //this selector is really important here!
		var obj = $(this);
		
		if(confirm("Are you sure you have solved this ticket?")){
			
			var token = $('#_csrf').attr('content');
			var header = $('#_csrf_header').attr('content');
			$.ajax({
				type:"POST",
				url:"/user/close?id="+id,
				beforeSend: function(xhr) {
		             xhr.setRequestHeader(header, token);
		         },
				success:function(result){
					if(result.status == "Done"){
						if(result.data==true){
							
							changeStatus(obj, id);
							
						}
						else{
							alert("Problem encountered during close!" );
						}
					}
				},
			error:function(e){
				alert("error!");
				console.log("ERROR:", e);
			}
			});
		}
		else{
			
		}
	})
});

function changeStatus(obj,id){
	var temp = $(obj).closest('tr').find('.status');
	if(temp.text() == "OPEN"){
		alert("Ticket number "+id+" has been successfully closed!" );
		temp.text("CLOSE");
		 $(obj).addClass('disabled');
		 $(obj).removeAttr('data-toggle');
	}
}

