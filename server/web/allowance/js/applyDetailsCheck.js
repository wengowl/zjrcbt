i=100;
$(function(){
	$(".radioPass").on("click","div:eq(1)",function(){
		$(".opinion").show();
	});
	$(".radioPass").on("click","div:eq(0)",function(){
		$(".opinion").hide();
	});
})
