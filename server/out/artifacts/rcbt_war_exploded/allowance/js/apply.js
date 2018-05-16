if(!window.console) { 
	window.console = {
		log: function() {}
	};
}
i=0;

$(document).ready(function(){
	layui.use(['laydate','form','upload'], function() {
		laydate = layui.laydate,
		form = layui.form,
		upload = layui.upload;
		
		//年月选择器
		laydate.render({
			elem: '#birth',
			type: 'month'
		});
		laydate.render({
			elem: '#workingTime',
			type: 'month'
		});
		laydate.render({
			elem: '#insuranceDate',
			type: 'month'
		});
		laydate.render({
			elem: '#applyTime',
			type: 'date',
			value: new Date()
		});
		laydate.render({
			elem: '#graduationDate',
			type: 'month'
		});
		laydate.render({
			elem: '#introductionTime',
			type: 'month'
		});
		//年月范围
		laydate.render({
			elem: '#rage1',
			type: 'month',
			range: true
		});
		
		
		form.verify({
			/*验证方法*/
			header: function(value, item) { //value：表单的值、item：表单的DOM对象    验证码
				if($("#c-headBtn").val() == "") {
					return '头像未上传';
				}
			},
			attachment: function(value, item) { //value：表单的值、item：表单的DOM对象    验证码
				if($("#file1").val() == "") {
					return '证明材料未上传';
				}
			},
		});
		/*提交申请表*/
		form.on('submit(apply)', function(data) {
			layer.confirm('您确认要提交吗？', {
				move: false,
				title: ' ',
			  	btn: ['确定','取消'] //按钮
			}, function(){
				workExperience = {};
				var i=1;
				$("#work input").each(function() {
					var t = Math.ceil(i / 4);
					if(!(workExperience[t] instanceof Object)) {
						workExperience[t] = {};
					}
						workExperience[t][$(this).attr('name').replace('[]', '')] = $(this).val();
						i++;
				});
				myarr1 = [];
				for (var key in workExperience) {
				  myarr1.push(workExperience[key]);
				}
				$.ajax({
					type: "post",
					url: httpCom+"",
					dataType: "json",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({
						"name":$("#name").val(),
						"idCard":$("#idCard").val(idCard),
						"sex":$("input[name='sex']:checked").val(),
						"national":$("#national").val(),
						"nativePlace":$("#province").val()+" "+$("#city").val(),
						"politicsStatus":$("#politicsStatus").val(),
						"birth":$("#birth").val(),
						"graduationDate":$("#graduationDate").val(),
						"school":$("#school").val(),
						"professional":$("#professional").val(),
						"education":$("#education").val(),
						"duty":$("#duty").val(),
						"workingTime":$("#workingTime").val(),
						"introductionTime":$("#introductionTime").val(),
						"applyTime":$("#applyTime").val(),
						"TalentCategory":$("#TalentCategory").val(),
						"applicationCategory":$("#applicationCategory").val(),
						"appliedAmount":$("#appliedAmount").val(),
						"doubleTop":$("#doubleTop").val(),
						"userPhone":$("#userPhone").val(),
						"companyType":$("#companyType").val(),
						"companyName":$("#companyName").val(),
						"companyAddress":$("#companyAddress").val(),
						"companyContact":$("#companyContact").val(),
						"contactProfessional":$("#contactProfessional").val(),
						"contactPhone":$("#contactPhone").val(),
						"bankCard":$("#bankCard").val(),
						"bank":$("#bank").val(),
						"pictureUrl":$("#c-headB")[0].src,
						"educationQrcode":$("#c-codeImg")[0].src,
						"workExperience":myarr1,
						"attachment":$("#file1").val(),
					}),
					success:function(data){
						if(data.status==0){
							window.location.href="list.html";
						}else{
							layer.msg("提交失败");
							return false;
						}
					}
				})
			})
		})
	});
	
	/*头像上传*/
    var fls = flashChecker();
    //alert(fls.f?'支持flash,版本是:'+fls.v:'不支持flash');
    var description=""
    var swfu;
	if(fls.f){
        var onUploadSuccess = function(file,data,respone){
            data = eval("("+data+")");
            if (data.status == 0) {
                $("#c-headImg").attr("src",data.data.fileUrl+"?"+new Date().getTime());
                $("#c-headBtn").val(data.data.fileUrl);
                layer.msg("上传成功！");
                layer.closeAll('loading');
                return false;
            }else{
                layer.msg("头像上传失败");
                layer.closeAll('loading');
                return false;
             }
        };
        var file1_config = uploadify_config;
        file1_config.fileTypeExts = "*.jpg;*.png;*.gif";
        file1_config.width = 320;
        file1_config.onUploadSuccess = onUploadSuccess;
        file1_config.onUploadStart = function(){
        	layer.load();
        }
        file1_config.uploader = httpCom+"file/upload";
        file1_config.formData = {idCard:idCard,pictureType:"0"};
        $('#c-headBtn').uploadify(file1_config);
    }else{
	    $("#c-headBtn").change(function () {
	    	layer.load();
	        var formData = new FormData();
	        formData.append("file", document.getElementById("c-headBtn").files[0]);
	        formData.append("idCard",idCard);
	        formData.append("pictureType", 0);
	        var fileName1=$("#c-headBtn").val();
            var point = fileName1.lastIndexOf("."); 
		    var type = fileName1.substr(point);
		    if(type==".jpg"||type==".gif"||type==".JPG"||type==".GIF" || type==".png" || type==".PNG" ||type==".jpeg"){ 
                $.ajax({
		            url: httpCom+"file/upload",
		            type: "POST",
		            dataType:"json",
		            data: formData,
		            contentType: false,
		            processData: false,
		            success: function (data) {
		                     if (data.status == 0) {
		                        $("#c-headImg").attr("src",data.data.pictureUrl+"?"+new Date().getTime());
		                        $("#c-headBtn").val(data.data.fileUrl);
		                        layer.msg("上传成功！");
		                        layer.closeAll('loading');
		                        return false;
		                     }else{
		                        layer.msg("上传失败");
		                        layer.closeAll('loading');
		                        return false;
		                     }
		                },
		                error: function () {
		                layer.msg("上传失败！");
		                layer.closeAll('loading');
		                return false;
		            }
		        });
     		}else{
     			layer.msg("请选择格式为jpg、gif、png格式的图片上传");
     			layer.closeAll('loading');
     			return false;
     		}
	        
	    });
	}
    
    /*学历二维码上传*/
	if(fls.f){
        var onUploadSuccess = function(file,data,respone){
            data = eval("("+data+")");
            if (data.status == 0) {
                $("#c-codeImg").attr("src",data.data.fileUrl+"?"+new Date().getTime());
                $("#c-code").val(data.data.fileUrl);
                layer.msg("上传成功！");
                layer.closeAll('loading');
                return false;
            }else{
                layer.msg("上传失败");
                layer.closeAll('loading');
                return false;
            }
        };
        var file1_config = uploadify_config;
        file1_config.fileTypeExts = "*.jpg;*.png;*.gif";
        file1_config.width = 320;
        file1_config.onUploadSuccess = onUploadSuccess;
        file1_config.onUploadStart = function(){
        	layer.load();
        }
        file1_config.uploader = httpCom+"file/upload";
        file1_config.formData = {idCard:idCard,pictureType:"1"};
        $('#c-code').uploadify(file1_config);
    }else{
	    $("#c-code").change(function () {
	    	layer.load();
	        var formData = new FormData();
	        formData.append("upload_file", document.getElementById("c-code").files[0]);
	        formData.append("idCard",idCard);
	        formData.append("pictureType", 1);
	        var fileName1=$("#c-code").val();
            var point = fileName1.lastIndexOf("."); 
		    var type = fileName1.substr(point);
		    if(type==".jpg"||type==".gif"||type==".JPG"||type==".GIF" || type==".png" || type==".PNG" ||type==".jpeg"){ 
                $.ajax({
		            url: httpCom+"file/upload",
		            type: "POST",
		            dataType:"json",
		            data: formData,
		            contentType: false,
		            processData: false,
		            success: function (data) {
		                     if (data.status == 0) {
		                        $("#c-codeImg").attr("src",data.data.pictureUrl+"?"+new Date().getTime());
		                        $("#c-code").val(data.data.fileUrl);
		                        layer.msg("上传成功！");
		                        layer.closeAll('loading');
		                        return false;
		                     }else{
		                        layer.msg("上传失败");
		                        layer.closeAll('loading');
		                        return false;
		                     }
		                },
		                error: function () {
		                layer.msg("上传失败！");
		                layer.closeAll('loading');
		                return false;
		            }
		        });
     		}else{
     			layer.msg("请选择格式为jpg、gif、png格式的图片上传");
     			layer.closeAll('loading');
     			return false;
     		}
	        
	    });
	}
    
	/*提交证明材料*/
	if(fls.f){
	    $(function () {
	        var onUploadSuccess = function(file,data,respone){
	            res = eval("("+data+")");
	            if (data.status == 0) {
	            	var fileName1='';
	            	fileName1="<div style='margin-top:10px;'><a href='"+data.data.fileUrl+"' style='font-size: 16px;color: #028ff8;text-decoration: underline;'>证明材料</a></div>"
	            	$(".fileName").html(fileName1);
	            	$("#file1").val(data.data.fileUrl);
	                layer.msg("上传成功！");
	                layer.closeAll('loading');
	                return false;
	            }else{
	                layer.msg("上传失败");
	                layer.closeAll('loading');
	                return false;
	            }
	        };
	        var file1_config = uploadify_config;
	        file1_config.fileTypeExts = "*.pdf";
	        file1_config.width = 320;
	        file1_config.onUploadSuccess = onUploadSuccess;
	        file1_config.onUploadStart = function(){
	        	layer.load();
	        }
	        file1_config.uploader = httpCom+"file/upload";
	        file1_config.formData = {idCard:idCard,pictureType:"2"};
	        $('#file1').uploadify(file1_config);
	    });
	}
	else{
	    $("#file1").change(function () {
	    		layer.load();
	            var formData = new FormData();
	            var fileName=document.getElementById("file1").files[0];
	            var fileName1=$("#file1").val();
	            var point = fileName1.lastIndexOf("."); 
			    var type = fileName1.substr(point); 
			    formData.append("idCard", idCard);
	            formData.append("pictureType",2);
			    if(type==".pdf" || type==".PDF"){
			    	$.ajax({
		                url: httpCom+"file/upload",
		                type: "POST",
		                dataType:"json",
		                data: formData,
		                contentType: false,
		                processData: false,
		                success: function (data) {
	                        if (data.status == 0) {
	                         	var fileName1='';
				            	fileName1="<div style='margin-top:10px;'><a href='"+data.data.fileUrl+"' style='font-size: 16px;color: #028ff8;text-decoration: underline;'>证明材料</a></div>"
				            	$(".fileName").html(fileName1);
				            	$("#file1").val(data.data.fileUrl);
				                layer.msg("上传成功！");
				                layer.closeAll('loading');
				                return false;
	                        }else{
	                            layer.msg("上传失败");
	                            layer.closeAll('loading');
	                            return false;
	                        }
	                    },
	                error: function (XMLHttpRequest, textStatus, errorThrown) {
	                    layer.msg("上传失败！");
	                    layer.closeAll('loading');
	                    return false;
	                }
	            }); 
		            
			    }else{
			    	layer.msg("请选择pdf文件上传！");
			    	layer.closeAll('loading');
			    	return false;
			    }
	        });
	}
	
	
	/*获取已提交的申请表数据*/
	$.ajax({
		type:"post",
		url: httpCom+"apply/get",
		dataType: "Json",
		crossDomain: true,
		contentType: "application/json;charset=utf-8",
		beforeSend:function(){   
			/*layer.load();*/ //上传loading
		},
		data: JSON.stringify({
			"idCard":idCard
		}),
		success:function(data){
			var datas=data.data;
			if(data.status==0){
				var id_list = new Array();
				$("#name").val(datas.name);
				$("#idCard").val(data.idCard);
				$("input[name='sex']:checked").val(datas.sex);
				$("#national").val(datas.national);
				if(datas.nativePalce!=null){
					var str=datas.nativePalce;
					console.log(str)
					var strs=str.split(' ');
					$("#province").val(strs[0]);
					$("#city").val(strs[1]);
				}
				$("#politicsStatus").val(datas.politicsStatus);
				$("#birth").val(datas.birth);
				$("#graduationDate").val(datas.graduationDate);
				$("#school").val(datas.school);
				$("#professional").val(datas.professional);
				$("#education").val(datas.education);
				$("#duty").val(datas.duty);
				$("#workingTime").val(datas.workingTime);
				$("#introductionTime").val(datas.introductionTime);
				$("#applyTime").val(datas.applyTime);
				$("#TalentCategory").val(datas.TalentCategory);
				$("#applicationCategory").val(datas.applicationCategory);
				$("#appliedAmount").val(datas.appliedAmount);
				$("#doubleTop").val(datas.doubleTop);
				$("#userPhone").val(datas.userPhone);
				$("#companyType").val(datas.companyType);
				$("#companyName").val(datas.companyName);
				$("#companyAddress").val(datas.companyAddress);
				$("#companyContact").val(datas.companyContact);
				$("#contactProfessional").val(datas.contactProfessional);
				$("#contactPhone").val(datas.contactPhone);
				$("#bankCard").val(datas.bankCard);
				$("#bank").val(datas.bank);
				if(datas.pictureUrl!=null){
					$("#c-headImg").attr('src',datas.pictureUrl);
					$("#c-headB").val(datas.pictureUrl);
				}
				if(datas.educationQrcode!=null){
					$("#c-codeImg").attr('src',datas.educationQrcode);
					$("#c-codeImg").val(datas.educationQrcode);
				}
				if(datas.workExperience!=null){
					var td="";
					for(var i in datas.workExperience){
						var qizhiTime = 'date_publish'+id_num;
						id_list[id_num+'1'] = qizhiTime;
						id_num++;
						td+="<tr>"+
							"<td><input name='qizhiTime' class='inputStyle date' value="+datas.workExperience.qizhiTime+" id='"+qizhiTime+"'/></td>"+
							"<td><input name='company' class='inputStyle' value='"+datas.workExperience.company+"'/></td>"+
							"<td><input name='department' class='inputStyle' value='"+datas.workExperience.department+"'/></td>"+
							"<td><input name='position' class='inputStyle' value='"+datas.workExperience.position+"'/></td>"+
							"<td><i class='layui-icon delete' style='font-size: 20px;' onclick='deleteTable(this)'>&#xe640;</i></td>"
					}
					$("#work").html(td);
				}
				if(datas.workExperience!=null){
					var fileName1="<div style='margin-top:10px;'><a href='"+datas.attachment+"' style='font-size: 16px;color: #028ff8;text-decoration: underline;'>证明材料</a></div>"
					$(".fileName").html(fileName1);
				}
				
				
			}
		},
		complete: function(data){
		    //这里判断，数据加载成功之后，loading 隐藏
		    layer.closeAll('loading');
		}
	});
	form.render();
	
	
	
	
})
$(function() {

	'use strict';

	var $distpicker = $('#distpicker');

	$distpicker.distpicker({
		province: '浙江省',
		city: '绍兴市',
	});

});
var id_num = 4;
function addTable(){
	var id_list = new Array();
    var id_name = 'rage' + id_num;
    id_list[id_num] = id_name;
    id_num++;
	var tableAdd = "";
	tableAdd = '<tr>' +
			   '<td><input name="date" class="inputStyle date" placeholder="" id="'+id_name+'"/></td>'+
	           '<td><input name="company" class="inputStyle" placeholder="" id="company1"/></td>'+
	           '<td><input name="department" class="inputStyle" placeholder="" id="department1"/></td>'+
	           '<td><input name="duty" class="inputStyle" placeholder="" id="duty1"/></td>'+
	           '<td><i class="layui-icon delete" style="font-size: 20px;" onclick="deleteTable(this)">&#xe640;</i></td>'+
	           '</tr>';
	$("#resume").append(tableAdd);
	laydate.render({
		elem: '#'+id_name,
		type: 'month',
		range: true
	});
}
