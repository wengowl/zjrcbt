i=100;
	$.ajax({
		type:"post",
		url: httpCom+"/rcbt/apply/get",
		dataType: "Json",
		crossDomain: true,
		contentType: "application/json;charset=utf-8",
		beforeSend:function(){   
			/*layer.load();*/ //上传loading
		},
		data: JSON.stringify({
			"idNum":idCard
		}),
		success:function(data){
			var datas=data.data;
			if(data.status==0){
				$("#name").text(datas.name);
				$("#idCard").text(data.idCard);
				$("#sex").text(datas.sex=='0'?'男':'女');
				$("#national").text(datas.national);
				$("#nativePlace").text(datas.nativePalce)
				$("#politicsStatus").text(datas.politicsStatus);
				$("#birth").text(datas.birth);
				$("#graduationDate").text(datas.graduationDate);
				$("#school").text(datas.school);
				$("#professional").text(datas.professional);
				var education="";
				if(datas.education==0){
					education="专科";
				}else if(datas.education==1){
					education="本科";
				}if(datas.education==2){
					education="硕士研究生";
				}if(datas.education==3){
					education="博士研究生";
				}
				$("#education").text(education);
				$("#duty").text(datas.duty);
				$("#workingTime").text(datas.workingTime);
				$("#introductionTime").text(datas.introductionTime);
				$("#applyTime").text(datas.applyTime);
				$("#TalentCategory").text(datas.TalentCategory);
				$("#applicationCategory").text(datas.applicationCategory=='0'?'住房补贴':'生活补贴');
				$("#appliedAmount").text(datas.appliedAmount);
				$("#doubleTop").text(datas.doubleTop=='0'?'是':'否');
				$("#userPhone").text(datas.userPhone);
				$("#companyType").text(datas.companyType);
				$("#companyName").text(datas.companyName);
				$("#companyAddress").text(datas.companyAddress);
				$("#companyContact").text(datas.companyContact);
				$("#contactProfessional").text(datas.contactProfessional);
				$("#contactPhone").text(datas.contactPhone);
				$("#bankCard").text(datas.bankCard);
				$("#bank").text(datas.bank);
				if(datas.pictureUrl!=null){
					$("#pictureUrl").attr('src',datas.pictureUrl);
				}
				if(datas.educationQrcode!=null){
					$("#educationQrcode").attr('src',datas.educationQrcode);
				}
				if(datas.workExperience!=null){
					var td="";
					for(var i in datas.workExperience){
						var qizhiTime = 'date_publish'+id_num;
						id_list[id_num+'1'] = qizhiTime;
						id_num++;
						td+="<tr>"+
							"<td>"+datas.workExperience.qizhiTime+"</td>"+
							"<td>"+datas.workExperience.company+"</td>"+
							"<td>"+datas.workExperience.department+"</td>"+
							"<td>"+datas.workExperience.position+"</td>";
					}
					$("#work").html(td);
				}
				if(datas.attachment!=null){
					var fileName1="<span style='color: #007DDB;text-decoration: underline;padding-right: 20px;font-size: 18px;'>证明材料</span>"+
						"<a href="+datas.attachment+" class='layui-btn layui-btn-sm'>下载</a>";
					$(".fileName").html(fileName1);
				}
				
				
			}
		},
		complete: function(data){
		    //这里判断，数据加载成功之后，loading 隐藏
		    layer.closeAll('loading');
		}
	});