i=0;
layui.use('laytpl', function(){
	applyBatch='';
	applicationCategory='';
	appliedAmount='';
	idCard='';
	applyStatus=''
	$.ajax({
		type: "post",
		url: httpCom+"apply/get",
		dataType: "json",
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify({
			"idCard":idCard,
		}),
		success:function(data){
			if(data.status==0){
				applyBatch=data.data.applyTime;
				applicationCategory=(data.data.applicationCategory==0?"住房不提":"生活补贴");
				appliedAmount=data.data.appliedAmount;
				idCard=idCard;
				applyStatus=data.data.applyStatus;
			}else if(data.status==-1){
				$("#list_all").html("<td colspan='6'>暂无数据</td>")
			}
		}
	});
	
	var laytpl = layui.laytpl;
	var data = { //数据
	    "list":[{"batch":applyBatch,"type":applicationCategory,"idCard":idCard,"money":appliedAmount,"status":applyStatus}]
	}
		/*"list":[{"batch":2018-6,"type":"住房补助","idCard":"123456789012345678","money":"600","status":"待复核"}]
	}*/
	var getTpl = demo.innerHTML
	,view = document.getElementById('view');
	laytpl(getTpl).render(data, function(html){
	  view.innerHTML = html;
	});
})