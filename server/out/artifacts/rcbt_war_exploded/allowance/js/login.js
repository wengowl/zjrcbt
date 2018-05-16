if(!window.console) { 
	window.console = {
		log: function() {}
	};
}
var code ; //在全局定义验证码
var code1 ;
var code2 ;
//产生验证码  
window.onload = function createCode(e){  
    code = ""; 
    code1 = "";
    code2 = ""; 
    var codeLength = 4;//验证码的长度  
    var checkCode = document.getElementById("code");
    var checkCode1 = document.getElementById("code1");  
    var checkCode2 = document.getElementById("code2");  
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',  
     's','t','u','v','w','x','y','z');//随机数  
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }  
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code1 += random[index];//根据索引取得随机数加到code上  
    }  
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code2 += random[index];//根据索引取得随机数加到code上  
    }  
    checkCode.value = code;//把code值赋给验证码  
    checkCode1.value = code1;//把code值赋给验证码
    checkCode2.value = code2;//把code值赋给验证码
};

function createCode(e){  
    code = ""; 
    code1 = "";
    code2 = "";
    var codeLength = 4;//验证码的长度  
    var checkCode = document.getElementById("code");   
    var checkCode1 = document.getElementById("code1"); 
    var checkCode2 = document.getElementById("code2"); 
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',  
     's','t','u','v','w','x','y','z');//随机数  
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code1 += random[index];//根据索引取得随机数加到code上  
    }
    for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*62);//取得随机数的索引（0~35）  
        code2 += random[index];//根据索引取得随机数加到code上  
    }
    checkCode.value = code;//把code值赋给验证码  
    checkCode1.value = code1;//把code值赋给验证码 
    checkCode2.value = code2;//把code值赋给验证码 
}  

/*--------cookie*/
function getCookie(c_name)
{
    if (document.cookie.length > 0)
    {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1)
        {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1)
                c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}

function setCookie(c_name, value, expiredays)
{
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = c_name + "=" + escape(value) +
            ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
}

function checkCookie(){
    username = getCookie('username')
    if (username != null && username != "")
    {
        alert('Welcome again ' + username + '!')
    } else
    {
        username = prompt('Please enter your name:', "")
        if (username != null && username != "")
        {
            setCookie('username', username, 365)
        }
    }
    idcard = getCookie('idcard')
    if (idcard != null && idcard != "")
    {
        alert('Welcome again ' + idcard + '!')
    } else
    {
        idcard = prompt('Please enter your name:', "")
        if (idcard != null && idcard != "")
        {
            setCookie('idcard', idcard, 365)
        }
    }
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
	/*登陆*/
	form.on('submit(c-login)', function(data) {
		$.ajax({
			type: "post",
			url: httpCom+"user/login",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				"user":$("#name-l").val(),
				"password":$("#password-l").val()
			}),
			success:function(data){
				if(data.status==0){
					userName=data.data.user;
					setCookie("username",userName,3650);
					idCard=data.data.idCard;
					setCookie("idcard",idCard,3650);
					layer.alert('登录成功', {
						title: ' '
						,closeBtn: 0
						,move: false
						,time: 0
						,btn: ['确定']
						,yes:function(index){
					  		//do something
					  	if(data.data.user_type==0){
								window.location.href="list.html";//个人
							}else{
								window.location.href="administratorCheck.html";//管理员
							}
					  	layer.close(index);
						} 
					});
				}else if(data.status=1){
					layer.msg("用户名或密码错误");
					return false;
				}
			}
		});
		return false;
	});
	/*注册*/
	form.on('submit(c-register)', function(data) {
		$.ajax({
			type: "post",
			url: httpCom+"user/register",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				"idCard":$("#Idcard-r").val(),
				"user":$("#name-r").val(),
				"email":$("#email-r").val(),
				"password":$("#password-r").val(),
			}),
			success:function(data){
				if(data.status==0){
					userName=$("#name-r").val();
					setCookie("username",userName,3650);
					idCard=$("#Idcard-r").val();
					setCookie("idcard",idCard,3650);
					layer.alert('注册成功', {
						title: ' '
						,closeBtn: 0
						,move: false
						,time: 0
						,btn: ['确定']
						,yes:function(index){
					  		//do something
							window.location.href="list.html";//个人
					  	layer.close(index);
						} 
					});
				}else if(data.status==1){
					layer.msg("用户名已被注册");
					return false;
				}else if(data.status==2){
					layer.msg("身份证已被注册");
					return false;
				}else if(data.status==4){
					layer.msg("邮箱已被注册");
					return false;
				}
			}
		});
		return false;
	});

	/*找回密码*/
	form.on('submit(c-find)', function(data) {
		$.ajax({
			type: "post",
			url: httpCom+"user/passwd/reset",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				"user":$("#name-f").val(),
				"email":$("#email-f").val(),
			}),
			success:function(data){
				if(data.status==0){
					layer.msg("新密码已发送到该邮箱,请登录该邮箱查看");
					return false;
				}else if(data.status==1){
					layer.msg("该用户未注册");
					return false;
				}else if(data.status==2){
					layer.msg("邮箱与用户名不匹配");
					return false;
				}else{
					layer.msg("邮件发送失败");
					return false;
				}
			}
		});
		return false;
	});

	/*修改密码*/

});