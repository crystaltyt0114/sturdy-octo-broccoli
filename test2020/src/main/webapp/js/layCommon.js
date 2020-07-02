layui.define([ 'jquery' ], function(exports) {
	var $ = layui.$;
	var clayui = {
		table: {
			cellMinWidth : 95,
	        page : true,
	        height : "full-95",
	        limits : [10,20,30],
	        limit : 10,
	        response: {
	        	statusCode: 1
	        },
	        parseData: function(ret){
	        	return {
	    	      "code": ret.code, //解析接口状态
	    	      "msg": ret.message, //解析提示文本
	    	      "count": ret.data.total, //解析数据长度
	    	      "data": ret.data.records //解析数据列表
	    	    };
	        }
		},
		selectRow: function (table, listId){
	    	var checkStatus = table.checkStatus(listId),
	    		data = checkStatus.data;
	    	if(data.length==0){
	    		layer.msg("请选择一行数据");
	    	}
	    	if(data.length>1) {
	    		layer.msg("只能选择一行数据");
	    	}
	    	return data;
	    }
	},
	clayer = {
		submit: function() {
			return clayer.shade("数据提交中，请稍候");
		},
		loading: function() {
			return clayer.shade("数据加载中，请稍候");
		},
		shade: function(msg) {
			msg = msg ? msg : '数据处理中，请稍候';
			return top.layer.msg(msg, {
				icon : 16,
				time : false,
				shade : 0.8
			});
		},
		full: function(opts) {
			var index = layui.layer.open(opts);
			layer.full(index);
			window.sessionStorage.setItem("index", index);
			//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layer.full(window.sessionStorage.getItem("index"));
			});
		}
	},
	treeMap = function(){
		function listMap(list, m){
			if(list&&list.length>0){
				var l = list.map(m);
				for(var i=0, len=list.length; i<len; i++){
					l[i].children = listMap(list[i].children, m);
				}
				return l;
			}
		}
		return function(list, m){
			return listMap(list, m);
		}
	}(),
	cjs = {
		layui: clayui,
		layer: clayer,
		treeMap: treeMap
	};
	exports('cjs', cjs);
});