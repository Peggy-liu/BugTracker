$(document).ready(function(){
	$('#select').change(function(){
		var selection = $(this).val();
		var dataset = $('table').find('tbody').find('tr');
		dataset.hide();
		
		if(selection=="solved"){
			    
				dataset.filter(function(){
					return $(this).find('.status').text()==="CLOSE";
				}).show();
			
				
		}
		
		if(selection=="unsolved"){		    
				dataset.filter(function(){
					return $(this).find('.status').text()==="OPEN";
				}).show();
			
		}
		
		if(selection=="all"){
			dataset.show();
		}

	})
});