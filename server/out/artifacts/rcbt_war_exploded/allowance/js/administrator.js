i=1;
layui.use(['laytpl','laypage'], function(){
	var laytpl = layui.laytpl,
	laypage = layui.laypage;
	var data = { //数据
	    "list":[{"username":"陈媛媛","id":"1","idCard":"612425199304121212","type":"住房补贴","time":"2017/09","money":"1000","bank":"中国农业银行股份有限公司诸暨市支行","bankCard":"622023100087893363","phone":"13356565656","status":"通过"},]
	}
	var getTpl = list.innerHTML
	,view = document.getElementById('view');
	laytpl(getTpl).render(data, function(html){
	  view.innerHTML = html;
	});
	
	/*分页*/
	laypage.render({
	    elem: 'page'
	    ,count: 100
	    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
	    ,jump: function(obj){
	    	
	    }
	});
})