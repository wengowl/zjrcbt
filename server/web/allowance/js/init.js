var laytpl;
$(function () {
    var data = {//数据
        "banner": "img/banner2.jpg"
        , "username": '13356565652'
    }
    var menu = {
        "title": "个人中心",
        "list": [
            {"title": "我的申报", "href": "list.html"},
            {"title": "补贴记录", "href": "listRecord.html"},
            {"title": "修改信息", "href": "changeMsg.html"},
            {"title": "修改密码", "href": "changePassword.html"},
            
        ]
    };
    var menu1 = {
        "title": "个人中心",
        "list": [
        	{"title": "待复核", "href": "administratorCheck.html"},
            {"title": "已复核", "href": "administrator.html"},
            {"title": "补贴汇总", "href": "administratorList.html"},
            {"title": "补贴记录", "href": "administratorRecord.html"},
            {"title": "导入数据", "href": "import.html"},
            {"title": "修改密码", "href": "changePasswordA.html"},
        ]
    };
    loadTpl('theme/header.html', data, 'header');
    loadTpl('theme/footer.html', data, 'footer');
    loadTpl('theme/menu.html', menu, 'menu',function(){
    	$(".layui-nav-child").children().eq(i).find("a").addClass("active");
    });
    loadTpl('theme/menu.html', menu1, 'menu1',function(){
    	$(".layui-nav-child").children().eq(i).find("a").addClass("active");
    });
    
})

function loadTpl(url, data, id,callback)
{
    if(typeof(laytpl) == 'undefined')
    {
        layui.use('laytpl', function (s) {
            laytpl = s;
        })
    }
    $.get(url, function (text) {
        view = $('#' + id);
        laytpl(text).render(data, function (html) {
            view.html(html);
		    if(typeof(callback) != 'undefined')
		    {
		        callback();
		    }
        });
    });
    
}
