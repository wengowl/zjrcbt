var laytpl;
$(function () {
   var data = {//数据
        
   	}
    loadTpl('theme/detail.html', data, 'detail');
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
