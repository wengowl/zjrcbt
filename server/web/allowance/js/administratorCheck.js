i=0;
layui.use(['laytpl','laypage','table'], function(){
	var laytpl = layui.laytpl,
	laypage = layui.laypage,
	table=layui.table;
	/*var data = { 
	    "list":[{"username":"陈媛媛","id":"1","idCard":"612425199304121212","type":"住房补贴","time":"2017/09","money":"1000","bank":"中国农业银行股份有限公司诸暨市支行","bankCard":"622023100087893363","phone":"13356565656","status":"待复核"},]
	}
	var getTpl = list.innerHTML
	,view = document.getElementById('view');
	laytpl(getTpl).render(data, function(html){
	  view.innerHTML = html;
	});*/
	
	/*分页*/
	/*laypage.render({
	    elem: 'page'
	    ,count: 100
	    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
	    ,jump: function(obj){
	    	
	    }
	});*/
	var statusCheck="'2','4'";
	var applicationCategory=$("#applicationCategory").val();
	console.log(applicationCategory)
	table.render({
		text: {
		    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
		 },
		elem: '#checkList'
	    ,url: httpCom+'audit/list' //数据接口
	    ,where:{applicationCategory:applicationCategory,status:statusCheck,}
	    ,method:"post"
	    ,page: true //开启分页
	    ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
	      layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
	      //,curr: 5 //设定初始在第 5 页
	      ,groups: 5 //只显示 1 个连续页码
	      ,first: true //不显示首页
	      ,last: true //不显示尾页
	      
	    }
	    ,cols: [[ //表头
	      {field: 'username', title: '姓名', width:"11%"}
	      ,{field: 'idCard', title: '身份证号', width:"15%"}
	      ,{field: 'applicationCategory', title: '类型', width:"10%"}
	      ,{field: 'introductionTime', title: '引进时间', width:"10%"} 
	      ,{field: 'appliedAmount', title: '申报金额', width: "10%"}
	      ,{field: 'bank', title: '个人开户银行', width: "15%"}
	      ,{field: 'bankCard', title: '银行卡号', width: "15%"}
	      ,{field: 'userPhone', title: '申请人电话', width: "15%"}
	    ]]
	    
	})
})
