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

/*获取cookie里面的用户名*/
userName = getCookie('username');
/*idCard = getCookie('idcard');*/
idCard="123456789012345678";

layui.use('element', function(){
  	var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  	
});
httpCom="http://127.0.0.1:8080/";
function navopen(){
	if($(".layui-nav-item").hasClass("layui-nav-itemed")){
		$(".layui-nav-item").removeClass("layui-nav-itemed");
		$(".nav1 i").html("&#xe619;")
	}else{
		$(".layui-nav-item").addClass("layui-nav-itemed") 
		$(".nav1 i").html("&#xe61a;")
	}
}




