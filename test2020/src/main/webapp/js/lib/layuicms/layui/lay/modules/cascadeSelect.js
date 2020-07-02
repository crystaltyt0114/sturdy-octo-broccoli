/***
 * 无限极联动选择组件封装模块
 */
layui.define(['jquery', 'form'], function(exports){
	var MOD_NAME = 'cascadeSelect';
	var $ = layui.jquery;
	var form = layui.form;
	function CS(config){
		//当前选中数据值名数据
		this.selected =[];
		//当前选中的名
		this.names = [];
		//初始化配置
		this.config = {
			//选择器id或class
			elem: '',
			//无限级分类数据
			data: [],
			//是否允许搜索，可设置为数组[true,true,true]
			search:false
		}
		//实例化配置
		this.config = $.extend(this.config, config);
		var cades = this.config.cades;
		for(var i=0, len=cades.length; i<len; i++) {
			cades[i]['index'] = i;
		}
		var $E = $(config.elem);
		var csval = $E.data('csval');
		if(csval){
			var values = csval.split(',');
			this.config.values = values;
		}
		this.init();
	}
	CS.prototype.initCade = function(cade, parentId) {
		var _this = this,
			config = _this.config;
		var url = config.url+'?parentId='+parentId;
		$.ajax({
			url:url,
			dataType:'json',
			success:function(d){
				var loadParse = config.loadParse;
				var name = cade.name;
				var id = cade.id;
				var filter = cade.filter;
				var cadeIndex = cade.index;
				var list = loadParse(d);
				var values = config.values;
				var val = (values && values[cadeIndex])?values[cadeIndex]:null;
				var hit = false;
				var html = '<div class="layui-input-inline"><select'+(name?' name="'+name+'"':'')+(id?' id="'+id+'"':'')+(filter?' lay-filter="'+filter+'"':'')+'>';
				for(var i=0, len=list.length; i<len; i++) {
					var item = list[i];
					var selected = (val&&val!= '*'&&item.id==val)?true:false;
					html += '<option value="'+item.id+'" '+(selected?'selected="selected"':'')+' >'+item.title+'</option>';
					if(selected){
						hit = true;
					}
				}
				html += '</select></div>';
				var $E = $(config.elem);
				$E.append(html);
				$('#'+id).data('cade', cade);
				form.render();
				form.on('select('+filter+')',function(data){
					_this.change(data.elem, data.value)
				});
				if (hit && cadeIndex < config.cades.length - 1) {
					var cades = config.cades;
					var nextCade = cades[cadeIndex+1];
					_this.initCade(nextCade, val);
				} else {
					config.values = null;
				}
			},
			error: function(){
				console.error(MOD_NAME+' hint：候选数据ajax请求错误 ');
			}
		});
	}
	CS.prototype.change = function(elem, value) {
		var _this = this,
			config = _this.config;
		var $thisItem = $(elem).parent();
		//移除后面的select
		$thisItem.nextAll('div.layui-input-inline').remove();
		var cade = $(elem).data('cade');
		var cadeIndex = cade.index;
		if (value && value != '*' && cadeIndex < config.cades.length - 1) {
			var cades = config.cades;
			var cade = cades[cadeIndex+1];
			_this.initCade(cade, value);
		}
	}
	CS.prototype.render = function(config) {
		var _this = this,
			config = _this.config,
			$E = $(config.elem);
		var cades = config.cades;
		var firstCade = cades[0];
		_this.initCade(firstCade, firstCade.parentId);
	}
	CS.prototype.init = function () {
		var _this = this,
			config = _this.config,
			$E = $(config.elem);
		if ($E.length == 0) {
			console.error(MOD_NAME + ' hint：找不到容器 ' + config.elem);
			return false;
		}
		//初始化
		_this.render();
	}
	exports(MOD_NAME, {render: function(config){
		return new CS(config);
	}});
});