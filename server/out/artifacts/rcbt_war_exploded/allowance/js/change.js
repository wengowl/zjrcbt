if(!window.console) { 
	window.console = {
		log: function() {}
	};
}
var code ; //在全局定义验证码

//产生验证码  
window.onload = function createCode(e){  
    code = ""; 

    var codeLength = 4;//验证码的长度  
    var checkCode = document.getElementById("code");
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',  
     's','t','u','v','w','x','y','z');//随机数  
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }  
    checkCode.value = code;//把code值赋给验证码  
};

function createCode(e){  
    code = ""; 
    var codeLength = 4;//验证码的长度  
    var checkCode = document.getElementById("code");   
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',  
     's','t','u','v','w','x','y','z');//随机数  
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }
    checkCode.value = code;//把code值赋给验证码  
}  



function navopen(){
	if($(".layui-nav-item").hasClass("layui-nav-itemed")){
		$(".layui-nav-item").removeClass("layui-nav-itemed");
		$(".nav1 i").html("&#xe619;")
	}else{
		$(".layui-nav-item").addClass("layui-nav-itemed") 
		$(".nav1 i").html("&#xe61a;")
	}
}


layui.use('form', function() {
	var form = layui.form;
	form.verify({
		card: function(value, item) { //value：表单的值、item：表单的DOM对象
			var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
			if(!reg.test(value)) {
				return '身份证号码错误';
			}
		},
		pass: [
			/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
		],
		pass1: function(value, item) { //value：表单的值、item：表单的DOM对象
			value1 = $("#password-r").val();
			if(value != value1) {
				return '两次密码不一致';
			}
		},
		pass2: function(value, item) { //value：表单的值、item：表单的DOM对象
			value1 = $("#password_pre").val();
			if(value == value1) {
				return '原密码和新密码一致';
			}
		},
		pass3: function(value, item) { //value：表单的值、item：表单的DOM对象
			value1 = $("#password1").val();
			if(value != value1) {
				return '确认密码与新密码不一致';
			}
		},
		vCode: function(value, item) { //value：表单的值、item：表单的DOM对象    验证码
			if($("#code").val() != value) {
				return '验证码错误,区分大小写';
			}
		},
		vCode1: function(value, item) { //value：表单的值、item：表单的DOM对象    验证码
			if($("#code1").val() != value) {
				return '验证码错误,区分大小写';
			}
		},
		vCode2: function(value, item) { //value：表单的值、item：表单的DOM对象    验证码
			if($("#code2").val() != value) {
				return '验证码错误,区分大小写';
			}
		},
	})

	/*修改密码*/
	form.on('submit(changePasswordBtn)', function(data) {
		$.ajax({
			type: "post",
			url: httpCom+"",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				"user":$("#user_name").val(),
				"oldPassword":$("#password_pre").val(),
				"newPassword":$("#password1").val(),
			}),
			success:function(data){
				if(data.status==0){
					layer.msg("修改成功");
					return false;
				}else if(data.status==1){
					layer.msg("原始密码错误");
					return false;
				}else{
					layer.msg("修改失败");
					return false;
				}
			}
		});
		return false;
	});

	/*修改密码*/
	form.on('submit(changeMsgBtn)', function(data) {
		$.ajax({
			type: "post",
			url: httpCom+"",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				"user":$("#user_name").val(),
				"idCard":$("#user_idCard").val(),
				"email":$("#email").val(),
			}),
			success:function(data){
				if(data.status==0){
					layer.msg("修改成功");
					return false;
				}else if(data.status==1){
					layer.msg("邮箱已存在");
					return false;
				}else{
					layer.msg("修改失败");
					return false;
				}
			}
		});
		return false;
	});

});